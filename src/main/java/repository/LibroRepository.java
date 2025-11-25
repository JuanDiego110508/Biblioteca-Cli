package main.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.java.db.Conexion;
import main.java.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepository {
    private Connection connection;

    public LibroRepository() {
        connection = Conexion.getConnection();
    }

    // Agregar libro
    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (isbn, titulo, autor_id, año_publicacion, cantidad_total, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getTitulo());
            preparedStatement.setInt(3, libro.getAutorId());
            preparedStatement.setInt(4, libro.getAñoPublicacion());
            preparedStatement.setInt(5, libro.getCantidadTotal());
            preparedStatement.setInt(6, libro.getCantidadDisponible());
            preparedStatement.executeUpdate();
            System.out.println("Libro agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }

    // Buscar libro por ISBN
    public Libro obtenerLibro(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        Libro libro = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                libro = new Libro(
                    resultSet.getString("isbn"),
                    resultSet.getString("titulo"),
                    resultSet.getInt("autor_id"),
                    resultSet.getInt("año_publicacion"),
                    resultSet.getInt("cantidad_total"),
                    resultSet.getInt("cantidad_disponible")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    // Listar libros
    public List<Libro> listarLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Libro libro = new Libro(
                    resultSet.getString("isbn"),
                    resultSet.getString("titulo"),
                    resultSet.getInt("autor_id"),
                    resultSet.getInt("año_publicacion"),
                    resultSet.getInt("cantidad_total"),
                    resultSet.getInt("cantidad_disponible")
                );
                lista.add(libro);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar cantidad disponible
    public void actualizarCantidad(String isbn, int nuevaCantidad) {
        String sql = "UPDATE libros SET cantidad_disponible = ? WHERE isbn = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaCantidad);
            preparedStatement.setString(2, isbn);
            preparedStatement.executeUpdate();
            System.out.println("Cantidad actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
