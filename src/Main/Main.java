package Main;

import Cancion.*;
import CancionDAO.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CancionDAO dao = new CancionDAO();

        System.out.println("🎵 Canciones actuales en la base de datos:");

        int offset = 0;
        int limit = 100;
        List<Cancion> canciones;

        do {
            canciones = dao.obtenerCancionesPaginado(offset, limit);
            for (Cancion c : canciones) {
                System.out.println("🎶 " + c.getNombre() + " - " + c.getArtista());
            }
            offset += limit;
        } while (!canciones.isEmpty());

        // Agregar canción de prueba
        Cancion nueva = new Cancion("Bohemian Rhapsody", "Queen", 1975, 354000);
        boolean exito = dao.agregarCancion(nueva);
        System.out.println(exito ? "✔ Canción de prueba agregada." : "❌ Falló el agregado de canción.");
    }
}
//1159707
//1159708