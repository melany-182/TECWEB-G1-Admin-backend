package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoGoogleAndIsDeletedFalse(String correoGoogle);
    List<Usuario> findByIsDeletedFalse();
}
