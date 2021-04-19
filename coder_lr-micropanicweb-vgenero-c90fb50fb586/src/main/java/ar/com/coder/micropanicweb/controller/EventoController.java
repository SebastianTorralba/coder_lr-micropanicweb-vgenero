package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.EventoAccion;
import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.model.TipoRiesgo;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.model.ax.EventoGps;
import ar.com.coder.micropanicweb.repository.TipoRiesgoRepository;

import ar.com.coder.micropanicweb.service.EstadoService;
import ar.com.coder.micropanicweb.service.EventoAccionService;
import ar.com.coder.micropanicweb.service.EventoService;
import ar.com.coder.micropanicweb.service.OrganismoService;
import ar.com.coder.micropanicweb.service.PersonaService;
import ar.com.coder.micropanicweb.service.RolService;
import ar.com.coder.micropanicweb.service.TipoEventoService;
import ar.com.coder.micropanicweb.service.UsuarioService;
import ar.com.coder.micropanicweb.utils.Encriptar;
import ar.com.coder.micropanicweb.utils.Fecha;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
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
public class EventoController {

    @Value("${url-server}")
    private String urlServer;
    @Autowired
    private EventoService eventoService;
    @Autowired
    TipoEventoService tipoEventoService;
    @Autowired
    private EventoAccionService eventoAccionService;
    @Autowired
    private OrganismoService organismoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private RolService rolService;
    @Autowired
    private TipoRiesgoRepository riesgoRepository;

