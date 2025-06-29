package CancionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Cancion.*;
import Conexion.java.*;

public class CancionDAO {

    // Obtener canciones por bloques (paginado)
    public List<Cancion> obtenerCancionesPaginado(int offset, int limit) {
        List<Cancion> canciones = new ArrayList<>();
        String sql = "SELECT id, nombre, artista, year, duration_ms FROM canciones ORDER BY id LIMIT ? OFFSET ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cancion c = new Cancion(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("artista"),
                        rs.getInt("year"),
                        rs.getInt("duration_ms")
                );
                canciones.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canciones;
    }

    // Agregar nueva canción
    public boolean agregarCancion(Cancion cancion) {
        String sql = "INSERT INTO canciones (nombre, artista, year, duration_ms) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cancion.getNombre());
            stmt.setString(2, cancion.getArtista());
            stmt.setInt(3, cancion.getYear());
            stmt.setInt(4, cancion.getDuracionMs());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cancion.setId(rs.getInt(1));
                    System.out.println("✔ Canción insertada con ID: " + cancion.getId());
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Obtener todas las canciones sin paginar (por si necesitas otras funciones)
    public List<Cancion> obtenerCanciones() {
        List<Cancion> canciones = new ArrayList<>();
        String sql = "SELECT id, nombre, artista, year, duration_ms FROM canciones ORDER BY id";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cancion c = new Cancion(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("artista"),
                        rs.getInt("year"),
                        rs.getInt("duration_ms")
                );
                canciones.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canciones;
    }
}

