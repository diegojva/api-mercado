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
@Table(name="unidades_medidas")
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=1, max=5, message ="La unidad de medida debe tener al menos 2 digitos y máximo de 5")
    @Column(name="unidad_medida", nullable = false, length =  20)
    private String abreviatura;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=50, message ="descripcion debe tener 3 caracteres como minímo y máximo de 50")
    @Column (name="descripcion", nullable = false, length =  50)
    private String descripcion;

}
