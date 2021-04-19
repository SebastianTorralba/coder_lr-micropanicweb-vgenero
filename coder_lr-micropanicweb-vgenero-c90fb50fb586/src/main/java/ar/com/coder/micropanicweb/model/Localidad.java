package ar.com.coder.micropanicweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "param_localidades")
public class Localidad{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_localidad")
    private Long id;

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "cp")
    private String cp;
 
    @ManyToOne
    @JoinColumn(name = "id_pcia")
    private Provincia provincia;

    
     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia Provincia) {
        this.provincia = Provincia;
    }


}
