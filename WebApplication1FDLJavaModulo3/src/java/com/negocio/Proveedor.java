/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByPrvCodigo", query = "SELECT p FROM Proveedor p WHERE p.prvCodigo = :prvCodigo"),
    @NamedQuery(name = "Proveedor.findByPrvNombre", query = "SELECT p FROM Proveedor p WHERE p.prvNombre = :prvNombre"),
    @NamedQuery(name = "Proveedor.findByPrvDireccion", query = "SELECT p FROM Proveedor p WHERE p.prvDireccion = :prvDireccion"),
    @NamedQuery(name = "Proveedor.findByPrvTelefono", query = "SELECT p FROM Proveedor p WHERE p.prvTelefono = :prvTelefono"),
    @NamedQuery(name = "Proveedor.findByPrvCelular", query = "SELECT p FROM Proveedor p WHERE p.prvCelular = :prvCelular")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "prv_codigo")
    private String prvCodigo;
    @Basic(optional = false)
    @Column(name = "prv_nombre")
    private String prvNombre;
    @Basic(optional = false)
    @Column(name = "prv_direccion")
    private String prvDireccion;
    @Basic(optional = false)
    @Column(name = "prv_telefono")
    private String prvTelefono;
    @Basic(optional = false)
    @Column(name = "prv_celular")
    private String prvCelular;
    @OneToMany(mappedBy = "prvCodigo")
    private List<Producto> productoList;

    public Proveedor() {
    }

    public Proveedor(String prvCodigo) {
        this.prvCodigo = prvCodigo;
    }

    public Proveedor(String prvCodigo, String prvNombre, String prvDireccion, String prvTelefono, String prvCelular) {
        this.prvCodigo = prvCodigo;
        this.prvNombre = prvNombre;
        this.prvDireccion = prvDireccion;
        this.prvTelefono = prvTelefono;
        this.prvCelular = prvCelular;
    }

    public String getPrvCodigo() {
        return prvCodigo;
    }

    public void setPrvCodigo(String prvCodigo) {
        this.prvCodigo = prvCodigo;
    }

    public String getPrvNombre() {
        return prvNombre;
    }

    public void setPrvNombre(String prvNombre) {
        this.prvNombre = prvNombre;
    }

    public String getPrvDireccion() {
        return prvDireccion;
    }

    public void setPrvDireccion(String prvDireccion) {
        this.prvDireccion = prvDireccion;
    }

    public String getPrvTelefono() {
        return prvTelefono;
    }

    public void setPrvTelefono(String prvTelefono) {
        this.prvTelefono = prvTelefono;
    }

    public String getPrvCelular() {
        return prvCelular;
    }

    public void setPrvCelular(String prvCelular) {
        this.prvCelular = prvCelular;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prvCodigo != null ? prvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.prvCodigo == null && other.prvCodigo != null) || (this.prvCodigo != null && !this.prvCodigo.equals(other.prvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.negocio.Proveedor[ prvCodigo=" + prvCodigo + " ]";
    }
    
}
