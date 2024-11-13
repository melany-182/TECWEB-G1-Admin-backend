package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class DocumentoDto {
    private Long idDocumento;
    private String titulo;
    private String direccion;
    private String autores;
    private String descripcion;

    public DocumentoDto() {} // importante: constructor por defecto / sin argumentos

    public DocumentoDto(Long idDocumento, String titulo, String direccion, String autores, String descripcion) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.direccion = direccion;
        this.autores = autores;
        this.descripcion = descripcion;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "DocumentoDto{" +
                "idDocumento=" + idDocumento +
                ", titulo='" + titulo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", autores='" + autores + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
