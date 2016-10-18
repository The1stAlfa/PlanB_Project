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
public class Facility {
    //aps Class Variables 
    private static ArrayList<Collaborator> collaboratorList;
    private static ArrayList<FuntionalArea> funtionalAreas;
    private ArrayList<Meeting> meetings;
    //************************************************************************
    private String id;
    private String name;
    private String acronym;
    private String city;
    
    /** 
    * Class Empty constructor.
    */    
    public Facility(){
        
    }

    public ArrayList<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public ArrayList<FuntionalArea> getFuntional_areas() {
        return funtionalAreas;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getCity() {
        return city;
    }
    
    public ArrayList getMeetingsNames(){
        ArrayList s = new ArrayList();
        for(Meeting meeting:meetings)
            s.add(meeting.getName());
        return s;
    }

    public void setCollaboratorList(ArrayList<Collaborator> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    public void setFuntionalAreas(ArrayList<FuntionalArea> funtionalAreas) {
        this.funtionalAreas = funtionalAreas;
    }

    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public Meeting searchMeeting(String name){
        for(Meeting meeting:meetings){
            if(meeting.getName().equalsIgnoreCase(name))
                return meeting;
        }
        return null;
    }
    
    public Collaborator searchCollaborator(String hint, byte type){
        if(type == 1){
            for(Collaborator collaborator: this.getCollaboratorList()){
                String collaborator_names = collaborator.getFirstName()
                        +" "+ collaborator.getLastName();
                if(collaborator_names.equalsIgnoreCase(hint))
                    return collaborator;
            }
        }
        else if(type == 2){
            for(Collaborator collaborator: this.getCollaboratorList()){
                if(collaborator.getAcronymName().equalsIgnoreCase(hint))
                    return collaborator;
            }
        }
        return null;
    }
    
    public Collaborator searchCollaborator(int employeeID){
        for(Collaborator collaborator: this.getCollaboratorList()){
            if(collaborator.getEmployeeId() == employeeID)
                return collaborator;
        }
        return null;
    }
}
