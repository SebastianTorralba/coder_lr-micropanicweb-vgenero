package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Organismo;
import ar.com.coder.micropanicweb.service.LocalidadService;
import ar.com.coder.micropanicweb.service.OrganismoService;
import ar.com.coder.micropanicweb.service.ProvinciaService;
import ar.com.coder.micropanicweb.service.UsuarioService;
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
public class OrganismoController {

    @Autowired
    private OrganismoService organismoService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/organismos/nuevo", method = RequestMethod.GET)
    public ModelAndView newGet() {
        ModelAndView view = new ModelAndView();
        Organismo or = new Organismo();

        view.addObject("organismo", or);
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("organismos/organismoForm");
        return view;
    }

    @RequestMapping(value = "/organismos", method = RequestMethod.POST)
    public ModelAndView newPost(@Valid Organismo or, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        Organismo organismoExists = organismoService.findOrganismoById(or.getId());
        Organismo organismoExists2 = organismoService.findOrganismoByDenominacion(or.getDenominacion());
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        if (organismoExists != null) {
            if (organismoExists2 != null) {
                if (organismoExists.getId() == organismoExists2.getId()) {
                    bindingResult
                            .rejectValue("denominacion", "error.organismo",
                                    "Ya existe una institucion registrada con la misma denominacion");
                }

            }
            if (bindingResult.hasErrors()) {
//                modelAndView.addObject("mostrarmsj", false);
//                modelAndView.addObject("successMessage", "Error");
//                modelAndView.addObject("organismos", organismoService.listAllOrganismo());
                view.setViewName("organismos/organismoForm");

            } else {
                organismoService.saveOrganismo(or);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se Modifico exitosamente");
                view.addObject("organismos", organismoService.listAllOrganismo());
                view.setViewName("organismos/organismoLista");

            }
        } else {
            if (organismoExists2 != null) {
                bindingResult
                        .rejectValue("denominacion", "error.organismo",
                                "Ya existe una institucion registrada con la misma denominacion");
            }
            if (bindingResult.hasErrors()) {
//                modelAndView.addObject("mostrarmsj", false);
//                modelAndView.addObject("successMessage", "Error");
//                modelAndView.addObject("organismos", organismoService.listAllOrganismo());
                view.setViewName("organismos/organismoForm");

            } else {
                organismoService.saveOrganismo(or);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se creo exitosamente");
                view.addObject("organismos", organismoService.listAllOrganismo());
                view.setViewName("organismos/organismoLista");

            }
        }

        return view;
    }

    @RequestMapping(value = "/organismos")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.addObject("organismos", organismoService.listAllOrganismo());
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("organismos/organismoLista");
        return view;
    }

    @RequestMapping("organismos/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id, Model model) {
        ModelAndView view = new ModelAndView();
        Organismo obj = organismoService.findOrganismoById(id);
        model.addAttribute("organismo", obj);
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("organismos/organismoForm");
        return view;
    }
}
