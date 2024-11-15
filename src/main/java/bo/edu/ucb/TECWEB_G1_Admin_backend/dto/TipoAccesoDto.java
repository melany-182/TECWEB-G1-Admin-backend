package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class TipoAccesoDto {
    private Long idTipoAcceso;
    private String nombre;
    private String descripcion;

    public TipoAccesoDto() {} // // importante: constructor por defecto / sin argumentos

    public TipoAccesoDto(Long idTipoAcceso, String nombre, String descripcion) {
        this.idTipoAcceso = idTipoAcceso;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getIdTipoAcceso() {
        return idTipoAcceso;
    }

    public void setIdTipoAcceso(Long idTipoAcceso) {
        this.idTipoAcceso = idTipoAcceso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoAccesoDto{" +
                "idTipoAcceso=" + idTipoAcceso +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
