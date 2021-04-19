/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.ax.EventoGps;
import ar.com.coder.micropanicweb.service.EventoService;
import ar.com.coder.micropanicweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lgaray
 */
@Controller
public class MainController {

    @Value("${url-server}")
    private String urlServer;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/")
    public String index(Model model) {
        
        EventoGps gps = new EventoGps();
        Iterable<Evento> eventos = eventoService.listAllEvento();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);
      
        model.addAttribute("urlServer",this.urlServer);

        model.addAttribute("eventosGps",eventosGps);
       // model.addAttribute("usuario", usuarioService.getUsuarioCurrent());//datos del usuario

        return "index";
    }

    @RequestMapping("/inicio")
    public String index2(Model model) {
        //model.addAttribute("eventos", eventoService.listAllEvento());
        EventoGps gps = new EventoGps();
        Iterable<Evento> eventos = eventoService.listAllEvento();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);
        
        model.addAttribute("urlServer",this.urlServer);
        model.addAttribute("eventosGps",eventosGps);
       //model.addAttribute("usuario", usuarioService.getUsuarioCurrent());//datos del usuario

        return "index";
    }

    @RequestMapping("/1")
    public String ejemplo1(Model model) {
        return "index2";
    }

    @RequestMapping("/403")
    public String error403() {
        return "error/403";
    }

    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }

}
