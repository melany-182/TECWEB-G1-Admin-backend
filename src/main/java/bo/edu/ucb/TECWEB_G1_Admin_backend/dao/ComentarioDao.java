package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioDao extends JpaRepository<Comentario, Long> {
    List<Comentario> findByForoIdForoAndIsDeletedFalse(Long idForo);
    List<Comentario> findByIsDeletedFalse();
}
