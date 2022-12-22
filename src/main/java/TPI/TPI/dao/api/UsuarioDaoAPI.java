package TPI.TPI.dao.api;

import TPI.TPI.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDaoAPI extends JpaRepository<Usuarios,Long> {

    Usuarios findByUsuario(String usuario);
}
