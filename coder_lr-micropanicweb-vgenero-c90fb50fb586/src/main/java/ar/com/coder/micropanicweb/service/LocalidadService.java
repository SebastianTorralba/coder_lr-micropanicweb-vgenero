package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Localidad;
import java.util.List;

/**
 *
 * @author lgaray
 */
public interface LocalidadService {

    public List<Localidad> getAllLocalidades();

    public Localidad getLocalidadById(Long id);

    public Localidad saveLocalidad(Localidad object);

    public List<Localidad> getLocalidadByIdPcia(Long id);
}
