package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.DocumentoLeido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoLeidoDao extends JpaRepository<DocumentoLeido, Long> {
    List<DocumentoLeido> findByAlumnoIdAlumnoAndIsDeletedFalse(Long idAlumno);
    List<DocumentoLeido> findByIsDeletedFalse();
}
