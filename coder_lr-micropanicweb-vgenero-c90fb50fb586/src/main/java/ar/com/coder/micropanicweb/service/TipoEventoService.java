/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;
import ar.com.coder.micropanicweb.model.TipoEvento;

/**
 *
 * @author eduxs
 */
public interface TipoEventoService {
    public Iterable<TipoEvento> listAllTipoEvento();
    public TipoEvento findTipoEventoByDenominacion(String denominacion);
    public TipoEvento findTipoEventoById(int id);

    public void saveTipoEvento(TipoEvento object);
}
