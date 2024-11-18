package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.AlumnoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.PersonaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.AlumnoDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Alumno;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoBl {

    private static final Logger LOG = LoggerFactory.getLogger(AlumnoBl.class);
    private final AlumnoDao alumnoDao;
    private final PersonaDao personaDao;

    @Autowired
    public AlumnoBl(AlumnoDao alumnoDao, PersonaDao personaDao) {
        this.alumnoDao = alumnoDao;
        this.personaDao = personaDao;
    }

    // obtener todos los alumnos que no están eliminados
    public List<AlumnoDto> findAll() {
        try {
            List<Alumno> alumnos = alumnoDao.findByIsDeletedFalse();
            List<AlumnoDto> alumnoDtos = new ArrayList<>();
            for (Alumno a : alumnos) {
                alumnoDtos.add(new AlumnoDto(a.getIdAlumno(), a.getSemestre(), a.getMateriasAprobadas(), a.getPuntaje(), a.getPersona().getIdPersona()));
            }
            LOG.info("Alumnos obtenidos correctamente");
            return alumnoDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los alumnos: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un alumno por ID
    public AlumnoDto findById(Long id) {
        try {
            Optional<Alumno> alumnoOpt = alumnoDao.findById(id);
            if (alumnoOpt.isPresent() && !alumnoOpt.get().getIsDeleted()) {
                Alumno alumno = alumnoOpt.get();
                LOG.info("Alumno obtenido correctamente");
                return new AlumnoDto(alumno.getIdAlumno(), alumno.getSemestre(), alumno.getMateriasAprobadas(), alumno.getPuntaje(), alumno.getPersona().getIdPersona());
            }
            LOG.error("Alumno con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el alumno por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo alumno
    public AlumnoDto createAlumno(AlumnoDto alumnoDto) {
        try {
            Optional<Alumno> alumnoExistente = alumnoDao.findByPersonaIdPersonaAndIsDeletedFalse(alumnoDto.getIdPersona());
            if (alumnoExistente.isPresent()) {
                LOG.error("El alumno con ID de persona {} ya existe", alumnoDto.getIdPersona());
                return null;
            }

            Optional<Persona> personaOpt = personaDao.findById(alumnoDto.getIdPersona());
            if (personaOpt.isEmpty()) {
                LOG.error("No se encontró la persona con ID {}", alumnoDto.getIdPersona());
                return null;
            }
            
            Alumno alumno = new Alumno();
            alumno.setSemestre(alumnoDto.getSemestre());
            alumno.setMateriasAprobadas(alumnoDto.getMateriasAprobadas());
            alumno.setPuntaje(alumnoDto.getPuntaje());
            alumno.setPersona(personaOpt.get());

            Alumno savedAlumno = alumnoDao.save(alumno);
            LOG.info("Alumno creado correctamente");
            return new AlumnoDto(savedAlumno.getIdAlumno(), savedAlumno.getSemestre(), savedAlumno.getMateriasAprobadas(), savedAlumno.getPuntaje(), savedAlumno.getPersona().getIdPersona());
        } catch (Exception ex) {
            LOG.error("Error al crear el alumno: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un alumno existente por ID
    public AlumnoDto updateAlumno(Long id, AlumnoDto alumnoDto) {
        try {
            Optional<Alumno> alumnoOpt = alumnoDao.findById(id);
            if (alumnoOpt.isPresent() && !alumnoOpt.get().getIsDeleted()) {
                Alumno alumno = alumnoOpt.get();
                alumno.setSemestre(alumnoDto.getSemestre());
                alumno.setMateriasAprobadas(alumnoDto.getMateriasAprobadas());
                alumno.setPuntaje(alumnoDto.getPuntaje());

                Alumno updatedAlumno = alumnoDao.save(alumno);
                LOG.info("Alumno actualizado correctamente");
                return new AlumnoDto(updatedAlumno.getIdAlumno(), updatedAlumno.getSemestre(), updatedAlumno.getMateriasAprobadas(), updatedAlumno.getPuntaje(), updatedAlumno.getPersona().getIdPersona());
            }
            LOG.error("Alumno con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el alumno: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un alumno por ID (borrado lógico)
    public void deleteAlumno(Long id) {
        try {
            Optional<Alumno> alumnoOpt = alumnoDao.findById(id);
            if (alumnoOpt.isPresent() && !alumnoOpt.get().getIsDeleted()) {
                Alumno alumno = alumnoOpt.get();
                alumno.setIsDeleted(true);
                alumnoDao.save(alumno);
                LOG.info("Alumno eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el alumno: {}", ex.getMessage());
        }
    }
}
