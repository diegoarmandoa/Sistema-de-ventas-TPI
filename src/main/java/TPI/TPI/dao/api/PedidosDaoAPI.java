package TPI.TPI.dao.api;

import TPI.TPI.Entity.Pedidos;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PedidosDaoAPI extends JpaRepository<Pedidos,Integer> {
    Collection<Pedidos> getAllByEstadoPedidosEquals(EstadoPedidos e);
    @Modifying(clearAutomatically = true)
    @Query("update Pedidos p set p.estadoPedidos =:estadoE where p.id_Pedido =:id")
    void updateEstado(@Param(value ="estadoE") EstadoPedidos estado, @Param(value ="id")Integer id);

    @Modifying(clearAutomatically = true)
    @Query("update Pedidos p set p.estadoPedidos =:estadoE where p.venta.id =:id")
    void ActualizarEstadoPorIdFactura(@Param(value ="estadoE") EstadoPedidos estado, @Param(value ="id")Integer id);

}
