package TPI.TPI.Entity;

import TPI.TPI.Enumeraciones.Rol;
import lombok.Data;

import javax.persistence.*;

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

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Personas persona;
}
