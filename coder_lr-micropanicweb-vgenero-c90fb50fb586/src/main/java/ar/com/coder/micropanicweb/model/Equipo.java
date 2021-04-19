package ar.com.coder.micropanicweb.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author lgaray
 */
@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_equipo")
    private Integer id;
    @Column(name = "modelo")
    @NotEmpty(message = "*Por Favor Ingrese el modelo")
    private String modelo;
    @Column(name = "numero")
    @NotEmpty(message = "*Por Favor Ingrese el numero")
    private String numero;
    @Column(name = "marca")
    private String marca;
    @Column(name = "imei")
    @NotEmpty(message = "*Por Favor Ingrese el IMEI del telefono")
    private String imei;
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
//    @OneToMany(mappedBy = "equipo")
//    private List<Activacion> activaciones;
//    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public List<Activacion> getActivaciones() {
//        return activaciones;
//    }
//
//    public void setActivaciones(List<Activacion> activaciones) {
//        this.activaciones = activaciones;
//    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", modelo=" + modelo + ", numero=" + numero + ", marca=" + marca + ", imei=" + imei + ", fechaAlta=" + fechaAlta + ", estado=" + estado + '}';
    }

   

}
