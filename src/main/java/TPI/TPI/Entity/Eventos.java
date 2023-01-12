package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="eventos")
@Data
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;
    @Column
    private Integer horaInicio;
    private Integer horaCierre;
}
