package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class AccesoDto {
    private Long idAcceso;
    private Long idUsuario;

    public AccesoDto() {} // // importante: constructor por defecto / sin argumentos

    public AccesoDto(Long idAcceso, Long idUsuario) {
        this.idAcceso = idAcceso;
        this.idUsuario = idUsuario;
    }

    public Long getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Long idAcceso) {
        this.idAcceso = idAcceso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "AccesoDto{" +
                "idAcceso='" + idAcceso + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                '}';
    }
}
