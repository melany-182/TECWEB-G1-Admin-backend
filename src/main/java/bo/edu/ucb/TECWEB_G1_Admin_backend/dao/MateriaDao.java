package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MateriaDao extends JpaRepository<Materia, Long> {
    List<Materia> findByDocenteIdDocenteAndIsDeletedFalse(Long idDocente);
    List<Materia> findByIsDeletedFalse();
}
