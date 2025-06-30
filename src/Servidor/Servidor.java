package Servidor;

import static spark.Spark.*;
import OrdenarCanciones.*;
import com.google.gson.Gson;
import Cancion.*;
import CancionDAO.*;
import BuscadorCanciones.*;

import java.util.List;

public class Servidor {
    static Gson gson = new Gson();
    static CancionDAO dao = new CancionDAO();

    public static void main(String[] args) {
        port(4567);

        // 🛡️ Preflight CORS
        options("/*", (request, response) -> {
            String headers = request.headers("Access-Control-Request-Headers");
            if (headers != null) {
                response.header("Access-Control-Allow-Headers", headers);
            }

            String methods = request.headers("Access-Control-Request-Method");
            if (methods != null) {
                response.header("Access-Control-Allow-Methods", methods);
            }

            return "OK";
        });

        // ✅ Permitir CORS para todas las rutas
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
            res.header("Access-Control-Allow-Headers", "*");
        });

        // 🔄 Ruta de prueba
        get("/", (req, res) -> {
            res.type("application/json");
            return gson.toJson("🎵 Servidor activo");
        });

        // ✅ Obtener canciones paginadas
        get("/canciones/paginado", (req, res) -> {
            res.type("application/json");

            int offset = 0;
            int limit = 100;

            if (req.queryParams("offset") != null) {
                try {
                    offset = Integer.parseInt(req.queryParams("offset"));
                } catch (NumberFormatException ignored) {}
            }

            if (req.queryParams("limit") != null) {
                try {
                    limit = Integer.parseInt(req.queryParams("limit"));
                } catch (NumberFormatException ignored) {}
            }

            List<Cancion> canciones = dao.obtenerCancionesPaginado(offset, limit);
            return gson.toJson(canciones);
        });

        // 🎶 Agregar nueva canción
        post("/agregar", (req, res) -> {
            res.type("application/json");

            try {
                String nombre = req.queryParams("nombre");
                String artista = req.queryParams("artista");
                int year = Integer.parseInt(req.queryParams("year"));
                int duracionMs = Integer.parseInt(req.queryParams("duracion_ms"));

                if (nombre == null || artista == null || nombre.isEmpty() || artista.isEmpty()) {
                    res.status(400);
                    return gson.toJson("❌ Parámetros inválidos.");
                }

                Cancion nueva = new Cancion(nombre, artista, year, duracionMs);
                boolean exito = dao.agregarCancion(nueva);

                return gson.toJson(exito ? "✔ Canción agregada" : "✖ Error al agregar");
            } catch (Exception e) {
                res.status(400);
                return gson.toJson("❌ Error al procesar datos");
            }
        });

        // 🔍 Buscar por nombre
        get("/buscar/nombre", (req, res) -> {
            res.type("application/json");
            String nombre = req.queryParams("nombre");
            return gson.toJson(BuscadorCanciones.buscarPorNombre(dao.obtenerCanciones(), nombre));
        });

        // 🔍 Buscar por artista
        get("/buscar/artista", (req, res) -> {
            res.type("application/json");
            String artista = req.queryParams("artista");
            return gson.toJson(BuscadorCanciones.buscarPorArtista(dao.obtenerCanciones(), artista));
        });

        // 🔀 Ordenar por duración
        get("/ordenar/duracion", (req, res) -> {
            res.type("application/json");
            return gson.toJson(OrdenadorCanciones.porDuracion(dao.obtenerCanciones()));
        });

        // 🔀 Ordenar por año
        get("/ordenar/year", (req, res) -> {
            res.type("application/json");
            return gson.toJson(OrdenadorCanciones.porYear(dao.obtenerCanciones()));
        });
    }
}


