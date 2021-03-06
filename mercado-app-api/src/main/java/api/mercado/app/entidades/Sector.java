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
@Table(name="sectores")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=30, message ="nombre debe tener 3 caracteres como minímo y máximo de 30")
    @Column (name="nombres", nullable = false, length =  30, unique = true)
    private String nombre;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=10, max=150, message ="descripcion debe tener 10 caracteres como minímo y máximo de 150")
    @Column (name="descripcion", nullable = false, length =  150)
    private String descripcion;

}