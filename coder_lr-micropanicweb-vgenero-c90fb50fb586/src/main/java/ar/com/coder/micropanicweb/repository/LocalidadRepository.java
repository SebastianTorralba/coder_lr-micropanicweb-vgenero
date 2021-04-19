package ar.com.coder.micropanicweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.coder.micropanicweb.model.Localidad;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository("localidadRepository")
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

    public Localidad findLocalidadById(Long id);

    @Query(value = "select * from param_localidades  where id_pcia= ?1", nativeQuery = true)
    public List<Localidad> findLocalidadByIdPcia(Long id);
}
