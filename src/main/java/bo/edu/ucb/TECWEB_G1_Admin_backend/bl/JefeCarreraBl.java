package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.AlumnoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.JefeCarreraDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.PersonaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.JefeCarreraDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Alumno;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.JefeCarrera;
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
public class JefeCarreraBl {

    private static final Logger LOG = LoggerFactory.getLogger(JefeCarreraBl.class);
    private final JefeCarreraDao jefeCarreraDao;
    private final PersonaDao personaDao;
    private final AlumnoDao alumnoDao;

    @Autowired
    public JefeCarreraBl(JefeCarreraDao jefeCarreraDao, PersonaDao personaDao, AlumnoDao alumnoDao) {
        this.jefeCarreraDao = jefeCarreraDao;
        this.personaDao = personaDao;
        this.alumnoDao = alumnoDao;
    }

    // obtener todos los jefes de carrera que no están eliminados
    public List<JefeCarreraDto> findAll() {
        try {
            List<JefeCarrera> jefes = jefeCarreraDao.findByIsDeletedFalse();
            List<JefeCarreraDto> jefeDtos = new ArrayList<>();
            for (JefeCarrera j : jefes) {
                jefeDtos.add(new JefeCarreraDto(j.getIdJefeCarrera(), j.getPersona().getIdPersona()));
            }
            LOG.info("Jefes de carrera obtenidos correctamente");
            return jefeDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los jefes de carrera: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un jefe de carrera por ID
    public JefeCarreraDto findById(Long id) {
        try {
            Optional<JefeCarrera> jefeOpt = jefeCarreraDao.findById(id);
            if (jefeOpt.isPresent() && !jefeOpt.get().getIsDeleted()) {
                JefeCarrera jefe = jefeOpt.get();
                LOG.info("Jefe de carrera obtenido correctamente");
                return new JefeCarreraDto(jefe.getIdJefeCarrera(), jefe.getPersona().getIdPersona());
            }
            LOG.error("Jefe de carrera con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el jefe de carrera por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo jefe de carrera
    public JefeCarreraDto createJefeCarrera(JefeCarreraDto jefeDto) {
        try {
            Optional<Alumno> alumnoExistente = alumnoDao.findByPersonaIdPersonaAndIsDeletedFalse(jefeDto.getIdPersona());
            if (alumnoExistente.isPresent()) {
                LOG.error("La persona con ID {} ya es un alumno", jefeDto.getIdPersona());
                return null;
            }

            Optional<JefeCarrera> jefeExistente = jefeCarreraDao.findByPersonaIdPersonaAndIsDeletedFalse(jefeDto.getIdPersona());
            if (jefeExistente.isPresent()) {
                LOG.error("Jefe de carrera con ID de persona {} ya existe", jefeDto.getIdPersona());
                return null;
            }

            Optional<Persona> personaOpt = personaDao.findById(jefeDto.getIdPersona());
            if (personaOpt.isEmpty()) {
                LOG.error("Persona con ID {} no encontrada", jefeDto.getIdPersona());
                return null;
            }

            JefeCarrera jefe = new JefeCarrera();
            jefe.setPersona(personaOpt.get());

            JefeCarrera savedJefe = jefeCarreraDao.save(jefe);
            LOG.info("Jefe de carrera creado correctamente");
            return new JefeCarreraDto(savedJefe.getIdJefeCarrera(), savedJefe.getPersona().getIdPersona());
        } catch (Exception ex) {
            LOG.error("Error al crear el jefe de carrera: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un jefe de carrera existente por ID
    public JefeCarreraDto updateJefeCarrera(Long id, JefeCarreraDto jefeDto) {
        try {
            Optional<JefeCarrera> jefeOpt = jefeCarreraDao.findById(id);
            if (jefeOpt.isPresent() && !jefeOpt.get().getIsDeleted()) {
                JefeCarrera jefe = jefeOpt.get();
                jefe.setPersona(personaDao.getOne(jefeDto.getIdPersona())); // excepción con respecto a los demás usuarios, lo único que se puede modificar es la persona

                JefeCarrera updatedJefe = jefeCarreraDao.save(jefe);
                LOG.info("Jefe de carrera actualizado correctamente");
                return new JefeCarreraDto(updatedJefe.getIdJefeCarrera(), updatedJefe.getPersona().getIdPersona());
            }
            LOG.error("Jefe de carrera con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el jefe de carrera: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un jefe de carrera por ID (borrado lógico)
    public void deleteJefeCarrera(Long id) {
        try {
            Optional<JefeCarrera> jefeOpt = jefeCarreraDao.findById(id);
            if (jefeOpt.isPresent() && !jefeOpt.get().getIsDeleted()) {
                JefeCarrera jefe = jefeOpt.get();
                jefe.setIsDeleted(true);
                jefeCarreraDao.save(jefe);
                LOG.info("Jefe de carrera eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el jefe de carrera: {}", ex.getMessage());
        }
    }
}
