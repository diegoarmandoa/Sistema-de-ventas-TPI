package TPI.TPI.Controller;

import TPI.TPI.Commons.ApplicationContextHolder;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import TPI.TPI.dao.api.PedidosDaoAPI;
import TPI.TPI.service.api.PedidosServiceApi;
import TPI.TPI.service.api.VentaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("dashboard/pedidos")
public class PedidosController {
    @Autowired
    PedidosDaoAPI pedidosDaoAPI;
    @Autowired
    PedidosServiceApi pedidosServiceApi;

    @Autowired
    VentaServiceAPI ventaServiceAPI;



    @GetMapping()
    public String vistaPedidosPreparacion(Model model) {

        model.addAttribute("listaPedidos",pedidosServiceApi.getAllQuery(EstadoPedidos.PREPARACION));

        return "dashboard/pedidios";
    }

    @GetMapping("/estado/listo")
    public String SetpedidoListo(@RequestParam Integer id) {
        pedidosServiceApi.setEstadoPedido(EstadoPedidos.LISTO,id);
        return "redirect:/dashboard/pedidos";
    }
    @GetMapping("/listo")
    public String vistaPedidosListo(Model model) {
        model.addAttribute("listaPedidos",pedidosServiceApi.getAllQuery(EstadoPedidos.LISTO));
        return "dashboard/pedidiosListo";
    }

    @GetMapping("/estado/entrega")
    public String SetpedidoEntrega(@RequestParam Integer id) {
        ventaServiceAPI.modificarEstadoPorId(true,id);
        pedidosServiceApi.CambiarEstadoPorIdFactura(EstadoPedidos.ENTREGA,id);
        return "redirect:/dashboard/pedidos/listo";
    }



}
