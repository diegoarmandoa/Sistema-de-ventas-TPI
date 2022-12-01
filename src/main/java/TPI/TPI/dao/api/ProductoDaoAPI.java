package TPI.TPI.dao.api;

import TPI.TPI.Entity.Productos;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDaoAPI extends CrudRepository<Productos, Long> {
}
