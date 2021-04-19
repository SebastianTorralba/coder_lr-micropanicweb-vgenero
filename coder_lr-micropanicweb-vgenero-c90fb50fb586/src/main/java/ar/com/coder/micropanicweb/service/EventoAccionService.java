/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;
import ar.com.coder.micropanicweb.model.EventoAccion;

/**
 *
 * @author eduxs
 */
public interface EventoAccionService {
    public Iterable<EventoAccion> listAllEventoAccion();
    public EventoAccion findEventoAccionById(Long id);
    public void saveEventoAccion(EventoAccion object);
}
