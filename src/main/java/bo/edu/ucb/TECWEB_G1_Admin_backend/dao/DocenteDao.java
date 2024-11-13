package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteDao extends JpaRepository<Docente, Long> {
    Optional<Docente> findByPersonaIdPersonaAndIsDeletedFalse(Long idPersona);
    List<Docente> findByIsDeletedFalse();
}
