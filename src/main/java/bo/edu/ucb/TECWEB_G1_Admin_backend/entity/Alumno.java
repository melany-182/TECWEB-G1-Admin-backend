package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Alumno")
    private Long idAlumno;

    @Column(name = "Semestre", nullable = false)
    private int semestre;

    @Column(name = "MateriasAprobadas", nullable = false)
    private int materiasAprobadas;

    @Column(name = "Puntaje", nullable = false, precision = 7, scale = 1)
    private BigDecimal puntaje;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Persona", nullable = false)
    private Persona persona;

    public Alumno() {} // constructor por defecto, necesario siempre

    public Alumno(int semestre, int materiasAprobadas, BigDecimal puntaje, Persona persona) {
        this.semestre = semestre;
        this.materiasAprobadas = materiasAprobadas;
        this.puntaje = puntaje;
        this.persona = persona;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(int materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    public BigDecimal getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(BigDecimal puntaje) {
        this.puntaje = puntaje;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", semestre=" + semestre +
                ", materiasAprobadas=" + materiasAprobadas +
                ", puntaje=" + puntaje +
                ", isDeleted=" + isDeleted +
                ", persona=" + persona +
                '}';
    }
}
