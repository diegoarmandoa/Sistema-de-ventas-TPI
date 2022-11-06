package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Data

public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Usuario;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Personas id_persona;
}
