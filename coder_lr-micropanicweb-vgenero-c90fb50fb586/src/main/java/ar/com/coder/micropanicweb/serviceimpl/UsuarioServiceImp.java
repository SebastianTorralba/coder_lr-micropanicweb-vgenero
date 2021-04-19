package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.repository.PersonaRepository;
import ar.com.coder.micropanicweb.repository.UsuarioRepository;
import ar.com.coder.micropanicweb.service.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lgaray
 */
@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private PersonaRepository persRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Usuario findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(Usuario user) {
        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
        Persona persExists = persRepository.findPersonaByDni(user.getPersona().getDni());
        if (persExists != null) {
            user.setPersona(persExists);
        } else {
            persRepository.save(user.getPersona());
            persExists = persRepository.findPersonaByDni(user.getPersona().getDni());
            user.setPersona(persExists);
        }
        userRepository.save(user);
    }

    @Override
    public Iterable<Usuario> listAllUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public Usuario findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Usuario getUsuarioCurrent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = this.findUserByUsername(auth.getName());
        if (user.getPersona().getFoto() == null || user.getPersona().getFoto().equals("")) {
            user.getPersona().setFoto("default.png");
        }
        return user;
    }

    @Override
    public Usuario findUserByHash(String hash) {
        return userRepository.findUserByHash(hash);
    }

    @Override
    public Usuario findUserByUsernameClave(String username, String clave) {
        return userRepository.findUserByUsernameClave(username, clave);
    }

    @Override
    public Usuario findUserByPersona(Persona persona) {
        return userRepository.findUserByPersona(persona);
    }

    @Override
    public  Optional<Usuario> findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public Iterable<Usuario> findUserByRol(String rol) {
        return userRepository.findUserByRol(rol);
    }
}
