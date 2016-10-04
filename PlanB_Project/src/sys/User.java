/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys;

import java.util.ArrayList;

/**
 *
 * @author AI-Saac
 */
public class User {
    private String username = null;
    private String password = null;
    private int employeeId;
    private Role role;
    private String email;
    private ArrayList<String> access_list;
    
    public User() {
    
    }
  
    public User(String username, String password, Role role, 
            ArrayList<String> access_list) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.access_list = access_list;
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

    public ArrayList<String> getAccess_list() {
        return access_list;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public int getEmployeeId(){
        return this.employeeId;
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

    public void setAccess_list(ArrayList<String> access_list) {
        this.access_list = access_list;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setEmployeeId(int id){
        this.employeeId = id;   
    }
}
