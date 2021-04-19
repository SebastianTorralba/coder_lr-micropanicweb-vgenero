/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.TipoEvento;
import ar.com.coder.micropanicweb.repository.TipoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.TipoEventoService;

/**
 *
 * @author lgaray
 */
@Service("tipoEventoService")
public class TipoEventoServiceImp implements TipoEventoService {

    @Autowired
    private TipoEventoRepository tipoEventoRepository;

    @Override
    public Iterable<TipoEvento> listAllTipoEvento() {
        return tipoEventoRepository.findAll();
    }
    @Override
    public void saveTipoEvento(TipoEvento item) {
        tipoEventoRepository.save(item);
    }

    @Override
    public TipoEvento findTipoEventoByDenominacion(String denominacion) {
        return tipoEventoRepository.findTipoEventoByDenominacion(denominacion);
    }

  
    @Override
    public TipoEvento findTipoEventoById(int id) {
        return tipoEventoRepository.findTipoEventoById(id);
    }

}
