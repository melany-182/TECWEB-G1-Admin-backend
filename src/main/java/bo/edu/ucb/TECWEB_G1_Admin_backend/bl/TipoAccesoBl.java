package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.TipoAccesoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.TipoAccesoDto;
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
public class TipoAccesoBl {

    private static final Logger LOG = LoggerFactory.getLogger(TipoAccesoBl.class);
    private final TipoAccesoDao tipoAccesoDao;

    @Autowired
    public TipoAccesoBl(TipoAccesoDao tipoAccesoDao) {
        this.tipoAccesoDao = tipoAccesoDao;
    }

    // obtener todos los tipos de acceso que no están eliminados
    public List<TipoAccesoDto> findAll() {
        try {
            List<TipoAcceso> tiposAcceso = tipoAccesoDao.findByIsDeletedFalse();
            List<TipoAccesoDto> tipoAccesoDtos = new ArrayList<>();
            for (TipoAcceso t : tiposAcceso) {
                tipoAccesoDtos.add(new TipoAccesoDto(t.getIdTipoAcceso(), t.getNombre(), t.getDescripcion()));
            }
            LOG.info("Tipos de acceso obtenidos correctamente");
            return tipoAccesoDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los tipos de acceso: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un tipo de acceso por ID
    public TipoAccesoDto findById(Long idTipoAcceso) {
        try {
            Optional<TipoAcceso> tipoAccesoOpt = tipoAccesoDao.findById(idTipoAcceso);
            if (tipoAccesoOpt.isPresent() && !tipoAccesoOpt.get().getIsDeleted()) {
                TipoAcceso tipoAcceso = tipoAccesoOpt.get();
                LOG.info("Tipo de acceso obtenido correctamente");
                return new TipoAccesoDto(tipoAcceso.getIdTipoAcceso(), tipoAcceso.getNombre(), tipoAcceso.getDescripcion());
            }
            LOG.error("Tipo de acceso con ID {} no encontrado", idTipoAcceso);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el tipo de acceso: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo tipo de acceso
    public TipoAccesoDto createTipoAcceso(TipoAccesoDto tipoAccesoDto) {
        try {
            Optional<TipoAcceso> tipoAccesoExistente = tipoAccesoDao.findByNombreAndIsDeletedFalse(tipoAccesoDto.getNombre());
            if (tipoAccesoExistente.isPresent()) {
                LOG.error("El tipo de acceso con nombre {} ya existe", tipoAccesoDto.getNombre());
                return null;
            }

            TipoAcceso tipoAcceso = new TipoAcceso();
            tipoAcceso.setNombre(tipoAccesoDto.getNombre());
            tipoAcceso.setDescripcion(tipoAccesoDto.getDescripcion());

            TipoAcceso savedTipoAcceso = tipoAccesoDao.save(tipoAcceso);
            LOG.info("Tipo de acceso creado correctamente");
            return new TipoAccesoDto(savedTipoAcceso.getIdTipoAcceso(), savedTipoAcceso.getNombre(), savedTipoAcceso.getDescripcion());
        } catch (Exception ex) {
            LOG.error("Error al crear el tipo de acceso: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un tipo de acceso existente por ID
    public TipoAccesoDto updateTipoAcceso(Long idTipoAcceso, TipoAccesoDto tipoAccesoDto) {
        try {
            Optional<TipoAcceso> tipoAccesoOpt = tipoAccesoDao.findById(idTipoAcceso);
            if (tipoAccesoOpt.isPresent() && !tipoAccesoOpt.get().getIsDeleted()) {
                TipoAcceso tipoAcceso = tipoAccesoOpt.get();
                tipoAcceso.setNombre(tipoAccesoDto.getNombre());
                tipoAcceso.setDescripcion(tipoAccesoDto.getDescripcion());

                TipoAcceso updatedTipoAcceso = tipoAccesoDao.save(tipoAcceso);
                LOG.info("Tipo de acceso actualizado correctamente");
                return new TipoAccesoDto(updatedTipoAcceso.getIdTipoAcceso(), updatedTipoAcceso.getNombre(), updatedTipoAcceso.getDescripcion());
            }
            LOG.error("Tipo de acceso con ID {} no encontrado", idTipoAcceso);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el tipo de acceso: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un tipo de acceso (borrado lógico)
    public void deleteTipoAcceso(Long idTipoAcceso) {
        try {
            Optional<TipoAcceso> tipoAccesoOpt = tipoAccesoDao.findById(idTipoAcceso);
            if (tipoAccesoOpt.isPresent() && !tipoAccesoOpt.get().getIsDeleted()) {
                TipoAcceso tipoAcceso = tipoAccesoOpt.get();
                tipoAcceso.setIsDeleted(true);
                tipoAccesoDao.save(tipoAcceso);
                LOG.info("Tipo de acceso eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el tipo de acceso: {}", ex.getMessage());
        }
    }
}
