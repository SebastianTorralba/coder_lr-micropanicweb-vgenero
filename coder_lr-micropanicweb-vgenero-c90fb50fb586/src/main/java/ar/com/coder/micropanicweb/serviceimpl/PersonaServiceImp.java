/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.PersonaService;

/**
 *
 * @author lgaray
 */
@Service("personaService")
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Iterable<Persona> listAllPersona() {
        return personaRepository.findAll();
    }

    @Override
    public void savePersona(Persona item) {
        Persona persExists = this.findPersonaByDni(item.getDni());//findPersonaByDni(item.getDni());
        if (persExists != null) {
            item = persExists;
        }
        personaRepository.save(item);
    }

    @Override
    public Persona findPersonaByDni(String dni) {
        return personaRepository.findPersonaByDni(dni);
    }

    @Override
    public Persona findPersonaById(Long id) {
        return personaRepository.findPersonaById(id);
    }

}
