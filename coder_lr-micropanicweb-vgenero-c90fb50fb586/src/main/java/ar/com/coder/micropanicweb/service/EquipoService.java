/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;
import ar.com.coder.micropanicweb.model.Equipo;

/**
 *
 * @author eduxs
 */
public interface EquipoService {
    public Iterable<Equipo> listAllEquipo();
    public Equipo findEquipoById(Integer id);
    public void saveEquipo(Equipo object);
}
