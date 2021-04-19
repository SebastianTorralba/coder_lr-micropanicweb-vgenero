package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paisRepository")
public interface PaisRepository extends JpaRepository<Pais, Long> {
 
}
