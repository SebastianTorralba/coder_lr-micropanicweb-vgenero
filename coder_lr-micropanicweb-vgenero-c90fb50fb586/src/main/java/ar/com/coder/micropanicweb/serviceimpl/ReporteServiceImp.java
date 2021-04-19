/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.coder.micropanicweb.service.ReporteService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lgaray
 */
@Service("reporteService")
public class ReporteServiceImp implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<String> findReportEventobyFechaTipo( ) {
        return reporteRepository.findReportEventobyFechaTipo();
    }

    


}
