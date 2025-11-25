package main.java.repository;
import main.java.db.Conexion;
import main.java.model.Prestamo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoRepository {
    private Connection connection;

    public PrestamoRepository() {
        this.connection = Conexion.getConnection();
    }

    // Agregar prestamo
    public void agregarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (libro_isbn, socio_id, fecha_prestamo, fecha_devolucion_prevista, estado) VALUES (?, ?, ?, ?, 'PRESTADO')";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, prestamo.getLibroIsbn());
            preparedStatement.setInt(2, prestamo.getSocioId());
            preparedStatement.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            preparedStatement.setDate(4, Date.valueOf(prestamo.getFechaDevolucionPrevista()));
            preparedStatement.executeUpdate();
            System.out.println("Prestamo agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar prestamo: " + e.getMessage());
        }
    }

    // Obtener prestamo por ID
    public Prestamo obtenerPrestamo(int id) {
        String sql = "SELECT * FROM prestamos WHERE id = ?";
        Prestamo prestamos = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                prestamos = new Prestamo(
                    resultSet.getInt("id"),
                    resultSet.getString("libro_isbn"),
                    resultSet.getInt("socio_id"),
                    resultSet.getDate("fecha_devolucion_prevista").toLocalDate(),
                    resultSet.getDate("fecha_devolucion_real") != null ? resultSet.getDate("fecha_devolucion_real").toLocalDate() : null,
                    resultSet.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener prestamo: " + e.getMessage());
        }
        return prestamos;
    }

    // Listar todos los prestamos
    public List<Prestamo> listarPrestamos() {
        String sql = "SELECT * FROM prestamos";
        List<Prestamo> prestamos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Prestamo prestamo = new Prestamo(
                    resultSet.getInt("id"),
                    resultSet.getString("libro_isbn"),
                    resultSet.getInt("socio_id"),
                    resultSet.getDate("fecha_devolucion_prevista").toLocalDate(),
                    resultSet.getDate("fecha_devolucion_real") != null ? resultSet.getDate("fecha_devolucion_real").toLocalDate() : null,
                    resultSet.getString("estado")
                );
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar prestamos: " + e.getMessage());
        }
        return prestamos;
    }

    // Registrar devolucion(Cambia estado)
    public void registrarDevolucion(int prestamoId, java.time.LocalDate fechaDevolucionReal) {
        String sql = "UPDATE prestamos SET fecha_devolucion_real = ?, estado = 'DEVUELTO' WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(fechaDevolucionReal));
            preparedStatement.setInt(2, prestamoId);
            preparedStatement.executeUpdate();
            System.out.println("Devolucion registrada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar devolucion: " + e.getMessage());
        }
    }

    // Listar prestamos activos
    public List<Prestamo> listarPrestamosActivos() {
        String sql = "SELECT * FROM prestamos WHERE estado = 'PRESTADO'";
        List<Prestamo> prestamos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                prestamos.add(new Prestamo(
                    resultSet.getInt("id"),
                    resultSet.getString("libro_isbn"),
                    resultSet.getInt("socio_id"),
                    resultSet.getDate("fecha_devolucion_prevista").toLocalDate(),
                    resultSet.getDate("fecha_devolucion_real") != null ? resultSet.getDate("fecha_devolucion_real").toLocalDate() : null,
                    resultSet.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar prestamos activos: " + e.getMessage());
        }
        return prestamos;
    }
}
