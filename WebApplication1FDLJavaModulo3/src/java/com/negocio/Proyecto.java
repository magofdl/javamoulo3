/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CEC
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByProCodigo", query = "SELECT p FROM Proyecto p WHERE p.proCodigo = :proCodigo"),
    @NamedQuery(name = "Proyecto.findByProNombre", query = "SELECT p FROM Proyecto p WHERE p.proNombre = :proNombre"),
    @NamedQuery(name = "Proyecto.findByProObservaciones", query = "SELECT p FROM Proyecto p WHERE p.proObservaciones = :proObservaciones"),
    @NamedQuery(name = "Proyecto.findByProFechareg", query = "SELECT p FROM Proyecto p WHERE p.proFechareg = :proFechareg")})
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pro_codigo")
    private String proCodigo;
    @Basic(optional = false)
    @Column(name = "pro_nombre")
    private String proNombre;
    @Column(name = "pro_observaciones")
    private String proObservaciones;
    @Basic(optional = false)
    @Column(name = "pro_fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proFechareg;
    @JoinColumn(name = "per_identificacion", referencedColumnName = "per_identificacion")
    @ManyToOne(optional = false)
    private Personal perIdentificacion;
    @JoinColumn(name = "cat_codigo", referencedColumnName = "cat_codigo")
    @ManyToOne(optional = false)
    private Catalogo catCodigo;

    public Proyecto() {
    }

    public Proyecto(String proCodigo) {
        this.proCodigo = proCodigo;
    }

    public Proyecto(String proCodigo, String proNombre, Date proFechareg) {
        this.proCodigo = proCodigo;
        this.proNombre = proNombre;
        this.proFechareg = proFechareg;
    }

    public String getProCodigo() {
        return proCodigo;
    }

    public void setProCodigo(String proCodigo) {
        this.proCodigo = proCodigo;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProObservaciones() {
        return proObservaciones;
    }

    public void setProObservaciones(String proObservaciones) {
        this.proObservaciones = proObservaciones;
    }

    public Date getProFechareg() {
        return proFechareg;
    }

    public void setProFechareg(Date proFechareg) {
        this.proFechareg = proFechareg;
    }

    public Personal getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(Personal perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public Catalogo getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(Catalogo catCodigo) {
        this.catCodigo = catCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proCodigo != null ? proCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.proCodigo == null && other.proCodigo != null) || (this.proCodigo != null && !this.proCodigo.equals(other.proCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.negocio.Proyecto[ proCodigo=" + proCodigo + " ]";
    }
    
}
