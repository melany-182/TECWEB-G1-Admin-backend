package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.PersonaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.UsuarioDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.PersonaDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Persona;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaBl {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaBl.class);
    private final PersonaDao personaDao;
    private final UsuarioDao usuarioDao;

    @Autowired
    public PersonaBl(PersonaDao personaDao, UsuarioDao usuarioDao) {
        this.personaDao = personaDao;
        this.usuarioDao = usuarioDao;
    }

    // obtener todas las personas que no están eliminadas
    public List<PersonaDto> findAll() {
        try {
            List<Persona> personas = personaDao.findByIsDeletedFalse();
            List<PersonaDto> personaDtos = new ArrayList<>();
            for (Persona p : personas) {
                personaDtos.add(new PersonaDto(p.getIdPersona(), p.getCi(), p.getNombres(), p.getApPaterno(), p.getApMaterno(), p.getUsuario().getIdUsuario()));
            }
            LOG.info("Personas obtenidas correctamente");
            return personaDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener las personas: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener una persona por ID
    public PersonaDto findById(Long id) {
        try {
            Optional<Persona> personaOpt = personaDao.findById(id);
            if (personaOpt.isPresent() && !personaOpt.get().getIsDeleted()) {
                Persona persona = personaOpt.get();
                LOG.info("Persona obtenida correctamente");
                return new PersonaDto(persona.getIdPersona(), persona.getCi(), persona.getNombres(), persona.getApPaterno(), persona.getApMaterno(), persona.getUsuario().getIdUsuario());
            }
            LOG.error("Persona con ID {} no encontrada", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener la persona por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear una nueva persona
    public PersonaDto createPersona(PersonaDto personaDto) {
        try {
            Optional<Persona> personaExistente = personaDao.findByUsuarioIdUsuarioAndIsDeletedFalse(personaDto.getIdUsuario());
            if (personaExistente.isPresent()) {
                LOG.error("La persona con ID de usuario {} ya existe", personaDto.getIdUsuario());
                return null;
            }

            Optional<Usuario> usuarioOpt = usuarioDao.findById(personaDto.getIdUsuario());
            if (usuarioOpt.isEmpty()) {
                LOG.error("No se encontró el usuario con ID {}", personaDto.getIdUsuario());
                return null;
            }

            Persona persona = new Persona();
            persona.setCi(personaDto.getCi());
            persona.setNombres(personaDto.getNombres());
            persona.setApPaterno(personaDto.getApPaterno());
            persona.setApMaterno(personaDto.getApMaterno());
            persona.setUsuario(usuarioOpt.get());

            Persona savedPersona = personaDao.save(persona);
            LOG.info("Persona creada correctamente");
            return new PersonaDto(savedPersona.getIdPersona(), savedPersona.getCi(), savedPersona.getNombres(), savedPersona.getApPaterno(), savedPersona.getApMaterno(), savedPersona.getUsuario().getIdUsuario());
        } catch (Exception ex) {
            LOG.error("Error al crear la persona: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar una persona existente por ID
    public PersonaDto updatePersona(Long id, PersonaDto personaDto) {
        try {
            Optional<Persona> personaOpt = personaDao.findById(id);
            if (personaOpt.isPresent() && !personaOpt.get().getIsDeleted()) {
                Persona persona = personaOpt.get();
                persona.setCi(personaDto.getCi());
                persona.setNombres(personaDto.getNombres());
                persona.setApPaterno(personaDto.getApPaterno());
                persona.setApMaterno(personaDto.getApMaterno());

                Persona updatedPersona = personaDao.save(persona);
                LOG.info("Persona actualizada correctamente");
                return new PersonaDto(updatedPersona.getIdPersona(), updatedPersona.getCi(), updatedPersona.getNombres(), updatedPersona.getApPaterno(), updatedPersona.getApMaterno(), updatedPersona.getUsuario().getIdUsuario());
            }
            LOG.error("Persona con ID {} no encontrada", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar la persona: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar una persona por ID (borrado lógico)
    public void deletePersona(Long id) {
        try {
            Optional<Persona> personaOpt = personaDao.findById(id);
            if (personaOpt.isPresent() && !personaOpt.get().getIsDeleted()) {
                Persona persona = personaOpt.get();
                persona.setIsDeleted(true);
                personaDao.save(persona);
                LOG.info("Persona eliminada correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar la persona: {}", ex.getMessage());
        }
    }
}
