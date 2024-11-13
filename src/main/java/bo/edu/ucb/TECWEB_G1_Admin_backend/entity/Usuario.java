package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @Column(name = "NombreGoogle", nullable = false, length = 255)
    private String nombreGoogle;

    @Column(name = "CorreoGoogle", nullable = false, length = 255)
    private String correoGoogle;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    public Usuario() {} // constructor por defecto, necesario siempre

    public Usuario(String nombreGoogle, String correoGoogle) {
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreGoogle='" + nombreGoogle + '\'' +
                ", correoGoogle='" + correoGoogle + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
