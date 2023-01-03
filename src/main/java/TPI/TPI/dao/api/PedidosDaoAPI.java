package TPI.TPI.dao.api;

import TPI.TPI.Entity.Pedidos;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PedidosDaoAPI extends JpaRepository<Pedidos,Integer> {
    Collection<Pedidos> getAllByEstadoPedidosEquals(EstadoPedidos e);
}
