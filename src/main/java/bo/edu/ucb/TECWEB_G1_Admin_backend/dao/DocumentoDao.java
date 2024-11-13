package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoDao extends JpaRepository<Documento, Long> {
    List<Documento> findByIsDeletedFalse();
}
