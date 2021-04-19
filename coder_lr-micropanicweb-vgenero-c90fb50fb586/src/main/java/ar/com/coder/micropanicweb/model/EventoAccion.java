/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author lgaray
 */
@Entity
@Table(name = "eventos_acciones")
public class EventoAccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_accion")
    private Integer id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_accion")
    private Integer nroAccion;
    
    @Column(name = "fecha_operacion")
    private Timestamp fechaOperacion;
    
    @Column(name = "observacion")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    @JsonIgnoreProperties("eventoAcciones")
    private Evento evento;
    @OneToOne
    @JoinColumn(name = "id_organismo")
    private Organismo organismo;

    @OneToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
   
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroAccion() {
        return nroAccion;
    }

    public void setNroAccion(Integer nroAccion) {
        this.nroAccion = nroAccion;
    }

    public Timestamp getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Timestamp fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    @Override
    public String toString() {
        return "EventoAccion{" + "id=" + id + ", fechaOperacion=" + fechaOperacion + ", observacion=" + observacion + '}';
    }

}
