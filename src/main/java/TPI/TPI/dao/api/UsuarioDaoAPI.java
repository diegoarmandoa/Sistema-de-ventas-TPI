package TPI.TPI.dao.api;

import TPI.TPI.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioDaoAPI extends JpaRepository<Usuarios,Long> {

    Usuarios findByUsuario(String usuario);


}
