package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.UsuarioDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.UsuarioDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.TipoAcceso;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioBl {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioBl.class); // LOGGER
    private final UsuarioDao usuarioDao;

    @Autowired
    public UsuarioBl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    // obtener todos los usuarios que no están eliminados
    public List<UsuarioDto> findAll() {
        try {
            List<Usuario> usuarios = usuarioDao.findByIsDeletedFalse();
            List<UsuarioDto> usuarioDtos = new ArrayList<>();
            for (Usuario u : usuarios) {
                usuarioDtos.add(new UsuarioDto(u.getIdUsuario(), u.getNombreGoogle(), u.getCorreoGoogle(), u.getTipoAcceso().getIdTipoAcceso()));
            }
            LOG.info("Usuarios obtenidos correctamente");
            return usuarioDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los usuarios: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un usuario por correo electrónico
    public UsuarioDto findByCorreoGoogle(String correoGoogle) {
        try {
            Optional<Usuario> usuarioOpt = usuarioDao.findByCorreoGoogleAndIsDeletedFalse(correoGoogle);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                LOG.info("Usuario obtenido correctamente");
                return new UsuarioDto(usuario.getIdUsuario(), usuario.getNombreGoogle(), usuario.getCorreoGoogle(), usuario.getTipoAcceso().getIdTipoAcceso());
            }
            LOG.error("Usuario con correo electrónico {} no encontrado", correoGoogle);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el usuario por correo electrónico: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo usuario
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        try {
            Optional<Usuario> usuarioExistente = usuarioDao.findByCorreoGoogleAndIsDeletedFalse(usuarioDto.getCorreoGoogle());
            if (usuarioExistente.isPresent()) {
                LOG.error("El usuario con correo electrónico {} ya existe", usuarioDto.getCorreoGoogle());
                return null;
            }

            Usuario usuario = new Usuario();
            usuario.setNombreGoogle(usuarioDto.getNombreGoogle());
            usuario.setCorreoGoogle(usuarioDto.getCorreoGoogle());
            TipoAcceso tipoAcceso = new TipoAcceso();
            tipoAcceso.setIdTipoAcceso(usuarioDto.getIdTipoAcceso());
            usuario.setTipoAcceso(tipoAcceso);

            Usuario savedUsuario = usuarioDao.save(usuario);
            LOG.info("Usuario creado correctamente");
            return new UsuarioDto(savedUsuario.getIdUsuario(), savedUsuario.getNombreGoogle(), savedUsuario.getCorreoGoogle(), savedUsuario.getTipoAcceso().getIdTipoAcceso());
        } catch (Exception ex) {
            LOG.error("Error al crear el usuario: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un usuario existente por ID
    public UsuarioDto updateUsuario(Long id, UsuarioDto usuarioDto) {
        try {
            Optional<Usuario> usuarioOpt = usuarioDao.findById(id);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                usuario.setNombreGoogle(usuarioDto.getNombreGoogle());
                usuario.setCorreoGoogle(usuarioDto.getCorreoGoogle());
                TipoAcceso tipoAcceso = new TipoAcceso();
                tipoAcceso.setIdTipoAcceso(usuarioDto.getIdTipoAcceso());
                usuario.setTipoAcceso(tipoAcceso);

                Usuario updatedUsuario = usuarioDao.save(usuario);
                LOG.info("Usuario actualizado correctamente");
                return new UsuarioDto(updatedUsuario.getIdUsuario(), updatedUsuario.getNombreGoogle(), updatedUsuario.getCorreoGoogle(), updatedUsuario.getTipoAcceso().getIdTipoAcceso());
            }
            LOG.error("Usuario con ID {} no encontrado", id);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el usuario: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un usuario por ID (borrado lógico)
    public void deleteUsuario(Long id) {
        try {
            Optional<Usuario> usuarioOpt = usuarioDao.findById(id);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                usuario.setIsDeleted(true);
                usuarioDao.save(usuario);
                LOG.info("Usuario eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el usuario: {}", ex.getMessage());
        }
    }

    /*
    TODO: crear un método que permita asignar una persona a un usuario cuando este se logueé por primera vez, además
     de registrar a la persona como alumno, docente o jefe de carrera; involucrando a las entidades correspondientes
    */
}
