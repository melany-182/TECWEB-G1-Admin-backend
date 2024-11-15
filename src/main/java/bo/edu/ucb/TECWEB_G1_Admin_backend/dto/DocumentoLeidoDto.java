package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

import java.time.LocalDate;

public class DocumentoLeidoDto {
    private Long idDocumentoLeido;
    private LocalDate fecha;
    private Long idDocumento;
    private Long idAlumno;

    public DocumentoLeidoDto() {} // importante: constructor por defecto / sin argumentos

    public DocumentoLeidoDto(Long idDocumentoLeido, LocalDate fecha, Long idDocumento, Long idAlumno) {
        this.idDocumentoLeido = idDocumentoLeido;
        this.fecha = fecha;
        this.idDocumento = idDocumento;
        this.idAlumno = idAlumno;
    }

    public Long getIdDocumentoLeido() {
        return idDocumentoLeido;
    }

    public void setIdDocumentoLeido(Long idDocumentoLeido) {
        this.idDocumentoLeido = idDocumentoLeido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public String toString() {
        return "DocumentoLeidoDto{" +
                "idDocumentoLeido=" + idDocumentoLeido +
                ", fecha=" + fecha +
                ", idDocumento=" + idDocumento +
                ", idAlumno=" + idAlumno +
                '}';
    }
}
