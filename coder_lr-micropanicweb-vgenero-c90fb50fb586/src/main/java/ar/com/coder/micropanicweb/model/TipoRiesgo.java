package ar.com.coder.micropanicweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "param_tipos_riego")
public class TipoRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo")
    private int id;
    @Column(name = "denominacion")
    @NotEmpty(message = "*Por Favor Ingrese una Denominacion")
    private String denominacion;
    @Column(name = "codigo")
    @NotEmpty(message = "*Por Favor ingrese un codigo")
    private String codigo;
    @Column(name = "color")
    @NotEmpty(message = "*Por Favor Seleccione un color de la paleta")
    private String color;
    @Column(name = "icono")
    @NotEmpty(message = "*Por Favor suba un incono")
    private String icono;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "TipoRiesgo{" + "id=" + id + ", denominacion=" + denominacion + '}';
    }

}
