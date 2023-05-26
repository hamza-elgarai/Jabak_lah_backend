//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2023.05.26 à 10:50:23 PM WEST 
//


package com.jl_entities.creancierservice;

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
 *         &lt;element name="credentials" type="{http://www.jl_entities.com/creancierservice}credentials"/&gt;
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
    "credentials"
})
@XmlRootElement(name = "GetImpayesByCreanceIDRequest")
public class GetImpayesByCreanceIDRequest {

    protected long creanceId;
    @XmlElement(required = true)
    protected Credentials credentials;

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
     * Obtient la valeur de la propriété credentials.
     * 
     * @return
     *     possible object is
     *     {@link Credentials }
     *     
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Définit la valeur de la propriété credentials.
     * 
     * @param value
     *     allowed object is
     *     {@link Credentials }
     *     
     */
    public void setCredentials(Credentials value) {
        this.credentials = value;
    }

}
