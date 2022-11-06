package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pedidos")
@Data
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Pedido;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos id_producto;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes id_persona;
}
