/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norman
 */
@Entity
@Table(name = "stat", catalog = "json_stat", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stat.findAll", query = "SELECT s FROM Stat s")
    , @NamedQuery(name = "Stat.findById", query = "SELECT s FROM Stat s WHERE s.id = :id")
    , @NamedQuery(name = "Stat.findByJsonObject", query = "SELECT s FROM Stat s WHERE s.jsonObject = :jsonObject")
    , @NamedQuery(name = "Stat.findByJsonString", query = "SELECT s FROM Stat s WHERE s.jsonString = :jsonString")
    , @NamedQuery(name = "Stat.findByJsonNumber", query = "SELECT s FROM Stat s WHERE s.jsonNumber = :jsonNumber")
    , @NamedQuery(name = "Stat.findByJsonArray", query = "SELECT s FROM Stat s WHERE s.jsonArray = :jsonArray")
    , @NamedQuery(name = "Stat.findByJsonBool", query = "SELECT s FROM Stat s WHERE s.jsonBool = :jsonBool")
    , @NamedQuery(name = "Stat.findByJsonNull", query = "SELECT s FROM Stat s WHERE s.jsonNull = :jsonNull")})
public class Stat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "json_object")
    private int jsonObject;
    @Basic(optional = false)
    @Column(name = "json_string")
    private int jsonString;
    @Basic(optional = false)
    @Column(name = "json_number")
    private int jsonNumber;
    @Basic(optional = false)
    @Column(name = "json_array")
    private int jsonArray;
    @Basic(optional = false)
    @Column(name = "json_bool")
    private int jsonBool;
    @Basic(optional = false)
    @Column(name = "json_null")
    private int jsonNull;

    public Stat() {
    }

    public Stat(Integer id) {
        this.id = id;
    }

    public Stat(Integer id, int jsonObject, int jsonString, int jsonNumber, int jsonArray, int jsonBool, int jsonNull) {
        this.id = id;
        this.jsonObject = jsonObject;
        this.jsonString = jsonString;
        this.jsonNumber = jsonNumber;
        this.jsonArray = jsonArray;
        this.jsonBool = jsonBool;
        this.jsonNull = jsonNull;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(int jsonObject) {
        this.jsonObject = jsonObject;
    }

    public int getJsonString() {
        return jsonString;
    }

    public void setJsonString(int jsonString) {
        this.jsonString = jsonString;
    }

    public int getJsonNumber() {
        return jsonNumber;
    }

    public void setJsonNumber(int jsonNumber) {
        this.jsonNumber = jsonNumber;
    }

    public int getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(int jsonArray) {
        this.jsonArray = jsonArray;
    }

    public int getJsonBool() {
        return jsonBool;
    }

    public void setJsonBool(int jsonBool) {
        this.jsonBool = jsonBool;
    }

    public int getJsonNull() {
        return jsonNull;
    }

    public void setJsonNull(int jsonNull) {
        this.jsonNull = jsonNull;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stat)) {
            return false;
        }
        Stat other = (Stat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "homework.Stat[ id=" + id + " ]";
    }
    
}
