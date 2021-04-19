/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb;

import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.service.UsuarioService;
import ar.com.coder.micropanicweb.utils.Encriptar;
import ar.com.coder.micropanicweb.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lgaray
 */
public class Micropanicweb {
 @Autowired
    private UsuarioService userService;
    public static void main(String[] args) {
          String claveMd5 = Encriptar.getMD5("twoboot");
          System.out.println(claveMd5);
         
    }
}
