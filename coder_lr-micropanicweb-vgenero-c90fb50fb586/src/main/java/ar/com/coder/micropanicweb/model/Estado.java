package ar.com.coder.micropanicweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "param_estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estado")
    private Long id;
    @Column(name = "denominacion")
     @NotEmpty(message = "*Por Favor Ingrese una Denominacion")
    private String denominacion;

    public Estado() {
    }

    public Estado(Long id) {
        this.id = id;
    }

    
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

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", denominacion=" + denominacion + '}';
    }

   

}
