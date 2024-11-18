package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaDao extends JpaRepository<Persona, Long> {
    Optional<Persona> findByUsuarioIdUsuarioAndIsDeletedFalse(Long idUsuario);
    List<Persona> findByIsDeletedFalse();
}
