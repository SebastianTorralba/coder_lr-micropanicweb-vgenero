package ar.com.coder.micropanicweb.service;

import ar.com.coder.micropanicweb.model.Pais;




/**
 *
 * @author lgaray
 */
public interface PaisService{

   public Iterable<Pais> listAllPais();
 
   public  Pais savePais(Pais object);
 
}
