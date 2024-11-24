package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.NoticiaBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.NoticiaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/noticias")
public class NoticiaApi {

    private final NoticiaBl noticiaBl;

    @Autowired
    public NoticiaApi(NoticiaBl noticiaBl) {
        this.noticiaBl = noticiaBl;
    }

    /** Endpoint que retorna todas las noticias.
     */
    @GetMapping
    public ResponseEntity<List<NoticiaDto>> getAllNoticias() {
        List<NoticiaDto> noticias = noticiaBl.findAll();
        return ResponseEntity.ok(noticias);
    }

    /** Endpoint que retorna el detalle de una noticia por ID.
     * @param id: El ID de la noticia a obtener.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDto> getNoticiaById(@PathVariable Long id) {
        NoticiaDto noticia = noticiaBl.findById(id);
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear una noticia.
     * @param noticiaDto: La noticia a crear.
     */
    @PostMapping
    public ResponseEntity<NoticiaDto> createNoticia(@RequestBody NoticiaDto noticiaDto) {
        NoticiaDto newNoticia = noticiaBl.createNoticia(noticiaDto);
        if (newNoticia == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newNoticia);
    }

    /** Endpoint que permite actualizar una noticia por ID.
     * @param id: El ID de la noticia a actualizar.
     * @param noticiaDto: La noticia con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NoticiaDto> updateNoticia(@PathVariable Long id, @RequestBody NoticiaDto noticiaDto) {
        NoticiaDto updatedNoticia = noticiaBl.updateNoticia(id, noticiaDto);
        if (updatedNoticia != null) {
            return ResponseEntity.ok(updatedNoticia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar una noticia por ID. (borrado lógico)
     * @param id: El ID de la noticia a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Long id) {
        noticiaBl.deleteNoticia(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
