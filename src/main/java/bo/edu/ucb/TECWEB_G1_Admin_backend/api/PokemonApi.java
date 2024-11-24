package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.PokemonBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemones")
public class PokemonApi {

    private final PokemonBl pokemonBl;

    @Autowired
    public PokemonApi(PokemonBl pokemonBl) {
        this.pokemonBl = pokemonBl;
    }

    /** Endpoint que retorna todos los Pokémon de los alumnos.
     */
    @GetMapping
    public ResponseEntity<List<PokemonDto>> getAllPokemons() {
        List<PokemonDto> pokemons = pokemonBl.findAll();
        return ResponseEntity.ok(pokemons);
    }

    /** Endpoint que retorna el detalle de un Pokémon por ID de alumno.
     * @param idAlumno: El ID de alumno del Pokémon a obtener.
     */
    @GetMapping("/{idAlumno}") // FIXME: utilizar token de autenticación para que solo el dueño del Pokémon pueda verlo
    public ResponseEntity<PokemonDto> getPokemonByIdAlumno(@PathVariable Long idAlumno) {
        PokemonDto pokemon = pokemonBl.findByIdAlumno(idAlumno);
        if (pokemon != null) {
            return ResponseEntity.ok(pokemon);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un Pokémon.
     * @param pokemonDto: El Pokémon a crear.
     */
    @PostMapping
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        PokemonDto newPokemon = pokemonBl.createPokemon(pokemonDto);
        if (newPokemon == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newPokemon);
    }

    /** Endpoint que permite actualizar un Pokémon por ID de alumno.
     * @param idAlumno: El ID de alumno del Pokémon a actualizar.
     * @param pokemonDto: El Pokémon con los datos actualizados.
     */
    @PutMapping("/{idAlumno}")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable Long idAlumno, @RequestBody PokemonDto pokemonDto) {
        PokemonDto updatedPokemon = pokemonBl.updatePokemon(idAlumno, pokemonDto);
        if (updatedPokemon != null) {
            return ResponseEntity.ok(updatedPokemon);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un Pokémon por ID de alumno. (borrado lógico)
     * @param idAlumno: El ID de alumno del Pokémon a eliminar.
     */
    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long idAlumno) {
        pokemonBl.deletePokemon(idAlumno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
