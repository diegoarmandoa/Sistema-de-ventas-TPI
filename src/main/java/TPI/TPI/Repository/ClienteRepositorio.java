package TPI.TPI.Repository;

import TPI.TPI.Entity.Clientes;
import TPI.TPI.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepositorio extends JpaRepository<Clientes, Integer> {
    @Query("SELECT u FROM Clientes u where u.id_persona.id = ?1")
    public Clientes buscarCliente(Integer id);

    //Consulta recupera el número de clientes activos
    @Query("SELECT COUNT(c.id_cliente) as cliente FROM Clientes c WHERE c.estado = true")
    Long countByEstadoCliente(Boolean estado);
}
