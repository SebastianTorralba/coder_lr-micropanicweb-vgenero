/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Usuario;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author lgaray
 */
public interface EventoService {

    public Iterable<Evento> listAllEvento();

    public Evento findEventoById(Long id);

    public Iterable<Evento> findByUsuario(Usuario user);

    public void saveEvento(Evento object);

    public List<Evento> findEventoByFechaOperacion(Date fechaDesde, Date fechaHasta, Pageable pageable);

}
