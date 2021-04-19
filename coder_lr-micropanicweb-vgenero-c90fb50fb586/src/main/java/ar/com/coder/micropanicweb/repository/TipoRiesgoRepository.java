package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.TipoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("tipoRiesgoRepository")
public interface TipoRiesgoRepository extends JpaRepository<TipoRiesgo, Integer>{
	TipoRiesgo findTipoRiesgoByDenominacion(String denominacion);
        TipoRiesgo findTipoRiesgoByCodigo(String codigo);
        TipoRiesgo findTipoRiesgoById(int id);

}
