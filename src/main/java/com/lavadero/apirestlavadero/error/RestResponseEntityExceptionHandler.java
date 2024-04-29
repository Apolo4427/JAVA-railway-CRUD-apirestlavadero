package com.lavadero.apirestlavadero.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lavadero.apirestlavadero.error.dto.ErrorMessage;

@ControllerAdvice //indicamos que esta clase manejara las exceptions glovales que puedan ocurrir en la aplicacion
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VehiculoNoFundException.class)//manejos de: (no se ha encontrado un vehiculo en la base de datos), se hace:
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> vehiculoNoFundException(VehiculoNoFundException exception){//devolvemos un ResponseEntity<ErrorMessage> con nuestra platilla creada ("ErrorMessage")
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    //sobrecargamos el metodo con el que spring boot esta manejando la excepcion de argumento no valido
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        Map<String,Object> errors= new HashMap<>();
        //NOTA: ex = exception -> de la excepcion obtenemos el resultado atravez de  getBindingResult()
        ex.getBindingResult().getFieldErrors()/*retorna una lista de los errores*/.forEach(error -> {//recorremos la lista con el forEach
            errors.put(error.getField(), error.getDefaultMessage());//por cada error en la lista agregamos un campo al Map "errors" con key=error.getField() y value=error.getDefaultMessage() (getDefaultMessage() = mensaje ubicado en el NotBlack)
        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    

}
