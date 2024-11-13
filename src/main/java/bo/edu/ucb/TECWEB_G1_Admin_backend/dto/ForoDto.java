package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class ForoDto {
    private Long idForo;
    private String nombre;
    private String tema;
    private Long idAcceso;

    public ForoDto() {} // importante: constructor por defecto / sin argumentos

    public ForoDto(Long idForo, String nombre, String tema, Long idAcceso) {
        this.idForo = idForo;
        this.nombre = nombre;
        this.tema = tema;
        this.idAcceso = idAcceso;
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

    public Long getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Long idAcceso) {
        this.idAcceso = idAcceso;
    }

    @Override
    public String toString() {
        return "ForoDto{" +
                "idForo=" + idForo +
                ", nombre='" + nombre + '\'' +
                ", tema='" + tema + '\'' +
                ", idAcceso=" + idAcceso +
                '}';
    }
}
