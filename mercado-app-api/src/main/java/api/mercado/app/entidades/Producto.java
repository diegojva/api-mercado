package api.mercado.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private double precio;


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

    @OneToOne
    @JoinColumn(name = "id_unidad_medida", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_unidad_medida_producto"))
    private UnidadMedida unidadMedida;


}