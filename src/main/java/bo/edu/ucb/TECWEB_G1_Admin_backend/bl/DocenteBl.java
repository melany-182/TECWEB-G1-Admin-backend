package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.AlumnoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.DocenteDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.PersonaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.DocenteDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Alumno;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Docente;
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
public class DocenteBl {

    private static final Logger LOG = LoggerFactory.getLogger(DocenteBl.class);
    private final DocenteDao docenteDao;
    private final PersonaDao personaDao;
    private final AlumnoDao alumnoDao;

    @Autowired
    public DocenteBl(DocenteDao docenteDao, PersonaDao personaDao, AlumnoDao alumnoDao) {
        this.docenteDao = docenteDao;
        this.personaDao = personaDao;
        this.alumnoDao = alumnoDao;
    }

    // obtener todos los docentes que no están eliminados
    public List<DocenteDto> findAll() {
        try {
            List<Docente> docentes = docenteDao.findByIsDeletedFalse();
            List<DocenteDto> docenteDtos = new ArrayList<>();
            for (Docente d : docentes) {
                docenteDtos.add(new DocenteDto(d.getIdDocente(), d.getAboutMe(), d.getGradoEstudio(), d.getPersona().getIdPersona()));
            }
            LOG.info("Docentes obtenidos correctamente");
            return docenteDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los docentes: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un docente por ID
    public DocenteDto findById(Long id) {
        try {
            Optional<Docente> docenteOpt = docenteDao.findById(id);
            if (docenteOpt.isPresent() && !docenteOpt.get().getIsDeleted()) {
                Docente docente = docenteOpt.get();
                LOG.info("Docente obtenido correctamente");
                return new DocenteDto(docente.getIdDocente(), docente.getAboutMe(), docente.getGradoEstudio(), docente.getPersona().getIdPersona());
            }
            LOG.error("Docente con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el docente por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo docente
    public DocenteDto createDocente(DocenteDto docenteDto) {
        try {
            Optional<Alumno> alumnoExistente = alumnoDao.findByPersonaIdPersonaAndIsDeletedFalse(docenteDto.getIdPersona());
            if (alumnoExistente.isPresent()) {
                LOG.error("La persona con ID {} ya es un alumno", docenteDto.getIdPersona());
                return null;
            }

            Optional<Docente> docenteExistente = docenteDao.findByPersonaIdPersonaAndIsDeletedFalse(docenteDto.getIdPersona());
            if (docenteExistente.isPresent()) {
                LOG.error("Docente con ID de persona {} ya existe", docenteDto.getIdPersona());
                return null;
            }

            Optional<Persona> personaOpt = personaDao.findById(docenteDto.getIdPersona());
            if (personaOpt.isEmpty()) {
                LOG.error("Persona con ID {} no encontrada", docenteDto.getIdPersona());
                return null;
            }

            Docente docente = new Docente();
            docente.setAboutMe(docenteDto.getAboutMe());
            docente.setGradoEstudio(docenteDto.getGradoEstudio());
            docente.setPersona(personaOpt.get());

            Docente savedDocente = docenteDao.save(docente);
            LOG.info("Docente creado correctamente");
            return new DocenteDto(savedDocente.getIdDocente(), savedDocente.getAboutMe(), savedDocente.getGradoEstudio(), savedDocente.getPersona().getIdPersona());
        } catch (Exception ex) {
            LOG.error("Error al crear el docente: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un docente existente por ID
    public DocenteDto updateDocente(Long id, DocenteDto docenteDto) {
        try {
            Optional<Docente> docenteOpt = docenteDao.findById(id);
            if (docenteOpt.isPresent() && !docenteOpt.get().getIsDeleted()) {
                Docente docente = docenteOpt.get();
                docente.setAboutMe(docenteDto.getAboutMe());
                docente.setGradoEstudio(docenteDto.getGradoEstudio());

                Docente updatedDocente = docenteDao.save(docente);
                LOG.info("Docente actualizado correctamente");
                return new DocenteDto(updatedDocente.getIdDocente(), updatedDocente.getAboutMe(), updatedDocente.getGradoEstudio(), updatedDocente.getPersona().getIdPersona());
            }
            LOG.error("Docente con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el docente: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un docente por ID (borrado lógico)
    public void deleteDocente(Long id) {
        try {
            Optional<Docente> docenteOpt = docenteDao.findById(id);
            if (docenteOpt.isPresent() && !docenteOpt.get().getIsDeleted()) {
                Docente docente = docenteOpt.get();
                docente.setIsDeleted(true);
                docenteDao.save(docente);
                LOG.info("Docente eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el docente: {}", ex.getMessage());
        }
    }
}
