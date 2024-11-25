package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.MedallaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.MedallaDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Medalla;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Documento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MedallaBl {

    private static final Logger LOG = LoggerFactory.getLogger(MedallaBl.class);
    private final MedallaDao medallaDao;

    @Autowired
    public MedallaBl(MedallaDao medallaDao) {
        this.medallaDao = medallaDao;
    }

    // obtener todas las medallas que no están eliminadas
    public List<MedallaDto> findAll() {
        try {
            List<Medalla> medallas = medallaDao.findByIsDeletedFalse();
            List<MedallaDto> medallaDtos = new ArrayList<>();
            for (Medalla m : medallas) {
                medallaDtos.add(new MedallaDto(m.getIdMedalla(), m.getNombre(), m.getPuntaje(), m.getImagen(), m.getDocumento().getIdDocumento()));
            }
            LOG.info("Medallas obtenidas correctamente");
            return medallaDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener las medallas: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener una medalla por ID
    public MedallaDto findById(Long idMedalla) {
        try {
            Optional<Medalla> medallaOpt = medallaDao.findById(idMedalla);
            if (medallaOpt.isPresent() && !medallaOpt.get().getIsDeleted()) {
                Medalla medalla = medallaOpt.get();
                LOG.info("Medalla obtenida correctamente");
                return new MedallaDto(medalla.getIdMedalla(), medalla.getNombre(), medalla.getPuntaje(), medalla.getImagen(), medalla.getDocumento().getIdDocumento());
            }
            LOG.error("Medalla con ID {} no encontrada", idMedalla);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener la medalla por ID: {}", ex.getMessage());
            return null;
        }
    }

    // obtener todas las medallas de un documento en específico
    public List<MedallaDto> findAllByDocumentoId(Long documentoId) {
        try {
            List<Medalla> medallas = medallaDao.findByDocumentoIdDocumentoAndIsDeletedFalse(documentoId);
            List<MedallaDto> medallaDtos = new ArrayList<>();
            for (Medalla m : medallas) {
                medallaDtos.add(new MedallaDto(m.getIdMedalla(), m.getNombre(), m.getPuntaje(), m.getImagen(), m.getDocumento().getIdDocumento()));
            }
            LOG.info("Medallas del documento {} obtenidas correctamente", documentoId);
            return medallaDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener las medallas del documento {}: {}", documentoId, ex.getMessage());
            return Collections.emptyList();
        }
    }

    // crear una nueva medalla
    public MedallaDto createMedalla(MedallaDto medallaDto) {
        try {
            Medalla medalla = new Medalla();
            medalla.setNombre(medallaDto.getNombre());
            medalla.setPuntaje(medallaDto.getPuntaje());
            medalla.setImagen(medallaDto.getImagen());
            Documento documento = new Documento();
            documento.setIdDocumento(medallaDto.getIdDocumento());
            medalla.setDocumento(documento);

            Medalla savedMedalla = medallaDao.save(medalla);
            LOG.info("Medalla creada correctamente");
            return new MedallaDto(savedMedalla.getIdMedalla(), savedMedalla.getNombre(), savedMedalla.getPuntaje(), savedMedalla.getImagen(), savedMedalla.getDocumento().getIdDocumento());
        } catch (Exception ex) {
            LOG.error("Error al crear la medalla: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar una medalla existente por ID
    public MedallaDto updateMedalla(Long idMedalla, MedallaDto medallaDto) {
        try {
            Optional<Medalla> medallaOpt = medallaDao.findById(idMedalla);
            if (medallaOpt.isPresent() && !medallaOpt.get().getIsDeleted()) {
                Medalla medalla = medallaOpt.get();
                medalla.setNombre(medallaDto.getNombre());
                medalla.setPuntaje(medallaDto.getPuntaje());
                medalla.setImagen(medallaDto.getImagen());

                Medalla updatedMedalla = medallaDao.save(medalla);
                LOG.info("Medalla actualizada correctamente");
                return new MedallaDto(updatedMedalla.getIdMedalla(), updatedMedalla.getNombre(), updatedMedalla.getPuntaje(), updatedMedalla.getImagen(), updatedMedalla.getDocumento().getIdDocumento());
            }
            LOG.error("Medalla con ID {} no encontrada", idMedalla);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar la medalla: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar una medalla (borrado lógico)
    public void deleteMedalla(Long idMedalla) {
        try {
            Optional<Medalla> medallaOpt = medallaDao.findById(idMedalla);
            if (medallaOpt.isPresent() && !medallaOpt.get().getIsDeleted()) {
                Medalla medalla = medallaOpt.get();
                medalla.setIsDeleted(true);
                medallaDao.save(medalla);
                LOG.info("Medalla eliminada correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar la medalla: {}", ex.getMessage());
        }
    }
}
