package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.JefeCarreraBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.JefeCarreraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jefes-carrera")
public class JefeCarreraApi {

    private final JefeCarreraBl jefeCarreraBl;

    @Autowired
    public JefeCarreraApi(JefeCarreraBl jefeCarreraBl) {
        this.jefeCarreraBl = jefeCarreraBl;
    }

    /** Endpoint que retorna todos los jefes de carrera.
     */
    @GetMapping
    public ResponseEntity<List<JefeCarreraDto>> getAllJefesCarrera() {
        List<JefeCarreraDto> jefes = jefeCarreraBl.findAll();
        return ResponseEntity.ok(jefes);
    }

    /** Endpoint que retorna el detalle de un jefe de carrera por ID.
     * @param id: El ID del jefe de carrera a obtener.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JefeCarreraDto> getJefeCarreraById(@PathVariable Long id) {
        JefeCarreraDto jefe = jefeCarreraBl.findById(id);
        if (jefe != null) {
            return ResponseEntity.ok(jefe);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un jefe de carrera.
     * @param jefeDto: El jefe de carrera a crear.
     */
    @PostMapping
    public ResponseEntity<JefeCarreraDto> createJefeCarrera(@RequestBody JefeCarreraDto jefeDto) {
        JefeCarreraDto newJefe = jefeCarreraBl.createJefeCarrera(jefeDto);
        if (newJefe == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newJefe);
    }

    /** Endpoint que permite actualizar un jefe de carrera por ID.
     * @param id: El ID del jefe de carrera a actualizar.
     * @param jefeDto: El jefe de carrera con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JefeCarreraDto> updateJefeCarrera(@PathVariable Long id, @RequestBody JefeCarreraDto jefeDto) {
        JefeCarreraDto updatedJefe = jefeCarreraBl.updateJefeCarrera(id, jefeDto);
        if (updatedJefe != null) {
            return ResponseEntity.ok(updatedJefe);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un jefe de carrera por ID.
     * @param id: El ID del jefe de carrera a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJefeCarrera(@PathVariable Long id) {
        jefeCarreraBl.deleteJefeCarrera(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
