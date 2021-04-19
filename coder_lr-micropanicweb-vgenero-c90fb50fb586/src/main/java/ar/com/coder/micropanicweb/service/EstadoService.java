/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;
import ar.com.coder.micropanicweb.model.Estado;

/**
 *
 * @author eduxs
 */
public interface EstadoService {
    public Iterable<Estado> listAllEstado();
    public Estado findEstadoByDenominacion(String denominacion);
    public Estado findEstadoById(Long id);
    public void saveEstado(Estado object);
}
