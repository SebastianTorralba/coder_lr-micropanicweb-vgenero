/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.repository;

import ar.com.coder.micropanicweb.model.Persona;
import ar.com.coder.micropanicweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lgaray
 */
@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Persona findPersonaByDni(String dni);

    public Persona findPersonaById(Long id); 
}
