package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.AlumnoBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.AlumnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/alumnos")
public class AlumnoApi {

    private final AlumnoBl alumnoBl;

    @Autowired
    public AlumnoApi(AlumnoBl alumnoBl) {
        this.alumnoBl = alumnoBl;
    }

    /** Endpoint que retorna todos los alumnos.
     */
    @GetMapping
    public ResponseEntity<List<AlumnoDto>> getAllAlumnos() {
        List<AlumnoDto> alumnos = alumnoBl.findAll();
        return ResponseEntity.ok(alumnos);
    }

    /** Endpoint que retorna el detalle de un alumno por ID.
     * @param id: El ID del alumno a obtener.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDto> getAlumnoById(@PathVariable Long id) {
        AlumnoDto alumno = alumnoBl.findById(id);
        if (alumno != null) {
            return ResponseEntity.ok(alumno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un alumno.
     * @param alumnoDto: El alumno a crear.
     */
    @PostMapping
    public ResponseEntity<AlumnoDto> createAlumno(@RequestBody AlumnoDto alumnoDto) {
        AlumnoDto newAlumno = alumnoBl.createAlumno(alumnoDto);
        if (newAlumno == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newAlumno);
    }

    /** Endpoint que permite actualizar un alumno por ID.
     * @param id: El ID del alumno a actualizar.
     * @param alumnoDto: El alumno con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDto> updateAlumno(@PathVariable Long id, @RequestBody AlumnoDto alumnoDto) {
        AlumnoDto updatedAlumno = alumnoBl.updateAlumno(id, alumnoDto);
        if (updatedAlumno != null) {
            return ResponseEntity.ok(updatedAlumno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un alumno por ID. (borrado lógico)
     * @param id: El ID del alumno a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        alumnoBl.deleteAlumno(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
