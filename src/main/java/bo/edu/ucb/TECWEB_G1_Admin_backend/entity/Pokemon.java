package bo.edu.ucb.TECWEB_G1_Admin_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    @Column(name = "ID_Alumno")
    private Long idAlumno;

    @Column(name = "NombrePokemon", nullable = false, length = 50)
    private String nombrePokemon;

    @Column(name = "Nivel", nullable = false)
    private int nivel;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_Alumno", nullable = false)
    private Alumno alumno;

    public Pokemon() {} // constructor por defecto, necesario siempre

    public Pokemon(String nombrePokemon, int nivel, Alumno alumno) {
        this.nombrePokemon = nombrePokemon;
        this.nivel = nivel;
        this.alumno = alumno;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "idAlumno=" + idAlumno +
                ", nombrePokemon='" + nombrePokemon + '\'' +
                ", nivel=" + nivel +
                ", isDeleted=" + isDeleted +
                ", alumno=" + alumno +
                '}';
    }
}
