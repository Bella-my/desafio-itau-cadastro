package br.com.ana.desafioitau.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErrosDeValidacao(MethodArgumentNotValidException exception) {
        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(erro -> {
            erros.put(erro.getField(), erro.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeLeituraDoJson(HttpMessageNotReadableException exception) {
        Map<String, String> erro = new HashMap<>();
        String mensagem = exception.getMessage();

        if (mensagem != null && mensagem.contains("dataNascimento")) {
            erro.put("dataNascimento", "Data de nascimento deve ser uma data real no formato yyyy-MM-dd");
        } else {
            erro.put("erro", "JSON inválido ou com formato incorreto.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarRegraDeNegocio(IllegalArgumentException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeLogin(IllegalStateException exception) {

        Map<String, String> erro = new HashMap<>();

        erro.put("erro", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
