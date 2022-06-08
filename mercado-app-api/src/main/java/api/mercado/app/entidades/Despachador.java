package api.mercado.app.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="despachador")
public class Despachador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=5, max=30,message ="password debe tener 5 caracteres como minímo y máximo de 30")
    @Column(name="username", length = 30, nullable = false, unique = true)
    private String username;

    @Size(min=8, message ="password debe tener 8 caracteres como minímo")
    @Column(name="password", length = 250, nullable = false)
    private String password;

    @Size(min=5, max=20, message ="estado debe tener 5 caracteres como minímo y máximo de 20")
    @Column(name="estado", length = 20)
    private String estado;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=2, max=25, message ="nombre debe tener 2 caracteres como minímo y máximo de 25")
    @Column (name="nombres", nullable = false, length =  25)
    private String nombre;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=5, max=50, message ="nombre debe tener 5 caracteres como minímo y máximo de 50")
    @Column (name="apellidos", nullable = false, length =  50)
    private String apellido;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=8, message="DNI debe tener 8 digitos")
    @Column (name="dni", nullable = false, length =  8, unique = true)
    private String dni;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "despachador_roles",
            joinColumns = @JoinColumn(name = "despachador_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
    )
    private Set<Rol> roles;

    public Despachador(String username, String password, String nombre, String apellido, String dni, Set<Rol> roles) {
        super();
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.dni= dni;
        this.roles = roles;
    }

}
