package TPI.TPI.dao.api;

import TPI.TPI.Entity.Personas;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonaDaoAPI extends CrudRepository<Personas, Integer> {
    @Modifying(clearAutomatically = true)
    @Query("update Personas p set p.estado = :estado where p.id = :id")
    void updateEstado(@Param(value = "id") Integer id, @Param(value = "estado") Boolean estado);
}
