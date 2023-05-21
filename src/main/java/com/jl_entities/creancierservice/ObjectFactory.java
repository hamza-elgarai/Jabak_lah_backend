//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2023.05.21 à 01:32:29 PM WEST 
//


package com.jl_entities.creancierservice;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jl_entities.creancierservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jl_entities.creancierservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCreancesByCreancierIDRequest }
     * 
     */
    public GetCreancesByCreancierIDRequest createGetCreancesByCreancierIDRequest() {
        return new GetCreancesByCreancierIDRequest();
    }

    /**
     * Create an instance of {@link GetCreancesByCreancierIDResponse }
     * 
     */
    public GetCreancesByCreancierIDResponse createGetCreancesByCreancierIDResponse() {
        return new GetCreancesByCreancierIDResponse();
    }

    /**
     * Create an instance of {@link Creance }
     * 
     */
    public Creance createCreance() {
        return new Creance();
    }

    /**
     * Create an instance of {@link GetAllCreanciersRequest }
     * 
     */
    public GetAllCreanciersRequest createGetAllCreanciersRequest() {
        return new GetAllCreanciersRequest();
    }

    /**
     * Create an instance of {@link GetAllCreanciersResponse }
     * 
     */
    public GetAllCreanciersResponse createGetAllCreanciersResponse() {
        return new GetAllCreanciersResponse();
    }

    /**
     * Create an instance of {@link Creancier }
     * 
     */
    public Creancier createCreancier() {
        return new Creancier();
    }

}
