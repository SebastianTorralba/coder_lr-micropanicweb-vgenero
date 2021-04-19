package ar.com.coder.micropanicweb.model.ax;

import ar.com.coder.micropanicweb.model.EventoAccion;
import java.sql.Timestamp;

/**
 *
 * @author lgaray
 */
public class EventoAccionGps {

    private Integer id;
    private Integer nroAccion;
    private Timestamp fechaOperacion;
    private String observacion;
    private Long eventoId;
    private Integer organismoId;
    private String organismoDenominacion;
    private Long estadoId;
    private String estadoDenominacion;
    private String username;

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

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getOrganismoId() {
        return organismoId;
    }

    public void setOrganismoId(Integer organismoId) {
        this.organismoId = organismoId;
    }

    public String getOrganismoDenominacion() {
        return organismoDenominacion;
    }

    public void setOrganismoDenominacion(String organismoDenominacion) {
        this.organismoDenominacion = organismoDenominacion;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstadoDenominacion() {
        return estadoDenominacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEstadoDenominacion(String estadoDenominacion) {
        this.estadoDenominacion = estadoDenominacion;
    }

    public void setEventoAccion(EventoAccion accion) {
        this.setId(accion.getId());
        this.setNroAccion(accion.getNroAccion());
        this.setFechaOperacion(accion.getFechaOperacion());
        this.setObservacion(accion.getObservacion());
        this.setEventoId(accion.getEvento().getId());
        this.setOrganismoId(accion.getOrganismo().getId());
        this.setOrganismoDenominacion(accion.getOrganismo().getDenominacion());
        this.setEstadoId(accion.getEstado().getId());
        this.setEstadoDenominacion(accion.getEstado().getDenominacion());
        //this.setUsername(accion.getUsuario().getUsername());
    }

}
