/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CEC
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByPerIdentificacion", query = "SELECT p FROM Personal p WHERE p.perIdentificacion = :perIdentificacion"),
    @NamedQuery(name = "Personal.findByPerNombres", query = "SELECT p FROM Personal p WHERE p.perNombres = :perNombres"),
    @NamedQuery(name = "Personal.findByPerApellidos", query = "SELECT p FROM Personal p WHERE p.perApellidos = :perApellidos"),
    @NamedQuery(name = "Personal.findByPerCargo", query = "SELECT p FROM Personal p WHERE p.perCargo = :perCargo"),
    @NamedQuery(name = "Personal.findByPerCorreo", query = "SELECT p FROM Personal p WHERE p.perCorreo = :perCorreo")})
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "per_identificacion")
    private String perIdentificacion;
    @Column(name = "per_nombres")
    private String perNombres;
    @Column(name = "per_apellidos")
    private String perApellidos;
    @Column(name = "per_cargo")
    private String perCargo;
    @Column(name = "per_correo")
    private String perCorreo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perIdentificacion")
    private Collection<Proyecto> proyectoCollection;

    public Personal() {
    }

    public Personal(String perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public String getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(String perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public String getPerNombres() {
        return perNombres;
    }

    public void setPerNombres(String perNombres) {
        this.perNombres = perNombres;
    }

    public String getPerApellidos() {
        return perApellidos;
    }

    public void setPerApellidos(String perApellidos) {
        this.perApellidos = perApellidos;
    }

    public String getPerCargo() {
        return perCargo;
    }

    public void setPerCargo(String perCargo) {
        this.perCargo = perCargo;
    }

    public String getPerCorreo() {
        return perCorreo;
    }

    public void setPerCorreo(String perCorreo) {
        this.perCorreo = perCorreo;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perIdentificacion != null ? perIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.perIdentificacion == null && other.perIdentificacion != null) || (this.perIdentificacion != null && !this.perIdentificacion.equals(other.perIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.negocio.Personal[ perIdentificacion=" + perIdentificacion + " ]";
    }
    
}
