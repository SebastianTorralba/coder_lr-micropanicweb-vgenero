package ar.com.coder.micropanicweb.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.coder.micropanicweb.model.Localidad;
import ar.com.coder.micropanicweb.repository.LocalidadRepository;
import ar.com.coder.micropanicweb.service.LocalidadService;
import java.util.List;

@Service("localidadService")
public class LocalidadServiceImpl implements LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    @Override
    public Localidad saveLocalidad(Localidad item) {
        return localidadRepository.save(item);
    }

    @Override
    public List<Localidad> getAllLocalidades() {
        return localidadRepository.findAll();
    }

    @Override
    public Localidad getLocalidadById(Long id) {
        return localidadRepository.findLocalidadById(id);
    }

    @Override
    public List<Localidad> getLocalidadByIdPcia(Long id) {
        return localidadRepository.findLocalidadByIdPcia(id);
    }

}
