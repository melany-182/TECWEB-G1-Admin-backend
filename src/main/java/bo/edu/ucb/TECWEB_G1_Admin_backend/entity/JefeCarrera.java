package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Jefe_Carrera")
public class JefeCarrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Jefe_Carrera")
    private Long idJefeCarrera;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Persona", nullable = false)
    private Persona persona;

    public JefeCarrera() {} // constructor por defecto, necesario siempre

    public JefeCarrera(Persona persona) {
        this.persona = persona;
    }

    public Long getIdJefeCarrera() {
        return idJefeCarrera;
    }

    public void setIdJefeCarrera(Long idJefeCarrera) {
        this.idJefeCarrera = idJefeCarrera;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "JefeCarrera{" +
                "idJefeCarrera=" + idJefeCarrera +
                ", isDeleted=" + isDeleted +
                ", persona=" + persona +
                '}';
    }
}
