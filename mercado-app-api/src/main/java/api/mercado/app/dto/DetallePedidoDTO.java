package api.mercado.app.dto;

import api.mercado.app.entidades.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

        private Long id;
        private Double precio;
        private Double cantidad;
        private Double total;
        private Producto producto;

}
