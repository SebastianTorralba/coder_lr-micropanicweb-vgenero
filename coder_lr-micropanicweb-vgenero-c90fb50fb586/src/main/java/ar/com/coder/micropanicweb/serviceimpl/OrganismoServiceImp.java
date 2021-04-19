/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Organismo;
import ar.com.coder.micropanicweb.repository.OrganismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.OrganismoService;

/**
 *
 * @author lgaray
 */
@Service("organismoService")
public class OrganismoServiceImp implements OrganismoService {

    @Autowired
    private OrganismoRepository OrganismoRepository;

    @Override
    public Iterable<Organismo> listAllOrganismo() {
        return OrganismoRepository.findAll();
    }

    @Override
    public void saveOrganismo(Organismo item) {
        OrganismoRepository.save(item);
    }

    @Override
    public Organismo findOrganismoByDenominacion(String denominacion) {
        return OrganismoRepository.findOrganismoByDenominacion(denominacion);
    }

    @Override
    public Organismo findOrganismoById(int id) {
        return OrganismoRepository.findOrganismoById(id);
    }

}
