package ar.com.coder.micropanicweb.model;

/**
 *
 * @author lgaray
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "eventos")
@ApiModel(value = "Evento", description = "Info Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_evento")
    private Long id;

    @GeneratedValue
    @Column(name = "nro_evento")
    private Long nroEvento;

    @Column(name = "fecha_operacion")
    private Timestamp fechaOperacion;
    @Column(name = "gps_lat")
    @NotEmpty(message = "*Por Favor Ingrese las Coordenadas (Lat)")
    @ApiModelProperty(required = true)
    private String gpsLat;
    @Column(name = "gps_lon")
    @NotEmpty(message = "*Por Favor Ingrese las Coordenadas (Long)")
    @ApiModelProperty(required = true)
    private String gpsLon;
    @Column(name = "gps_pres")
    //@NotEmpty(message = "*Por Favor Ingrese las Coordenadas (Pres)")
    private String gpsPres;

    @ManyToOne
    @JoinColumn(name = "id_usuario") //,nullable=false
    @ApiModelProperty(required = true)
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "id_evento")
    @JsonIgnoreProperties("evento")
    private List<EventoAccion> eventoAcciones;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    @ApiModelProperty(required = true)
    private Equipo equipo;
    
    @ManyToOne
    @JoinColumn(name = "id_estado",nullable=false)
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "id_tipo_evento",nullable=false)
    private TipoEvento tipoEvento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Timestamp fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLon() {
        return gpsLon;
    }

    public void setGpsLon(String gpsLon) {
        this.gpsLon = gpsLon;
    }

    public String getGpsPres() {
        return gpsPres;
    }

    public void setGpsPres(String gpsPres) {
        this.gpsPres = gpsPres;
    }

    public Long getNroEvento() {
        return nroEvento;
    }

    public void setNroEvento(Long nroEvento) {
        this.nroEvento = nroEvento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EventoAccion> getEventoAcciones() {
        return eventoAcciones;
    }

    public void setEventoAcciones(List<EventoAccion> eventoAcciones) {
        this.eventoAcciones = eventoAcciones;
    }

    public void addEventoAccion(EventoAccion accion) {
        this.eventoAcciones.add(accion);
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    @Override
    public String toString() {
        return "Evento{" + "id:" + id + ", fechaOperacion:" + fechaOperacion + ", gpsLat:" + gpsLat + ", gpsLon:" + gpsLon + ", gpsPres:" + gpsPres + ", usuario:" + usuario + ", eventoAcciones:" + eventoAcciones + ", equipo:" + equipo + ", estado:" + estado + ", tipoEvento:" + tipoEvento + '}';
    }

}
