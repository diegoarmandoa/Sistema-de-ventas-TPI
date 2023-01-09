package TPI.TPI.dao.api;

import TPI.TPI.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaDaoAPI extends JpaRepository<Ventas, Integer> {
    //querys
    @Query(value = "select v.id  from ventas v order by v.id desc limit 1;" , nativeQuery = true)
    Integer ultimoID ();
}
