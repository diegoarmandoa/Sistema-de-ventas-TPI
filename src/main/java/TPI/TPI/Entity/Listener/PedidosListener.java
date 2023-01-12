package TPI.TPI.Entity.Listener;
import TPI.TPI.Entity.Pedidos;
import javax.persistence.PostPersist;

public class PedidosListener {

    @PostPersist
    void  SendData(Pedidos pedidos) {
    }
}
