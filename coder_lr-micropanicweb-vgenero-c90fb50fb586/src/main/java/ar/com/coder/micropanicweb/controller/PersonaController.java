package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.model.ax.EventoGps;
import ar.com.coder.micropanicweb.service.EventoService;
import ar.com.coder.micropanicweb.service.PersonaService;
import ar.com.coder.micropanicweb.service.ProvinciaService;
import ar.com.coder.micropanicweb.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
public class PersonaController {

    @Autowired
    private PersonaService persService;
    @Autowired
    private ProvinciaService pciaService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private UsuarioService usuarioService;
    @Value("${url-server}")
    private String urlServer;

    @RequestMapping(value = "/personas")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.addObject("usuarios", usuarioService.findUserByRol("USER"));
        //view.addObject("personas", persService.listAllPersona());

        //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("personas/personaLista");
        return view;
    }

    @RequestMapping("personas/{id}")
    public ModelAndView getShow(@PathVariable Long id) {
        ModelAndView view = new ModelAndView();

        Persona p = persService.findPersonaById(id);
        Usuario user = usuarioService.findUserByPersona(p);

        Iterable<Evento> eventos = eventoService.findByUsuario(user);
        EventoGps gps = new EventoGps();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);

        view.addObject("urlServer", this.urlServer);
        view.addObject("eventosGps", eventosGps);

        if (p.getFoto() == null || p.getFoto().isEmpty()) {
            p.setFoto("default.png");
        }
        view.addObject("persona", p);

        //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("personas/personaPerfil");
        return view;
    }

//    @RequestMapping(value = "/personas/nuevo", method = RequestMethod.GET)
//    public ModelAndView newGet() {
//        ModelAndView view = new ModelAndView();
//        Persona pers = new Persona();
//
//        view.addObject("pcias", pciaService.listAllProvincia());
//        // modelAndView.addObject("localidades",  localidadService.getAllLocalidades());
//        view.addObject("persona", pers);
//
//       //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
//        view.setViewName("personas/personaForm");
//        return view;
//    }
//
//    @RequestMapping(value = "/personas/nuevo", method = RequestMethod.POST)
//    public ModelAndView newPost(@Valid Persona pers, BindingResult bindingResult) {
//        ModelAndView view = new ModelAndView();
//        Persona persExists = persService.findPersonaByDni(pers.getDni());
//        if (persExists != null && persExists.getId() != pers.getId()) {
//            bindingResult
//                    .rejectValue("dni", "error.persona",
//                            "Ya hay una persona registrada con el DNI ingresado");
//        }
//
//        if (bindingResult.hasErrors()) {
//            view.addObject("pcias", pciaService.listAllProvincia());
//            view.setViewName("personas/personaForm");
//        } else {
//            persService.savePersona(pers);
//            view.addObject("successMessage", "El registro se ha guardado exitosamente");
//            view.addObject("persona", new Persona());
//            view.setViewName("personas/personaForm");
//
//        }
//
//       //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
//        return view;
//    }
//    @RequestMapping("personas/edit/{id}")
//    public ModelAndView edit(@PathVariable Long id) {
//        ModelAndView view = new ModelAndView();
//        Persona obj = persService.findPersonaById(id);
//        //System.out.println(obj);
//        view.addObject("persona", obj);
//        view.addObject("pcias", pciaService.listAllProvincia());
//
//       //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
//        view.setViewName("personas/personaForm");
//        return view;
//    }
}
