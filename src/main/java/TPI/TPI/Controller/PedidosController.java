package TPI.TPI.Controller;

import TPI.TPI.Commons.ApplicationContextHolder;
import TPI.TPI.Componets.PedidoEventBridge;
import TPI.TPI.Entity.Listener.PedidosListener;
import TPI.TPI.Entity.Pedidos;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import TPI.TPI.dao.api.PedidosDaoAPI;
import TPI.TPI.service.api.PedidosServiceApi;
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

    private ExecutorService nonBlockingService = Executors
            .newCachedThreadPool();

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        /*
        SseEmitter emitter = new SseEmitter();
        nonBlockingService.execute(() -> {
            try {
                SseEmitter.SseEventBuilder se;

                emitter.send("/sse" + " @ " + new Date());
                // we could send more events
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;*/

        PedidoEventBridge eventBridge= ApplicationContextHolder.getApplicationContext().getBean(PedidoEventBridge.class);
        Optional<SseEmitter> obj= Optional.ofNullable(eventBridge.getEmitter());
        if (obj.isPresent()) return obj.get();

        SseEmitter emitter = new SseEmitter();
        nonBlockingService.execute(() -> {
            try {
                SseEmitter.SseEventBuilder se;

                emitter.send("/sse" + " @ " + new Date());
                // we could send more events
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;

    }

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
        pedidosServiceApi.setEstadoPedido(EstadoPedidos.ENTREGADO,id);
        return "redirect:/dashboard/pedidos/listo";
    }



}
