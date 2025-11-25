package main.java.model;

import java.time.LocalDate;

public class Prestamo {
    private int id;
    private String libroIsbn;
    private int socioId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private String estado;

    public Prestamo() {}

    public Prestamo(int id, String libroIsbn, int socioId, LocalDate fechaDevolucionPrevista, LocalDate fechaDevolucionReal, String estado) {
        this.id = id;
        this.libroIsbn = libroIsbn;
        this.socioId = socioId;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
    }

    public Prestamo(String libroIsbn, int socioId, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista, LocalDate fechaDevolucionReal, String estado) {
        this.libroIsbn = libroIsbn;
        this.socioId = socioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.estado = "Prestado";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLibroIsbn() {
        return libroIsbn;
    }
    public void setLibroIsbn(String libroIsbn) {
        this.libroIsbn = libroIsbn;
    }

    public int getSocioId() {
        return socioId;
    }
    public void setSocioId(int socioId) {
        this.socioId = socioId;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }
    public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }
    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Prestamo: \n" +
                "id = " + id + "\n" +
                "isbnLibro = " + libroIsbn + "\n" +
                "id Usuario = " + socioId + "\n" +
                "fecha Prestamo = " + fechaPrestamo + "\n" +
                "fecha Devolucion = " + fechaDevolucionPrevista + "\n" +
                "fecha Devolucion Real = " + fechaDevolucionReal + "\n" +
                "estado = " + estado + "\n";    
    }
}
