package main.java.model;

public class Libro {
    private String isbn;
    private String titulo;
    private int autorId;
    private int añoPublicacion;
    private int cantidadTotal;
    private int cantidadDisponible;

    public Libro(String isbn, String titulo, int autorId, int añoPublicacion, int cantidadTotal, int cantidadDisponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autorId = autorId;
        this.añoPublicacion = añoPublicacion;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Libro() {}

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAutorId() {
        return autorId;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }
    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }


    @Override
    public String toString() {
        return "ISBN: " + isbn + "\n" +
        ", Titulo: " + titulo + "\n" +
        ", Autor ID: " + autorId + "\n" +
        ", Año Publicacion: " + añoPublicacion + "\n" +
        ", Cantidad Total: " + cantidadTotal + "\n" +
        ", Cantidad Disponible: " + cantidadDisponible;
    }
}