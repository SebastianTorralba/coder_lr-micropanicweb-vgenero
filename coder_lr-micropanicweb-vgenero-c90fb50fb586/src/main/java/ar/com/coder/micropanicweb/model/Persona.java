/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.coder.micropanicweb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author lgaray
 */
@Entity
@Table(name = "anx_personas")
@ApiModel(value = "Persona", description = "Info Personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "razon_social")
    @NotEmpty(message = "*Debe ingresar Apellido y Nombre")
    @ApiModelProperty(required = true)
    private String razonSocial;

    @Column(name = "nombre_fantasia")
    private String nombreFantasia;

    @Column(name = "dni")
    //@NotEmpty(message = "*Debe ingresar su DNI")
    private String dni;

    @Column(name = "cuil_cuit")
    private String cuil;

    @Column(name = "sexo")
    //@NotEmpty(message = "*Debe ingresar el Sexo")
    private String sexo;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "barrio")
    //@NotEmpty(message = "*Debe ingresar Barrio")
    private String barrio;

    @Column(name = "domicilio")
    //@NotEmpty(message = "*Debe ingresar el Domicilio")
    private String domicilio;

    @Column(name = "nro_casa")
    private String nroCasa;

    @Column(name = "nro_dpto")
    private String nroDpto;

    @Column(name = "tel_cel")
    @NotEmpty(message = "*Debe ingresar un telefono de contacto principal")
    @ApiModelProperty(required = true)
    private String tel;

    @Column(name = "celu_1")
    private String celu1;

    @Column(name = "celu_2")
    private String celu2;

    @Column(name = "celu_3")
    private String celu3;

    @Column(name = "email")
    @Email(message = "*Por favor ingrese un email valido")
    @NotEmpty(message = "*Por favor ingrese un email")
    private String email;

    @Column(name = "foto")
    private String foto;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;
    @ManyToOne
    @JoinColumn(name = "id_tipo_riesgo")
    private TipoRiesgo tipoRiesgo;
    public Long getId() {
        return id;
    }

    public void setId(Long idPersona) {
        this.id = idPersona;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNroCasa() {
        return nroCasa;
    }

    public void setNroCasa(String nroCasa) {
        this.nroCasa = nroCasa;
    }

    public String getNroDpto() {
        return nroDpto;
    }

    public void setNroDpto(String nroDpto) {
        this.nroDpto = nroDpto;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCelu1() {
        return celu1;
    }

    public void setCelu1(String celu1) {
        this.celu1 = celu1;
    }

    public String getCelu2() {
        return celu2;
    }

    public void setCelu2(String celu2) {
        this.celu2 = celu2;
    }

    public String getCelu3() {
        return celu3;
    }

    public void setCelu3(String celu3) {
        this.celu3 = celu3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public TipoRiesgo getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(TipoRiesgo tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", razonSocial=" + razonSocial + ", dni=" + dni + ", email=" + email + ", foto=" + foto + '}';
    }

}
