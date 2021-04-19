package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findRolByDenominacion(String denominacion);

    Rol findRolById(int id);

}
