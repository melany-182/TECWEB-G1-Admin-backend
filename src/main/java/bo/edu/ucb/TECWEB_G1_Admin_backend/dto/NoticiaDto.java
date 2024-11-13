package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

import java.time.LocalDate;

public class NoticiaDto {
    private Long idNoticia;
    private String nombre;
    private String descripcion;
    private String imagen;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long idJefeCarrera;

    public NoticiaDto() {} // importante: constructor por defecto / sin argumentos

    public NoticiaDto(Long idNoticia, String nombre, String descripcion, String imagen, LocalDate fechaInicio, LocalDate fechaFin, Long idJefeCarrera) {
        this.idNoticia = idNoticia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idJefeCarrera = idJefeCarrera;
    }

    public Long getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(Long idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getIdJefeCarrera() {
        return idJefeCarrera;
    }

    public void setIdJefeCarrera(Long idJefeCarrera) {
        this.idJefeCarrera = idJefeCarrera;
    }

    @Override
    public String toString() {
        return "NoticiaDto{" +
                "idNoticia=" + idNoticia +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", idJefeCarrera=" + idJefeCarrera +
                '}';
    }
}