    @RequestMapping(value = "/eventos")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        //model.addAttribute("evento", eventoGps);
        Iterable<Evento> eventos = eventoService.listAllEvento();
        EventoGps gps = new EventoGps();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);

        view.addObject("urlServer", this.urlServer);
        view.addObject("eventos", eventosGps);
        //view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("eventos/eventosLista");
        return view;
    }

    @RequestMapping("eventos/{id}")
    public ModelAndView getShow(@PathVariable Long id) {
        ModelAndView view = new ModelAndView();
        //Evento evento = eventoService.findEventoById(Long.parseLong("2"));
        Evento evento = eventoService.findEventoById(id);
        EventoAccion accion = new EventoAccion();
        accion.setEvento(evento);

        if (evento.getUsuario().getPersona().getFoto() == null || evento.getUsuario().getPersona().getFoto().isEmpty()) {
            evento.getUsuario().getPersona().setFoto("default.png");
        }
        EventoGps eventoGps = new EventoGps();
        eventoGps.setEventoGps(evento);
        view.addObject("urlServer", this.urlServer);
        view.addObject("eventoGps", eventoGps);

        //envio el evento para trabajar con eventoAccion
        view.addObject("evento", evento);
        view.addObject("eventoAccion", accion);
        view.addObject("organismos", organismoService.listAllOrganismo());
        //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("eventos/eventoInfo");
        return view;
    }

    //Filtro para eventos
    @RequestMapping(value = "/eventos/nuevo", method = RequestMethod.GET)
    public ModelAndView createEvent() {
        ModelAndView view = new ModelAndView();
        Evento evento = new Evento();
        view.addObject("evento", evento);
        view.addObject("tiposEvento", tipoEventoService.listAllTipoEvento());

        //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("eventos/eventoForm");
        return view;
    }

    /*
    * crea Evento
    **/
    @RequestMapping(value = "/eventos/nuevo", method = RequestMethod.POST)
    public String createEvento(@Valid Evento evento, BindingResult bindingResult, Model model) {

        //view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        /*seteamos los datos por defecto al crear el evento*/
        evento.setFechaOperacion(new java.sql.Timestamp(new Date().getTime()));
        evento.setEstado(estadoService.findEstadoById(Long.parseLong("3")));
        if (evento.getGpsPres() == null) {
            evento.setGpsPres("1");
        }
        if (evento.getGpsLat() == null || evento.getGpsLon() == null) {
            bindingResult
                    .rejectValue("gpsLat", "error.gpsLat", "Faltan Datos");
        }
//        if (!Util.isNumeric(evento.getUsuario().getPersona().getDni())) {
//            bindingResult
//                    .rejectValue("usuario.persona.dni", "error.usuario.persona.dni", "El DNI debe ser Numerico");
//        }
        if (evento.getUsuario().getPersona().getRazonSocial().equals("")) {
            bindingResult
                    .rejectValue("usuario.persona.razonSocial", "error.usuario.persona.razonSocial", "Debe ingresar Nombre y Apellido");
        }
        if (evento.getUsuario().getPersona().getTel().equals("")) {
            bindingResult
                    .rejectValue("usuario.persona.tel", "error.usuario.persona.tel", "Debe ingresar un Nro. de Telefono o Celular");
        }
        if (evento.getTipoEvento() == null) {
            bindingResult
                    .rejectValue("tipoEvento", "error.tipoEvento", "Seleccione un Tipo de Evento");
        }

        try {
            Persona persExists = personaService.findPersonaByDni(evento.getUsuario().getPersona().getDni());
            Usuario userExists = usuarioService.findUserByPersona(persExists);

            if (userExists != null) {
                /*actualizo el email username siempre y cuando el email sea diferente al que esta guardado
                sea diferente al de por defecto y tenga estado 4 usuario creado desde la web*/
                if (!evento.getUsuario().getPersona().getEmail().equals("")
                        && !evento.getUsuario().getPersona().getEmail().equals(userExists.getEmail())
                        && userExists.getEmail().equals("asd@asd.com")
                        && userExists.getEstado() == 4) {
                    userExists.getPersona().setEmail(evento.getUsuario().getPersona().getEmail());
                    userExists.setEmail(evento.getUsuario().getPersona().getEmail());
                    userExists.setUsername(evento.getUsuario().getPersona().getEmail());
                    usuarioService.saveUser(userExists); //actualizo email y el username 
                }
                evento.setUsuario(userExists);
            } else {
                if (evento.getUsuario().getPersona().getEmail() == null
                        || evento.getUsuario().getPersona().getEmail().equals("")) {
                    evento.getUsuario().setEmail("asd@asd.com");
                    evento.getUsuario().getPersona().setEmail("asd@asd.com");
                    evento.getUsuario().setUsername(evento.getUsuario().getPersona().getDni());
                } else {
                    evento.getUsuario().setEmail(evento.getUsuario().getPersona().getEmail());
                    evento.getUsuario().setUsername(evento.getUsuario().getEmail());
                }

//                if (persExists == null) {
//                    personaService.savePersona(evento.getUsuario().getPersona());
//                }
                //persExists = personaService.findPersonaByDni(evento.getUsuario().getPersona().getDni());
                String clave = Encriptar.getMD5(evento.getUsuario().getPersona().getDni());
                evento.getUsuario().setClave(evento.getUsuario().getPersona().getDni());
                evento.getUsuario().setClaveMd5(clave);
                evento.getUsuario().setEstado(4);//usuario creado desde la web
                TipoRiesgo riesgo = riesgoRepository.findTipoRiesgoByCodigo("SC");
                evento.getUsuario().getPersona().setTipoRiesgo(riesgo);

                Rol userRole = rolService.findRolByDenominacion("USER");
                evento.getUsuario().setRoles(new HashSet<Rol>(Arrays.asList(userRole)));

                usuarioService.saveUser(evento.getUsuario());
                userExists = usuarioService.findUserByUsername(evento.getUsuario().getUsername());
                evento.setUsuario(userExists);
            }

            System.out.println(bindingResult.getAllErrors());
            if (!bindingResult.hasErrors()) {
                eventoService.saveEvento(evento);
//               model.addAttribute("mostrarmsj", true);
//                model.addAttribute("successMessage", "El registro se Modifico exitosamente");
                return "redirect:/eventos/";
            } else {
                System.out.println("hay errores");
                model.addAttribute("tiposEvento", tipoEventoService.listAllTipoEvento());
                System.out.println(tipoEventoService.listAllTipoEvento());
                // // view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
                model.addAttribute("mostrarmsj", true);
                model.addAttribute("successMessage", "Se produjo un error al intentar guardar el registro. Verifique que ha ingresado todos los datos");
                return "eventos/eventoForm";
            }
        } catch (Exception e) {
            model.addAttribute("tiposEvento", tipoEventoService.listAllTipoEvento());
            //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
            model.addAttribute("mostrarmsj", true);
            model.addAttribute("successMessage", "Se produjo un error al intentar guardar el registro. Verifique que ha ingresado todos los datos");
            e.printStackTrace();
            return "eventos/eventoForm";
        }

    }

    @RequestMapping(value = "eventos/a/{f1}/{f2}/10")
    public ModelAndView getFecha(@PathVariable String f1, @PathVariable String f2, Pageable pageable) {
        ModelAndView view = new ModelAndView();
        Date fechaInicio = Fecha.getFecha(f1);
        Date fechaFin = Fecha.getFecha(f2);;
        if (fechaInicio == null || fechaFin == null) {
            view.addObject("eventos", "Las fechas ingresadas tienen un formato incorrecto");
        } else {
//            f1 = Fecha.validarFecha(f1);
//            f2 = Fecha.validarFecha(f2);
//            
//            int dia = 1, mes = 5, anio = 2018;

//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.DAY_OF_MONTH, dia);
//            c.set(Calendar.MONTH, mes);
//            c.set(Calendar.YEAR, anio);
//            
//            fechaInicio = c.getTime();
//            c.set(Calendar.DAY_OF_MONTH, 28);
//            fechafin = c.getTime();
            List<Evento> eventos = eventoService.findEventoByFechaOperacion(fechaInicio, fechaFin, pageable);
            view.addObject("eventos", eventos);
        }
        view.addObject("urlServer", this.urlServer);
        view.setViewName("eventos/eventosLista");
        return view;
    }

    /*
    * nueva accion sobre un evento
     */
    @RequestMapping(value = "/eventos/nuevaAccion", method = RequestMethod.POST)
    public String newPost(@Valid EventoAccion accion, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        accion.setEstado(estadoService.findEstadoById(Long.parseLong("3"))); //se crea en pendiente
        accion.setFechaOperacion(new java.sql.Timestamp(new Date().getTime()));
        // accion.setUsuario(usuarioService.getUsuarioCurrent()); //usuario actual

        System.out.println("Accion");
        System.out.println(accion.toString());
        if (bindingResult.hasErrors()) {

            //view.setViewName("personas/personaForm");
        } else {
            eventoAccionService.saveEventoAccion(accion);
            view.addObject("successMessage", "El registro se ha guardado exitosamente");
            //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
            view.setViewName("eventos/eventoInfo");
        }
        return "redirect:/eventos/" + accion.getEvento().getId();
        //return modelAndView;
    }

}
