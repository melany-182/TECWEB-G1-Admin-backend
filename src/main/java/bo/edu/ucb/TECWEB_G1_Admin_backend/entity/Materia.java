package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Materia")
    private Long idMateria;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Docente", nullable = false)
    private Docente docente;

    public Materia() {} // constructor por defecto, necesario siempre

    public Materia(String nombre, String descripcion, Docente docente) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.docente = docente;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "idMateria=" + idMateria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", isDeleted=" + isDeleted +
                ", docente=" + docente +
                '}';
    }
}
