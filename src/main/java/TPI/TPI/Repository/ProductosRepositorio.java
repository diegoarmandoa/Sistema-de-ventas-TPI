package TPI.TPI.Repository;

import TPI.TPI.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosRepositorio extends JpaRepository<Productos, Integer>{
@Query("select p from Productos p where p.estado = true")
public List<Productos> findAll();
    @Query("select p from Productos p where p.id_Productos = ?1")
    public Productos buscar(Integer id);

    //@Query("SELECT COUNT(id_productos) as producto\n" +
    //        "FROM productos\n" +
    //        "WHERE estado = true")
   // public List<Productos> productosActivos();
}
