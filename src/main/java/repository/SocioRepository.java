package main.java.repository;

import main.java.db.Conexion;
import main.java.model.Socio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioRepository {

    private Connection connection;
    
    public SocioRepository() {
        this.connection = Conexion.getConnection();
    }

    // Agregar socio
    public void agregarSocio(Socio socio) {
        String sql = "INSERT INTO socios (nombre, apellido, dni, telefono) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido());
            preparedStatement.setString(3, socio.getDni());
            preparedStatement.setString(4, socio.getTelefono());
            preparedStatement.executeUpdate();
            System.out.println("Socio agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar socio: " + e.getMessage());
        }
    }

    // Buscar socio por DNI
    public Socio obtenerSocio(int id) {
        String sql = "SELECT * FROM socios WHERE id = ?";
        Socio socio = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                socio = new Socio(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("dni"),
                    resultSet.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener socio: " + e.getMessage());
        }
        return socio;
    }

    //Listar todos los socios
    public List<Socio> listarSocios() {
            List<Socio> socios = new ArrayList<>();
            String sql = "SELECT * FROM socios";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Socio socio = new Socio(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("dni"),
                    resultSet.getString("telefono")
                );
                socios.add(socio);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar socios: " + e.getMessage());
        }
        return socios;
    }

    // Actualizar socio
    public void actualizarSocio(Socio socio) {
        String sql = "UPDATE socios SET nombre = ?, apellido = ?, dni = ?, telefono = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido());
            preparedStatement.setString(3, socio.getDni());
            preparedStatement.setString(4, socio.getTelefono());
            preparedStatement.setInt(5, socio.getId());
            preparedStatement.executeUpdate();
            System.out.println("Socio actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar socio: " + e.getMessage());
        }
    }

    // Eliminar socio
    public void eliminarSocio(int id) {
    String sql = "DELETE FROM socios WHERE id = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int filas = preparedStatement.executeUpdate();

        if (filas > 0) {
            System.out.println("Socio eliminado correctamente.");
        } else {
            System.out.println("No existe ningún socio con ese ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error al eliminar socio: " + e.getMessage());
    }
}
}

