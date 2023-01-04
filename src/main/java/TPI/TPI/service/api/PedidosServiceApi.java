package TPI.TPI.service.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.Entity.Pedidos;
import TPI.TPI.Enumeraciones.EstadoPedidos;

import java.util.ArrayList;

public interface PedidosServiceApi extends GenericServiceAPI<Pedidos,Integer> {
    ArrayList<Pedidos> getAllQuery(EstadoPedidos x);

    void setEstadoPedido(EstadoPedidos estado, Integer id);

}
