package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

import java.time.LocalDateTime;

public class ComentarioDto {
    private Long idComentario;
    private String texto;
    private LocalDateTime fechaHora;
    private Long idForo;
    private Long idUsuario;

    public ComentarioDto() {} // importante: constructor por defecto / sin argumentos

    public ComentarioDto(Long idComentario, String texto, LocalDateTime fechaHora, Long idForo, Long idUsuario) {
        this.idComentario = idComentario;
        this.texto = texto;
        this.fechaHora = fechaHora;
        this.idForo = idForo;
        this.idUsuario = idUsuario;
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

    public Long getIdForo() {
        return idForo;
    }

    public void setIdForo(Long idForo) {
        this.idForo = idForo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "ComentarioDto{" +
                "idComentario=" + idComentario +
                ", texto='" + texto + '\'' +
                ", fechaHora=" + fechaHora +
                ", idForo=" + idForo +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
