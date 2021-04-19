/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.EventoService;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author lgaray
 */
@Service("eventoService")
public class EventoServiceImp implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Iterable<Evento> listAllEvento() {
        //return eventoRepository.findAll();
       return eventoRepository.findAll(orderByFechaOperacion());
    }

       
    private Sort orderByFechaOperacion() {
    return new Sort(Sort.Direction.DESC, "fechaOperacion")
                .and(new Sort(Sort.Direction.DESC, "fechaOperacion"));
}

    @Override
    public void saveEvento(Evento item) {
        eventoRepository.save(item);
    }

    @Override
    public Evento findEventoById(Long id) {
        return eventoRepository.findEventoById(id);
    }

    @Override
    public Iterable<Evento> findByUsuario(Usuario user) {
        return eventoRepository.findEventoByUsuario(user);
    }

    @Override
    public List<Evento> findEventoByFechaOperacion(Date fechaDesde, Date fechaHasta, Pageable pageable) {
        return eventoRepository.findEventoByFechaOperacion(fechaDesde, fechaHasta, pageable);
    }

}
