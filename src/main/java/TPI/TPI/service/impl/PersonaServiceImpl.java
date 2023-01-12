package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.Entity.Personas;
import TPI.TPI.dao.api.PersonaDaoAPI;
import TPI.TPI.service.api.PersonaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Personas,Integer>  implements PersonaServiceAPI {
    @Autowired
    PersonaDaoAPI personaDaoAPI;


    @Override
    public CrudRepository<Personas, Integer> getDao() {
        return personaDaoAPI;
    }

    @Transactional
    @Override
    public void usuarioSetEstado(Boolean estado, Integer id) {
        personaDaoAPI.updateEstado(id,estado);
    }
}
