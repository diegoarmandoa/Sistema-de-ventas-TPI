package TPI.TPI.Entity;

import TPI.TPI.Enumeraciones.Rol;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Data

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Usuario;

    @Column
    private String usuario;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Personas id_persona;
}
