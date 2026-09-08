package springboot.ejs.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(
            RecursoNoEncontradoException ex,
            WebRequest request) {

        logger.warn("Recurso no encontrado: {}", ex.getMessage());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("estado", HttpStatus.NOT_FOUND.value());
        respuesta.put("ruta", request.getDescription(false));

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepcionGlobal(
            Exception ex,
            WebRequest request) {

        logger.error("Error inesperado del sistema", ex);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDateTime.now());
        respuesta.put("mensaje",
                "Ocurrió un error interno en el servidor");
        respuesta.put("estado",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(
                respuesta,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

