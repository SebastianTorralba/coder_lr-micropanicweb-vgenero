/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.rest;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.model.ax.EventoGps;
import ar.com.coder.micropanicweb.service.EquipoService;
import ar.com.coder.micropanicweb.service.EstadoService;
import ar.com.coder.micropanicweb.service.EventoService;
import ar.com.coder.micropanicweb.service.PersonaService;
import ar.com.coder.micropanicweb.service.TipoEventoService;
import ar.com.coder.micropanicweb.service.UsuarioService;
import ar.com.coder.micropanicweb.utils.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Permite gestionar los eventos desde la web
 *
 * @author lgaray
 */
@RestController
@RequestMapping("/api")
@ApiModel(value = "Evento WEB microservice", description = "Api para eventos WEB")
public class EventoRest {

    @Autowired
    EventoService eventoService;
    @Autowired
    EquipoService equipoService;
    @Autowired
    EstadoService estadoService;
    @Autowired
    TipoEventoService tipoEventoService;
    @Autowired
    UsuarioService userService;
    @Autowired
    PersonaService personaService;

//    /*
//    * crea Evento
//    **/
//    @RequestMapping(value = "/eventos/create", method = RequestMethod.POST)
//    @ApiOperation(value = "Crea un Evento", notes = "Crea Evento sin loguearse ")
//
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "success")
//        ,@ApiResponse(code = 201, message = "Error Interno")
//        ,@ApiResponse(code = 202, message = "Faltan datos")
//    })
//    public ResponseEntity<?> createEvento(@RequestBody Evento evento, UriComponentsBuilder ucBuilder) {
//        Response response = new Response();
//        /*al enviar un evento nos logueamos*/
////        Usuario user = userService.findUserByUsernameClave(evento.getUsuario().getUsername(), evento.getUsuario().getClaveMd5());
////        if (user == null) {
////            response.setMsg("Error al ingresar");
////            response.setStatus("104");
////            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
////        }
//
//        /*seteamos los datos por defecto al crear el evento*/
//        evento.setFechaOperacion(new java.sql.Timestamp(new Date().getTime()));
//        evento.setEstado(estadoService.findEstadoById(Long.parseLong("3")));
//        evento.setTipoEvento(tipoEventoService.findTipoEventoById(5));
//        if (evento.getId() != null) {
//            response.setMsg("Error interno");
//            response.setStatus("201");
//            return new ResponseEntity(response, HttpStatus.CONFLICT);
//        }
//        if (evento.getEquipo() == null
//                || evento.getGpsLat() == null
//                || evento.getGpsLon() == null
//                || evento.getUsuario() == null) {
//            response.setMsg("Faltan datos");
//            response.setStatus("202");
//            return new ResponseEntity(response, HttpStatus.CONFLICT);
//        }
//        try {
//            eventoService.saveEvento(evento);
//            response.setData("success");
//            response.setMsg("Se creado con exito el evento");
//            response.setStatus("200");
//            return new ResponseEntity(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.setMsg("Error guardando el evento Catch");
//            response.setStatus("201");
//            return new ResponseEntity(response, HttpStatus.CONFLICT);
//        }
//    }

    /*
    *
     */
    @RequestMapping(value = "/eventos/getAll")
    @ApiOperation(value = "Busca Todos eventos", notes = "Retorna todos los eventos")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success - List<Evento>")
        ,@ApiResponse(code = 203, message = "No existen registros")
    })
    public ResponseEntity<List<Evento>> listAll() {
        Response response = new Response();
        /*al enviar un evento nos logueamos*/
        List<Evento> eventos = (List<Evento>) eventoService.listAllEvento();
        EventoGps gps = new EventoGps();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);

        if (eventos.isEmpty()) {
            response.setMsg("No existen registros");
            response.setStatus("205");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        response.setStatus("200");
        response.setData(eventosGps);
        response.setMsg("success");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/eventos/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Find an Evento", notes = "Find Evento by Id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success - Evento")
        ,@ApiResponse(code = 204, message = "No existe el evento con el id ingresado.")
    })
    public ResponseEntity<?> getEvento(@PathVariable("id") Integer id) {

        Evento evento = eventoService.findEventoById(Long.parseLong(String.valueOf(id)));
        EventoGps eventoGps = new EventoGps();
        eventoGps.setEventoGps(evento);

        Response response = new Response();
        /*al enviar un evento nos logueamos*/
//        Usuario user = userService.findUserByUsernameClave(evento.getUsuario().getUsername(), evento.getUsuario().getClave());
//
//        if (user == null) {
//            response.setMsg("Error al ingresar");
//            response.setStatus("104");
//            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//        }
        if (evento == null) {
            response.setMsg("No existe el Eventocon el id ingresado.");
            response.setStatus("204");

            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

//        response.setData(evento);
        response.setData(eventoGps);
        response.setMsg("success");
        response.setStatus("200");
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    //id es el id de la persona
    @RequestMapping(value = "/eventos/username/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Busca Eventos de una persona", notes = "Busca Eventos de una persona")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success - Evento")
        ,@ApiResponse(code = 204, message = "No existe el evento con el id ingresado.")
    })
    public ResponseEntity<?> getEventoUsuario(@PathVariable("id") Long id) {
        Response response = new Response();
        Persona persona = personaService.findPersonaById(id);
        Usuario user = userService.findUserByPersona(persona);
        List<Evento> eventos = (List<Evento>) eventoService.findByUsuario(user);

        EventoGps gps = new EventoGps();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);

        if (eventos.isEmpty()) {
            response.setMsg("No existen registros");
            response.setStatus("205");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        response.setStatus("200");
        response.setData(eventosGps);
        response.setMsg("success");
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
