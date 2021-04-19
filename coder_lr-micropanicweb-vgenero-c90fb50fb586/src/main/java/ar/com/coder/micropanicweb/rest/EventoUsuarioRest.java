/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.rest;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.model.ax.EventoGps;
import ar.com.coder.micropanicweb.utils.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * gestiona los eventos desde la app solo para los usuarios
 *
 * @author lgaray
 */
@RestController
@RequestMapping("/api/usuarios")
@ApiModel(value = "Evento App microservice", description = "Api para eventos desde la app")
public class EventoUsuarioRest extends EventoRest {

    /*
    * crea Evento
    **/
    @RequestMapping(value = "/eventos/create", method = RequestMethod.POST)
    @ApiOperation(value = "Crea un Evento", notes = "Crea Evento con usuario logueado")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success")
        ,@ApiResponse(code = 201, message = "Error Interno")
        ,@ApiResponse(code = 202, message = "Faltan datos")
    })
    public ResponseEntity<?> createEvento(@RequestBody Evento evento, UriComponentsBuilder ucBuilder) {
        Response response = new Response();
        /*al enviar un evento nos logueamos*/
        Usuario user;
        user = userService.findUserByUsernameClave(evento.getUsuario().getUsername(), evento.getUsuario().getClaveMd5());
        if (user == null) {
            response.setMsg("Error al ingresar");
            response.setStatus("104");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        evento.setUsuario(user);

        /*seteamos los datos por defecto al crear el evento*/
        evento.setFechaOperacion(new java.sql.Timestamp(new Date().getTime()));
        evento.setEstado(estadoService.findEstadoById(Long.parseLong("3")));
        evento.setTipoEvento(tipoEventoService.findTipoEventoById(5));
        if (evento.getId() != null) {
            response.setMsg("Error interno");
            response.setStatus("201");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
        if (evento.getEquipo() == null
                || evento.getGpsLat() == null
                || evento.getGpsLon() == null
                || evento.getUsuario() == null) {
            response.setMsg("Faltan datos");
            response.setStatus("202");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
        try {
            eventoService.saveEvento(evento);
            response.setData("success");
            response.setMsg("Se creado con exito el evento");
            response.setStatus("200");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            response.setMsg("Error guardando el evento Catch");
            response.setStatus("201");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

    /*
    *
     */
    @RequestMapping(value = "/eventos/{username}", method = RequestMethod.POST)
    @ApiOperation(value = "Usuario eventos", notes = "Retorna todos los eventos de un usuario")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success - List<Evento>")
        ,@ApiResponse(code = 203, message = "No existen registros")
        ,@ApiResponse(code = 104, message = "Error al ingresar")
        ,@ApiResponse(code = 105, message = "Error interno, los username no coinciden                                                                                                                                                                                                                                            ")
    })
    public ResponseEntity<List<Evento>> listAllUsuario(@PathVariable("username") String username,
            @RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        Response response = new Response();
        /*al enviar un evento nos logueamos*/
        Usuario user = userService.findUserByUsernameClave(usuario.getUsername(), usuario.getClaveMd5());

        if (!username.equals(usuario.getUsername())) {
            response.setMsg("Erro interno, los username no coinciden");
            response.setStatus("105");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        if (user == null) {
            response.setMsg("Error al ingresar");
            response.setStatus("104");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        List<Evento> eventos = (List<Evento>) eventoService.findByUsuario(user);
        if (eventos.isEmpty()) {
            response.setMsg("No existen registros");
            response.setStatus("205");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        EventoGps gps = new EventoGps();
        Iterable<EventoGps> eventosGps = gps.getEventosGps(eventos);

        response.setStatus("200");
        response.setData(eventosGps);
        response.setMsg("success");
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
