package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class UsuarioDto {
    private Long idUsuario;
    private String nombreGoogle;
    private String correoGoogle;
    private Long idTipoAcceso;

    public UsuarioDto() {} // importante: constructor por defecto / sin argumentos

    public UsuarioDto(Long idUsuario, String nombreGoogle, String correoGoogle, Long idTipoAcceso) {
        this.idUsuario = idUsuario;
        this.nombreGoogle = nombreGoogle;
        this.correoGoogle = correoGoogle;
        this.idTipoAcceso = idTipoAcceso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreGoogle() {
        return nombreGoogle;
    }

    public void setNombreGoogle(String nombreGoogle) {
        this.nombreGoogle = nombreGoogle;
    }

    public String getCorreoGoogle() {
        return correoGoogle;
    }

    public void setCorreoGoogle(String correoGoogle) {
        this.correoGoogle = correoGoogle;
    }

    public Long getIdTipoAcceso() {
        return idTipoAcceso;
    }

    public void setIdTipoAcceso(Long idTipoAcceso) {
        this.idTipoAcceso = idTipoAcceso;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "idUsuario=" + idUsuario +
                ", nombreGoogle='" + nombreGoogle + '\'' +
                ", correoGoogle='" + correoGoogle + '\'' +
                ", idTipoAcceso=" + idTipoAcceso +
                '}';
    }
}
