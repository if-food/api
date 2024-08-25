package br.com.ifdelivery.modelo.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    //obter restaurante por id de usuario

    Restaurante findByUsuarioId(Long id);
}
