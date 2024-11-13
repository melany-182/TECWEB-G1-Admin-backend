package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class JefeCarreraDto {
    private Long idJefeCarrera;
    private Long idPersona;

    public JefeCarreraDto() {} // importante: constructor por defecto / sin argumentos

    public JefeCarreraDto(Long idJefeCarrera, Long idPersona) {
        this.idJefeCarrera = idJefeCarrera;
        this.idPersona = idPersona;
    }

    public Long getIdJefeCarrera() {
        return idJefeCarrera;
    }

    public void setIdJefeCarrera(Long idJefeCarrera) {
        this.idJefeCarrera = idJefeCarrera;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "JefeCarreraDto{" +
                "idJefeCarrera=" + idJefeCarrera +
                ", idPersona=" + idPersona +
                '}';
    }
}
