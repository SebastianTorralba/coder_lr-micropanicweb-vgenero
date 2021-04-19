/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;
import ar.com.coder.micropanicweb.model.UsuarioEquipos;

/**
 *
 * @author eduxs
 */
public interface UsuarioEquipoService {
    public Iterable<UsuarioEquipos> listAllUsuarioEquipos();
    public UsuarioEquipos findUsuarioEquipoById(Long id);
    public void saveUsuarioEquipo(UsuarioEquipos object);
}
