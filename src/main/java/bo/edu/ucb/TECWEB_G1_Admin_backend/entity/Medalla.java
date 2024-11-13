package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Medalla")
public class Medalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Medalla")
    private Long idMedalla;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Puntaje", nullable = false, precision = 7, scale = 1)
    private BigDecimal puntaje;

    @Column(name = "Imagen")
    private String imagen;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Documento", nullable = false)
    private Documento documento;

    public Medalla() {} // constructor por defecto, necesario siempre

    public Medalla(String nombre, BigDecimal puntaje, String imagen, Documento documento) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.imagen = imagen;
        this.documento = documento;
    }

    public Long getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(Long idMedalla) {
        this.idMedalla = idMedalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(BigDecimal puntaje) {
        this.puntaje = puntaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    @Override
    public String toString() {
        return "Medalla{" +
                "idMedalla=" + idMedalla +
                ", nombre='" + nombre + '\'' +
                ", puntaje=" + puntaje +
                ", imagen='" + imagen + '\'' +
                ", isDeleted=" + isDeleted +
                ", documento=" + documento +
                '}';
    }
}
