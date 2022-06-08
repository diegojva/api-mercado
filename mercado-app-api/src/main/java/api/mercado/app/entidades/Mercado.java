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
@Table(name="mercados")
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=30, message ="el nombre del mercado debe tener 3 caracteres como minímo y máximo de 30")
    @Column(name="nombres", nullable = false, length =  30, unique = true)
    private String nombre;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=30, message ="el distrito debe tener 3 caracteres como minímo y máximo de 30")
    @Column(name="distritos", nullable = false, length =  30)
    private String distrito;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=5, max=70, message ="el distrito debe tener 5 caracteres como minímo y máximo de 70")
    @Column(name="ubicaciones", nullable = false, length =  70)
    private String ubicacion;

}
