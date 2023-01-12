package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ventas")
@Data
public class Ventas {
    @Id
    private Integer id;

    @Column(name = "estado", columnDefinition = "boolean  default false")
    private boolean estado;

    @Column(name = "entregado",columnDefinition = "boolean  default false")
    private boolean entregado;

    @Column(name = "total")
    private Float total ;

    @Column(name = "fecha", columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente")
    private Clientes cliente ;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Collection<Pedidos> pedidos;

}
