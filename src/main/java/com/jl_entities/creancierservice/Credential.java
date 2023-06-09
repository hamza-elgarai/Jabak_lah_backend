//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2023.06.09 à 11:48:17 AM WEST 
//


package com.jl_entities.creancierservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour credential complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="credential"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentialName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="credentialValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credential", propOrder = {
    "credentialName",
    "credentialValue"
})
public class Credential {

    @XmlElement(required = true)
    protected String credentialName;
    @XmlElement(required = true)
    protected String credentialValue;

    /**
     * Obtient la valeur de la propriété credentialName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialName() {
        return credentialName;
    }

    /**
     * Définit la valeur de la propriété credentialName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialName(String value) {
        this.credentialName = value;
    }

    /**
     * Obtient la valeur de la propriété credentialValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialValue() {
        return credentialValue;
    }

    /**
     * Définit la valeur de la propriété credentialValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialValue(String value) {
        this.credentialValue = value;
    }

}
