package br.com.gian418.customersuccessbalancing.resources.exceptions;

import br.com.gian418.customersuccessbalancing.services.exceptions.MaximoGenretesIndisponiveisException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExcpetionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validacao", System.currentTimeMillis());

        for(FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MaximoGenretesIndisponiveisException.class)
    public ResponseEntity<StandardError> maximoGerentesIndisponiveis(MaximoGenretesIndisponiveisException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
