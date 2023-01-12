package TPI.TPI.Repository;

import TPI.TPI.Entity.Pedidos;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidosRepositorio extends JpaRepository<Pedidos, Integer> {
    @Query("select p from Pedidos p where (p.estadoPedidos = ?2 or p.estadoPedidos = ?3) and (p.id_persona.id_persona.id = ?1) ")
    public List<Pedidos> pedidosEnProceso(Integer id, EstadoPedidos x, EstadoPedidos y);
    @Query("select sum(p.cantidad * p.id_producto.precio) as total from Pedidos p where (p.estadoPedidos = ?2 or p.estadoPedidos = ?3) and (p.id_persona.id_persona.id = ?1) ")
    public Double pedidosEnProcesoTotal(Integer id, EstadoPedidos x, EstadoPedidos y);

    //Consulta recupera el número de pedidos pendientes
    @Query("SELECT COUNT(p.estadoPedidos) as pedido FROM Pedidos p WHERE p.estadoPedidos = 'PREPARACION'")
    Long countByEstadoPedido();

    //intento fallido
    //Consulta recupera el productos con mas unidaes vendidas o mas pedidos realizados
    /*
    @Query("SELECT p.nombre FROM productos p INNER JOIN pedidos pe ON p.id_productos = pe.id_producto WHERE pe.estado = 'ENTREGADO' GROUP BY p.nombre ORDER BY SUM(pe.cantidad) DESC, COUNT(*) DESC FETCH FIRST 1 ROW ONLY")
    String productoVendido(String estado);
     */
}
