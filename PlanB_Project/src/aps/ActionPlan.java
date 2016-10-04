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
    private ArrayList<Action> action_list;       
   //************************************************************************ 
    private short id;
    private LocalDateTime date_created;
    private LocalDateTime date_modified;
    private LocalDateTime current_date;
    private byte execution; // Action Plan porcentage of execution
    private byte zeros;
    private byte initial_zeros = 4;
            
    /**
     *
     */
    public ActionPlan(){
        this.action_list = new ArrayList<>();
    }

    /**
     *
     * @param meetingid
     * @param owner
     */
    public ActionPlan(Collaborator owner, short id) {
        this.id = id;
        this.owner = owner;
        this.date_created = LocalDateTime.now();
        this.summary = summary;
        action_list = null;
    }

    public APSummary getSummary() {
        return summary;
    }

    public Collaborator getOwner() {
        return owner;
    }

    public ArrayList<Action> getActionList() {
        return action_list;
    }

    public short getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return date_created;
    }

    public LocalDateTime getDateModified() {
        return date_modified;
    }

    public LocalDateTime getCurrentDate() {
        return current_date;
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

    public void setActionList(ArrayList<Action> action_list) {
        this.action_list = action_list;
    }

    public void setId(short id) {
        this.id = id;
    }

    public void setDateCreated(LocalDateTime date_created) {
        this.date_created = date_created;
    }

    public void setDateModified(LocalDateTime date_modified) {
        this.date_modified = date_modified;
    }

    public void setCurrentDate(LocalDateTime current_date) {
        this.current_date = current_date;
    }

    public void setExecution(byte execution) {
        this.execution = execution;
    }    

    public void setZeros(byte zeros) {
        this.zeros = zeros;
    }
    
    public void setZeros(int number){
        String n = String.valueOf(number); 
        if(n.length() == initial_zeros)
            this.zeros = (byte)(initial_zeros+(byte)1);
        else if(n.length() > initial_zeros)
            this.zeros = (byte)(n.length()+1);
        else
            this.zeros = initial_zeros;
    }
    
    public Action searchAction(String key){
        Optional<Action> a = action_list.stream()
        .filter(p -> p.getID().equals(key))
        .findFirst();
        return a.isPresent() ? a.get() : null;
        //return null;
    }
    
    public boolean insertAction(Action action){
        action_list.add(action);
        return true;
    }
    
    //Overload Method
    public ArrayList<Action> searchAction(ActionItemFilter filter, int key){
        if(filter.equals(ActionItemFilter.BY_DURATION)){
            
        }
        return null;
    }
    
    public Boolean deleteAction(String action_id){
        for(Action action:action_list){
            if(action.getID().equalsIgnoreCase(action_id)){
                action_list.remove(action);
                return true;
            }
        }
        return false;
    }
}
