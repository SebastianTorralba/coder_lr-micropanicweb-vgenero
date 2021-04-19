/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.EventoAccion;
import ar.com.coder.micropanicweb.repository.EventoAccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.EventoAccionService;

/**
 *
 * @author lgaray
 */
@Service("eventoAccionService")
public class EventoAccionServiceImp implements EventoAccionService {

    @Autowired
    private EventoAccionRepository eventoRepository;

    @Override
    public Iterable<EventoAccion> listAllEventoAccion() {
        return eventoRepository.findAll();
    }

    @Override
    public void saveEventoAccion(EventoAccion item) {
        eventoRepository.save(item);
    }

    @Override
    public EventoAccion findEventoAccionById(Long id) {
        return eventoRepository.findEventoAccionById(id);
    }

}
