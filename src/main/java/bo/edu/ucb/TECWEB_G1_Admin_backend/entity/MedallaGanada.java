package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MedallaGanada")
public class MedallaGanada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MedallaGanada")
    private Long idMedallaGanada;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Alumno", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "ID_Medalla", nullable = false)
    private Medalla medalla;

    public MedallaGanada() {} // constructor por defecto, necesario siempre

    public MedallaGanada(LocalDate fecha, Alumno alumno, Medalla medalla) {
        this.fecha = fecha;
        this.alumno = alumno;
        this.medalla = medalla;
    }

    public Long getIdMedallaGanada() {
        return idMedallaGanada;
    }

    public void setIdMedallaGanada(Long idMedallaGanada) {
        this.idMedallaGanada = idMedallaGanada;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Medalla getMedalla() {
        return medalla;
    }

    public void setMedalla(Medalla medalla) {
        this.medalla = medalla;
    }

    @Override
    public String toString() {
        return "MedallaGanada{" +
                "idMedallaGanada=" + idMedallaGanada +
                ", fecha=" + fecha +
                ", isDeleted=" + isDeleted +
                ", alumno=" + alumno +
                ", medalla=" + medalla +
                '}';
    }
}
