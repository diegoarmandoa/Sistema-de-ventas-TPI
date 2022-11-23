package TPI.TPI.service.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.Entity.Usuarios;

public interface UsuarioServiceAPI extends GenericServiceAPI<Usuarios,Long> {
    //Esta interface aloja los metodos de la interface GenericServiceAPI y se le pasa la clase
    //de la entidad en este caso usuario,
}
