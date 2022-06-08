package api.mercado.app.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ordenes")
@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name="date_created", nullable = false, updatable = false)
    private LocalDateTime fecha_creacion;

    @Column(name="total", nullable = false)
    private Double total;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=5, max=30, message ="estado debe tener 10 caracteres como minímo y máximo de 30")
    @Column (name="estado", length =  150)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_orden_cliente"))
    private Cliente cliente;

    @Column(nullable = false)
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrdenDetalle> items;

    @OneToOne
    @JoinColumn(name = "id_pago_contra_entrega", nullable = false,
            foreignKey = @ForeignKey(name = "Fk_pago_contra_entrega_orden"))
    private PagoContraEntrega pagoContraEntrega;

    @ManyToOne
    @JoinColumn(name = "id_despachador",
            foreignKey = @ForeignKey(name = "Fk_despachador_orden"))
    private Despachador despachador;

}
