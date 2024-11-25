package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.PokemonDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.AlumnoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.PokemonDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Pokemon;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonBl {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonBl.class);
    private final PokemonDao pokemonDao;
    private final AlumnoDao alumnoDao;

    @Autowired
    public PokemonBl(PokemonDao pokemonDao, AlumnoDao alumnoDao) {
        this.pokemonDao = pokemonDao;
        this.alumnoDao = alumnoDao;
    }

    // obtener todos los Pokémon de los alumnos
    public List<PokemonDto> findAll() {
        try {
            List<Pokemon> pokemons = pokemonDao.findByIsDeletedFalse();
            List<PokemonDto> pokemonDtos = new ArrayList<>();
            for (Pokemon p : pokemons) {
                pokemonDtos.add(new PokemonDto(p.getIdAlumno(), p.getNombrePokemon(), p.getNivel()));
            }
            LOG.info("Pokémon obtenidos correctamente");
            return pokemonDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los Pokémon: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un Pokémon por ID de alumno
    public PokemonDto findByIdAlumno(Long idAlumno) {
        try {
            Optional<Pokemon> pokemonOpt = pokemonDao.findByAlumnoIdAlumnoAndIsDeletedFalse(idAlumno);
            if (pokemonOpt.isPresent()) {
                Pokemon pokemon = pokemonOpt.get();
                LOG.info("Pokémon obtenido correctamente");
                return new PokemonDto(pokemon.getIdAlumno(), pokemon.getNombrePokemon(), pokemon.getNivel());
            }
            LOG.error("Pokémon con ID de alumno {} no encontrado", idAlumno);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el Pokémon por ID de alumno: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo Pokémon para un alumno
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        try {
            Optional<Pokemon> pokemonOpt = pokemonDao.findByAlumnoIdAlumnoAndIsDeletedFalse(pokemonDto.getIdAlumno());
            if (pokemonOpt.isPresent()) {
                LOG.error("El Pokémon ya existe para el alumno con ID {}", pokemonDto.getIdAlumno());
                return null;
            }

            Optional<Alumno> alumnoOpt = alumnoDao.findById(pokemonDto.getIdAlumno());
            if (alumnoOpt.isEmpty()) {
                LOG.error("No se encontró el alumno con ID {}", pokemonDto.getIdAlumno());
                return null;
            }

            Pokemon pokemon = new Pokemon();
            pokemon.setIdAlumno(pokemonDto.getIdAlumno());
            pokemon.setNombrePokemon(pokemonDto.getNombrePokemon());
            pokemon.setNivel(pokemonDto.getNivel());

            Pokemon savedPokemon = pokemonDao.save(pokemon);
            LOG.info("Pokémon creado correctamente");
            return new PokemonDto(savedPokemon.getIdAlumno(), savedPokemon.getNombrePokemon(), savedPokemon.getNivel());
        } catch (Exception ex) {
            LOG.error("Error al crear el Pokémon: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un Pokémon existente por ID de alumno
    public PokemonDto updatePokemon(Long idAlumno, PokemonDto pokemonDto) {
        try {
            Optional<Pokemon> pokemonOpt = pokemonDao.findByAlumnoIdAlumnoAndIsDeletedFalse(idAlumno);
            if (pokemonOpt.isPresent()) {
                Pokemon pokemon = pokemonOpt.get();
                pokemon.setNombrePokemon(pokemonDto.getNombrePokemon());
                pokemon.setNivel(pokemonDto.getNivel());

                Pokemon updatedPokemon = pokemonDao.save(pokemon);
                LOG.info("Pokémon actualizado correctamente");
                return new PokemonDto(updatedPokemon.getIdAlumno(), updatedPokemon.getNombrePokemon(), updatedPokemon.getNivel());
            }
            LOG.error("Pokémon con ID de alumno {} no encontrado", idAlumno);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el Pokémon: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un Pokémon por ID de alumno (borrado lógico)
    public void deletePokemon(Long idAlumno) {
        try {
            Optional<Pokemon> pokemonOpt = pokemonDao.findByAlumnoIdAlumnoAndIsDeletedFalse(idAlumno);
            if (pokemonOpt.isPresent()) {
                Pokemon pokemon = pokemonOpt.get();
                pokemon.setIsDeleted(true);
                pokemonDao.save(pokemon);
                LOG.info("Pokémon eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el Pokémon: {}", ex.getMessage());
        }
    }
}
