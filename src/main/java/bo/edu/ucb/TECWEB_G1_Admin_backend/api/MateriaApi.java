package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.MateriaBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.MateriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materias")
public class MateriaApi {

    private final MateriaBl materiaBl;

    @Autowired
    public MateriaApi(MateriaBl materiaBl) {
        this.materiaBl = materiaBl;
    }

    /** Endpoint que retorna todas las materias.
     */
    @GetMapping
    public ResponseEntity<List<MateriaDto>> getAllMaterias() {
        List<MateriaDto> materias = materiaBl.findAll();
        return ResponseEntity.ok(materias);
    }

    /** Endpoint que retorna el detalle de una materia por ID.
     * @param id: El ID de la materia a obtener.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> getMateriaById(@PathVariable Long id) {
        MateriaDto materia = materiaBl.findById(id);
        if (materia != null) {
            return ResponseEntity.ok(materia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear una materia.
     * @param materiaDto: La materia a crear.
     */
    @PostMapping
    public ResponseEntity<MateriaDto> createMateria(@RequestBody MateriaDto materiaDto) {
        MateriaDto newMateria = materiaBl.createMateria(materiaDto);
        if (newMateria == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newMateria);
    }

    /** Endpoint que permite actualizar una materia por ID.
     * @param id: El ID de la materia a actualizar.
     * @param materiaDto: La materia con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MateriaDto> updateMateria(@PathVariable Long id, @RequestBody MateriaDto materiaDto) {
        MateriaDto updatedMateria = materiaBl.updateMateria(id, materiaDto);
        if (updatedMateria != null) {
            return ResponseEntity.ok(updatedMateria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar una materia por ID. (borrado lógico)
     * @param id: El ID de la materia a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        materiaBl.deleteMateria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
