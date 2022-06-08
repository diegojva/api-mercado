package api.mercado.app.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ordenes_detalles")
@Entity
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name="quantity", nullable = false)
    private Double quantity;

    @Column(name="total", nullable = false)
    private Double total;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "FK_order_order_details"))
    private Orden order;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_product_order_details"))
    private Producto producto;
}
