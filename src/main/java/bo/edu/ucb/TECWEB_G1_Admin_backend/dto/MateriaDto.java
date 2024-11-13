package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class MateriaDto {
    private Long idMateria;
    private String nombre;
    private String descripcion;
    private Long idDocente;

    public MateriaDto() {} // importante: constructor por defecto / sin argumentos

    public MateriaDto(Long idMateria, String nombre, String descripcion, Long idDocente) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDocente = idDocente;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
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

    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    @Override
    public String toString() {
        return "MateriaDto{" +
                "idMateria=" + idMateria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idDocente=" + idDocente +
                '}';
    }
}
