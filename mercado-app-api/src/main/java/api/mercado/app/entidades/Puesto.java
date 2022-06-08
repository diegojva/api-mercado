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
@Table(name="puestos")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=20, message ="nombre debe tener 3 caracteres como minímo y máximo de 20")
    @Column (name="nombres", nullable = false, length =  20, unique=true)
    private String nombre;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=10, max=150, message ="descripcion debe tener 10 caracteres como minímo y máximo de 150")
    @Column (name="descripcion", nullable = false, length =  150)
    private String descripcion;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=5, max=30, message ="estado debe tener 10 caracteres como minímo y máximo de 30")
    @Column (name="estado", length =  150)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_puesto_vendedor"))
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "id_sector", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_productos_sector"))
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "id_mercado",
            foreignKey = @ForeignKey(name = "Fk_puestos_mercado"))
    private Mercado mercado;

}
