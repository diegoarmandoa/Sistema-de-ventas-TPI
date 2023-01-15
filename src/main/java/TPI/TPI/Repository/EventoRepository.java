package TPI.TPI.Repository;

import TPI.TPI.Entity.Eventos;
import TPI.TPI.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.sql.Time;
@EnableJpaRepositories
public interface EventoRepository extends JpaRepository<Eventos,Integer> {
    @Query(value = "SELECT date_part('hour', current_time)",nativeQuery = true)
    public BigDecimal hourServidor();
    
}
