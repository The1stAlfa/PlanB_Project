/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author AI-Saac
 */
public class Meeting {
    //aps Class Variables 
    private ArrayList<Collaborator> adtParticipants; //aditional partcipants
    private WorkTeam team; 
    private ActionPlan actionPlan;
 //************************************************************************
    private String name;
    private String acronym;
    private String purpose;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    
    public Meeting(){
        
    }
    
    /** 
    * Class Empty constructor.
     * @param name
    */
    public Meeting(String name){
        setAcronym(name);
    }
    
    /**
     *
     * @param name
     * @param purpose
     * @param id
     * @param date_created
     */
    public Meeting(String name, String purpose, String id,
            LocalDateTime dateCreated){
        this.name = name;
        this.purpose = purpose;
        this.dateCreated = dateCreated;
        
    }

    /**
     *
     * @return
     */
    public ArrayList<Collaborator> getAditionalParticipants() {
        return adtParticipants;
    }

    /**
     *
     * @return
     */
    public WorkTeam getTeam() {
        return team;
    }

    /**
     *
     * @return
     */
    public ActionPlan getActionPlan() {
        return actionPlan;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     *
     * @return
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getDateModified() {
        return dateModified;
    }

    /**
     *
     * @param adtParticipants
     */
    public void setAditionalParticipants(ArrayList<Collaborator> adtParticipants) {
        this.adtParticipants = adtParticipants;
    }

    /**
     *
     * @param team
     */
    public void setTeam(WorkTeam team) {
        this.team = team;
    }

    /**
     *
     * @param actionPlan
     */
    public void setActionPlan(ActionPlan actionPlan) {
        this.actionPlan = actionPlan;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param acronym
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    /**
     *
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     *
     * @param date_created
     */
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *
     * @param date_modified
     */
    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }    
}
