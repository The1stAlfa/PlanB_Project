/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.sys;

import java.util.ArrayList;

/**
 *
 * @author AI-Saac
 */
public class User {
    private String username = null;
    private String password = null;
    private int employeeID;
    private Role role;
    private String email;
    private ArrayList<String> accessList;
    
    public User() {
    
    }
  
    public User(String username, String password, Role role, 
            ArrayList<String> accessList) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accessList = accessList;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return this.role;
    }

    public ArrayList<String> getAccessList() {
        return accessList;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public int getEmployeeId(){
        return this.employeeID;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAccessList(ArrayList<String> accessList) {
        this.accessList = accessList;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setEmployeeId(int id){
        this.employeeID = id;   
    }
}
