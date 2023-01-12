package TPI.TPI.dao.api;

import TPI.TPI.Entity.Administradores;
import TPI.TPI.Enumeraciones.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdministradorDaoAPI extends JpaRepository<Administradores,Integer> {
    Administradores findByRol(Rol rol);
}
