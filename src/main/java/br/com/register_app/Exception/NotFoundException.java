package br.com.register_app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "O usuário não foi encontrado!") // erro 404
public class NotFoundException extends Exception{
    
}
