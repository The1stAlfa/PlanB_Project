/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import java.time.LocalDateTime;

/**
 *
 * @author AI-Saac
 */
public class APSummary {
    private LocalDateTime dateModified;
    private int actionsCancelled;
    private int actions;  // Total of Actions
    private int actionsCompleted;  // Completed after as per plan
    private int actionsCompletedApp;  // Completed app = As Per Plan
    private int actionsInProgress;
    private int actionsNearToDueDay;
    private int actionsOverdue;
    
    /**
     *
     */
    public APSummary(){
        actions = 0;
        actionsCompleted = 0;
        actionsCompletedApp = 0;
        actionsInProgress= 0;
        actionsNearToDueDay = 0;
        actionsOverdue = 0;
        actionsCancelled = 0;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public int getActionsCancelled() {
        return actionsCancelled;
    }

    public int getActions() {
        return actions;
    }

    public int getActionsCompleted() {
        return actionsCompleted;
    }

    public int getActionsCompletedApp() {
        return actionsCompletedApp;
    }

    public int getActionsInProgress() {
        return actionsInProgress;
    }

    public int getActionsNearDueDate() {
        return actionsNearToDueDay;
    }

    public int getActionsOverdue() {
        return actionsOverdue;
    }
    
    public void setDate_modified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public void setActionsCancelled(int actionsCancelled) {
        this.actionsCancelled = actionsCancelled;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public void setActionsCompleted(int actionsCompleted) {
        this.actionsCompleted = actionsCompleted;
    }

    public void setActionsCompletedApp(int actionsCompletedApp) {
        this.actionsCompletedApp = actionsCompletedApp;
    }

    public void setActionsInProgress(int actionsInProgress) {
        this.actionsInProgress = actionsInProgress;
    }

    public void setActionsNearToDueDay(int actionsNearToDueDay) {
        this.actionsNearToDueDay = actionsNearToDueDay;
    }

    public void setActionsOverdue(int actionsOverdue) {
        this.actionsOverdue = actionsOverdue;
    }
}
