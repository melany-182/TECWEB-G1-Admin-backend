package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.UsuarioBl;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/usuarios")
public class UsuarioApi {

    private final UsuarioBl usuarioBl;

    @Autowired
    public UsuarioApi(UsuarioBl usuarioService) {
        this.usuarioBl = usuarioService;
    }

    /** Endpoint que retorna todos los usuarios.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        List<UsuarioDto> usuarios = usuarioBl.findAll();
        return ResponseEntity.ok(usuarios);
    }

    /** Endpoint que retorna el detalle de un usuario por correo electrónico.
     * @param correoGoogle: El correo electrónico de Google del usuario a obtener.
     */
    @GetMapping(params = "correoGoogle")
    public ResponseEntity<UsuarioDto> getUsuarioByCorreo(@RequestParam String correoGoogle) {
        UsuarioDto usuario = usuarioBl.findByCorreoGoogle(correoGoogle);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite crear un usuario.
     * @param usuarioDto: El usuario a crear.
     */
    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioDto newUsuario = usuarioBl.createUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    /** Endpoint que permite actualizar un usuario por ID.
     * @param id: El ID del usuario a actualizar.
     * @param usuarioDto: El usuario con los datos actualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto updatedUsuario = usuarioBl.updateUsuario(id, usuarioDto);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /** Endpoint que permite eliminar un usuario por ID. (borrado lógico)
     * @param id: El ID del usuario a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioBl.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
