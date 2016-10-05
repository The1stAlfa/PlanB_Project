/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aps;

/**
 *
 * @author Jordone3
 */
public enum Status {

    /**
     *
     */
    COMPLETED (1),
    /**
     *
     */
    COMPLETED_APP (2),
    /**
     *
     */
    IN_PROCESS (3),
    /**
     *
     */
    NEAR_DUE_DATE (4),
    /**
     *
     */
    OVERDUE (5),
    /**
     *
     */
    CANCELLED (6),
    /**
     *
     */
    WAITING_TO_START (7);
    
    private int value;
    
    private Status(int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
}
