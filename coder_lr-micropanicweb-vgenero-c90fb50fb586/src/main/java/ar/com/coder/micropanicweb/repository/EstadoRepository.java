package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("estadoRepository")
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Estado findEstadoByDenominacion(String denominacion);

    public Estado findEstadoById(Long id);

}
