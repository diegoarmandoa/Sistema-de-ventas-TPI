package TPI.TPI.service.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.Entity.Ventas;
import io.swagger.models.auth.In;

import java.util.Optional;

public interface VentaServiceAPI extends GenericServiceAPI<Ventas, Integer> {
    Integer obtenerUltimoID();
}
