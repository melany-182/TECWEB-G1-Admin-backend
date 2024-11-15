package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Noticia")
    private Long idNoticia;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Descripcion", nullable = false, length = 5000)
    private String descripcion;

    @Column(name = "Imagen")
    private String imagen;

    @Column(name = "Fecha_Inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "Fecha_Fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Jefe_Carrera", nullable = false)
    private JefeCarrera jefeCarrera;

    public Noticia() {} // constructor por defecto, necesario siempre

    public Noticia(String nombre, String descripcion, String imagen, LocalDate fechaInicio, LocalDate fechaFin, JefeCarrera jefeCarrera) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefeCarrera = jefeCarrera;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public JefeCarrera getJefeCarrera() {
        return jefeCarrera;
    }

    public void setJefeCarrera(JefeCarrera jefeCarrera) {
        this.jefeCarrera = jefeCarrera;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "idNoticia=" + idNoticia +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", isDeleted=" + isDeleted +
                ", jefeCarrera=" + jefeCarrera +
                '}';
    }
}
