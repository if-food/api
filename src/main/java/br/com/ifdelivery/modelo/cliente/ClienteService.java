package br.com.ifdelivery.modelo.cliente;

import java.time.LocalDate;

import java.util.List;

import br.com.ifdelivery.modelo.acesso.UsuarioService;
import br.com.ifdelivery.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UsuarioService usuarioService;


    @Transactional
    public Cliente save(Cliente cliente) {
        usuarioService.save(cliente.getUsuario());
        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        try {
            return repository.save(cliente);
        } catch (Exception e) {
            throw new UserException(UserException.MSG_EMAIL_ALREADY_EXISTS);
        }
    }

    public List<Cliente> listarTodos() {

        return repository.findAll();
    }

    @SuppressWarnings("null")
    public Cliente obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

        @SuppressWarnings("null")
        Cliente cliente = repository.findById(id).get();
//        cliente.setEmail(clienteAlterado.getEmail());
//        cliente.setPassword(clienteAlterado.getPassword());
        cliente.setDesconto(clienteAlterado.getDesconto());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setTelefone(clienteAlterado.getTelefone());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setRole(clienteAlterado.getRole());
        

        cliente.setVersao(cliente.getVersao() + 1);
        try {
            repository.save(cliente);
        } catch (Exception e) {
            throw new UserException(UserException.MSG_EMAIL_ALREADY_EXISTS);
        }
    }

    @Transactional
    public void delete(Long id) {

        @SuppressWarnings("null")
        Cliente cliente = repository.findById(id).get();
        cliente.setHabilitado(Boolean.FALSE);
        cliente.setVersao(cliente.getVersao() + 1);
        
        repository.save(cliente);
    }
}
