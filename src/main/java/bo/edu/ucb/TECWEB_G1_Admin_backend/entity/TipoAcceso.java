package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tipo_Acceso")
public class TipoAcceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tipo_Acceso")
    private Long idTipoAcceso;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    public TipoAcceso() {} // constructor por defecto, necesario siempre

    public TipoAcceso(String nombre, String descripcion) {
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TipoAcceso{" +
                "idTipoAcceso='" + idTipoAcceso + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
