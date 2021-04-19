package ar.com.coder.micropanicweb.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "param_paises")
public class Pais implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pais")
    private Long idPais;
    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "codigo_iso")
    private String codigoIso;

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long newIdPais) {
        idPais = newIdPais;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String newDenominacion) {
        denominacion = newDenominacion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String newNacionalidad) {
        nacionalidad = newNacionalidad;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String newCodigoIso) {
        codigoIso = newCodigoIso;
    }

}
