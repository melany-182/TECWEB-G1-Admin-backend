package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.ForoBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.ForoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/foros")
public class ForoApi {

    private final ForoBl foroBl;

    @Autowired
    public ForoApi(ForoBl foroBl) {
        this.foroBl = foroBl;
    }

    /** Endpoint que retorna todos los foros.
     */
    @GetMapping
    public ResponseEntity<List<ForoDto>> getAllForos() {
        List<ForoDto> foros = foroBl.findAll();
        return ResponseEntity.ok(foros);
    }

    /** Endpoint que retorna el detalle de un foro por ID.
     * @param idForo: El ID del foro a obtener.
     */
    @GetMapping("/{idForo}")
    public ResponseEntity<ForoDto> getForoById(@PathVariable Long idForo) {
        ForoDto foro = foroBl.findById(idForo);
        if (foro != null) {
            return ResponseEntity.ok(foro);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un foro.
     * @param foroDto: El foro a crear.
     */
    @PostMapping
    public ResponseEntity<ForoDto> createForo(@RequestBody ForoDto foroDto) {
        ForoDto newForo = foroBl.createForo(foroDto);
        if (newForo == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newForo);
    }

    /** Endpoint que permite actualizar un foro por ID.
     * @param idForo: El ID del foro a actualizar.
     * @param foroDto: El foro con los datos actualizados.
     */
    @PutMapping("/{idForo}")
    public ResponseEntity<ForoDto> updateForo(@PathVariable Long idForo, @RequestBody ForoDto foroDto) {
        ForoDto updatedForo = foroBl.updateForo(idForo, foroDto);
        if (updatedForo != null) {
            return ResponseEntity.ok(updatedForo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un foro por ID. (borrado lógico)
     * @param idForo: El ID del foro a eliminar.
     */
    @DeleteMapping("/{idForo}")
    public ResponseEntity<Void> deleteForo(@PathVariable Long idForo) {
        foroBl.deleteForo(idForo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
