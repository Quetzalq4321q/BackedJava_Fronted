package OrdenarCanciones;

import java.util.List;
import Cancion.*;
import java.util.*;

public class OrdenadorCanciones {

    public static List<Cancion> porDuracion(List<Cancion> canciones) {
        canciones.sort(Comparator.comparingInt(Cancion::getDuracionMs));
        return canciones;
    }

    public static List<Cancion> porYear(List<Cancion> canciones) {
        canciones.sort(Comparator.comparingInt(Cancion::getYear));
        return canciones;
    }

}
