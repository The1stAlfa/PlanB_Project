/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import java.time.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author AI-Saac
 */
public class ActionPlan {
    //aps Class Variables
    private APSummary summary;
    private Collaborator owner;
    private ArrayList<Action> actionList;       
   //************************************************************************ 
    private short id;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private LocalDateTime currentDate;
    private byte execution; // Action Plan porcentage of execution
    private byte zeros;
    private byte initialZeros = 4;
            
    /**
     *
     */
    public ActionPlan(){
        this.actionList = new ArrayList<>();
    }

    /**
     *
     * @param id
     * @param owner
     */
    public ActionPlan(Collaborator owner, short id) {
        this.id = id;
        this.owner = owner;
        this.dateCreated = LocalDateTime.now();
        this.summary = summary;
        actionList = null;
    }

    public APSummary getSummary() {
        return summary;
    }

    public Collaborator getOwner() {
        return owner;
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public short getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public byte getExecution() {
        return execution;
    }
    
    public byte getZeros() {
        return zeros;
    }
    
    public void setSummary(APSummary summary) {
        this.summary = summary;
    }
        
    public void setOwner(Collaborator owner) {
        this.owner = owner;
    }

    public void setActionList(ArrayList<Action> actionList) {
        this.actionList = actionList;
    }

    public void setId(short id) {
        this.id = id;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public void setExecution(byte execution) {
        this.execution = execution;
    }    

    public void setZeros(byte zeros) {
        this.zeros = zeros;
    }
    
    public void setZeros(int number){
        String n = String.valueOf(number); 
        if(n.length() == initialZeros)
            this.zeros = (byte)(initialZeros+(byte)1);
        else if(n.length() > initialZeros)
            this.zeros = (byte)(n.length()+1);
        else
            this.zeros = initialZeros;
    }
    
    public Action searchAction(String key){
        Optional<Action> a = actionList.stream()
        .filter(p -> p.getID().equals(key))
        .findFirst();
        return a.isPresent() ? a.get() : null;
        //return null;
    }
    
    public boolean insertAction(Action action){
        actionList.add(action);
        return true;
    }
    
    //Overload Method
    public ArrayList<Action> searchAction(ActionItemFilter filter, int key){
        if(filter.equals(ActionItemFilter.BY_DURATION)){
            
        }
        return null;
    }
    
    public Boolean deleteAction(String action_id){
        for(Action action:actionList){
            if(action.getID().equalsIgnoreCase(action_id)){
                actionList.remove(action);
                return true;
            }
        }
        return false;
    }
}
