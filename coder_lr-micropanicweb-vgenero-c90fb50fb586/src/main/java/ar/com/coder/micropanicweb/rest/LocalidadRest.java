package ar.com.coder.micropanicweb.rest;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.Localidad;
import ar.com.coder.micropanicweb.service.LocalidadService;
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
 *
 * @author lgaray
 */
@RestController
@RequestMapping("/api")
@ApiModel(value = "Localidad microservice", description = "Api Localidad")
public class LocalidadRest {

    @Autowired
    LocalidadService localidadService;

    @RequestMapping(value = "/localidades/getAll")
    @ApiOperation(value = "Busca todas las localidades", notes = "Retorna todos las localidades")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success - List<Evento>")
        ,@ApiResponse(code = 203, message = "No existen registros")
    })
    public ResponseEntity<List<Evento>> listAll() {
        Response response = new Response();

        List<Localidad> localidades = localidadService.getAllLocalidades();

        if (localidades.isEmpty()) {
            response.setMsg("No existen registros");
            response.setStatus("205");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        response.setStatus("200");
        response.setData(localidades);
        response.setMsg("success");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/localidades/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Find an Localidad", notes = "Find a Localidad by pcia")
    @ApiResponses({
        @ApiResponse(code = 200, message = "success - Localidades")
        ,@ApiResponse(code = 204, message = "No existe la pcia con el id ingresado.")
    })
    public ResponseEntity<?> getLocalidad(@PathVariable("id") Long id) {

        List<Localidad> localidades = localidadService.getLocalidadByIdPcia(id);

        Response response = new Response();

        if (localidades == null) {
            response.setMsg("No existe la Pcia ingresado.");
            response.setStatus("204");

            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

//        response.setData(evento);
        response.setData(localidades);
        response.setMsg("success");
        response.setStatus("200");
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
