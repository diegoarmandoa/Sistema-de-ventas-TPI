package TPI.TPI.Repository;

import TPI.TPI.Entity.Personas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository <Personas, Integer> {

}
