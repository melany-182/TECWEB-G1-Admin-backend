package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

import java.math.BigDecimal;

public class MedallaDto {
    private Long idMedalla;
    private String nombre;
    private BigDecimal puntaje;
    private String imagen;
    private Long idDocumento;

    public MedallaDto() {} // importante: constructor por defecto / sin argumentos

    public MedallaDto(Long idMedalla, String nombre, BigDecimal puntaje, String imagen, Long idDocumento) {
        this.idMedalla = idMedalla;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.imagen = imagen;
        this.idDocumento = idDocumento;
    }

    public Long getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(Long idMedalla) {
        this.idMedalla = idMedalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(BigDecimal puntaje) {
        this.puntaje = puntaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public String toString() {
        return "MedallaDto{" +
                "idMedalla=" + idMedalla +
                ", nombre='" + nombre + '\'' +
                ", puntaje=" + puntaje +
                ", imagen='" + imagen + '\'' +
                ", idDocumento=" + idDocumento +
                '}';
    }
}
