//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2023.05.26 à 05:10:41 PM WEST 
//


package com.jl_entities.creancierservice;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creanceId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="creandentials" type="{http://www.jl_entities.com/creancierservice}credentials" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creanceId",
    "creandentials"
})
@XmlRootElement(name = "GetImpayesByCreanceIDRequest")
public class GetImpayesByCreanceIDRequest {

    protected long creanceId;
    @XmlElement(required = true)
    protected List<Credentials> creandentials;

    /**
     * Obtient la valeur de la propriété creanceId.
     * 
     */
    public long getCreanceId() {
        return creanceId;
    }

    /**
     * Définit la valeur de la propriété creanceId.
     * 
     */
    public void setCreanceId(long value) {
        this.creanceId = value;
    }

    /**
     * Gets the value of the creandentials property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the creandentials property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreandentials().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Credentials }
     * 
     * 
     */
    public List<Credentials> getCreandentials() {
        if (creandentials == null) {
            creandentials = new ArrayList<Credentials>();
        }
        return this.creandentials;
    }

}
