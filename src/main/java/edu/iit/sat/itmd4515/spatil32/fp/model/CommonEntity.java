/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.model;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Common Entity used by all domain classes
 *
 * @author dwadekar
 */
@MappedSuperclass
public class CommonEntity {

    /**
     * Used for assign primary ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Used for assign date of creation
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date creationDate;

    /**
     * Default Constructor
     */
    public CommonEntity() {
    }

    /**
     * Used for assign date of creation
     */
    @PrePersist
    @PreUpdate
    protected void doCreationDate() {
        this.creationDate = new Date();
    }

    /**
     * Returns the Set ID for the given object in the class
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the date of creation of account for the given object
     *
     * @return
     */
    public Date getCreationDate() {
        return creationDate;
    }

}
