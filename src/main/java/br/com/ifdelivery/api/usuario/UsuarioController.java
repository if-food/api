package br.com.ifdelivery.api.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifdelivery.modelo.usuario.Usuario;
import br.com.ifdelivery.modelo.usuario.UsuarioService;
/*

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin
public class UsuarioController {

    final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioSerivce) {
    	this.usuarioService = usuarioSerivce;
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioRequest request) {

        Usuario usuario = usuarioService.save(request.Usuariobuild());
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }


    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario obterPorID(@PathVariable Long id) {
        return usuarioService.obterPorID(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id,
                                          @RequestBody UsuarioRequest request) {

        usuarioService.update(id, request.Usuariobuild());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

}
*/