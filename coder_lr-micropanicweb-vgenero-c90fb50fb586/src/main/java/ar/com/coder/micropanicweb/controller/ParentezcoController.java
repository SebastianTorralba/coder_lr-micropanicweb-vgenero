package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Parentezco;
import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.service.LocalidadService;
import ar.com.coder.micropanicweb.service.ParentezcoService;
import ar.com.coder.micropanicweb.service.RolService;
import ar.com.coder.micropanicweb.service.ProvinciaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lgaray
 */
@Controller
public class ParentezcoController {

    @Autowired
    private ParentezcoService parService;
    @Autowired

    @RequestMapping(value = "/parentezcos/nuevo", method = RequestMethod.GET)
    public ModelAndView newGet() {
        ModelAndView modelAndView = new ModelAndView();
        Parentezco par = new Parentezco();

       
        modelAndView.addObject("parentezco", par);
        modelAndView.setViewName("parentezcos/parentezcoForm");
        return modelAndView;
    }

    @RequestMapping(value = "/parentezcos", method = RequestMethod.POST)
    public ModelAndView newPost(@Valid Parentezco par, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Parentezco parExists = parService.findParentezcoById(par.getId());
        if (parExists != null) {
             if (bindingResult.hasErrors()) {
                   modelAndView.addObject("mostrarmsj",false);
            modelAndView.addObject("successMessage", "Error");
            modelAndView.addObject("parentezcos", parService.listAllParentezco());
            modelAndView.setViewName("parentezcos/parentezcoLista");
            
        } else {
            parService.saveParentezco(par);
                modelAndView.addObject("mostrarmsj",true);
                modelAndView.addObject("successMessage", "El registro se Modifico exitosamente");
                modelAndView.addObject("parentezcos", parService.listAllParentezco());
            modelAndView.setViewName("parentezcos/parentezcoLista");

        }
        }else{
            if (bindingResult.hasErrors()) {
                   modelAndView.addObject("mostrarmsj",false);
            modelAndView.addObject("successMessage", "Error");
            modelAndView.addObject("parentezcos", parService.listAllParentezco());
            modelAndView.setViewName("parentezcos/parentezcoLista");
            
        } else {
            parService.saveParentezco(par);
               modelAndView.addObject("mostrarmsj",true);
                modelAndView.addObject("successMessage", "El registro se creo exitosamente");
                modelAndView.addObject("parentezco", parService.listAllParentezco());
            modelAndView.setViewName("parentezcos/parentezcoLista");

        }
        }

    
        return modelAndView;
    }

    @RequestMapping(value = "/parentezcos")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.addObject("parentezcos", parService.listAllParentezco());
        view.setViewName("parentezcos/parentezcoLista");
        return view;
    }
   
    @RequestMapping("parentezcos/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Parentezco obj = parService.findParentezcoById(id);
        model.addAttribute("parentezco", obj);

        return "parentezcos/parentezcoForm";
    }
}
