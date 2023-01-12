package TPI.TPI.Entity;

import TPI.TPI.Entity.Listener.PedidosListener;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import TPI.TPI.Enumeraciones.Rol;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(PedidosListener.class)
@Entity
@Table(name = "pedidos")
@Data
public class Pedidos  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Pedido;

    @Column(name = "cantidad")
    private Integer cantidad;


    @Enumerated(EnumType.STRING)
    private EstadoPedidos estadoPedidos;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos id_producto;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes id_persona;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta" , nullable = false)
    private  Ventas venta ;

}
