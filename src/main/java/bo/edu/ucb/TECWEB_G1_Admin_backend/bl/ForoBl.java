package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.ForoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.ForoDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Foro;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.TipoAcceso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ForoBl {

    private static final Logger LOG = LoggerFactory.getLogger(ForoBl.class);
    private final ForoDao foroDao;

    @Autowired
    public ForoBl(ForoDao foroDao) {
        this.foroDao = foroDao;
    }

    // obtener todos los foros que no están eliminados
    public List<ForoDto> findAll() {
        try {
            List<Foro> foros = foroDao.findByIsDeletedFalse();
            List<ForoDto> foroDtos = new ArrayList<>();
            for (Foro f : foros) {
                foroDtos.add(new ForoDto(f.getIdForo(), f.getNombre(), f.getTema(), f.getTipoAcceso().getIdTipoAcceso()));
            }
            LOG.info("Foros obtenidos correctamente");
            return foroDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los foros: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un foro por ID
    public ForoDto findById(Long idForo) {
        try {
            Optional<Foro> foroOpt = foroDao.findById(idForo);
            if (foroOpt.isPresent() && !foroOpt.get().getIsDeleted()) {
                Foro foro = foroOpt.get();
                LOG.info("Foro obtenido correctamente");
                return new ForoDto(foro.getIdForo(), foro.getNombre(), foro.getTema(), foro.getTipoAcceso().getIdTipoAcceso());
            }
            LOG.error("Foro con ID {} no encontrado", idForo);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el foro por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo foro
    public ForoDto createForo(ForoDto foroDto) {
        try {
            Foro foro = new Foro();
            foro.setNombre(foroDto.getNombre());
            foro.setTema(foroDto.getTema());
            TipoAcceso tipoAcceso = new TipoAcceso();
            tipoAcceso.setIdTipoAcceso(foroDto.getIdTipoAcceso());
            foro.setTipoAcceso(tipoAcceso);

            Foro savedForo = foroDao.save(foro);
            LOG.info("Foro creado correctamente");
            return new ForoDto(savedForo.getIdForo(), savedForo.getNombre(), savedForo.getTema(), savedForo.getTipoAcceso().getIdTipoAcceso());
        } catch (Exception ex) {
            LOG.error("Error al crear el foro: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un foro existente por ID
    public ForoDto updateForo(Long idForo, ForoDto foroDto) {
        try {
            Optional<Foro> foroOpt = foroDao.findById(idForo);
            if (foroOpt.isPresent() && !foroOpt.get().getIsDeleted()) {
                Foro foro = foroOpt.get();
                foro.setNombre(foroDto.getNombre());
                foro.setTema(foroDto.getTema());

                Foro updatedForo = foroDao.save(foro);
                LOG.info("Foro actualizado correctamente");
                return new ForoDto(updatedForo.getIdForo(), updatedForo.getNombre(), updatedForo.getTema(), updatedForo.getTipoAcceso().getIdTipoAcceso());
            }
            LOG.error("Foro con ID {} no encontrado", idForo);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el foro: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un foro (borrado lógico)
    public void deleteForo(Long idForo) {
        try {
            Optional<Foro> foroOpt = foroDao.findById(idForo);
            if (foroOpt.isPresent() && !foroOpt.get().getIsDeleted()) {
                Foro foro = foroOpt.get();
                foro.setIsDeleted(true);
                foroDao.save(foro);
                LOG.info("Foro eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el foro: {}", ex.getMessage());
        }
    }
}
