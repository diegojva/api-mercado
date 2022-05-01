package api.mercado.app.configuracion;


import api.mercado.app.excepciones.GeneralServiceException;
import api.mercado.app.excepciones.IncorrectResourceRequestException;
import api.mercado.app.excepciones.ResourceNotFoundException;
import api.mercado.app.utilidades.ErrorDetalles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;


@ControllerAdvice
@RestController
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getAllErrors().stream().map(e -> {
            return e.getDefaultMessage().toString().concat(", ");
        }).collect(Collectors.joining());
        ErrorDetalles errorDetails = new ErrorDetalles<>(false, message, null);
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles<?>> all(Exception e, WebRequest request){
        ErrorDetalles<?> response = new ErrorDetalles<>(false, "Internal Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectResourceRequestException.class)
    public ResponseEntity<ErrorDetalles<?> > validateServiceException(IncorrectResourceRequestException e, WebRequest request){
        ErrorDetalles<?> response = new ErrorDetalles<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles<?> > noDataFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorDetalles<?> response = new ErrorDetalles<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<ErrorDetalles<?> > generalServiceException(GeneralServiceException e, WebRequest request){
        ErrorDetalles<?> response = new ErrorDetalles<>(false, "Internal Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}