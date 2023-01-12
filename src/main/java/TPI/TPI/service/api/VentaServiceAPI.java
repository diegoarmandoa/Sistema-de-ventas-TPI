package TPI.TPI.service.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.Entity.Ventas;

import java.util.ArrayList;
import java.util.List;

public interface VentaServiceAPI extends GenericServiceAPI<Ventas, Integer> {
    Integer obtenerUltimoID();
    void modificarEstadoPorId(Boolean estado, Integer ID);
}
