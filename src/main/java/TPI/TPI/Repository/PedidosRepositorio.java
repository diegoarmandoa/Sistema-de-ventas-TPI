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

}
