package TPI.TPI.Entity;

import lombok.Data;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;

@Entity
@Table(name = "personas")
@Data
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "estado")
    private Boolean estado;


}
