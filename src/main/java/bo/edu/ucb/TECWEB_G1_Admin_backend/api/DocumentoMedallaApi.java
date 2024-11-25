package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.DocumentoMedallaBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.DocumentoLeidoDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.MedallaGanadaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos-leidos")
public class DocumentoMedallaApi {
    private final DocumentoMedallaBl documentoMedallaBl;

    @Autowired
    public DocumentoMedallaApi(DocumentoMedallaBl documentoMedallaBl) {
        this.documentoMedallaBl = documentoMedallaBl;
    }

    /** Endpoint que permite registrar un documento leído y otorgar una medalla.
     * @param documentoLeidoDto: El documento leído a registrar.
     */
    @PostMapping
    public ResponseEntity<DocumentoLeidoDto> createDocumentoLeido(@RequestBody DocumentoLeidoDto documentoLeidoDto) {
        DocumentoLeidoDto newDocumentoLeido = documentoMedallaBl.createDocumentoLeido(documentoLeidoDto);
        if (newDocumentoLeido == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newDocumentoLeido);
    }

    /** Endpoint que retorna todos los documentos leídos por un alumno.
     * @param idAlumno: El ID del alumno a obtener los documentos leídos.
     */
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<DocumentoLeidoDto>> getAllDocumentosLeidosByAlumno(@PathVariable Long idAlumno) {
        List<DocumentoLeidoDto> documentosLeidos = documentoMedallaBl.findAllDocumentosLeidosByAlumno(idAlumno);
        return ResponseEntity.ok(documentosLeidos);
    }

    /** Endpoint que retorna todas las medallas ganadas por un alumno.
     * @param idAlumno: El ID del alumno a obtener las medallas ganadas.
     */
    @GetMapping("/medallas/alumno/{idAlumno}")
    public ResponseEntity<List<MedallaGanadaDto>> getAllMedallasGanadasByAlumno(@PathVariable Long idAlumno) {
        List<MedallaGanadaDto> medallasGanadas = documentoMedallaBl.findAllMedallasGanadasByAlumno(idAlumno);
        return ResponseEntity.ok(medallasGanadas);
    }
}
