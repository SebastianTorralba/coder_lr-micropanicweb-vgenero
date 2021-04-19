/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;
import ar.com.coder.micropanicweb.model.TipoRiesgo;

/**
 *
 * @author eduxs
 */
public interface TipoRiesgoService {
    public Iterable<TipoRiesgo> listAllTipoRiesgo();
    public TipoRiesgo findTipoRiesgoByDenominacion(String denominacion);
    public TipoRiesgo findTipoRiesgoById(int id);

    public void saveTipoRiesgo(TipoRiesgo object);
}
