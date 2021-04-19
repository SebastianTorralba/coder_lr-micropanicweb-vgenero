package ar.com.coder.micropanicweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anx_parientes")
public class Pariente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pariente")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_parentezco")
    private Parentezco parentezco;

    public Pariente() {
    }

    public Pariente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Parentezco getParentezco() {
        return parentezco;
    }

    public void setParentezco(Parentezco parentezco) {
        this.parentezco = parentezco;
    }
    
     @Override
    public String toString() {
        return "Pariente{" + "id=" + id  + '}';
    }

}
