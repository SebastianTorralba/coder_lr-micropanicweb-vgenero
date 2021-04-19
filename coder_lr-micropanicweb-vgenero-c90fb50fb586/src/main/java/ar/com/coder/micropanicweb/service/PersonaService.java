/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Persona;

/**
 *
 * @author lgaray
 */
public interface PersonaService {

    public Iterable<Persona> listAllPersona();
    public Persona findPersonaById(Long id);
    public Persona findPersonaByDni(String dni);

    public void savePersona(Persona object);
}
