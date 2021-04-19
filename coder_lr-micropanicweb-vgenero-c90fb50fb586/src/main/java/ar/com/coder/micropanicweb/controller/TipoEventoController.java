package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.TipoEvento;
import ar.com.coder.micropanicweb.service.TipoEventoService;
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
public class TipoEventoController {

    @Autowired
    private TipoEventoService tipoEventoService;
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/tipoEventos/nuevo", method = RequestMethod.GET)
    public ModelAndView newGet() {
        ModelAndView view = new ModelAndView();
        TipoEvento te = new TipoEvento();
        view.addObject("tipoEvento", te);
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("tipoEventos/tipoEventoForm");
        return view;
    }

    @RequestMapping(value = "/tipoEventos", method = RequestMethod.POST)
    public ModelAndView newPost(@Valid TipoEvento te, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        TipoEvento tipoEventoExists = tipoEventoService.findTipoEventoById(te.getId());
        TipoEvento tipoEventoExists2 = tipoEventoService.findTipoEventoByDenominacion(te.getDenominacion());
        if (tipoEventoExists != null) {
            if (tipoEventoExists2 != null) {
                if (tipoEventoExists.getId() != tipoEventoExists2.getId()) {
                    bindingResult
                            .rejectValue("denominacion", "error.tipoEvento",
                                    "Ya existe un Tipo Evento con la denominacion ingresada");

                }

            }
            if (bindingResult.hasErrors()) {
//                view.addObject("mostrarmsj", false);
//                view.addObject("successMessage", "Error");
//                view.addObject("tipoEventos", tipoEventoService.listAllTipoEvento());
                view.setViewName("tipoEventos/tipoEventoForm");

            } else {
                tipoEventoService.saveTipoEvento(te);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se Modifico exitosamente");
                view.addObject("tipoEventos", tipoEventoService.listAllTipoEvento());
                view.setViewName("tipoEventos/tipoEventoLista");

            }
        } else {
            if (tipoEventoExists2 != null) {
                bindingResult
                        .rejectValue("denominacion", "error.tipoEvento",
                                "Ya existe un Tipo Evento con la denominacion ingresada");

            }
            if (bindingResult.hasErrors()) {
//                view.addObject("mostrarmsj", false);
//                view.addObject("successMessage", "Error");
//                view.addObject("tipoEventos", tipoEventoService.listAllTipoEvento());
                view.setViewName("tipoEventos/tipoEventoForm");

            } else {
                tipoEventoService.saveTipoEvento(te);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se creo exitosamente");
                view.addObject("tipoEventos", tipoEventoService.listAllTipoEvento());
                view.setViewName("tipoEventos/tipoEventoLista");

            }
        }
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        return view;
    }

    @RequestMapping(value = "/tipoEventos")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.addObject("tipoEventos", tipoEventoService.listAllTipoEvento());
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("tipoEventos/tipoEventoLista");
        return view;
    }

    @RequestMapping("tipoEventos/edit/{id}")
    public ModelAndView edit(@PathVariable int id, Model model) {
        ModelAndView view = new ModelAndView();
        TipoEvento obj = tipoEventoService.findTipoEventoById(id);

        model.addAttribute("tipoEvento", obj);
       // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario

        view.setViewName("tipoEventos/tipoEventoForm");
        return view;
    }
}
