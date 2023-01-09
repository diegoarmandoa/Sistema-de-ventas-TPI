package TPI.TPI.Controller;

import TPI.TPI.Entity.Productos;
import TPI.TPI.Repository.ClienteRepositorio;
import TPI.TPI.Repository.PedidosRepositorio;
import TPI.TPI.Repository.ProductosRepositorio;
import TPI.TPI.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class DashboardController {
    @Autowired
    private ProductosRepositorio productosRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidosRepositorio pedidosRepositorio;

    @RequestMapping("/dashboard/home")
    public String inicio(){
        return "dashboard/index.html";
    }

    @GetMapping("/dashboard/home")
    public String infoHome(Model model){
        //Productos Activos
        Long countProducto = productosRepositorio.countByEstadoProducto();
        model.addAttribute("countProductos", countProducto);

        //Clientes Activos
        Long countCliente = clienteRepositorio.countByEstadoCliente(true);
        model.addAttribute("countClientes", countCliente);

        //Pedidos en preparacion
        Long countPedidoPendiente = pedidosRepositorio.countByEstadoPedido();
        model.addAttribute("countPedidoPendiente", countPedidoPendiente);
        /*
        String pedidos = pedidosRepositorio.productoVendido("PREPARACION");
        model.addAttribute("pedidos", pedidos);
*/
        //Producto mas vendido
        String producto = productosRepositorio.productoMasVendido();
        model.addAttribute("producto", producto);

        return "dashboard/index.html";
    }

}
