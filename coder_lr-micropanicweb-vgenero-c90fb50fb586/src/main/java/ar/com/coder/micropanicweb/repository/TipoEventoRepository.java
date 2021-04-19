package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("tipoEventoRepository")
public interface TipoEventoRepository extends JpaRepository<TipoEvento, Integer>{
	TipoEvento findTipoEventoByDenominacion(String denominacion);
        TipoEvento findTipoEventoById(int id);

}
