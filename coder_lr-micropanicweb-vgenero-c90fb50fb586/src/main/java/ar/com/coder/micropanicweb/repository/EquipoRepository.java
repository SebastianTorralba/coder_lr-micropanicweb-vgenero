package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("equipoRepository")
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    public Equipo findEquipoById(Integer id);

}
