package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.Entity.Pedidos;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import TPI.TPI.dao.api.PedidosDaoAPI;
import TPI.TPI.service.api.PedidosServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class PedidosServiceImpl  extends GenericServiceImpl<Pedidos,Integer>  implements PedidosServiceApi {
    @Autowired
    PedidosDaoAPI pedidosDaoAPI;
    @Override
    public CrudRepository<Pedidos, Integer> getDao() {
        return pedidosDaoAPI;
    }

    public ArrayList<Pedidos> getAllQuery(EstadoPedidos x) {
        return (ArrayList<Pedidos>) pedidosDaoAPI.getAllByEstadoPedidosEquals(x);
    }

    @Override
    @Transactional
    public void setEstadoPedido(EstadoPedidos estado, Integer id) {
       pedidosDaoAPI.updateEstado(estado,id);
    }

    @Override
    @Transactional
    public void CambiarEstadoPorIdFactura(EstadoPedidos estado, Integer id) {
        pedidosDaoAPI.ActualizarEstadoPorIdFactura(estado,id);
    }
}
