package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.MedallaBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.MedallaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medallas")
public class MedallaApi {

    private final MedallaBl medallaBl;

    @Autowired
    public MedallaApi(MedallaBl medallaBl) {
        this.medallaBl = medallaBl;
    }

    /** Endpoint que retorna todas las medallas.
     */
    @GetMapping
    public ResponseEntity<List<MedallaDto>> getAllMedallas() {
        List<MedallaDto> medallas = medallaBl.findAll();
        return ResponseEntity.ok(medallas);
    }

    /** Endpoint que retorna el detalle de una medalla por ID.
     * @param idMedalla: El ID de la medalla a obtener.
     */
    @GetMapping("/{idMedalla}")
    public ResponseEntity<MedallaDto> getMedallaById(@PathVariable Long idMedalla) {
        MedallaDto medalla = medallaBl.findById(idMedalla);
        if (medalla != null) {
            return ResponseEntity.ok(medalla);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que retorna todas las medallas de un documento en específico.
     * @param documentoId: El ID del documento a obtener las medallas.
     */
    @GetMapping("/documento/{documentoId}")
    public ResponseEntity<List<MedallaDto>> getMedallasByDocumentoId(@PathVariable Long documentoId) {
        List<MedallaDto> medallas = medallaBl.findAllByDocumentoId(documentoId);
        return ResponseEntity.ok(medallas);
    }

    /** Endpoint que permite crear una medalla.
     * @param medallaDto: La medalla a crear.
     */
    @PostMapping
    public ResponseEntity<MedallaDto> createMedalla(@RequestBody MedallaDto medallaDto) {
        MedallaDto newMedalla = medallaBl.createMedalla(medallaDto);
        if (newMedalla == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newMedalla);
    }

    /** Endpoint que permite actualizar una medalla por ID.
     * @param idMedalla: El ID de la medalla a actualizar.
     * @param medallaDto: La medalla con los datos actualizados.
     */
    @PutMapping("/{idMedalla}")
    public ResponseEntity<MedallaDto> updateMedalla(@PathVariable Long idMedalla, @RequestBody MedallaDto medallaDto) {
        MedallaDto updatedMedalla = medallaBl.updateMedalla(idMedalla, medallaDto);
        if (updatedMedalla != null) {
            return ResponseEntity.ok(updatedMedalla);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar una medalla por ID. (borrado lógico)
     * @param idMedalla: El ID de la medalla a eliminar.
     */
    @DeleteMapping("/{idMedalla}")
    public ResponseEntity<Void> deleteMedalla(@PathVariable Long idMedalla) {
        medallaBl.deleteMedalla(idMedalla);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
