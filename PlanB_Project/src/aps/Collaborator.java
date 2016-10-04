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
    private FuntionalArea funtional_area; // Example: Production, Accounting
    //************************************************************************
    private String acronym_name; // This variable is exposed in the Action Plan
    private String charge;       // where the collaborator belongs.
    private int employee_id;
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
     * @param username
     * @param password
     * @param names
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
        return funtional_area;
    }

    public String getAcronymName() {
        return acronym_name;
    }
    
    public String getCharge() {
        return charge;
    }

    public int getEmployeeId() {
        return employee_id;
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
    
    public void setFuntionalArea(FuntionalArea funtional_area) {
        this.funtional_area = funtional_area;
    }

    private void setAcronymName(){
        acronym_name = firstName.substring(0,1);
        String[] names = lastName.split(" ");
        for(String name:names)
            acronym_name = acronym_name+name.substring(0,1).toUpperCase();
    }
    
    public void setAcronymName(String acronym){
        this.acronym_name = acronym;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;

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
