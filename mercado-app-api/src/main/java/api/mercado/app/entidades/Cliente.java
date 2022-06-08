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
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=5, max=60,message ="password debe tener 5 caracteres como minímo y máximo de 30")
    @Column(name="email", length = 30, nullable = false, unique = true)
    private String email;

    @Size(min=8, message ="password debe tener 8 caracteres como minímo")
    @Column(name="password", length = 250, nullable = false)
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=30, message ="el nombre debe tener 3 caracteres como minímo y máximo de 30")
    @Column(name="nombres", nullable = false, length =  30)
    private String nombre;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3, max=60, message ="los apellidos debe tener 3 caracteres como minímo y máximo de 60")
    @Column(name="apellidos", nullable = false, length =  60)
    private String apellido;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=9, max=9, message ="el telefono debe tener 9 caracteres")
    @Column(name="telefonos", nullable = false, length =  9)
    private String telefono;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=5, max=120, message ="el distrito debe tener 5 caracteres como minímo y máximo 120")
    @Column(name="direcciones", nullable = false, length =  120)
    private String direccion;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "cliente_rol",
            joinColumns = @JoinColumn(name = "cliente_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
    )
    private Set<Rol> roles;

    public Cliente(String username, String password, String nombre, String apellido, String telefono, String direccion, Set<Rol> roles) {
        super();
        this.email = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.roles = roles;
    }

}
