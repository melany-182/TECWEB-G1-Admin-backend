package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Medalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedallaDao extends JpaRepository<Medalla, Long> {
    List<Medalla> findByIsDeletedFalse();
    List<Medalla> findByDocumentoIdDocumentoAndIsDeletedFalse(Long idDocumento);
}
