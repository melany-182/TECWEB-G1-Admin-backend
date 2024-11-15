package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Foro")
public class Foro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Foro")
    private Long idForo;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Tema", nullable = false, length = 50)
    private String tema;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Tipo_Acceso", nullable = false)
    private TipoAcceso tipoAcceso;

    public Foro() {} // constructor por defecto, necesario siempre

    public Foro(String nombre, String tema, TipoAcceso tipoAcceso) {
        this.nombre = nombre;
        this.tema = tema;
        this.tipoAcceso = tipoAcceso;
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
        return "Foro{" +
                "idForo=" + idForo +
                ", nombre='" + nombre + '\'' +
                ", tema='" + tema + '\'' +
                ", isDeleted=" + isDeleted +
                ", tipoAcceso=" + tipoAcceso +
                '}';
    }
}
