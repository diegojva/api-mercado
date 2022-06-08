package api.mercado.app.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=30, message ="nombre debe tener 3 caracteres como minímo y máximo de 30")
    @Column(name="nombres", nullable = false, length =  30)
    private String nombre;

    @Column(name="precio", nullable = false)
    private Double precio;

    @Column(name="stock", nullable = false)
    private Double stock;

    @ManyToOne
    @JoinColumn(name = "id_sector", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_producto_sector"))
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "id_puesto", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_producto_puesto"))
    private Puesto puesto;

    @Size(min=5, max=850, message ="la dirección de la imagen debe tener 5 caracteres como minímo y máximo de 850")
    @Column(name="foto",length = 850)
    private String foto;

    @OneToOne
    @JoinColumn(name = "id_unidad_medida", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_unidad_medida_producto"))
    private UnidadMedida unidadMedida;

}