package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Provincia;
import ar.com.coder.micropanicweb.repository.ProvinciaRepository;
import ar.com.coder.micropanicweb.service.ProvinciaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("provinciaService")
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private ProvinciaRepository pciaRepository;

    @Override
    public Iterable<Provincia> listAllProvincia() {
        return pciaRepository.findAll();
    }

    @Override
    public Provincia saveProvincia(Provincia item) {
        return pciaRepository.save(item);
    }

    @Override
    public Provincia getProvinciaById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
