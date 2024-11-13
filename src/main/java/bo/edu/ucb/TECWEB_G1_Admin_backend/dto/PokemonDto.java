package bo.edu.ucb.TECWEB_G1_Admin_backend.dto;

public class PokemonDto {
    private Long idAlumno;
    private String nombrePokemon;
    private int nivel;

    public PokemonDto() {} // importante: constructor por defecto / sin argumentos

    public PokemonDto(Long idAlumno, String nombrePokemon, int nivel) {
        this.idAlumno = idAlumno;
        this.nombrePokemon = nombrePokemon;
        this.nivel = nivel;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public void setNombrePokemon(String nombrePokemon) {
        this.nombrePokemon = nombrePokemon;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "PokemonDto{" +
                "idAlumno=" + idAlumno +
                ", nombrePokemon='" + nombrePokemon + '\'' +
                ", nivel=" + nivel +
                '}';
    }
}
