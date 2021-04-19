/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lgaray
 */
@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findByUsername(String username);

    public Usuario findUserById(Integer id);

    public Usuario findUserByHash(String hash);

    @Query(value = "SELECT * FROM admin_usuarios WHERE username=?1 and clave_md5=?2", nativeQuery = true)
    public Usuario findUserByUsernameClave(String username, String clave);

    public Usuario findUserByPersona(Persona persona);

    @Query(value = "SELECT u.* FROM admin_usuarios AS u,admin_usuarios_roles as ur,param_roles r WHERE u.id_usuario=ur.id_usuario AND r.id_rol= ur.id_rol AND r.denominacion LIKE ?1", nativeQuery = true)
    public Iterable<Usuario> findUserByRol(String rol);

    public Optional<Usuario> findByResetToken(String resetToken);

}
