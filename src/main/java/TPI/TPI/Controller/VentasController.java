package TPI.TPI.Controller;

import TPI.TPI.service.api.VentaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/dashboard/ventas")
public class VentasController {
    @Autowired
    VentaServiceAPI ventaServiceAPI;

    @GetMapping("/pedidoListo")
    String ObtenerVentasPorPedidosListos(){
        ventaServiceAPI.obtenerVentasConProductosListos();
        return "redirect:/login";
    }
}
