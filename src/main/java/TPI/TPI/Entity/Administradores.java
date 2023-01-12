package TPI.TPI.Entity;

import TPI.TPI.Enumeraciones.Rol;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "administradores")
@Data

public class Administradores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdministrador;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private Rol rol ;


    @OneToMany (mappedBy = "administrador", cascade = CascadeType.ALL)
    private Collection<Usuarios> usuarios;

    public Administradores(Rol rol) {
        this.rol = rol;
    }

    public Administradores() {

    }
}
