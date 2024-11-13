package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.JefeCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JefeCarreraDao extends JpaRepository<JefeCarrera, Long> {
    Optional<JefeCarrera> findByPersonaIdPersonaAndIsDeletedFalse(Long idPersona);
    List<JefeCarrera> findByIsDeletedFalse();
}
