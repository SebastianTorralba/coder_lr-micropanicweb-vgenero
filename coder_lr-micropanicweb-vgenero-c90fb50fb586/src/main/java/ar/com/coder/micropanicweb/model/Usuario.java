package ar.com.coder.micropanicweb.model;

import ar.com.coder.micropanicweb.utils.Encriptar;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "admin_usuarios")
@ApiModel(value = "Usuario", description = "Info Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "username")
    @Length(min = 5, message = "*Su nombre de usuario debe tener al menos 5 caracteres")
    //@NotEmpty(message = "*Por favor ingrese un nombre de usuario")
    @ApiModelProperty(required = true)
    private String username;

    @Column(name = "email")
    @Email(message = "*Por favor ingrese un email valido")
    @NotEmpty(message = "*Por favor ingrese un email")
    @ApiModelProperty(required = true)
    private String email;

    @Column(name = "clave")
    @Length(min = 5, message = "*Su clave debe tener al menos 5 caracteres")
    @NotEmpty(message = "*Por favor ingrese una clave")
    @Transient
    @ApiModelProperty(required = true)
    private String clave;
    //metodo de jorge
    @Column(name = "clave_md5")
    @Transient
    private String claveMd5;

    @Column(name = "estado")
    //0 No activado 1 activado 4 creado desde la web
    private int estado;

    @Column(name = "hash")
    private String hash;
 
    @Column(name = "fecha_creado")
    private Date fechaCreado;
    @Column(name = "reset_token")
    private String resetToken;
    @ManyToMany(cascade = CascadeType.ALL) 
    @JoinTable(name = "admin_usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    @ApiModelProperty(required = true)
    private Set<Rol> roles;

    @OneToOne
    @JoinColumn(name = "id_persona")
    //@ApiModelProperty(required = true)
    private Persona persona;

//    @OneToMany(cascade={CascadeType.ALL})
//    @JoinColumn(name = "id_usuario" )
//    @JsonIgnoreProperties("usuario")
//    private List<Activacion> activaciones;
//    @OneToMany
//    @JoinColumn(name = "id_equipo")
//    @JsonIgnoreProperties("equipo")
//    private Equipo equipo;
    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveMd5() {
        return claveMd5;
    }

    public void setClaveMd5(String claveMd5) {
        this.claveMd5 = claveMd5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getHash() {
        return hash;
    }

    public void setHash() {
        Timestamp fecha = new java.sql.Timestamp(new Date().getTime());
        String preHash = fecha.toString() + username;

        preHash = Encriptar.Encode(preHash);
        String replace = preHash.replace("/", ".");
        this.hash = replace;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", username=" + username + ", email=" + email + ", clave=" + clave + ", estado=" + estado + ", hash=" + hash + ", persona=" + persona.toString() + '}';
    }

}
