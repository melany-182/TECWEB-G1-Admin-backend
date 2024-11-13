package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

import java.time.LocalDate;

public class MedallaGanadaDto {
    private Long idMedallaGanada;
    private LocalDate fecha;
    private Long idAlumno;
    private Long idMedalla;

    public MedallaGanadaDto() {} // importante: constructor por defecto / sin argumentos

    public MedallaGanadaDto(Long idMedallaGanada, LocalDate fecha, Long idAlumno, Long idMedalla) {
        this.idMedallaGanada = idMedallaGanada;
        this.fecha = fecha;
        this.idAlumno = idAlumno;
        this.idMedalla = idMedalla;
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

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Long getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(Long idMedalla) {
        this.idMedalla = idMedalla;
    }

    @Override
    public String toString() {
        return "MedallaGanadaDto{" +
                "idMedallaGanada=" + idMedallaGanada +
                ", fecha=" + fecha +
                ", idAlumno=" + idAlumno +
                ", idMedalla=" + idMedalla +
                '}';
    }
}
