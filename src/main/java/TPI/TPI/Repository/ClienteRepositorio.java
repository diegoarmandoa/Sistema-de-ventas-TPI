package TPI.TPI.Repository;

import TPI.TPI.Entity.Clientes;
import TPI.TPI.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ClienteRepositorio extends JpaRepository<Clientes, Integer> {
    @Query("SELECT u FROM Clientes u where u.id_persona.id = ?1")
    public Clientes buscarCliente(Integer id);

    //Consulta recupera el número de clientes activos
    @Query("SELECT COUNT(c.id_cliente) as cliente FROM Clientes c WHERE c.estado = true")
    Long countByEstadoCliente(Boolean estado);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Clientes c set c.latitud = :la, c.longitud = :lo where c.id_cliente = :id")
    void updateLatitudYLongitud(@Param(value = "la") Double latitud,
                                @Param(value = "lo") Double longitud, @Param(value = "id")Integer id);
}
