package TPI.TPI.Repository;

import TPI.TPI.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductosRepositorio extends JpaRepository<Productos, Integer>{
@Query("select p from Productos p where p.estado = true")
public List<Productos> findAll();
    @Query("select p from Productos p where p.id_Productos = ?1")
    public Productos buscar(Integer id);

    //Consulta recupera el número de productos activos
    @Query("SELECT COUNT(p.id_Productos) FROM Productos p WHERE p.estado = true")
    Long countByEstadoProducto();

    //Consulta recupera el producto con mas pedidos o mas vendido
    @Query(value = "SELECT p.nombre FROM productos p INNER JOIN pedidos pe ON p.id_productos = pe.id_producto WHERE pe.estado_pedidos = 'ENTREGADO' GROUP BY p.nombre ORDER BY SUM(pe.cantidad) DESC, COUNT(*) DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    String productoMasVendido();

}
