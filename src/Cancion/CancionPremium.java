package Cancion;

public class CancionPremium extends Cancion {
    private boolean altaCalidad;

    public CancionPremium(int id, String nombre, String artista, String popularidad, int year, int duracionMs, boolean altaCalidad) {
        super(id, nombre, artista, popularidad, year, duracionMs);
        this.altaCalidad = altaCalidad;
    }

    @Override
    public void reproducir() {
        int segundos = duracionMs / 1000;
        System.out.println("Reproduciendo en alta calidad: " + nombre + " de " + artista + " (" + segundos + " segundos)");
    }

    public boolean isAltaCalidad() {
        return altaCalidad;
    }
}