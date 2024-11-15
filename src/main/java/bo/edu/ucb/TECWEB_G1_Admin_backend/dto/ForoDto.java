package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class ForoDto {
    private Long idForo;
    private String nombre;
    private String tema;
    private Long idTipoAcceso;

    public ForoDto() {} // importante: constructor por defecto / sin argumentos

    public ForoDto(Long idForo, String nombre, String tema, Long idTipoAcceso) {
        this.idForo = idForo;
        this.nombre = nombre;
        this.tema = tema;
        this.idTipoAcceso = idTipoAcceso;
    }

    public Long getIdForo() {
        return idForo;
    }

    public void setIdForo(Long idForo) {
        this.idForo = idForo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Long getIdTipoAcceso() {
        return idTipoAcceso;
    }

    public void setIdTipoAcceso(Long idTipoAcceso) {
        this.idTipoAcceso = idTipoAcceso;
    }

    @Override
    public String toString() {
        return "ForoDto{" +
                "idForo=" + idForo +
                ", nombre='" + nombre + '\'' +
                ", tema='" + tema + '\'' +
                ", idTipoAcceso=" + idTipoAcceso +
                '}';
    }
}
