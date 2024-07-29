package br.com.ifdelivery.modelo.usuario;

import java.time.LocalDate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository repository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    
    
    public UsuarioService(UsuarioRepository userRepository, AuthenticationManager
    authenticationManager,
    PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.repository = userRepository;
    this.passwordEncoder = passwordEncoder;
    }
    
    public Usuario authenticate(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return repository.findByUsername(username).orElseThrow();
    }

    

    @Transactional
    public Usuario save(Usuario usuario) {
    	usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setHabilitado(Boolean.TRUE);
        usuario.setVersao(1L);
        usuario.setDataCriacao(LocalDate.now());
        return repository.save(usuario);
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
        usuario.setHabilitado(usuarioAlterado.getHabilitado());
        usuario.setUsername(usuarioAlterado.getUsername());
        usuario.setPassword(usuarioAlterado.getPassword());
        usuario.setRoles(usuarioAlterado.getRoles());

        usuario.setVersao(usuario.getVersao() + 1);
        repository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {

        @SuppressWarnings("null")
        Usuario usuario = repository.findById(id).get();
        usuario.setHabilitado(Boolean.FALSE);
        usuario.setVersao(usuario.getVersao() + 1);
        
        repository.save(usuario);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username).get();
	}
}
