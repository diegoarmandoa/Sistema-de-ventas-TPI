package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "administradores")
@Data

public class Administradores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_administrador;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Personas id_persona;
}
