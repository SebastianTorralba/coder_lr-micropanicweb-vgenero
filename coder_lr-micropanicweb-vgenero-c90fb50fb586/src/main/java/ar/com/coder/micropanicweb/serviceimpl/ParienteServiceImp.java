/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Pariente;

import ar.com.coder.micropanicweb.repository.ParienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.ParienteService;

/**
 *
 * @author lgaray
 */
@Service("parienteService")
public class ParienteServiceImp implements ParienteService {

    @Autowired
    private ParienteRepository parienteRepository;

    @Override
    public Iterable<Pariente> listAllPariente() {
        return parienteRepository.findAll();
    }

    @Override
    public Pariente findParienteById(int id) {
        return parienteRepository.findParienteById(id);
    }

    @Override
    public void savePariente(Pariente item) {
        parienteRepository.save(item);
    }

}
