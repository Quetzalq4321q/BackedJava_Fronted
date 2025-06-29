package Cancion;


public class Cancion {
    private int id;
    private String nombre;
    private String artista;
    private int year;
    private int duracionMs;

    public Cancion(int id, String nombre, String artista, int year, int duracionMs) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.year = year;
        this.duracionMs = duracionMs;
    }

    public Cancion(String nombre, String artista, int year, int duracionMs) {
        this.nombre = nombre;
        this.artista = artista;
        this.year = year;
        this.duracionMs = duracionMs;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getArtista() { return artista; }

    public void setArtista(String artista) { this.artista = artista; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public int getDuracionMs() { return duracionMs; }

    public void setDuracionMs(int duracionMs) { this.duracionMs = duracionMs; }
}

