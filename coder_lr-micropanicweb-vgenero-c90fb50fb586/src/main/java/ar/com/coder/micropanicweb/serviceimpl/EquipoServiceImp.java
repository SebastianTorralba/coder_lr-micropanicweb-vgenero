/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Equipo;
import ar.com.coder.micropanicweb.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.EquipoService;

/**
 *
 * @author lgaray
 */
@Service("equipoService")
public class EquipoServiceImp implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public Iterable<Equipo> listAllEquipo() {
        return equipoRepository.findAll();
    }

    @Override
    public void saveEquipo(Equipo item) {
        equipoRepository.save(item);
    }

    @Override
    public Equipo findEquipoById(Integer id) {
        return equipoRepository.findEquipoById(id);
    }

}
