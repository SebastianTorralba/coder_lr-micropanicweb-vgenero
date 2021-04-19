package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Estado;
import ar.com.coder.micropanicweb.service.EstadoService;
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
public class EstadoController {

    @Autowired
    private EstadoService estadoService;
    @Autowired
    private UsuarioService usuarioService;

    //  private ProvinciaService pciaService;
    @RequestMapping(value = "/estados/nuevo", method = RequestMethod.GET)
    public ModelAndView newGet() {
        ModelAndView modelAndView = new ModelAndView();
        Estado es = new Estado();

        modelAndView.addObject("estado", es);
        modelAndView.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        modelAndView.setViewName("estados/estadoForm");
        return modelAndView;
    }

    @RequestMapping(value = "/estados", method = RequestMethod.POST)
    public ModelAndView newPost(@Valid Estado es, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Estado estadoExists = estadoService.findEstadoById(es.getId());
        Estado estadoExists2 = estadoService.findEstadoByDenominacion(es.getDenominacion());
        modelAndView.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        if (estadoExists != null) {
            if (estadoExists2 != null) {
                if (estadoExists.getId() != estadoExists2.getId()) {
                    bindingResult
                            .rejectValue("denominacion", "error.estado",
                                    "Ya existe un estado registrado con la denominacion ingresada");
                }

            }
            if (bindingResult.hasErrors()) {
                //modelAndView.addObject("mostrarmsj", false);
                //modelAndView.addObject("successMessage", "Error");
                //modelAndView.addObject("estados", estadoService.listAllEstado());
                modelAndView.setViewName("estados/estadoForm");

            } else {
                estadoService.saveEstado(es);
                modelAndView.addObject("mostrarmsj", true);
                modelAndView.addObject("successMessage", "El registro se Modifico exitosamente");
                modelAndView.addObject("estados", estadoService.listAllEstado());
                modelAndView.setViewName("estados/estadoLista");

            }
        } else {
            if (estadoExists2 != null) {
                bindingResult
                        .rejectValue("denominacion", "error.estado",
                                "Ya existe un estado registrado con la denominacion ingresada");
            }
            if (bindingResult.hasErrors()) {
//                modelAndView.addObject("mostrarmsj", false);
//                modelAndView.addObject("successMessage", "Error");
//                modelAndView.addObject("estados", estadoService.listAllEstado());
                modelAndView.setViewName("estados/estadoForm");

            } else {
                estadoService.saveEstado(es);
                modelAndView.addObject("mostrarmsj", true);
                modelAndView.addObject("successMessage", "El registro se creo exitosamente");
                modelAndView.addObject("estados", estadoService.listAllEstado());
                modelAndView.setViewName("estados/estadoLista");

            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/estados")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("estados", estadoService.listAllEstado());
        //modelAndView.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        modelAndView.setViewName("estados/estadoLista");
        return modelAndView;
    }

    @RequestMapping("estados/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Estado obj = estadoService.findEstadoById(id);
        //System.out.println(obj);
        //modelAndView.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        modelAndView.addObject("estado", obj);
        modelAndView.setViewName("estados/estadoForm");
        return modelAndView;

    }
}
