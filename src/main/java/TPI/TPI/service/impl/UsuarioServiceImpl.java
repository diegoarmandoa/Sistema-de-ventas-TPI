package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.Entity.Usuarios;
import TPI.TPI.dao.api.UsuarioDaoAPI;
import TPI.TPI.service.api.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuarios,Long>  implements UsuarioServiceAPI {
   @Autowired
    private UsuarioDaoAPI usuarioDaoAPI;
    @Override
    public CrudRepository<Usuarios, Long> getDao() {
        return usuarioDaoAPI;
    }
}
