package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Persona")
    private Long idPersona;

    @Column(name = "CI", nullable = false, length = 20)
    private String ci;

    @Column(name = "Nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "Ap_Paterno", nullable = false, length = 100)
    private String apPaterno;

    @Column(name = "Ap_Materno", nullable = false, length = 100)
    private String apMaterno;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    public Persona() {} // constructor por defecto, necesario siempre

    public Persona(String ci, String nombres, String apPaterno, String apMaterno, Usuario usuario) {
        this.ci = ci;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.usuario = usuario;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
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
        return "Persona{" +
                "idPersona=" + idPersona +
                ", ci='" + ci + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                ", isDeleted=" + isDeleted +
                ", usuario=" + usuario +
                '}';
    }
}
