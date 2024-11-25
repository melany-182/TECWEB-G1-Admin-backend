package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.ComentarioDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.ComentarioDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Comentario;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Foro;
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
public class ComentarioBl {

    private static final Logger LOG = LoggerFactory.getLogger(ComentarioBl.class);
    private final ComentarioDao comentarioDao;

    @Autowired
    public ComentarioBl(ComentarioDao comentarioDao) {
        this.comentarioDao = comentarioDao;
    }

    // obtener todos los comentarios que no están eliminados (por foro)
    public List<ComentarioDto> findAllByForoId(Long foroId) {
        try {
            List<Comentario> comentarios = comentarioDao.findByForoIdForoAndIsDeletedFalse(foroId);
            List<ComentarioDto> comentarioDtos = new ArrayList<>();
            for (Comentario c : comentarios) {
                comentarioDtos.add(new ComentarioDto(c.getIdComentario(), c.getTexto(), c.getFechaHora(), c.getForo().getIdForo(), c.getUsuario().getIdUsuario()));
            }
            LOG.info("Comentarios del foro {} obtenidos correctamente", foroId);
            return comentarioDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los comentarios del foro {}: {}", foroId, ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un comentario por ID
    public ComentarioDto findById(Long idComentario) {
        try {
            Optional<Comentario> comentarioOpt = comentarioDao.findById(idComentario);
            if (comentarioOpt.isPresent() && !comentarioOpt.get().getIsDeleted()) {
                Comentario comentario = comentarioOpt.get();
                LOG.info("Comentario obtenido correctamente");
                return new ComentarioDto(comentario.getIdComentario(), comentario.getTexto(), comentario.getFechaHora(), comentario.getForo().getIdForo(), comentario.getUsuario().getIdUsuario());
            }
            LOG.error("Comentario con ID {} no encontrado", idComentario);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el comentario por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo comentario
    public ComentarioDto createComentario(ComentarioDto comentarioDto) {
        try {
            Comentario comentario = new Comentario();
            comentario.setTexto(comentarioDto.getTexto());
            comentario.setFechaHora(comentarioDto.getFechaHora());
            Foro foro = new Foro();
            foro.setIdForo(comentarioDto.getIdForo());
            comentario.setForo(foro);
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(comentarioDto.getIdUsuario());
            comentario.setUsuario(usuario);

            Comentario savedComentario = comentarioDao.save(comentario);
            LOG.info("Comentario creado correctamente");
            return new ComentarioDto(savedComentario.getIdComentario(), savedComentario.getTexto(), savedComentario.getFechaHora(), savedComentario.getForo().getIdForo(), savedComentario.getUsuario().getIdUsuario());
        } catch (Exception ex) {
            LOG.error("Error al crear el comentario: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un comentario existente por ID
    public ComentarioDto updateComentario(Long idComentario, ComentarioDto comentarioDto) {
        try {
            Optional<Comentario> comentarioOpt = comentarioDao.findById(idComentario);
            if (comentarioOpt.isPresent() && !comentarioOpt.get().getIsDeleted()) {
                Comentario comentario = comentarioOpt.get();
                comentario.setTexto(comentarioDto.getTexto());
                comentario.setFechaHora(comentarioDto.getFechaHora());

                Comentario updatedComentario = comentarioDao.save(comentario);
                LOG.info("Comentario actualizado correctamente");
                return new ComentarioDto(updatedComentario.getIdComentario(), updatedComentario.getTexto(), updatedComentario.getFechaHora(), updatedComentario.getForo().getIdForo(), updatedComentario.getUsuario().getIdUsuario());
            }
            LOG.error("Comentario con ID {} no encontrado", idComentario);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el comentario: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un comentario (borrado lógico)
    public void deleteComentario(Long idComentario) {
        try {
            Optional<Comentario> comentarioOpt = comentarioDao.findById(idComentario);
            if (comentarioOpt.isPresent() && !comentarioOpt.get().getIsDeleted()) {
                Comentario comentario = comentarioOpt.get();
                comentario.setIsDeleted(true);
                comentarioDao.save(comentario);
                LOG.info("Comentario eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el comentario: {}", ex.getMessage());
        }
    }
}
