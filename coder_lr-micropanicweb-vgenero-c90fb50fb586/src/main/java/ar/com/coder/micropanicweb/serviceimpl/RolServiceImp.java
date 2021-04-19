/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.RolService;

/**
 *
 * @author lgaray
 */
@Service("rolService")
public class RolServiceImp implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Iterable<Rol> listAllRol() {
        return rolRepository.findAll();
    }
    @Override
    public void saveRol(Rol item) {
        rolRepository.save(item);
    }

    @Override
    public Rol findRolByDenominacion(String denominacion) {
        return rolRepository.findRolByDenominacion(denominacion);
    }

  /*  @Override
    public Persona findPersonaById(Long id) {
    return personaRepository.findPersonaById(id);
    }  */


    @Override
    public Rol findRolById(int id) {
        return rolRepository.findRolById(id);
    
    }
}
