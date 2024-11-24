package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.JefeCarreraDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.NoticiaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.NoticiaDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Noticia;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.JefeCarrera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaBl {

    private static final Logger LOG = LoggerFactory.getLogger(NoticiaBl.class);
    private final NoticiaDao noticiaDao;
    private final JefeCarreraDao jefeCarreraDao;

    @Autowired
    public NoticiaBl(NoticiaDao noticiaDao, JefeCarreraDao jefeCarreraDao) {
        this.noticiaDao = noticiaDao;
        this.jefeCarreraDao = jefeCarreraDao;
    }

    // obtener todas las noticias que no están eliminadas
    public List<NoticiaDto> findAll() {
        try {
            List<Noticia> noticias = noticiaDao.findByIsDeletedFalse();
            List<NoticiaDto> noticiaDtos = new ArrayList<>();
            for (Noticia n : noticias) {
                noticiaDtos.add(new NoticiaDto(n.getIdNoticia(), n.getNombre(), n.getDescripcion(), n.getImagen(), n.getFechaInicio(), n.getFechaFin(), n.getJefeCarrera().getIdJefeCarrera()));
            }
            LOG.info("Noticias obtenidas correctamente");
            return noticiaDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener las noticias: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener una noticia por ID
    public NoticiaDto findById(Long id) {
        try {
            Optional<Noticia> noticiaOpt = noticiaDao.findById(id);
            if (noticiaOpt.isPresent() && !noticiaOpt.get().getIsDeleted()) {
                Noticia noticia = noticiaOpt.get();
                LOG.info("Noticia obtenida correctamente");
                return new NoticiaDto(noticia.getIdNoticia(), noticia.getNombre(), noticia.getDescripcion(), noticia.getImagen(), noticia.getFechaInicio(), noticia.getFechaFin(), noticia.getJefeCarrera().getIdJefeCarrera());
            }
            LOG.error("Noticia con ID {} no encontrada", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener la noticia por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear una nueva noticia
    public NoticiaDto createNoticia(NoticiaDto noticiaDto) {
        try {
            Optional<JefeCarrera> jefeOpt = jefeCarreraDao.findById(noticiaDto.getIdJefeCarrera());
            if (jefeOpt.isEmpty()) {
                LOG.error("No se encontró el jefe de carrera con ID {}", noticiaDto.getIdJefeCarrera());
                return null;
            }

            Noticia noticia = new Noticia();
            noticia.setNombre(noticiaDto.getNombre());
            noticia.setDescripcion(noticiaDto.getDescripcion());
            noticia.setImagen(noticiaDto.getImagen());
            noticia.setFechaInicio(noticiaDto.getFechaInicio());
            noticia.setFechaFin(noticiaDto.getFechaFin());
            noticia.setJefeCarrera(jefeOpt.get());

            Noticia savedNoticia = noticiaDao.save(noticia);
            LOG.info("Noticia creada correctamente");
            return new NoticiaDto(savedNoticia.getIdNoticia(), savedNoticia.getNombre(), savedNoticia.getDescripcion(), savedNoticia.getImagen(), savedNoticia.getFechaInicio(), savedNoticia.getFechaFin(), savedNoticia.getJefeCarrera().getIdJefeCarrera());
        } catch (Exception ex) {
            LOG.error("Error al crear la noticia: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar una noticia existente por ID
    public NoticiaDto updateNoticia(Long id, NoticiaDto noticiaDto) {
        try {
            Optional<Noticia> noticiaOpt = noticiaDao.findById(id);
            if (noticiaOpt.isPresent() && !noticiaOpt.get().getIsDeleted()) {
                Noticia noticia = noticiaOpt.get();
                noticia.setNombre(noticiaDto.getNombre());
                noticia.setDescripcion(noticiaDto.getDescripcion());
                noticia.setImagen(noticiaDto.getImagen());
                noticia.setFechaInicio(noticiaDto.getFechaInicio());
                noticia.setFechaFin(noticiaDto.getFechaFin());
                Optional<JefeCarrera> jefeOpt = jefeCarreraDao.findById(noticiaDto.getIdJefeCarrera());
                if (jefeOpt.isEmpty()) {
                    LOG.error("No se encontró el jefe de carrera con ID {}", noticiaDto.getIdJefeCarrera());
                    return null;
                }
                noticia.setJefeCarrera(jefeOpt.get());

                Noticia updatedNoticia = noticiaDao.save(noticia);
                LOG.info("Noticia actualizada correctamente");
                return new NoticiaDto(updatedNoticia.getIdNoticia(), updatedNoticia.getNombre(), updatedNoticia.getDescripcion(), updatedNoticia.getImagen(), updatedNoticia.getFechaInicio(), updatedNoticia.getFechaFin(), updatedNoticia.getJefeCarrera().getIdJefeCarrera());
            }
            LOG.error("Noticia con ID {} no encontrada", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar la noticia: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar una noticia por ID (borrado lógico)
    public void deleteNoticia(Long id) {
        try {
            Optional<Noticia> noticiaOpt = noticiaDao.findById(id);
            if (noticiaOpt.isPresent() && !noticiaOpt.get().getIsDeleted()) {
                Noticia noticia = noticiaOpt.get();
                noticia.setIsDeleted(true);
                noticiaDao.save(noticia);
                LOG.info("Noticia eliminada correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar la noticia: {}", ex.getMessage());
        }
    }
}
