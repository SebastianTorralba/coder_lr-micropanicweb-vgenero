package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Parentezco;
import ar.com.coder.micropanicweb.model.Pariente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("parienteRepository")
public interface ParienteRepository extends JpaRepository<Pariente, Integer> {

     Pariente findParienteById(int id);

}
