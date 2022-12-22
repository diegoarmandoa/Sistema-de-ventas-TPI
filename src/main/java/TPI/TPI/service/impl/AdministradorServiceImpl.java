package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.Entity.Administradores;
import TPI.TPI.Entity.Productos;
import TPI.TPI.dao.api.AdministradorDaoAPI;
import TPI.TPI.service.api.AdministradorServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiceImpl extends GenericServiceImpl<Administradores, Integer> implements AdministradorServiceAPI {
    @Autowired
    AdministradorDaoAPI administradorDaoAPI;

    @Override
    public CrudRepository<Administradores, Integer> getDao() {
        return administradorDaoAPI;
    }

}
