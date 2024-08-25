package br.com.ifdelivery.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class RestauranteException extends RuntimeException {

    public static final String MSG_RESTAURANTE_NOT_FOUND = "Restaurante não encontrado";
    public static final String MSG_ERROR_SAVING = "Erro ao salvar restaurante";
    public static final String MSG_ERROR_LISTING = "Erro ao listar restaurantes";
    public static final String MSG_ERROR_UPDATING = "Erro ao atualizar restaurante";
    public static final String MSG_ERROR_DELETING = "Erro ao deletar restaurante";
    public static final String MSG_ERROR_FINDING_BY_USER_ID = "Erro ao buscar restaurante por usuarioId";

    public RestauranteException(String message) {
        super(message); // Simplified constructor
    }
}