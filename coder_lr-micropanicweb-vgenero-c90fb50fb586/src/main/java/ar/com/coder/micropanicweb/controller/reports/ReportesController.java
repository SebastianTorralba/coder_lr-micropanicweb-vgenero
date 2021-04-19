/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lgaray
 */
@Controller
public class ReportesController {
    @RequestMapping(value = "/informes/principal")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.setViewName("reports/reportePrincipal");
        return view;
    }
}
