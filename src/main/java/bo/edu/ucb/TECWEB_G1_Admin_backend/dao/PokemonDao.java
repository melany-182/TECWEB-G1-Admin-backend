package bo.edu.ucb.TECWEB_G1_Admin_backend.dao;

import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByAlumnoIdAlumnoAndIsDeletedFalse(Long idAlumno);
    List<Pokemon> findByIsDeletedFalse();
}
