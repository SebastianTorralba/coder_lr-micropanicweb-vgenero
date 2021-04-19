/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Estado;
import ar.com.coder.micropanicweb.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.EstadoService;

/**
 *
 * @author lgaray
 */
@Service("estadoService")
public class EstadoServiceImp implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Iterable<Estado> listAllEstado() {
        return estadoRepository.findAll();
    }

    @Override
    public void saveEstado(Estado item) {
        estadoRepository.save(item);
    }

    @Override
    public Estado findEstadoByDenominacion(String denominacion) {
        return estadoRepository.findEstadoByDenominacion(denominacion);
    }

    @Override
    public Estado findEstadoById(Long id) {
        return estadoRepository.findEstadoById(id);
    }

}
