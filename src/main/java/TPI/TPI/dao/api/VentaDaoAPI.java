package TPI.TPI.dao.api;

import TPI.TPI.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDaoAPI extends JpaRepository<Ventas, Integer> {
    //querys
}
