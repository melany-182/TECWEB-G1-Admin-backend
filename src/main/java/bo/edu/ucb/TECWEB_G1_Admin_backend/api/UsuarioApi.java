package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.UsuarioBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // FIXME: restringir a los orígenes que se deseen
@RequestMapping("/api/v1/usuarios")
public class UsuarioApi {

    private final UsuarioBl usuarioBl;

    @Autowired
    public UsuarioApi(UsuarioBl usuarioService) {
        this.usuarioBl = usuarioService;
    }

    // obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        List<UsuarioDto> usuarios = usuarioBl.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // obtener un usuario por correo electrónico
    @GetMapping(params = "correoGoogle")
    public ResponseEntity<UsuarioDto> getUsuarioByCorreo(@RequestParam String correoGoogle) {
        UsuarioDto usuario = usuarioBl.findByCorreoGoogle(correoGoogle);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDTO) {
        UsuarioDto newUsuario = usuarioBl.createUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    // actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto updatedUsuario = usuarioBl.updateUsuario(id, usuarioDto);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // eliminar un usuario (borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioBl.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
