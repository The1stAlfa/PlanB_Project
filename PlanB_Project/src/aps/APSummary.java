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
    private LocalDateTime date_modified;
    private int actions_cancelled;
    private int actions;  // Total of Actions
    private int actions_completed_after_app;  // Completed after as per plan
    private int actions_completed_app;  // Completed app = As Per Plan
    private int actions_in_progress;
    private int actions_near_to_due_day;
    private int actions_overdue;
    
    /**
     *
     */
    public APSummary(){
        actions = 0;
        actions_completed_after_app = 0;
        actions_completed_app = 0;
        actions_in_progress= 0;
        actions_near_to_due_day = 0;
        actions_overdue = 0;
        actions_cancelled = 0;
    }

    public LocalDateTime getDateModified() {
        return date_modified;
    }

    public int getActionsCancelled() {
        return actions_cancelled;
    }

    public int getActions() {
        return actions;
    }

    public int getActionsCompletedAfterApp() {
        return actions_completed_after_app;
    }

    public int getActionsCompletedApp() {
        return actions_completed_app;
    }

    public int getActionsInProgress() {
        return actions_in_progress;
    }

    public int getActionsNearDueDate() {
        return actions_near_to_due_day;
    }

    public int getActionsOverdue() {
        return actions_overdue;
    }
    
    public void setDate_modified(LocalDateTime date_modified) {
        this.date_modified = date_modified;
    }

    public void setActionsCancelled(int actions_cancelled) {
        this.actions_cancelled = actions_cancelled;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public void setActionsCompletedAfterApp(int actions_completed_after_app) {
        this.actions_completed_after_app = actions_completed_after_app;
    }

    public void setActionsCompletedApp(int actions_completed_app) {
        this.actions_completed_app = actions_completed_app;
    }

    public void setActionsInProgress(int actions_in_progress) {
        this.actions_in_progress = actions_in_progress;
    }

    public void setActionsNearToDueDay(int actions_near_to_due_day) {
        this.actions_near_to_due_day = actions_near_to_due_day;
    }

    public void setActionsOverdue(int actions_overdue) {
        this.actions_overdue = actions_overdue;
    }
}
