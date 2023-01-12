package TPI.TPI.Controller;

import TPI.TPI.Entity.Ventas;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import TPI.TPI.dao.api.PedidosDaoAPI;
import TPI.TPI.dao.api.VentaDaoAPI;
import TPI.TPI.service.api.PedidosServiceApi;
import TPI.TPI.service.api.VentaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/dashboard/ventas")
public class VentasController {
    @Autowired
    VentaServiceAPI ventaServiceAPI;
    @Autowired
    VentaDaoAPI ventaDaoAPI;
    @Autowired
    PedidosServiceApi pedidosServiceApi;

    @GetMapping
    public String TodasLasVentasActivasNoEntregadas(Model model){
        ArrayList<Ventas> mostrarVentas = new ArrayList<>(ventaDaoAPI.obtenerVentasActivasNoEntregadas());
        model.addAttribute("ventas",mostrarVentas );
        return "dashboard/ventas";
    }

    @GetMapping("/id")
    public String getDatabyId(Model model,  @RequestParam  int id){
        Ventas venta = ventaServiceAPI.get(id);
        model.addAttribute("venta", venta);
        return "dashboard/detalleVenta";
    }

    @GetMapping  ("/update/id")
    public String updateVenta(Model model, @RequestParam int id){
        ventaServiceAPI.modificarEntregadoPorId(true,id);
        pedidosServiceApi.CambiarEstadoPorIdFactura(EstadoPedidos.ENTREGADO,id);
        return "redirect:/dashboard/ventas";
    }



}
