package OrdenarCanciones;

import java.util.*;
import Cancion.*;

public class OrdenarPorId extends OrdenadorCanciones {
    public List<Cancion> ordenar(List<Cancion> canciones) {
        canciones.sort(Comparator.comparingInt(Cancion::getId)); // ✅ usando getter
        return canciones;
    }
}
