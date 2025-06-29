package Cancion;

public class CancionPremium extends Cancion {
    private boolean descarga;

    public CancionPremium(int id, String nombre, String artista, int year, int duracionMs, boolean descarga) {
        super(id, nombre, artista, year, duracionMs);
        this.descarga = descarga;
    }

    public CancionPremium(String nombre, String artista, int year, int duracionMs, boolean descarga) {
        super(nombre, artista, year, duracionMs);
        this.descarga = descarga;
    }

    public boolean isDescargable() {
        return descarga;
    }

    public void setDescarga(boolean descarga) {
        this.descarga = descarga;
    }
}
