package api.mercado.app.utilidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetalles<T> {

    private boolean ok;
    private String message;
    private T body;

    public ResponseEntity<ErrorDetalles<T>> createResponse(HttpStatus status){
        return new ResponseEntity<>(this,status);
    }
}
