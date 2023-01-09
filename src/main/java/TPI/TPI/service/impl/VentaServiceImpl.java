package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.Entity.Ventas;
import TPI.TPI.dao.api.VentaDaoAPI;
import TPI.TPI.service.api.VentaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl extends GenericServiceImpl<Ventas, Integer> implements VentaServiceAPI {
    @Autowired
    VentaDaoAPI ventaDaoAPI;


    @Override
    public CrudRepository<Ventas, Integer> getDao() {
        return ventaDaoAPI;
    }
}
