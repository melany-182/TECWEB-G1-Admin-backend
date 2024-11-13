package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class DocenteDto {
    private Long idDocente;
    private String aboutMe;
    private String gradoEstudio;
    private Long idPersona;

    public DocenteDto() {} // importante: constructor por defecto / sin argumentos

    public DocenteDto(Long idDocente, String aboutMe, String gradoEstudio, Long idPersona) {
        this.idDocente = idDocente;
        this.aboutMe = aboutMe;
        this.gradoEstudio = gradoEstudio;
        this.idPersona = idPersona;
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

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "DocenteDto{" +
                "idDocente=" + idDocente +
                ", aboutMe='" + aboutMe + '\'' +
                ", gradoEstudio='" + gradoEstudio + '\'' +
                ", idPersona=" + idPersona +
                '}';
    }
}
