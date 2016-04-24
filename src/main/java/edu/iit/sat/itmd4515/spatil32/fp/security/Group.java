/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Used to store Group information
 *
 * @author dwadekar
 */
@Entity
@Table(name = "sec_group")
public class Group {

    @Id
    private String groupName;
    private String groupDescription;

    @ManyToMany(mappedBy = "userGroups")
    private List<User> groupMembers = new ArrayList<>();

    /**
     * Default Constructor of Class
     */
    public Group() {
    }

    /**
     * Parameterized Constructor
     *
     * @param groupName
     * @param groupDescription
     */
    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    /**
     * Returns the name of Group
     *
     * @return
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the name of Group
     *
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Returns the Group Description
     *
     * @return
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set the Group Description
     *
     * @param groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * Returns the list of Group Members
     *
     * @return
     */
    public List<User> getGroupMembers() {
        return groupMembers;
    }

    /**
     * Set the list of Group Members
     *
     * @param groupMembers
     */
    public void setGroupMembers(List<User> groupMembers) {
        this.groupMembers = groupMembers;
    }

}
