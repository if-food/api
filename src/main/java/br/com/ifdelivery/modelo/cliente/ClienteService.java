package br.com.ifdelivery.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifdelivery.modelo.usuario.UsuarioRepository;
import br.com.ifdelivery.modelo.usuario.UsuarioService;

@Service
public class ClienteService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente save(Cliente cliente) {
		usuarioService.save(cliente.getUsuario());
		
		return clienteRepository.save(cliente);
		
	}

}
