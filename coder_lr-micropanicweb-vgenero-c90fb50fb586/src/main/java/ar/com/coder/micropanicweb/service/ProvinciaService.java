package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Provincia;

/**
 *
 * @author lgaray
 */
public interface ProvinciaService {

    public Iterable<Provincia> listAllProvincia();

    public Provincia saveProvincia(Provincia object);

    public Provincia getProvinciaById(Long id);


}
