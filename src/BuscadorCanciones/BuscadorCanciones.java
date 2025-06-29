package BuscadorCanciones;

import Cancion.*;
import java.util.ArrayList;
import java.util.List;

public class BuscadorCanciones {

    public static List<Cancion> buscarPorNombre(List<Cancion> lista, String nombre) {
        List<Cancion> resultado = new ArrayList<>();
        for (Cancion c : lista) {
            if (c.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public static List<Cancion> buscarPorArtista(List<Cancion> lista, String artista) {
        List<Cancion> resultado = new ArrayList<>();
        for (Cancion c : lista) {
            if (c.getArtista().toLowerCase().contains(artista.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

