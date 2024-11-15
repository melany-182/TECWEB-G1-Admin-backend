package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Documento_Leido")
public class DocumentoLeido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Documento_Leido")
    private Long idDocumentoLeido;

    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Documento", nullable = false)
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "ID_Alumno", nullable = false)
    private Alumno alumno;

    public DocumentoLeido() {} // constructor por defecto, necesario siempre

    public DocumentoLeido(LocalDate fecha, Documento documento, Alumno alumno) {
        this.fecha = fecha;
        this.documento = documento;
        this.alumno = alumno;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "DocumentoLeido{" +
                "idDocumentoLeido=" + idDocumentoLeido +
                ", fecha=" + fecha +
                ", isDeleted=" + isDeleted +
                ", documento=" + documento +
                ", alumno=" + alumno +
                '}';
    }
}
