package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.DocenteDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.MateriaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.MateriaDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Materia;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Docente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaBl {

    private static final Logger LOG = LoggerFactory.getLogger(MateriaBl.class);
    private final MateriaDao materiaDao;
    private final DocenteDao docenteDao;

    @Autowired
    public MateriaBl(MateriaDao materiaDao, DocenteDao docenteDao) {
        this.materiaDao = materiaDao;
        this.docenteDao = docenteDao;
    }

    // obtener todas las materias que no están eliminadas
    public List<MateriaDto> findAll() {
        try {
            List<Materia> materias = materiaDao.findByIsDeletedFalse();
            List<MateriaDto> materiaDtos = new ArrayList<>();
            for (Materia m : materias) {
                materiaDtos.add(new MateriaDto(m.getIdMateria(), m.getNombre(), m.getDescripcion(), m.getDocente().getIdDocente()));
            }
            LOG.info("Materias obtenidas correctamente");
            return materiaDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener las materias: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener una materia por ID
    public MateriaDto findById(Long id) {
        try {
            Optional<Materia> materiaOpt = materiaDao.findById(id);
            if (materiaOpt.isPresent() && !materiaOpt.get().getIsDeleted()) {
                Materia materia = materiaOpt.get();
                LOG.info("Materia obtenida correctamente");
                return new MateriaDto(materia.getIdMateria(), materia.getNombre(), materia.getDescripcion(), materia.getDocente().getIdDocente());
            }
            LOG.error("Materia con ID {} no encontrada", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener la materia por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear una nueva materia
    public MateriaDto createMateria(MateriaDto materiaDto) {
        try {
            Optional<Docente> docenteOpt = docenteDao.findById(materiaDto.getIdDocente());
            if (docenteOpt.isEmpty()) {
                LOG.error("No se encontró el docente con ID {}", materiaDto.getIdDocente());
                return null;
            }

            Materia materia = new Materia();
            materia.setNombre(materiaDto.getNombre());
            materia.setDescripcion(materiaDto.getDescripcion());
            materia.setDocente(docenteOpt.get());

            Materia savedMateria = materiaDao.save(materia);
            LOG.info("Materia creada correctamente");
            return new MateriaDto(savedMateria.getIdMateria(), savedMateria.getNombre(), savedMateria.getDescripcion(), savedMateria.getDocente().getIdDocente());
        } catch (Exception ex) {
            LOG.error("Error al crear la materia: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar una materia existente por ID
    public MateriaDto updateMateria(Long id, MateriaDto materiaDto) {
        try {
            Optional<Materia> materiaOpt = materiaDao.findById(id);
            if (materiaOpt.isPresent() && !materiaOpt.get().getIsDeleted()) {
                Materia materia = materiaOpt.get();
                materia.setNombre(materiaDto.getNombre());
                materia.setDescripcion(materiaDto.getDescripcion());
                Optional<Docente> docenteOpt = docenteDao.findById(materiaDto.getIdDocente());
                if (docenteOpt.isEmpty()) {
                    LOG.error("No se encontró el docente con ID {}", materiaDto.getIdDocente());
                    return null;
                }
                materia.setDocente(docenteOpt.get());

                Materia updatedMateria = materiaDao.save(materia);
                LOG.info("Materia actualizada correctamente");
                return new MateriaDto(updatedMateria.getIdMateria(), updatedMateria.getNombre(), updatedMateria.getDescripcion(), updatedMateria.getDocente().getIdDocente());
            }
            LOG.error("Materia con ID {} no encontrada", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar la materia: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar una materia por ID (borrado lógico)
    public void deleteMateria(Long id) {
        try {
            Optional<Materia> materiaOpt = materiaDao.findById(id);
            if (materiaOpt.isPresent() && !materiaOpt.get().getIsDeleted()) {
                Materia materia = materiaOpt.get();
                materia.setIsDeleted(true);
                materiaDao.save(materia);
                LOG.info("Materia eliminada correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar la materia: {}", ex.getMessage());
        }
    }
}
