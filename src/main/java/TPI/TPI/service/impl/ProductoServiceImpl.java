package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.Entity.Productos;
import TPI.TPI.Repository.ProductosRepositorio;
import TPI.TPI.dao.api.ProductoDaoAPI;
import TPI.TPI.service.api.ProductoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Productos, Integer> implements ProductoServiceAPI {
   @Autowired
    private ProductoDaoAPI productoDaoAPI;

    @Override
    public CrudRepository<Productos, Integer> getDao() {
        return productoDaoAPI;
    }

}
