/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Rol;

/**
 *
 * @author eduxs
 */
public interface RolService {

    public Iterable<Rol> listAllRol();

    public Rol findRolByDenominacion(String denominacion);

    public Rol findRolById(int id);

    public void saveRol(Rol object);
}
