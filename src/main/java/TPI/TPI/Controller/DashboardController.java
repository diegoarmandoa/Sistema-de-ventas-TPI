package TPI.TPI.Controller;


import TPI.TPI.Entity.Eventos;
import TPI.TPI.Repository.ClienteRepositorio;
import TPI.TPI.Repository.EventoRepository;
import TPI.TPI.Repository.PedidosRepositorio;
import TPI.TPI.Repository.ProductosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller

public class DashboardController {
    @Autowired
    private ProductosRepositorio productosRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidosRepositorio pedidosRepositorio;
    @Autowired
    EventoRepository eventoRepository;

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

      //  String pedidos = pedidosRepositorio.productoVendido("PREPARACION");
        //model.addAttribute("pedidos", pedidos);

        //Producto mas vendido
        String producto = productosRepositorio.productoMasVendido();
        model.addAttribute("producto", producto);


        Optional<List<Eventos>> obj = Optional.ofNullable(eventoRepository.findAll());
        Eventos eventos;
        if (obj.isPresent() && obj.get().size() > 0) eventos = obj.get().get(0);
        else eventos = new Eventos();
        model.addAttribute("eventos", eventos);
        return "dashboard/index";
    }

    @PostMapping("/dashboard/Configuracion")
    public String configuracion(Eventos eventos){
        eventoRepository.save(eventos);
        return "redirect:/dashboard/home";
    }

}
