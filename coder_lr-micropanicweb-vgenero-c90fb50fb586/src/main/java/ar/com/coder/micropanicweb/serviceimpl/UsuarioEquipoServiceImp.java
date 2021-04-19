/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.UsuarioEquipos;
import ar.com.coder.micropanicweb.repository.UsuarioEquipoRepository;
import ar.com.coder.micropanicweb.service.UsuarioEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lgaray
 */
@Service("usuarioEquipoService")
public class UsuarioEquipoServiceImp implements UsuarioEquipoService {

    @Autowired
    private UsuarioEquipoRepository userEquipoRepository;

    @Override
    public Iterable<UsuarioEquipos> listAllUsuarioEquipos() {
        return userEquipoRepository.findAll();
    }

    @Override
    public UsuarioEquipos findUsuarioEquipoById(Long id) {
        return userEquipoRepository.findUsuarioEquipoById(id);
    }

    @Override
    public void saveUsuarioEquipo(UsuarioEquipos object) {
        userEquipoRepository.save(object);
    }

}
