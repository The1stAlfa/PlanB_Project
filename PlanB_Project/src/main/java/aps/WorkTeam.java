/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import java.util.ArrayList;

/**
 *
 * @author AI-Saac
 */
public class WorkTeam {
    //aps Class Variables
    private ArrayList<Collaborator> members;
    private ArrayList<ActionPlan> plansIdList;
    //************************************************************************
    private short id;
    private byte performance; // Team APP Percentage of performance
    
    /**
     *
     */
    public WorkTeam(){
        
    }
    
    /**
     *
     * @param members
     * @param plansID_list
     */
    public WorkTeam(ArrayList<Collaborator> members, ArrayList<ActionPlan> plansIdList) {
        setId((short)2);
        this.members = members;
        this.plansIdList = plansIdList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Collaborator> getMembers() {
        return members;
    }

    /**
     *
     * @return
     */
    public ArrayList<ActionPlan> getPlansIdList() {
        return plansIdList;
    }

    /**
     *
     * @return
     */
    public short getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public byte getPerformance() {
        return performance;
    }

    /**
     *
     * @param members
     */
    public void setMembers(ArrayList<Collaborator> members) {
        this.members = members;
    }

    /**
     *
     * @param plansID_list
     */
    public void setPlansIdList(ArrayList<ActionPlan> plansIdList) {
        this.plansIdList = plansIdList;
    }

    /**
     *
     * @param id
     */
    public void setId(short id) {
        this.id = id;
    }

    /**
     *
     * @param performance
     */
    public void setPerformance(byte performance) {
        this.performance = performance;
    }
}
