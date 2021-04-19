/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Estado;
import ar.com.coder.micropanicweb.model.Parentezco;
import ar.com.coder.micropanicweb.repository.EstadoRepository;
import ar.com.coder.micropanicweb.repository.ParentezcoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.ParentezcoService;

/**
 *
 * @author lgaray
 */
@Service("parentezcoService")
public class ParentezcoServiceImp implements ParentezcoService {

    @Autowired
    private ParentezcoRepository parentezcoRepository;

    @Override
    public Iterable<Parentezco> listAllParentezco() {
        return parentezcoRepository.findAll();
    }

    @Override
    public Parentezco findParentezcoByDenominacion(String denominacion) {
        return parentezcoRepository.findParentezcoByDenominacion(denominacion);
    }

    @Override
    public Parentezco findParentezcoById(int id) {
        return parentezcoRepository.findParentezcoById(id);
    }

    @Override
    public void saveParentezco(Parentezco item) {
        parentezcoRepository.save(item);
    }

}
