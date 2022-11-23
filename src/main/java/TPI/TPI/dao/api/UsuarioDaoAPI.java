package TPI.TPI.dao.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.Entity.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDaoAPI extends CrudRepository<Usuarios,Long> {

}
