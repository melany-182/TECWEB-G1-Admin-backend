package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class PersonaDto {
    private Long idPersona;
    private String ci;
    private String nombres;
    private String apPaterno;
    private String apMaterno;
    private Long idUsuario;

    public PersonaDto() {} // importante: constructor por defecto / sin argumentos

    public PersonaDto(Long idPersona, String ci, String nombres, String apPaterno, String apMaterno, Long idUsuario) {
        this.idPersona = idPersona;
        this.ci = ci;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.idUsuario = idUsuario;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "PersonaDto{" +
                "idPersona=" + idPersona +
                ", ci='" + ci + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
