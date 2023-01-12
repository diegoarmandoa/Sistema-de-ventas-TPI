package TPI.TPI.DTO;

import TPI.TPI.Enumeraciones.Rol;
import lombok.Data;

@Data
public class UserDTO {

    private String nombre;
    private String apellido;
    private Boolean estado;
    private String usuario;
    private String password;
    private Rol rol;


}
