package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.PersonaBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/personas")
public class PersonaApi {

    private final PersonaBl personaBl;

    @Autowired
    public PersonaApi(PersonaBl personaBl) {
        this.personaBl = personaBl;
    }

    /** Endpoint que retorna todas las personas.
     */
    @GetMapping
    public ResponseEntity<List<PersonaDto>> getAllPersonas() {
        List<PersonaDto> personas = personaBl.findAll();
        return ResponseEntity.ok(personas);
    }

    /** Endpoint que retorna el detalle de una persona por ID.
     * @param id: El ID de la persona a obtener.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable Long id) {
        PersonaDto persona = personaBl.findById(id);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear una persona.
     * @param personaDto: La persona a crear.
     */
    @PostMapping
    public ResponseEntity<PersonaDto> createPersona(@RequestBody PersonaDto personaDto) {
        PersonaDto newPersona = personaBl.createPersona(personaDto);
        if (newPersona == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersona);
    }

    /** Endpoint que permite actualizar una persona por ID.
     * @param id: El ID de la persona a actualizar.
     * @param personaDto: La persona con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable Long id, @RequestBody PersonaDto personaDto) {
        PersonaDto updatedPersona = personaBl.updatePersona(id, personaDto);
        if (updatedPersona != null) {
            return ResponseEntity.ok(updatedPersona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar una persona por ID. (borrado lógico)
     * @param id: El ID de la persona a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaBl.deletePersona(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
