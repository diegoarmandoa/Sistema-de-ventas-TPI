package TPI.TPI.Repository;

import TPI.TPI.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer> {
    @Query("SELECT u FROM Usuarios u where u.usuario = ?1")
    public Usuarios buscarUsuario(String usuario);


}
