package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ventas")
@Data
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venta;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "estregado",length = 1)
    private String estregado;

    @Column(name = "precio")
    private Float precio;

}
