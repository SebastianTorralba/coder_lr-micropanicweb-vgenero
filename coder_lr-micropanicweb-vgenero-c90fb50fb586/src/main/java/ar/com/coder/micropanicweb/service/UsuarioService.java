/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Usuario;
import java.util.Optional;

/**
 *
 * @author lgaray
 */
public interface UsuarioService {

    public Iterable<Usuario> listAllUsuarios();

    public Iterable<Usuario> findUserByRol(String rol);

    public Usuario findUserById(Integer id);

    public Usuario findUserByEmail(String email);

    public Usuario findUserByPersona(Persona persona);

    public Usuario findUserByUsername(String username);

    public void saveUser(Usuario user);

    public Usuario getUsuarioCurrent();

    public Usuario findUserByHash(String hash);

    public Usuario findUserByUsernameClave(String username, String clave);

    public Optional<Usuario> findUserByResetToken(String resetToken);
}
