package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Parentezco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("parentezcoRepository")
public interface ParentezcoRepository extends JpaRepository<Parentezco, Integer> {

    Parentezco findParentezcoByDenominacion(String denominacion);

     Parentezco findParentezcoById(int id);

}
