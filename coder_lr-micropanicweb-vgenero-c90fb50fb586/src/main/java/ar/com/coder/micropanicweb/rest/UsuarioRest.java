package ar.com.coder.micropanicweb.rest;
 
import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.model.TipoRiesgo;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.repository.RolRepository;
import ar.com.coder.micropanicweb.repository.TipoRiesgoRepository;
import ar.com.coder.micropanicweb.serviceimpl.EmailServices;
import ar.com.coder.micropanicweb.service.UsuarioService;
import ar.com.coder.micropanicweb.utils.Encriptar;
import ar.com.coder.micropanicweb.utils.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Arrays;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author LeoGaray
 */
@RestController
@RequestMapping("/api")
@ApiModel(value = "Usuario microservice", description = "Api para usuario")
public class UsuarioRest {

    public static final Logger logger = LoggerFactory.getLogger(UsuarioRest.class);
    @Autowired
    private UsuarioService userService;
    @Autowired
    private RolRepository roleRepository;
    @Autowired
    private TipoRiesgoRepository riesgoRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    EmailServices emailService; //email

//    @RequestMapping(value = "/usuarios/getAll", method = RequestMethod.GET)
//    @ApiOperation(value = "Find all usuarios", notes = "Return all users")
//    @ApiResponses({
//        @ApiResponse(code = 100, message = "success - List<Usuario>")
//        ,@ApiResponse(code = 105, message = "No existen registros")
//    })
//    public ResponseEntity<List<Usuario>> listAllUsers() {
//        List<Usuario> users = (List<Usuario>) userService.listAllUsuarios();
//        if (users.isEmpty()) {
//            Response response = new Response();
//            response.setMsg("No existen registros");
//            response.setStatus("105");
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        Response response = new Response();
//        response.setStatus("100");
//        response.setData(users);
//        response.setMsg("success");
//        return new ResponseEntity(response, HttpStatus.OK);
//    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Find an Usuario", notes = "Find Usuario by Id")
    @ApiResponses({
        @ApiResponse(code = 100, message = "success - Usuario")
        ,@ApiResponse(code = 106, message = "No existe el usuario con el id ingresado.")
    })
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        Usuario user = userService.findUserById(id);
        if (user == null) {
            logger.error("No existe el usuario con el id ingresado.", id);
            Response response = new Response();
            response.setMsg("No existe el usuario con el id ingresado.");
            response.setStatus("106");

            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        Response response = new Response();
        response.setData(user);
        response.setMsg("success");
        response.setStatus("100");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /*
    * crea usuarios
    **/
    @RequestMapping(value = "/usuarios/create", method = RequestMethod.POST)
    @ApiOperation(value = "Create an Usuario", notes = "Create Usuario")
    @ApiResponses({
        @ApiResponse(code = 100, message = "success - Se creado con exito el usuario")
        ,@ApiResponse(code = 101, message = "Error Interno,Se esta enviando un id cuando se crea el usuario")
        ,@ApiResponse(code = 102, message = "Ya hay un usuario registrado con el correo electrónico proporcionado ")
        ,@ApiResponse(code = 103, message = "Ya hay un usuario registrado con el nombre de usuario proporcionado.")
        ,@ApiResponse(code = 104, message = "Se creado con exito el usuario, pero no se pudo enviar el email")
    })
    public ResponseEntity<?> createUser(@RequestBody Usuario user, UriComponentsBuilder ucBuilder) {

        Response response = new Response();
        if (user.getId() != null) {
            response.setMsg("Error interno");
            response.setStatus("101");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
        Usuario userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            response.setMsg("Ya hay un usuario registrado con el correo electrónico proporcionado " + user.getEmail());
            response.setStatus("102");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
        userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            response.setMsg("Ya hay un usuario registrado con el nombre de usuario proporcionado " + user.getUsername());
            response.setStatus("103");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }

        user.setEstado(0);
        user.setHash();
        Rol userRole = roleRepository.findRolByDenominacion("USER");
        user.setRoles(new HashSet<Rol>(Arrays.asList(userRole)));
        TipoRiesgo riesgo = riesgoRepository.findTipoRiesgoByCodigo("SC");
        user.getPersona().setTipoRiesgo(riesgo);
        
        String claveEmail = user.getClave(); //se utiliza para enviarla por email
        String claveMd5 = Encriptar.getMD5(user.getClave());
        user.setClaveMd5(claveMd5);

        userService.saveUser(user);
        //envio de emailF
        user.setClave(claveEmail);
        Boolean result = emailService.nuevoUsuario(user);
        if (!result) {
           response.setData("success");
           response.setMsg("Se ha creado con exito el usuario, pero no se ha podido enviar el email, debido a un error con el email");
           response.setStatus("104");
        }else{
           response.setData("success");
           response.setMsg("Se ha creado con exito el usuario");
           response.setStatus("100");
       }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /*
    * aactualiza usuarios
    **/
    @RequestMapping(value = "/usuarios/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an Usuario", notes = "Update Usuario by Id")
    @ApiResponses({
        @ApiResponse(code = 100, message = "success - Usuario")
        ,@ApiResponse(code = 104, message = "Error no existe el usuario"),})
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody Usuario user) {

        Response response = new Response();

        Usuario userActual = userService.findUserById(id);

        if (userActual == null) {
            logger.error("Error no existe el usuario", id);
            response.setMsg("Error no existe el usuario");
            response.setStatus("104");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        userActual.setClave(user.getClave());
        userActual.setEmail(user.getEmail());
        userActual.setPersona(user.getPersona());

        userService.saveUser(user);
        response.setData(user);
        response.setMsg("Se actualizo con exito el usuario");
        response.setStatus("100");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/usuarios/login/{username}/{clave}", method = RequestMethod.GET)
    @ApiOperation(value = "Login Usuario", notes = "Login usuario")
    @ApiResponses({
        @ApiResponse(code = 100, message = "success - Usuario")
        ,@ApiResponse(code = 104, message = "Error no existe el usuario"),})
    public ResponseEntity<?> login(@PathVariable("username") String username, @PathVariable("clave") String clave) {
        System.out.println("clave" + clave);

        //clave = bCryptPasswordEncoder.encode(clave);
        System.out.println("nueva clave" + clave);
        Usuario user = userService.findUserByUsernameClave(username, clave);
        Response response = new Response();
        if (user == null) {
            response.setMsg("Error no existe el usuario");
            response.setStatus("104");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        response.setData(user);
        response.setMsg("Se logueo correctamente");
        response.setStatus("100");
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
}
