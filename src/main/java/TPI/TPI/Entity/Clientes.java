package TPI.TPI.Entity;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
@Data
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "latitud")
    private double latitud;
    @Column (name ="longitud")
    private double longitud;
    @Column
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Personas id_persona;

    public Clientes() {

    }
}
