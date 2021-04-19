package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.EventoAccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eventoAccionRepository")
public interface EventoAccionRepository extends JpaRepository<EventoAccion, Long> {
    public EventoAccion findEventoAccionById(Long id);

}
