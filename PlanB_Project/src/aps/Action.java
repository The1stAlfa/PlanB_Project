package aps;
import java.time.LocalDate;  
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Cell of the logic model which is based the hole system. This class represents
 * an Action Item of an Action Plan.
 * 
 * @author AI-Saac
 */
public class Action {
    //aps Class Variables 
    private Collaborator responsible;
    private ArrayList<Task> tasksList;
    private Status status;
    //************************************************************************
    // Format of a customize Action Plan
    private String itemID;
    private String detail;
    private String comments;
    private String benefit;
    private LocalDate plannedStartDate;  // Equals to APP = As Per Plan
    private LocalDate plannedEndDate;
    private LocalDate realEndDate;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String trackingBy;
    private int duration;
    private byte progress; //action percentage of completion
    private byte daysToDueDate;
    private ArrayList<String> dependencies;  
    
    public Action(){
        
    }
    
    /** 
     * @param facilityId
     * @param meetingAcronymName
     * @param number
     * @param zeros
    */
    public Action(String facilityID, String meetingAcronymName, short number, byte zeros){
        setID(facilityID, meetingAcronymName,number, zeros);
        this.setDateCreated(LocalDateTime.now());
    }
    
    /**
     *
     * @param meetingAcronym
     * @param detail
     * @param responsible
     * @param plannedStartDate
     * @param plannedEndDate
     * @param comments
     * @param benefit
     */
    public Action(String meetingAcronym,String detail, 
            Collaborator responsible, LocalDate plannedStartDate, 
            LocalDate plannedEndDate,String comments, String benefit){
        this.detail = detail;
        this.responsible = responsible;
        this.plannedStartDate = plannedStartDate;
        this.plannedEndDate = plannedEndDate;
        this.comments = comments;
        this.benefit = benefit;
        setStatus(status);
        this.dateCreated = LocalDateTime.now();
    }
     
    /**
     *
     * @return
     */
    public Collaborator getResponsible() {
        return responsible;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }
    
    /**
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     *
     * @return
     */
    public String getID() {
        return itemID;
    }
    
    /**
     *
     * @return
     */
    public String getDetail() {
        return detail;
    }
    
    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
    }
    
    /**
     *
     * @return
     */
    public String getBenefit() {
        return benefit;
    }
    
    /**
     *
     * @return
     */
    public LocalDate getPlannedStartDate() {
        return plannedStartDate;
    }
    
    /**
     *
     * @return
     */
    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }
    
    /**
     *
     * @return
     */
    public LocalDate getRealEndDate() {
        return realEndDate;
    }
    
    /**
     *
     * @return
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    
    /**
     *
     * @return
     */
    public LocalDateTime getDateModified() {
        return dateModified;
    }
    
    /**
     *
     * @return
     */
    public String getTrackingBy() {
        return trackingBy;
    }
    
    /**
     *
     * @return
     */
    public int getDuration() {
        return duration;
    }
    
    /**
     *
     * @return
     */
    public byte getProgress() {
        return progress;
    }
    
    /**
     *
     * @return
     */
    public byte getDaysToDueDate() {
        return daysToDueDate;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getDependencies() {
        return dependencies;
    }
    
    /**
     *
     * @param responsible
     */
    public void setResponsible(Collaborator responsible) {
        this.responsible = responsible;
    }
    
    /**
     *
     * @param tasksList
     */
    public void setTasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }
    
    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     *
     * @param facilityID
     * @param meetingAcronymName
     * @param number
     * @param zeros
     */
    public void setID(String facilityID, String meetingAcronymName, short number, byte zeros) {
        this.itemID = generateId(facilityID, meetingAcronymName, number, zeros);
    }

    public void setID(String id){
        this.itemID = id;
    }
    
    /**
     *
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    /**
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }  
    
    /**
     *
     * @param benefit
     */
    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
    
    /**
     *
     * @param plannedStartDate
     */
    public void setPlannedStartDate(LocalDate plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }
    
    /**
     *
     * @param plannedEndDate
     */
    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }
   
    /**
     *
     * @param realEndDate
     */
    public void setRealEndDate(LocalDate realEndDate) {
        this.realEndDate = realEndDate;
    }

    /**
     *
     * @param dateCreated
     */
    protected void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    /**
     *
     */
    public void setDateModified() {
        this.dateModified = LocalDateTime.now();
    }
    
    /**
     *
     * @param trackingBy
     */
    public void setTrackingBy(String trackingBy) {
        this.trackingBy = trackingBy;
    }
   
    /**
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    /**
     *
     * @param progress
     */
    public void setProgress(byte progress) {
        this.progress = progress;
    }
 
    /**
     *
     * @param daysToDueDate
     */
    public void setDaysToDueDate(byte daysToDueDate) {
        this.daysToDueDate = daysToDueDate;
    }
    
    /**
     *
     */
    public void setDependencies() {
        this.dependencies = null;
    }
    
    public boolean validateDate(String Date){
        return true;
    }
    
    public static String generateId(String facilityID, String meetingAcronymName,short number,byte zeros){
        return String.format("%s%s%0" + zeros + "d", facilityID, meetingAcronymName,
                (int)number + 1);
    }
}
