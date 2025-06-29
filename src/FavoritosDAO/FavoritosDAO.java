package FavoritosDAO;

import Conexion.java.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FavoritosDAO {

    // Marca una canción como favorita
    public boolean marcarFavorito(int idCancion, String nombre, String artista) {
        String sql = "INSERT INTO canciones_favoritas (id_cancion, nombre, artista) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCancion);
            ps.setString(2, nombre);
            ps.setString(3, artista);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al marcar favorito: " + e.getMessage());
            return false;
        }
    }
}
