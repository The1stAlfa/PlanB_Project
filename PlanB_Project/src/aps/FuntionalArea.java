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
public class FuntionalArea {
    private String name;
    private String acronym;

    public FuntionalArea(String name) {
        this.name = name;
        setAcronym(name);
    }
    
    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
