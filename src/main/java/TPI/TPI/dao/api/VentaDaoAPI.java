package TPI.TPI.dao.api;

import TPI.TPI.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface VentaDaoAPI extends JpaRepository<Ventas, Integer> {
    //querys
    @Query(value = "select v.id  from ventas v order by v.id desc limit 1;" , nativeQuery = true)
    Integer ultimoID ();

    @Modifying(clearAutomatically = true)
    @Query("update Ventas v set v.estado =:estado where  v.id =:id ")
    void modificarEstadoPorId(@Param(value = "estado") boolean estado, @Param(value = "id") Integer id);

    @Query("select v from Ventas v where v.estado = true and v.entregado = false order by v.fecha asc ")
    Collection<Ventas> obtenerVentasActivasNoEntregadas();
}
