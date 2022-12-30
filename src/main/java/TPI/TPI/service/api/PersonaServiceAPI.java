package TPI.TPI.service.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.Entity.Personas;

public interface PersonaServiceAPI extends GenericServiceAPI<Personas,Integer> {
    void usuarioSetEstado(Boolean estado, Integer id);
}
