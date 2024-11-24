package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.DocenteBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.DocenteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/docentes")
public class DocenteApi {

    private final DocenteBl docenteBl;

    @Autowired
    public DocenteApi(DocenteBl docenteBl) {
        this.docenteBl = docenteBl;
    }

    /** Endpoint que retorna todos los docentes.
     */
    @GetMapping
    public ResponseEntity<List<DocenteDto>> getAllDocentes() {
        List<DocenteDto> docentes = docenteBl.findAll();
        return ResponseEntity.ok(docentes);
    }

    /** Endpoint que retorna el detalle de un docente por ID.
     * @param id: El ID del docente a obtener.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocenteDto> getDocenteById(@PathVariable Long id) {
        DocenteDto docente = docenteBl.findById(id);
        if (docente != null) {
            return ResponseEntity.ok(docente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un docente.
     * @param docenteDto: El docente a crear.
     */
    @PostMapping
    public ResponseEntity<DocenteDto> createDocente(@RequestBody DocenteDto docenteDto) {
        DocenteDto newDocente = docenteBl.createDocente(docenteDto);
        if (newDocente == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newDocente);
    }

    /** Endpoint que permite actualizar un docente por ID.
     * @param id: El ID del docente a actualizar.
     * @param docenteDto: El docente con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DocenteDto> updateDocente(@PathVariable Long id, @RequestBody DocenteDto docenteDto) {
        DocenteDto updatedDocente = docenteBl.updateDocente(id, docenteDto);
        if (updatedDocente != null) {
            return ResponseEntity.ok(updatedDocente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un docente por ID. (borrado lógico)
     * @param id: El ID del docente a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
        docenteBl.deleteDocente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
