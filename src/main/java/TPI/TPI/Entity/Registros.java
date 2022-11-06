package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "registros")
@Data
public class Registros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_registro;

    @Column(name = "total")
    private Float total;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Ventas id_venta;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedidos id_pedido;
}
