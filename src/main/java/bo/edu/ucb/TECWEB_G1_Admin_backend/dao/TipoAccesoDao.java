package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.TipoAcceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TipoAccesoDao extends JpaRepository<TipoAcceso, Long> {
    Optional<TipoAcceso> findByNombreAndIsDeletedFalse(String nombre);
    List<TipoAcceso> findByIsDeletedFalse();
}
