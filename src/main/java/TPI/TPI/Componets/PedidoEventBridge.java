package TPI.TPI.Componets;

import TPI.TPI.Entity.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Component
public
class PedidoEventBridge {
   // private final SseEmitter dataEvent;
   private final SseEmitter emitter;

   @Autowired
    PedidoEventBridge(SseEmitter x) {
        this.emitter = x;
    }


    public void sendEvent(Pedidos p) {
        try {
            emitter.send(p.toString());
        } catch (IOException e) {
// Handle the exception
        }
    }

    public SseEmitter getEmitter() {
        return emitter;
    }
}
