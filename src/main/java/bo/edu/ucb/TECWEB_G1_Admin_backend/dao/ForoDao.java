package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ForoDao extends JpaRepository<Foro, Long> {
    List<Foro> findByIsDeletedFalse();
}
