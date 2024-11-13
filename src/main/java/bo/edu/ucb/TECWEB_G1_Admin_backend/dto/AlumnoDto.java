package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

import java.math.BigDecimal;

public class AlumnoDto {
    private Long idAlumno;
    private int semestre;
    private int materiasAprobadas;
    private BigDecimal puntaje;
    private Long idPersona;

    public AlumnoDto() {} // importante: constructor por defecto / sin argumentos

    public AlumnoDto(Long idAlumno, int semestre, int materiasAprobadas, BigDecimal puntaje, Long idPersona) {
        this.idAlumno = idAlumno;
        this.semestre = semestre;
        this.materiasAprobadas = materiasAprobadas;
        this.puntaje = puntaje;
        this.idPersona = idPersona;
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

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "AlumnoDto{" +
                "idAlumno='" + idAlumno + '\'' +
                ", semestre='" + semestre + '\'' +
                ", materiasAprobadas='" + materiasAprobadas + '\'' +
                ", puntaje='" + puntaje + '\'' +
                ", idPersona='" + idPersona + '\'' +
                '}';
    }
}
