/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

/**
 *
 * @author AI-Saac
 */
public class Collaborator {
    //aps Class Variables 
    private FuntionalArea funtionalArea; // Example: Production, Accounting
    //************************************************************************
    private String acronymName; // This variable is exposed in the Action Plan
    private String charge;       // where the collaborator belongs.
    private int employeeID;
    private String firstName;
    private String middlename;
    private String lastName;
    
    /** 
    * Class Empty constructor.
    */
    public Collaborator(){
        
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param charge
     */
    public Collaborator(String firstName, String lastName, String charge) {
        // Variables Initialization
        this.firstName = firstName;
        this.lastName = lastName;
        this.charge = charge;
        setAcronymName();
    }

    public FuntionalArea getFuntionalArea() {
        return funtionalArea;
    }

    public String getAcronymName() {
        return acronymName;
    }
    
    public String getCharge() {
        return charge;
    }

    public int getEmployeeId() {
        return employeeID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName(){
        return this.middlename;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setFuntionalArea(FuntionalArea funtionalArea) {
        this.funtionalArea = funtionalArea;
    }

    private void setAcronymName(){
        acronymName = firstName.substring(0,1);
        String[] names = lastName.split(" ");
        for(String name:names)
            acronymName = acronymName+name.substring(0,1).toUpperCase();
    }
    
    public void setAcronymName(String acronym){
        this.acronymName = acronym;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public void setEmployeeId(int employeeID) {
        this.employeeID = employeeID;

    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setMiddleName(String middlename){
        this.middlename = middlename;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
