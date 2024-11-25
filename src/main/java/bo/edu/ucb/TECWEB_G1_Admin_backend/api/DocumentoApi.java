package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.DocumentoBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.DocumentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos")
public class DocumentoApi {

    private final DocumentoBl documentoBl;

    @Autowired
    public DocumentoApi(DocumentoBl documentoBl) {
        this.documentoBl = documentoBl;
    }

    /** Endpoint que retorna todos los documentos.
     */
    @GetMapping
    public ResponseEntity<List<DocumentoDto>> getAllDocumentos() {
        List<DocumentoDto> documentos = documentoBl.findAll();
        return ResponseEntity.ok(documentos);
    }

    /** Endpoint que retorna el detalle de un documento por ID.
     * @param idDocumento: El ID del documento a obtener.
     */
    @GetMapping("/{idDocumento}")
    public ResponseEntity<DocumentoDto> getDocumentoById(@PathVariable Long idDocumento) {
        DocumentoDto documento = documentoBl.findById(idDocumento);
        if (documento != null) {
            return ResponseEntity.ok(documento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un documento.
     * @param documentoDto: El documento a crear.
     */
    @PostMapping
    public ResponseEntity<DocumentoDto> createDocumento(@RequestBody DocumentoDto documentoDto) {
        DocumentoDto newDocumento = documentoBl.createDocumento(documentoDto);
        if (newDocumento == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newDocumento);
    }

    /** Endpoint que permite actualizar un documento por ID.
     * @param idDocumento: El ID del documento a actualizar.
     * @param documentoDto: El documento con los datos actualizados.
     */
    @PutMapping("/{idDocumento}")
    public ResponseEntity<DocumentoDto> updateDocumento(@PathVariable Long idDocumento, @RequestBody DocumentoDto documentoDto) {
        DocumentoDto updatedDocumento = documentoBl.updateDocumento(idDocumento, documentoDto);
        if (updatedDocumento != null) {
            return ResponseEntity.ok(updatedDocumento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un documento por ID. (borrado lógico)
     * @param idDocumento: El ID del documento a eliminar.
     */
    @DeleteMapping("/{idDocumento}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long idDocumento) {
        documentoBl.deleteDocumento(idDocumento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
