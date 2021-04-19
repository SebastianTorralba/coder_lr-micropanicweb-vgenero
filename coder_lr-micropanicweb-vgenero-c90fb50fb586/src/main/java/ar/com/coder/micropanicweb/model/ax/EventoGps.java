/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.model.ax;

import ar.com.coder.micropanicweb.model.Evento;
import ar.com.coder.micropanicweb.model.EventoAccion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author lgaray
 */
public class EventoGps {

    /*datos del vento*/
    private Long id;
    private Long nroEvento;
    private Timestamp fechaOperacion;
    private String gpsLat;
    private String gpsLon;
    private String gpsPres;
    //private List<EventoAccion> eventoAcciones;
    //private Equipo equipo;
    private String estadoEvento;
    private String tipoEvento;
    /*datos del usuario*/
    private Integer idUsuario;
    private String username;
    private String email;
//    private Integer estadoUsuario;
//    private String rol;
    /*datos de la persona*/
    private Long idPersona;
    private String razonSocial;
    private String dni;
    private String foto;
    private String tipoRiesgo;
    private String icono;
//    private String barrio;
//    private String domicilio;
//    private String nroCasa;
//    private String nroDpto;
    private String tel;
//    private String celu1;
//    private String celu2;
//    private String celu3;
//    private String localidad;
    private List<EventoAccionGps> eventoAccionesGps;

    public EventoGps() {
        this.eventoAccionesGps =  new ArrayList();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Long getNroEvento() {
        return nroEvento;
    }

    public void setNroEvento(Long nroEvento) {
        this.nroEvento = nroEvento;
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

    public String getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(String tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

//    public Integer getEstadoUsuario() {
//        return estadoUsuario;
//    }
//
//    public void setEstadoUsuario(Integer estadoUsuario) {
//        this.estadoUsuario = estadoUsuario;
//    }
//
//    public String getRol() {
//        return rol;
//    }
//
//    public void setRol(String rol) {
//        this.rol = rol;
//    }
//    public String getBarrio() {
//        return barrio;
//    }
//
//    public void setBarrio(String barrio) {
//        this.barrio = barrio;
//    }
//
//    public String getDomicilio() {
//        return domicilio;
//    }
//
//    public void setDomicilio(String domicilio) {
//        this.domicilio = domicilio;
//    }
//
//    public String getNroCasa() {
//        return nroCasa;
//    }
//
//    public void setNroCasa(String nroCasa) {
//        this.nroCasa = nroCasa;
//    }
//
//    public String getNroDpto() {
//        return nroDpto;
//    }
//
//    public void setNroDpto(String nroDpto) {
//        this.nroDpto = nroDpto;
//    }
//    public String getCelu1() {
//        return celu1;
//    }
//
//    public void setCelu1(String celu1) {
//        this.celu1 = celu1;
//    }
//
//    public String getCelu2() {
//        return celu2;
//    }
//
//    public void setCelu2(String celu2) {
//        this.celu2 = celu2;
//    }
//
//    public String getCelu3() {
//        return celu3;
//    }
//    public void setCelu3(String celu3) {
//        this.celu3 = celu3;
//    }
//
//    public String getLocalidad() {
//        return localidad;
//    }
//
//    public void setLocalidad(String localidad) {
//        this.localidad = localidad;
//    }
    public List<EventoAccionGps> getEventoAccionesGps() {
        return eventoAccionesGps;
    }

    public void setEventoAccionesGps(List<EventoAccionGps> eventoAcciones) {
        this.eventoAccionesGps = eventoAcciones;
    }

    public void addEventoAccionGps(EventoAccion accion) {
        EventoAccionGps gps = new EventoAccionGps();
        gps.setEventoAccion(accion);
        this.eventoAccionesGps.add(gps);
    }

    public void setEventoGps(Evento evento) {

        this.setId(evento.getId());
        this.setNroEvento(evento.getNroEvento());
        this.setFechaOperacion(evento.getFechaOperacion());
        this.setGpsLat(evento.getGpsLat());
        this.setGpsLon(evento.getGpsLon());
        this.setGpsPres(evento.getGpsPres());
        this.setEstadoEvento(evento.getEstado().getDenominacion());
        this.setTipoEvento(evento.getTipoEvento().getDenominacion());

        this.setIdUsuario(evento.getUsuario().getId());
        this.setUsername(evento.getUsuario().getUsername());
        this.setEmail(evento.getUsuario().getEmail());
//        this.setEstadoUsuario(evento.getUsuario().getEstado());

        this.setIdPersona(evento.getUsuario().getPersona().getId());
        this.setRazonSocial(evento.getUsuario().getPersona().getRazonSocial());
        this.setDni(evento.getUsuario().getPersona().getDni());
        this.setFoto(evento.getUsuario().getPersona().getFoto());
        this.setTipoRiesgo(evento.getUsuario().getPersona().getTipoRiesgo().getDenominacion());
        this.setIcono(evento.getUsuario().getPersona().getTipoRiesgo().getIcono());
//        this.setBarrio(evento.getUsuario().getPersona().getBarrio());
//        this.setDomicilio(evento.getUsuario().getPersona().getDomicilio());
//        this.setNroCasa(evento.getUsuario().getPersona().getNroCasa());
//        this.setNroDpto(evento.getUsuario().getPersona().getNroDpto());
        this.setTel(evento.getUsuario().getPersona().getTel());
//        this.setCelu1(evento.getUsuario().getPersona().getCelu1());
//        this.setCelu2(evento.getUsuario().getPersona().getCelu2());
//        this.setCelu3(evento.getUsuario().getPersona().getCelu3());
//        this.setLocalidad(evento.getUsuario().getPersona().getLocalidad().getDenominacion());
        if (evento.getEventoAcciones() != null) {
            for (Iterator<EventoAccion> iterator = evento.getEventoAcciones().iterator(); iterator.hasNext();) {
                EventoAccion next = iterator.next();
                this.addEventoAccionGps(next);
            }
        }
    }

    public List<EventoGps> getEventosGps(Iterable<Evento> eventos) {
        List<EventoGps> eventosGps = new ArrayList();
        for (Iterator<Evento> iterator = eventos.iterator(); iterator.hasNext();) {
            Evento next = iterator.next();
            EventoGps gps = new EventoGps();
            gps.setEventoGps(next);

            eventosGps.add(gps);
        }
        return eventosGps;
    }

    @Override
    public String toString() {
        return "EventoGps{" + "id=" + id + ", nroEvento=" + nroEvento + ", fechaOperacion=" + fechaOperacion + ", gpsLat=" + gpsLat + ", gpsLon=" + gpsLon + ", gpsPres=" + gpsPres + '}';
    }

}
