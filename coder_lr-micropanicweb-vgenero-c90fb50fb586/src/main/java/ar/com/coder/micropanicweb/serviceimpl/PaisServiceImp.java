package ar.com.coder.micropanicweb.serviceimpl;

 import ar.com.coder.micropanicweb.model.Pais;

import ar.com.coder.micropanicweb.repository.PaisRepository;
import ar.com.coder.micropanicweb.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paisService")
public class PaisServiceImp  implements PaisService {

    @Autowired
    private PaisRepository paisRepository;
 
    @Override
    public Iterable<Pais> listAllPais() {
        return paisRepository.findAll();
    }

     
    @Override
    public Pais savePais(Pais item) {
        return paisRepository.save(item);
    }

    
}