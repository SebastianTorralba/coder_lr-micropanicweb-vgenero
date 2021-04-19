/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Parentezco;

/**
 *
 * @author eduxs
 */
public interface ParentezcoService {

    public Iterable<Parentezco> listAllParentezco();

    public Parentezco findParentezcoByDenominacion(String denominacion);

    public Parentezco findParentezcoById(int id);

    public void saveParentezco(Parentezco object);
}
