package TPI.TPI.service.api;

import TPI.TPI.Commons.GenericServiceAPI;
import TPI.TPI.DTO.UpdatePasswordDTO;
import TPI.TPI.DTO.UserDTO;
import TPI.TPI.Entity.Usuarios;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServiceAPI extends GenericServiceAPI<Usuarios,Long> , UserDetailsService {
    //Esta interface aloja los metodos de la interface GenericServiceAPI y se le pasa la clase
    //de la entidad en este caso usuario,
    Usuarios save(UserDTO userDTO);
    void setPassword(UpdatePasswordDTO updatePasswordDTO);


}
