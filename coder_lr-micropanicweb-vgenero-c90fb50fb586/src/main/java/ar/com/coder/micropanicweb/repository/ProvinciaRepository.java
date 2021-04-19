package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("provinciaRepository")
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

}
