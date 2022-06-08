package api.mercado.app.dto;

import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtencionPedidoDTO {

    private Long id;
    private Cliente cliente;
    private String fechaPedido;
    private Double total;
    private String estado;
}
