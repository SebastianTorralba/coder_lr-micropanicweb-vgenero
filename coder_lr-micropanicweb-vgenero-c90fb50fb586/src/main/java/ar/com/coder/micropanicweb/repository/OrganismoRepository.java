package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Organismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("organismoRepository")
public interface OrganismoRepository extends JpaRepository<Organismo, Integer> {

    Organismo findOrganismoByDenominacion(String denominacion);

    Organismo findOrganismoById(int id);
}
