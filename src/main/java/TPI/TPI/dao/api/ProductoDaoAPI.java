package TPI.TPI.dao.api;

import TPI.TPI.Entity.Productos;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDaoAPI extends CrudRepository<Productos, Integer> {
}
