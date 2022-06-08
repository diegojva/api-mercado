package api.mercado.app.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3, max=15,message ="username debe tener 3 caracteres como minímo y máximo de 15")
    @Column(name="username", length = 15, nullable = false, unique = true)
    private String username;

    @Size(min=8, message ="password debe tener 8 caracteres como minímo")
    @Column(name="password", length = 250, nullable = false)
    private String password;

    @Size(min=3, max=20, message ="password debe tener 8 caracteres como minímo y 20 como máximo")
    @Column(name="estado", length = 20, nullable = false)
    private String estado;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "adm_rol",
            joinColumns = @JoinColumn(name = "adm_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
    )
    private Set<Rol> roles;

    public Administrador(String username, String password, Set<Rol> roles) {
        super();
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Administrador(String username, String password, String estado, Set<Rol> roles) {
        super();
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.roles = roles;
    }
}
