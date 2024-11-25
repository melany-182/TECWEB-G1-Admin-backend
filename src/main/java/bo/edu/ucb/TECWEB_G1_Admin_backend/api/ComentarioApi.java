package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.ComentarioBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.ComentarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioApi {

    private final ComentarioBl comentarioBl;

    @Autowired
    public ComentarioApi(ComentarioBl comentarioBl) {
        this.comentarioBl = comentarioBl;
    }

    /** Endpoint que retorna todos los comentarios de un foro.
     * @param foroId: El ID del foro a obtener los comentarios.
     */
    @GetMapping("/foro/{foroId}")
    public ResponseEntity<List<ComentarioDto>> getComentariosByForoId(@PathVariable Long foroId) {
        List<ComentarioDto> comentarios = comentarioBl.findAllByForoId(foroId);
        return ResponseEntity.ok(comentarios);
    }

    /** Endpoint que retorna el detalle de un comentario por ID.
     * @param idComentario: El ID del comentario a obtener.
     */
    @GetMapping("/{idComentario}")
    public ResponseEntity<ComentarioDto> getComentarioById(@PathVariable Long idComentario) {
        ComentarioDto comentario = comentarioBl.findById(idComentario);
        if (comentario != null) {
            return ResponseEntity.ok(comentario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un comentario.
     * @param comentarioDto: El comentario a crear.
     */
    @PostMapping
    public ResponseEntity<ComentarioDto> createComentario(@RequestBody ComentarioDto comentarioDto) {
        ComentarioDto newComentario = comentarioBl.createComentario(comentarioDto);
        if (newComentario == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newComentario);
    }

    /** Endpoint que permite actualizar un comentario por ID.
     * @param idComentario: El ID del comentario a actualizar.
     * @param comentarioDto: El comentario con los datos actualizados.
     */
    @PutMapping("/{idComentario}")
    public ResponseEntity<ComentarioDto> updateComentario(@PathVariable Long idComentario, @RequestBody ComentarioDto comentarioDto) {
        ComentarioDto updatedComentario = comentarioBl.updateComentario(idComentario, comentarioDto);
        if (updatedComentario != null) {
            return ResponseEntity.ok(updatedComentario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un comentario por ID. (borrado lógico)
     * @param idComentario: El ID del comentario a eliminar.
     */
    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long idComentario) {
        comentarioBl.deleteComentario(idComentario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
