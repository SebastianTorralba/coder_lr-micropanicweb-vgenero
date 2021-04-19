/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Organismo;

/**
 *
 * @author eduxs
 */
public interface OrganismoService {

    public Iterable<Organismo> listAllOrganismo();

    public Organismo findOrganismoByDenominacion(String denominacion);

   public Organismo findOrganismoById(int id);

    public void saveOrganismo(Organismo object);
}
