package br.com.ifdelivery.modelo.restaurante;

import br.com.ifdelivery.modelo.acesso.UsuarioRepository;
import br.com.ifdelivery.modelo.acesso.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioService usuarioService;

    public RestauranteService(RestauranteRepository restauranteRepository, UsuarioService usuarioService) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public Restaurante save(Restaurante restaurante) {
        usuarioService.save(restaurante.getUsuario());
        restaurante.setHabilitado(Boolean.TRUE);
        restaurante.setVersao(1L);
        restaurante.setDataCriacao(LocalDate.now());
        return restauranteRepository.save(restaurante);

    }

    public List<Restaurante> listarTodos() {

        return restauranteRepository.findAll();
    }
    public Restaurante obterPorID(Long id) {

        return restauranteRepository.findById(id).get();
    }

    @Transactional
    public void update(long id, Restaurante restauranteAlterado){

        Restaurante restaurante = restauranteRepository.findById(id).get();
        restaurante.setNomeFantasia(restauranteAlterado.getNomeFantasia());
        restaurante.setRazaoSocial(restauranteAlterado.getRazaoSocial());
        restaurante.setCnpj(restauranteAlterado.getCnpj());
        restaurante.setCep(restauranteAlterado.getCep());
        restaurante.setEstado(restauranteAlterado.getEstado());
        restaurante.setBairro(restauranteAlterado.getBairro());
        restaurante.setRua(restauranteAlterado.getRua());
        restaurante.setNumero(restauranteAlterado.getNumero());
        restaurante.setComplemento(restauranteAlterado.getComplemento());

        restauranteRepository.save(restaurante);
    }

    @Transactional
    public void delete(Long id) {

        Restaurante restaurante = restauranteRepository.findById(id).get();
        restaurante.setHabilitado(Boolean.FALSE);
        restaurante.setVersao(restaurante.getVersao() + 1);

        restauranteRepository.save(restaurante);
    }
}
