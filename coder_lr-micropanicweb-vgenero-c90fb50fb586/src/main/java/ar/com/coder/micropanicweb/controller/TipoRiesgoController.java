package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.TipoRiesgo;
import ar.com.coder.micropanicweb.service.TipoRiesgoService;
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
public class TipoRiesgoController {

    @Autowired
    private TipoRiesgoService tipoRiesgoService;
     

    @RequestMapping(value = "/tipoRiesgos/nuevo", method = RequestMethod.GET)
    public ModelAndView newGet() {
        ModelAndView view = new ModelAndView();
        TipoRiesgo te = new TipoRiesgo();
        view.addObject("tipoRiesgo", te);
        view.setViewName("tipoRiesgos/tipoRiesgoForm");
        return view;
    }

    @RequestMapping(value = "/tipoRiesgos", method = RequestMethod.POST)
    public ModelAndView newPost(@Valid TipoRiesgo te, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        TipoRiesgo tipoRiesgoExists = tipoRiesgoService.findTipoRiesgoById(te.getId());
        TipoRiesgo tipoRiesgoExists2 = tipoRiesgoService.findTipoRiesgoByDenominacion(te.getDenominacion());
        if (tipoRiesgoExists != null) {
            if (tipoRiesgoExists2 != null) {
                if (tipoRiesgoExists.getId() != tipoRiesgoExists2.getId()) {
                    bindingResult
                            .rejectValue("denominacion", "error.tipoRiesgo",
                                    "Ya existe un Tipo Riesgo con la denominacion ingresada");

                }

            }
            if (bindingResult.hasErrors()) {
//                view.addObject("mostrarmsj", false);
//                view.addObject("successMessage", "Error");
//                view.addObject("tipoRiesgos", tipoRiesgoService.listAllTipoRiesgo());
                view.setViewName("tipoRiesgos/tipoRiesgoForm");

            } else {
                tipoRiesgoService.saveTipoRiesgo(te);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se Modifico exitosamente");
                view.addObject("tipoRiesgos", tipoRiesgoService.listAllTipoRiesgo());
                view.setViewName("tipoRiesgos/tipoRiesgoLista");

            }
        } else {
            if (tipoRiesgoExists2 != null) {
                bindingResult
                        .rejectValue("denominacion", "error.tipoRiesgo",
                                "Ya existe un Tipo Riesgo con la denominacion ingresada");

            }
            if (bindingResult.hasErrors()) {
//                view.addObject("mostrarmsj", false);
//                view.addObject("successMessage", "Error");
//                view.addObject("tipoRiesgos", tipoRiesgoService.listAllTipoRiesgo());
                view.setViewName("tipoRiesgos/tipoRiesgoForm");

            } else {
                tipoRiesgoService.saveTipoRiesgo(te);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se creo exitosamente");
                view.addObject("tipoRiesgos", tipoRiesgoService.listAllTipoRiesgo());
                view.setViewName("tipoRiesgos/tipoRiesgoLista");

            }
        }
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        return view;
    }

    @RequestMapping(value = "/tipoRiesgos")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.addObject("tipoRiesgos", tipoRiesgoService.listAllTipoRiesgo());
       
        view.setViewName("tipoRiesgos/tipoRiesgoLista");
        return view;
    }

    @RequestMapping("tipoRiesgos/edit/{id}")
    public ModelAndView edit(@PathVariable int id, Model model) {
        ModelAndView view = new ModelAndView();
        TipoRiesgo obj = tipoRiesgoService.findTipoRiesgoById(id);
        model.addAttribute("tipoRiesgo", obj);

        view.setViewName("tipoRiesgos/tipoRiesgoForm");
        return view;
    }
}
