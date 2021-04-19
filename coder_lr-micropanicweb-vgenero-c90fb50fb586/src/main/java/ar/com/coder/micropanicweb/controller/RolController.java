package ar.com.coder.micropanicweb.controller;

import ar.com.coder.micropanicweb.model.Rol;
import ar.com.coder.micropanicweb.service.RolService;
import ar.com.coder.micropanicweb.service.UsuarioService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RolController {

    @Autowired
    private RolService rolService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired

    @RequestMapping(value = "/roles/nuevo", method = RequestMethod.GET)
    public ModelAndView newGet() {
        ModelAndView view = new ModelAndView();
        Rol rl = new Rol();

        view.addObject("rol", rl);
       //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("roles/rolForm");
        return view;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ModelAndView newPost(@Valid Rol rl, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        //view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        Rol rolExists = rolService.findRolById(rl.getId());
        Rol rolExists2 = rolService.findRolByDenominacion(rl.getDenominacion());
        if (rolExists != null) {
            if (rolExists2 != null) {
                if (rolExists.getId() != rolExists2.getId()) {
                    bindingResult
                            .rejectValue("denominacion", "error.rol",
                                    "Ya existe un rol registrado con la denominacion ingresada");
                }

            }
            if (bindingResult.hasErrors()) {
                // modelAndView.addObject("mostrarmsj", false);
                //modelAndView.addObject("successMessage", "Error");
                //modelAndView.addObject("roles", rolService.listAllRol());
                view.setViewName("roles/rolForm");

            } else {
                rolService.saveRol(rl);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se Modifico exitosamente");
                view.addObject("roles", rolService.listAllRol());
                view.setViewName("roles/rolLista");

            }
        } else {
            if (rolExists2 != null) {
                bindingResult
                        .rejectValue("denominacion", "error.rol",
                                "Ya hay un rol registrado con al denominacion ingresada");
            }

            if (bindingResult.hasErrors()) {

                //modelAndView.addObject("mostrarmsj", false);
                //modelAndView.addObject("successMessage", "Error");
                //modelAndView.addObject("roles", rolService.listAllRol());
                view.setViewName("roles/rolForm");

            } else {
                rolService.saveRol(rl);
                view.addObject("mostrarmsj", true);
                view.addObject("successMessage", "El registro se creo exitosamente");
                view.addObject("roles", rolService.listAllRol());
                view.setViewName("roles/rolLista");

            }

        }
        return view;
    }

    @RequestMapping(value = "/roles")
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.addObject("roles", rolService.listAllRol());
        //view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario
        view.setViewName("roles/rolLista");
        return view;
    }

    @RequestMapping("roles/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView view = new ModelAndView();
        Rol obj = rolService.findRolById(id);
        view.addObject("rol", obj);
       //// view.addObject("usuario", usuarioService.getUsuarioCurrent());//datos del usuario

        view.setViewName("roles/rolForm");
        return view;
    }
}
