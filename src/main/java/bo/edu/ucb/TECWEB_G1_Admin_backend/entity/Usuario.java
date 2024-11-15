package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @Column(name = "Nombre_Google", nullable = false, length = 255)
    private String nombreGoogle;

    @Column(name = "Correo_Google", nullable = false, length = 255)
    private String correoGoogle;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Tipo_Acceso", nullable = false)
    private TipoAcceso tipoAcceso;

    public Usuario() {} // constructor por defecto, necesario siempre

    public Usuario(String nombreGoogle, String correoGoogle, TipoAcceso tipoAcceso) {
        this.nombreGoogle = nombreGoogle;
        this.correoGoogle = correoGoogle;
        this.tipoAcceso = tipoAcceso;
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

    public TipoAcceso getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(TipoAcceso tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreGoogle='" + nombreGoogle + '\'' +
                ", correoGoogle='" + correoGoogle + '\'' +
                ", isDeleted=" + isDeleted +
                ", tipoAcceso=" + tipoAcceso +
                '}';
    }
}
