package TPI.TPI.Repository;

import TPI.TPI.Entity.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidosRepositorio extends JpaRepository<Pedidos, Integer> {

}
