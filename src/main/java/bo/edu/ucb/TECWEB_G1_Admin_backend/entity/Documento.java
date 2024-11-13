package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Documento")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Documento")
    private Long idDocumento;

    @Column(name = "Titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "Direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "Autores", nullable = false, length = 500)
    private String autores;

    @Column(name = "Descripcion", nullable = false, length = 5000)
    private String descripcion;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    public Documento() {} // constructor por defecto, necesario siempre

    public Documento(String titulo, String direccion, String autores, String descripcion) {
        this.titulo = titulo;
        this.direccion = direccion;
        this.autores = autores;
        this.descripcion = descripcion;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
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
        return "Documento{" +
                "idDocumento=" + idDocumento +
                ", titulo='" + titulo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", autores='" + autores + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
