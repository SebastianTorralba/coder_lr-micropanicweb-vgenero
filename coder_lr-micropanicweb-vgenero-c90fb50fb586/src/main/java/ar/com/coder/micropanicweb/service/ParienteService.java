/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Pariente;

/**
 *
 * @author eduxs
 */
public interface ParienteService {

    public Iterable<Pariente> listAllPariente();

    public Pariente findParienteById(int id);

    public void savePariente(Pariente object);
}
