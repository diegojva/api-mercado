package api.mercado.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoClienteDTO {

    private Long id;
    private Double cantidad;
    private Double total;
    private String fecha_pedido;
    private String estado;
}
