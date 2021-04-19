/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.TipoRiesgo;
import ar.com.coder.micropanicweb.repository.TipoRiesgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.TipoRiesgoService;

/**
 *
 * @author lgaray
 */
@Service("tipoRiesgoService")
public class TipoRiesgoServiceImp implements TipoRiesgoService {

    @Autowired
    private TipoRiesgoRepository tipoRiesgoRepository;

    @Override
    public Iterable<TipoRiesgo> listAllTipoRiesgo() {
        return tipoRiesgoRepository.findAll();
    }
    @Override
    public void saveTipoRiesgo(TipoRiesgo item) {
        tipoRiesgoRepository.save(item);
    }

    @Override
    public TipoRiesgo findTipoRiesgoByDenominacion(String denominacion) {
        return tipoRiesgoRepository.findTipoRiesgoByDenominacion(denominacion);
    }

  
    @Override
    public TipoRiesgo findTipoRiesgoById(int id) {
        return tipoRiesgoRepository.findTipoRiesgoById(id);
    }

}
