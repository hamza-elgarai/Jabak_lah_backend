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
 * <p>Classe Java pour ImpayeSoap complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ImpayeSoap"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="isPaid" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpayeSoap", propOrder = {
    "id",
    "name",
    "price",
    "isPaid",
    "date",
    "type"
})
public class ImpayeSoap {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    protected double price;
    protected boolean isPaid;
    protected long date;
    @XmlElement(required = true)
    protected String type;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété price.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Définit la valeur de la propriété price.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Obtient la valeur de la propriété isPaid.
     * 
     */
    public boolean isIsPaid() {
        return isPaid;
    }

    /**
     * Définit la valeur de la propriété isPaid.
     * 
     */
    public void setIsPaid(boolean value) {
        this.isPaid = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     */
    public long getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     */
    public void setDate(long value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
