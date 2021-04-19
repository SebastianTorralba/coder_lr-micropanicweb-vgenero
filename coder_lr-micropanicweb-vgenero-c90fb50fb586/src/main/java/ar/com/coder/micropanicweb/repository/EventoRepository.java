/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Usuario;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lgaray
 */
@Transactional
@Repository("eventoRepository")
public interface EventoRepository extends JpaRepository<Evento, Long> {

    public Evento findEventoById(Long id);

    public Iterable<Evento> findEventoByUsuario(Usuario user);

    @Query(value = "select * from eventos  where fecha_operacion >= ?1 and fecha_operacion <=?2", nativeQuery = true)
    public List<Evento> findEventoByFechaOperacion(Date fechaDesde, Date fechaHasta, Pageable pageable);
}
