package TPI.TPI.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "eventos")
@Data
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;
    @Column(columnDefinition = "TIME")
    private LocalTime horaInicio;
    @Column(columnDefinition = "TIME")
    private LocalTime horaCierre;
}
