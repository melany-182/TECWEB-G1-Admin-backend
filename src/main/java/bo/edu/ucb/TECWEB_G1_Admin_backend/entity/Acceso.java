package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Acceso")
public class Acceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Acceso")
    private Long idAcceso;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    public Acceso() {} // constructor por defecto, necesario siempre

    public Acceso(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Long idAcceso) {
        this.idAcceso = idAcceso;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Acceso{" +
                "idAcceso=" + idAcceso +
                ", isDeleted=" + isDeleted +
                ", usuario=" + usuario +
                '}';
    }
}
