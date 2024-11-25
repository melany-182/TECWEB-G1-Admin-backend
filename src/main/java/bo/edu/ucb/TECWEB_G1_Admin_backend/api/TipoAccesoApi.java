package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.TipoAccesoBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.TipoAccesoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-acceso")
public class TipoAccesoApi {

    private final TipoAccesoBl tipoAccesoBl;

    @Autowired
    public TipoAccesoApi(TipoAccesoBl tipoAccesoBl) {
        this.tipoAccesoBl = tipoAccesoBl;
    }

    /** Endpoint que retorna todos los tipos de acceso.
     */
    @GetMapping
    public ResponseEntity<List<TipoAccesoDto>> getAllTiposAcceso() {
        List<TipoAccesoDto> tiposAcceso = tipoAccesoBl.findAll();
        return ResponseEntity.ok(tiposAcceso);
    }

    /** Endpoint que retorna el detalle de un tipo de acceso por ID.
     * @param idTipoAcceso: El ID del tipo de acceso a obtener.
     */
    @GetMapping("/{idTipoAcceso}")
    public ResponseEntity<TipoAccesoDto> getTipoAccesoById(@PathVariable Long idTipoAcceso) {
        TipoAccesoDto tipoAcceso = tipoAccesoBl.findById(idTipoAcceso);
        if (tipoAcceso != null) {
            return ResponseEntity.ok(tipoAcceso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un tipo de acceso.
     * @param tipoAccesoDto: El tipo de acceso a crear.
     */
    @PostMapping
    public ResponseEntity<TipoAccesoDto> createTipoAcceso(@RequestBody TipoAccesoDto tipoAccesoDto) {
        TipoAccesoDto newTipoAcceso = tipoAccesoBl.createTipoAcceso(tipoAccesoDto);
        if (newTipoAcceso == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newTipoAcceso);
    }

    /** Endpoint que permite actualizar un tipo de acceso por ID.
     * @param idTipoAcceso: El ID del tipo de acceso a actualizar.
     * @param tipoAccesoDto: El tipo de acceso con los datos actualizados.
     */
    @PutMapping("/{idTipoAcceso}")
    public ResponseEntity<TipoAccesoDto> updateTipoAcceso(@PathVariable Long idTipoAcceso, @RequestBody TipoAccesoDto tipoAccesoDto) {
        TipoAccesoDto updatedTipoAcceso = tipoAccesoBl.updateTipoAcceso(idTipoAcceso, tipoAccesoDto);
        if (updatedTipoAcceso != null) {
            return ResponseEntity.ok(updatedTipoAcceso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un tipo de acceso por ID. (borrado lógico)
     * @param idTipoAcceso: El ID del tipo de acceso a eliminar.
     */
    @DeleteMapping("/{idTipoAcceso}")
    public ResponseEntity<Void> deleteTipoAcceso(@PathVariable Long idTipoAcceso) {
        tipoAccesoBl.deleteTipoAcceso(idTipoAcceso);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
