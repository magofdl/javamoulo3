/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CEC
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByPrdCodigo", query = "SELECT p FROM Producto p WHERE p.prdCodigo = :prdCodigo"),
    @NamedQuery(name = "Producto.findByPrdNombre", query = "SELECT p FROM Producto p WHERE p.prdNombre = :prdNombre"),
    @NamedQuery(name = "Producto.findByPrdStock", query = "SELECT p FROM Producto p WHERE p.prdStock = :prdStock"),
    @NamedQuery(name = "Producto.findByPrdCosto", query = "SELECT p FROM Producto p WHERE p.prdCosto = :prdCosto"),
    @NamedQuery(name = "Producto.findByPrdPvp", query = "SELECT p FROM Producto p WHERE p.prdPvp = :prdPvp"),
    @NamedQuery(name = "Producto.findByPrdObservaciones", query = "SELECT p FROM Producto p WHERE p.prdObservaciones = :prdObservaciones")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "prd_codigo")
    private String prdCodigo;
    @Basic(optional = false)
    @Column(name = "prd_nombre")
    private String prdNombre;
    @Basic(optional = false)
    @Column(name = "prd_stock")
    private int prdStock;
    @Basic(optional = false)
    @Column(name = "prd_costo")
    private float prdCosto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prd_pvp")
    private Float prdPvp;
    @Column(name = "prd_observaciones")
    private String prdObservaciones;
    @JoinColumn(name = "prv_codigo", referencedColumnName = "prv_codigo")
    @ManyToOne
    private Proveedor prvCodigo;

    public Producto() {
    }

    public Producto(String prdCodigo) {
        this.prdCodigo = prdCodigo;
    }

    public Producto(String prdCodigo, String prdNombre, int prdStock, float prdCosto) {
        this.prdCodigo = prdCodigo;
        this.prdNombre = prdNombre;
        this.prdStock = prdStock;
        this.prdCosto = prdCosto;
    }

    public String getPrdCodigo() {
        return prdCodigo;
    }

    public void setPrdCodigo(String prdCodigo) {
        this.prdCodigo = prdCodigo;
    }

    public String getPrdNombre() {
        return prdNombre;
    }

    public void setPrdNombre(String prdNombre) {
        this.prdNombre = prdNombre;
    }

    public int getPrdStock() {
        return prdStock;
    }

    public void setPrdStock(int prdStock) {
        this.prdStock = prdStock;
    }

    public float getPrdCosto() {
        return prdCosto;
    }

    public void setPrdCosto(float prdCosto) {
        this.prdCosto = prdCosto;
    }

    public Float getPrdPvp() {
        return prdPvp;
    }

    public void setPrdPvp(Float prdPvp) {
        this.prdPvp = prdPvp;
    }

    public String getPrdObservaciones() {
        return prdObservaciones;
    }

    public void setPrdObservaciones(String prdObservaciones) {
        this.prdObservaciones = prdObservaciones;
    }

    public Proveedor getPrvCodigo() {
        return prvCodigo;
    }

    public void setPrvCodigo(Proveedor prvCodigo) {
        this.prvCodigo = prvCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prdCodigo != null ? prdCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.prdCodigo == null && other.prdCodigo != null) || (this.prdCodigo != null && !this.prdCodigo.equals(other.prdCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.negocio.Producto[ prdCodigo=" + prdCodigo + " ]";
    }
    
}
