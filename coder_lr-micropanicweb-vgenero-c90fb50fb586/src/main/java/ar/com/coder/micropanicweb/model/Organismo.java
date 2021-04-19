package ar.com.coder.micropanicweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "param_organismo")
public class Organismo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_organismo")
    private int id;
    @Column(name = "denominacion")
    private String denominacion;
    
    
    @Column(name = "tel")
    @NotEmpty(message = "*Debe ingresar un numero de Telefono")
    private String telefono;
    
    @Column(name = "direccion")
    @NotEmpty(message = "*Debe ingresar una direccion")
    private String direccion;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Organismo{" + "id=" + id + ", denominacion=" + denominacion + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }

}