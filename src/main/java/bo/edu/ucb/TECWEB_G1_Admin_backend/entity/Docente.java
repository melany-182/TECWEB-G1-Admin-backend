package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Docente")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Docente")
    private Long idDocente;

    @Column(name = "About_Me", nullable = false, length = 500)
    private String aboutMe;

    @Column(name = "Grado_Estudio", nullable = false, length = 50)
    private String gradoEstudio;

    @Column(name = "Is_Deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "ID_Persona", nullable = false)
    private Persona persona;

    public Docente() {} // constructor por defecto, necesario siempre

    public Docente(String aboutMe, String gradoEstudio, Persona persona) {
        this.aboutMe = aboutMe;
        this.gradoEstudio = gradoEstudio;
        this.persona = persona;
    }

    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getGradoEstudio() {
        return gradoEstudio;
    }

    public void setGradoEstudio(String gradoEstudio) {
        this.gradoEstudio = gradoEstudio;
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
        return "Docente{" +
                "idDocente=" + idDocente +
                ", aboutMe='" + aboutMe + '\'' +
                ", gradoEstudio='" + gradoEstudio + '\'' +
                ", isDeleted=" + isDeleted +
                ", persona=" + persona +
                '}';
    }
}
