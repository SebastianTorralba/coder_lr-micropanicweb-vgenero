/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.rest;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.service.ReporteService;
import ar.com.coder.micropanicweb.utils.Fecha;
import ar.com.coder.micropanicweb.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lgaray
 */
@RestController
@RequestMapping("/api")
@ApiModel(value = "Usuario microservice", description = "Api para usuario")
public class ReportEventosRest {

    @Autowired
    ReporteService reporteService;

    @RequestMapping(value = "/reportes/ppal")
    public ResponseEntity<List<Evento>> listAll() throws JsonProcessingException, IOException {
        Response response = new Response();
//        Date fechaInicio = Fecha.getFecha("2018-01-30");
//        Date fechaFin = Fecha.getFecha("2018-01-01");;
//
        List<String> elementos = reporteService.findReportEventobyFechaTipo();
        ArrayList<String> meses = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < elementos.size(); i++) {
           
        }
        
        
        
        
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(elementos);

        List<String> myObjects = Arrays.asList(mapper.readValue(json, String[].class));
        
        // System.out.println(elementos);
//        if (elementos.isEmpty()) {
//            response.setMsg("No existen registros");
//            response.setStatus("205");
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
        response.setStatus("200");
        response.setData(elementos);
        response.setMsg("success");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ApiOperation(value = "Find an Usuario", notes = "Find Usuario by Id")
    @ApiResponses({
        @ApiResponse(code = 100, message = "success - Usuario")
        ,@ApiResponse(code = 106, message = "No existe el usuario con el id ingresado.")
    })
    public ResponseEntity<?> getUser() {

        Response response = new Response();
        response.setData("leo");
        response.setMsg("success");
        response.setStatus("100");
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
