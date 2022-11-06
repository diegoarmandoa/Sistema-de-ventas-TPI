package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="productos")
@Data

public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Productos;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Float precio;

    //@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "url_imagen")
    private String url_imagen;

    @Column(name = "estado")
    private Boolean estado;



}
