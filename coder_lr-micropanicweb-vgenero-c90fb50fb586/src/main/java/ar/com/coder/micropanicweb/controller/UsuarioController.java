package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.model.TipoRiesgo;
import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.repository.RolRepository;
import ar.com.coder.micropanicweb.repository.TipoRiesgoRepository;
import ar.com.coder.micropanicweb.service.RolService;
import ar.com.coder.micropanicweb.service.UsuarioService;
import ar.com.coder.micropanicweb.utils.Encriptar;
import ar.com.coder.micropanicweb.serviceimpl.EmailServices;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    EmailServices emailService; //email
    @Autowired
    private RolRepository roleRepository;
    @Autowired
    private RolService rolService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private TipoRiesgoRepository riesgoRepository;

    @RequestMapping(value = "/usuarios/nuevo", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView view = new ModelAndView();
        Usuario user = new Usuario();

        view.addObject("roles", rolService.listAllRol());
        view.addObject("usuario", user);
        // //view.addObject("usuarioCurrent", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("usuarios/usuarioForm");
        return view;
    }

    @RequestMapping(value = "/usuarios/nuevo", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Usuario user, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        Usuario userExists = usuarioService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.usuario",
                            "Ya hay un usuario registrado con el correo electrónico proporcionado");
        }
        userExists = usuarioService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.usuario",
                            "Ya hay un usuario registrado con el nombre de usuario proporcionado");
        }

        if (bindingResult.hasErrors()) {
            view.addObject("roles", rolService.listAllRol());
            view.setViewName("usuarios/usuarioForm");
        } else {
            user.setUsername(user.getEmail()); //le asignamos como username el email

            String clave = Encriptar.getMD5(user.getClave());
            String claveEmail = user.getClave(); //se utiliza para enviarla por email
            user.setClaveMd5(clave);
            user.setEstado(0);
            Rol userRole = roleRepository.findRolByDenominacion("ADMIN");
            user.setRoles(new HashSet<Rol>(Arrays.asList(userRole)));
            TipoRiesgo riesgo = riesgoRepository.findTipoRiesgoByCodigo("SC");
            user.getPersona().setTipoRiesgo(riesgo);
            user.getPersona().setEmail(user.getEmail());
            user.setHash();

            usuarioService.saveUser(user);
            user.setClave(claveEmail);
            //envio de email
            Boolean result = emailService.nuevoUsuario(user);
            if (!result) {
                view.addObject("msjError", true);
                view.addObject("msjError", "No se pudo enviar email");
            }

            view.addObject("mostrarmsj", true);
            view.addObject("successMessage", "El registro se Modifico exitosamente");

            //view.addObject("usuario", new Usuario());
            //view.addObject("usuarioCurrent", usuarioService.getUsuarioCurrent());//datos del usuario
            view.setViewName("usuarios/usuariosLista");

        }
        return view;
    }

    @RequestMapping(value = "/usuarios")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        //view.addObject("usuarios", usuarioService.listAllUsuarios());
        view.addObject("usuarios", usuarioService.findUserByRol("ADMIN"));
        //view.addObject("usuarioCurrent", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("usuarios/usuariosLista");
        return view;
    }

    @RequestMapping("usuarios/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        Usuario obj = usuarioService.findUserById(id);
        ModelAndView view = new ModelAndView();

        view.addObject("usuario", obj);
        //view.addObject("usuarioCurrent", usuarioService.getUsuarioCurrent());//datos del usuario

        view.setViewName("usuarios/usuarioForm");
        return view;
    }

    @RequestMapping("auth/{hash}")
    public ModelAndView confirmUserEmail(@PathVariable String hash) {
        Usuario user = usuarioService.findUserByHash(hash);
        ModelAndView view = new ModelAndView();
        String msj;
        if (user == null) {
            msj = "Error interno";
        } else {
            user.setEstado(1);
            usuarioService.saveUser(user);
            msj = "Ha confirmado su cuenta de usuario";

        }
        view.addObject("msj", msj);
        //view.addObject("usuarioCurrent", usuarioService.getUsuarioCurrent());//datos del usuario

        view.setViewName("usuarios/usuarioConfirmEmail");
        return view;
    }

// ****************reset pasword********************************
    @RequestMapping(value = "/resetPwd", method = RequestMethod.GET)
    public ModelAndView resetPwd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/resetPwd");
        return modelAndView;
    }

    // Procesamos el reset password desde la pagina
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("username") String username, HttpServletRequest request) {

        // Lookup user in database by e-mail
        Usuario user = usuarioService.findUserByUsername(username);

        if (user == null) {
            modelAndView.addObject("errorMessage", "No encontramos una cuenta para esa dirección de correo electrónico.");
        } else {

            // Generate random 36-character string token for reset password 
            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            usuarioService.saveUser(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();
            System.out.println("Para restablecer su contraseña, haga clic en el siguiente enlace:\n" + appUrl
                    + "/reset?token=" + user.getResetToken());
            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("ncerezo@larioja.gov.ar");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Restablecer contraseña MicroPanic");
            passwordResetEmail.setText("Para restablecer su contraseña, haga clic en el siguiente enlace:\n" + appUrl
                    + "/reset?token=" + user.getResetToken());

            // emailService.sendSimpleMessage(passwordResetEmail);
            // Add success message to view
            modelAndView.addObject("successMessage", "Se envió un enlace de restablecimiento de contraseña a " + user.getEmail());
        }

        modelAndView.setViewName("login/forgotPassword");
        return modelAndView;

    }

    // Display desde el link de reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView,
            @RequestParam("token") String token) {

        Optional<Usuario> user = usuarioService.findUserByResetToken(token);

        if (user.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops! Este es un enlace de restablecimiento de contraseña inválido.");
        }
        modelAndView.setViewName("login/confirmPwd");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView,
            @RequestParam Map<String, String> requestParams,
            RedirectAttributes redir) {
        System.out.println(requestParams.get("token"));
        // Find the user associated with the reset token
        Optional<Usuario> user = usuarioService.findUserByResetToken(requestParams.get("token"));

        // This should always be non-null but we check just in case
        if (user.isPresent()) {

            Usuario resetUser = user.get();
            System.out.println(resetUser.toString());
            // Set new password    
            resetUser.setClave(bCryptPasswordEncoder.encode(requestParams.get("password")));

            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);
            // Save user
            usuarioService.saveUser(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "Ha restablecido su contraseña exitosamente. Ahora puede iniciar sesión.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops! Este es un enlace de restablecimiento de contraseña inválido.");
            modelAndView.setViewName("login/confirmPwd");
        }

        return modelAndView;
    }
    // Ir a la página de reinicio sin un token redirige a la página de inicio de sesión

//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
//        return new ModelAndView("redirect:login");
//    }
}
