package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.MedallaGanada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedallaGanadaDao extends JpaRepository<MedallaGanada, Long> {
    List<MedallaGanada> findByAlumnoIdAlumnoAndIsDeletedFalse(Long idAlumno);
    List<MedallaGanada> findByIsDeletedFalse();
}
