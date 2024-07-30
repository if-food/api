package br.com.ifdelivery.modelo.usuario;

import java.time.LocalDate;

import java.util.List;

import br.com.ifdelivery.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario save(Usuario usuario) {

        usuario.setHabilitado(Boolean.TRUE);
        usuario.setVersao(1L);
        usuario.setDataCriacao(LocalDate.now());
        try {
            return repository.save(usuario);
        } catch (Exception e) {
            throw new UserException(UserException.MSG_EMAIL_ALREADY_EXISTS);
        }
    }

    public List<Usuario> listarTodos() {

        return repository.findAll();
    }

    @SuppressWarnings("null")
    public Usuario obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Usuario usuarioAlterado) {

        @SuppressWarnings("null")
        Usuario usuario = repository.findById(id).get();
        usuario.setEmail(usuarioAlterado.getEmail());
        usuario.setSenha(usuarioAlterado.getSenha());
        usuario.setDesconto(usuarioAlterado.getDesconto());
        usuario.setDataNascimento(usuarioAlterado.getDataNascimento());
        usuario.setTelefone(usuarioAlterado.getTelefone());
        usuario.setCpf(usuarioAlterado.getCpf());
        usuario.setRole(usuarioAlterado.getRole());
        

        usuario.setVersao(usuario.getVersao() + 1);
        try {
            repository.save(usuario);
        } catch (Exception e) {
            throw new UserException(UserException.MSG_EMAIL_ALREADY_EXISTS);
        }
    }

    @Transactional
    public void delete(Long id) {

        @SuppressWarnings("null")
        Usuario usuario = repository.findById(id).get();
        usuario.setHabilitado(Boolean.FALSE);
        usuario.setVersao(usuario.getVersao() + 1);
        
        repository.save(usuario);
    }
}
