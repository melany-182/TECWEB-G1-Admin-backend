package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoDao extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByPersonaIdPersonaAndIsDeletedFalse(Long idPersona);
    List<Alumno> findByIsDeletedFalse();
}
