package br.com.ifdelivery.modelo.acesso;

import br.com.ifdelivery.modelo.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByCodigoAuth(String code);
}
