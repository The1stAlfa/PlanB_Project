/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author AI-Saac
 */
public class Meeting {
    //aps Class Variables 
    private ArrayList<Collaborator> adt_participants; //aditional partcipants
    private WorkTeam team; 
    private ActionPlan action_plan;
 //************************************************************************
    private String name;
    private String acronym;
    private String purpose;
    private LocalDateTime date_created;
    private LocalDateTime date_modified;
    
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
            LocalDate date_created){
        this.name = name;
        this.purpose = purpose;
        
    }

    /**
     *
     * @return
     */
    public ArrayList<Collaborator> getAditionalParticipants() {
        return adt_participants;
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
        return action_plan;
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
        return date_created;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getDateModified() {
        return date_modified;
    }

    /**
     *
     * @param adt_participants
     */
    public void setAditionalParticipants(ArrayList<Collaborator> adt_participants) {
        this.adt_participants = adt_participants;
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
     * @param plan
     */
    public void setActionPlan(ActionPlan action_plan) {
        this.action_plan = action_plan;
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
    public void setDateCreated(LocalDateTime date_created) {
        this.date_created = date_created;
    }

    /**
     *
     * @param date_modified
     */
    public void setDateModified(LocalDateTime date_modified) {
        this.date_modified = date_modified;
    }    
}
