/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eduxs
 */
@Entity
@Table(name = "param_parentezco")
public class Parentezco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_parentezco")
    private int id;
    @Column(name = "denominacion")
    @NotEmpty(message = "*Por Favor Ingrese una Denominacion")
    private String denominacion;

    public Parentezco() {
    }
     public Parentezco(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public String toString(){
        return "Parentezco{" + "id=" + id + ",denominacion=" + denominacion + '}';
    }
}
