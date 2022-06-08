package api.mercado.app.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pago_contra_entrega")
@Entity
public class PagoContraEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="precio", nullable = false)
    private Double montoAPagar;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=10, max=80, message ="el nombre completo debe tener 10 caracteres como minímo y máximo de 80")
    @Column(name="nombres", nullable = false, length =  80)
    private String nombreCompleto;

}
