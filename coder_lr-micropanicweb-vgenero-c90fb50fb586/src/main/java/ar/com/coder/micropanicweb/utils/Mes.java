/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.utils;

/**
 *
 * @author lgaray
 */
public class Mes {
    private Integer orden;
    private String ingles;
    private String mes;

    public Mes(Integer orden, String ingles, String mes) {
        this.orden = orden;
        this.ingles = ingles;
        this.mes = mes;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
}
