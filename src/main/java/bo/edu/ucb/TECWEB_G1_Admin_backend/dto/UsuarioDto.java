package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class UsuarioDto {
    private Long idUsuario;
    private String nombreGoogle;
    private String correoGoogle;

    public UsuarioDto() {} // importante: constructor por defecto / sin argumentos

    public UsuarioDto(Long idUsuario, String nombreGoogle, String correoGoogle) {
        this.idUsuario = idUsuario;
        this.nombreGoogle = nombreGoogle;
        this.correoGoogle = correoGoogle;
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

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "idUsuario=" + idUsuario +
                ", nombreGoogle='" + nombreGoogle + '\'' +
                ", correoGoogle='" + correoGoogle + '\'' +
                '}';
    }
}
