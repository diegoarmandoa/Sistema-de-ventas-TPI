package TPI.TPI.dao.api;

import TPI.TPI.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioDaoAPI extends JpaRepository<Usuarios,Long> {
    @Query("select count(id_Usuario) from Usuarios")
    Integer cantidadUsuarios();
    Usuarios findByUsuario(String usuario);
    @Modifying(clearAutomatically = true)
    @Query("update Usuarios p set p.password = :pass where p.id_Usuario = :id")
    void updatePassword(@Param(value = "pass") String password, @Param("id") Long id);


}
