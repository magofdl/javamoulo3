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
@Table(name = "catalogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c"),
    @NamedQuery(name = "Catalogo.findByCatCodigo", query = "SELECT c FROM Catalogo c WHERE c.catCodigo = :catCodigo"),
    @NamedQuery(name = "Catalogo.findByCatDescripcion", query = "SELECT c FROM Catalogo c WHERE c.catDescripcion = :catDescripcion")})
public class Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cat_codigo")
    private String catCodigo;
    @Column(name = "cat_descripcion")
    private String catDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catCodigo")
    private Collection<Proyecto> proyectoCollection;

    public Catalogo() {
    }

    public Catalogo(String catCodigo) {
        this.catCodigo = catCodigo;
    }

    public String getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(String catCodigo) {
        this.catCodigo = catCodigo;
    }

    public String getCatDescripcion() {
        return catDescripcion;
    }

    public void setCatDescripcion(String catDescripcion) {
        this.catDescripcion = catDescripcion;
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
        hash += (catCodigo != null ? catCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.catCodigo == null && other.catCodigo != null) || (this.catCodigo != null && !this.catCodigo.equals(other.catCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.negocio.Catalogo[ catCodigo=" + catCodigo + " ]";
    }
    
}
