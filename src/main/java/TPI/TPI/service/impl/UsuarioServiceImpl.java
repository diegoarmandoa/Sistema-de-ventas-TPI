package TPI.TPI.service.impl;

import TPI.TPI.Commons.GenericServiceImpl;
import TPI.TPI.DTO.UserDTO;
import TPI.TPI.Entity.Administradores;
import TPI.TPI.Entity.Personas;
import TPI.TPI.Entity.Usuarios;
import TPI.TPI.dao.api.UsuarioDaoAPI;
import TPI.TPI.service.api.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuarios,Long>  implements UsuarioServiceAPI {
   @Autowired
    private UsuarioDaoAPI usuarioDaoAPI;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public CrudRepository<Usuarios, Long> getDao() {
        return usuarioDaoAPI;
    }

    @Override
    public Usuarios save(UserDTO userDTO) {

        Personas personas = new Personas();
        personas.setNombre(userDTO.getNombre());
        personas.setApellido(userDTO.getApellido());
        personas.setEstado(true);

        Administradores administradores = new Administradores();
        administradores.setRol(userDTO.getRol());

        Usuarios usuarios = new Usuarios();
        usuarios.setPersona(personas);
        usuarios.setAdministrador(administradores);
        usuarios.setUsuario(userDTO.getUsuario());
        usuarios.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return usuarioDaoAPI.save(usuarios);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuarios user = usuarioDaoAPI.findByUsuario(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(), mapRolesToAuthorities(user.getAdministrador()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Administradores roles) {
        Collection<Administradores> cRoles = new ArrayList<>();
        cRoles.add(roles);

        return cRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRol().toString())).collect(Collectors.toList());
    }
}
