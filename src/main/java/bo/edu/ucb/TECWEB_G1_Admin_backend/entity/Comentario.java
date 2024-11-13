package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comentario")
    private Long idComentario;

    @Column(name = "Texto", nullable = false, length = 500)
    private String texto;

    @Column(name = "FechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Foro", nullable = false)
    private Foro foro;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    public Comentario() {} // constructor por defecto, necesario siempre

    public Comentario(String texto, LocalDateTime fechaHora, Foro foro, Usuario usuario) {
        this.texto = texto;
        this.fechaHora = fechaHora;
        this.foro = foro;
        this.usuario = usuario;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "idComentario=" + idComentario +
                ", texto='" + texto + '\'' +
                ", fechaHora=" + fechaHora +
                ", isDeleted=" + isDeleted +
                ", foro=" + foro +
                ", usuario=" + usuario +
                '}';
    }
}
