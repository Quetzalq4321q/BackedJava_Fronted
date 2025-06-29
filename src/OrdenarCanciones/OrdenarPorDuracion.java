package OrdenarCanciones;

import Cancion.Cancion;
import java.util.*;


public class OrdenarPorDuracion extends OrdenadorCanciones {
    public List<Cancion> ordenar(List<Cancion> canciones) {
        canciones.sort(Comparator.comparingInt(Cancion::getDuracionMs));
        return canciones;
    }
}

