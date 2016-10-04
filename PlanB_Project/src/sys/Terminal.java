/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AI-Saac
 */
package sys;

import aps.APSummary;
import aps.ActionItemFilter;
import aps.ActionPlan;
import aps.Collaborator;
import aps.Facility;
import aps.Meeting;
import aps.Organization;
import aps.Status;
import aps.Action;
import aps.WorkTeam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class Terminal{
    private byte id;
    private User user;
    private final DataBase planB;
    private static final String SALT = "MtO27:37";
    private final Organization org;
    
    public Terminal() throws Exception {
        user = new User();
        planB = new DataBase();
        org = Aps.getOrganization();
        boolean con = planB.connection();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    
    public ArrayList defineAccessList(String accesses){
        ArrayList<String> list = new ArrayList();
	char comparator = '-';
	int counter = -1;

        for(int i=0;i<accesses.length();i++){
            if(accesses.charAt(i) == comparator){
                list.add(accesses.substring(counter+1,i));
                counter = i;	
            }
        }
        return list;
    }
   
    public void loadBusinessInformation() throws Exception{
        String query;
        Facility facility = new Facility();
        
        query = "SELECT facilityId, name, acronym, city FROM planb.facility INNER JOIN "
                + "planb.facility_collaborator ON facilityId = facility_id AND "
                + "collaborator_id="+(int)user.getEmployeeId()+";";
        planB.connection();
        ResultSet result = planB.selectQuery(query);
        result.next();
        if(result != null){
            facility.setId(result.getString("facilityId"));
            facility.setName(result.getString("name"));
            facility.setAcronym(result.getString("acronym"));
            facility.setCity(result.getString("city"));
            facility.setCollaboratorList(getCollaborators(facility.getId()));
            facility.setMeetings(getMeetings(facility));
            org.getFacilities().add(facility);
            
        }
        planB.disconnection();
        
    }
    
    public boolean login(String username, String password) throws NoSuchAlgorithmException, SQLException, Exception{
        boolean isAuthenticated = false;
        int count;
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);

        String query = "SELECT COUNT(*) AS isUser FROM planb.user WHERE username='"
                +username+"' AND password='"+hashedPassword+"';";
        planB.connection();
        ResultSet result = planB.selectQuery(query);
       if(result != null){
            result.next();
            count = result.getInt("isUser");
            if(count == 1){
                isAuthenticated = true;
                query = "SELECT email,role from planb.user where username='"+username+"';";
                result = planB.selectQuery(query);
                result.next();
                user.setUsername(username);
                user.setEmail(result.getString("email"));
                user.setRole(getRole(result.getInt("role")));
                query = "SELECT collaborator_id FROM planb.user_collaborator WHERE username='"+username+"';";
                result = planB.selectQuery(query);
                result.next();
                user.setEmployeeId(result.getInt("collaborator_id"));
            }
       }
        planB.disconnection();
        return isAuthenticated;
    }
    
    public boolean signup(String username, String password) throws NoSuchAlgorithmException{
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return false;
    }
    
    public Object[] getTableContent(ActionItemFilter filter, String meetingName) throws Exception{
        String id, responsible, date, status, duration;
        ActionPlan plan;
        Facility facility = org.getFacility("01");
        Meeting meeting = facility.searchMeeting(meetingName);
        
        if(meeting!= null)
            plan = meeting.getActionPlan();
        else
            plan = new ActionPlan();
        ArrayList<Action> list = new ArrayList();
        DefaultTableModel dm = new DefaultTableModel(null, new String [] {
                "ID","Resp.", "Detail", "Comments", 
                "P.Start Date", "P.End Date", "R.End Date",
                "Prog. %", "Status", "Dur."
            }){
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
        
        if(filter.equals(ActionItemFilter.ALL)){
            String query = "SELECT actionId,itemId,detail,comments,plannedStartDate,"
                    + "plannedEndDate, realEndDate,progress,status,"
                    + "timestampdiff(day,plannedStartDate,plannedEndDate) AS duration "
                    + "FROM planb.action INNER JOIN planb.actionplan_action ON "
                    + "actionId=action_id and isDeleted=0 and actionPlan_id="+plan.getId()+";";
            planB.connection();
            ResultSet result = planB.selectQuery(query);
            if(result != null){
                while(result.next()){
                    Action action = new Action();
                    Vector row = new Vector();
                    id = result.getString("actionId");
                    row.add(result.getString("itemId"));
                    action.setID(result.getString("itemId"));
                    responsible = getOwnerAcronym(id);
                    row.add(responsible);
                    action.setResponsible(getCollaborator(facility.getId(),responsible));
                    row.add(result.getString("detail"));
                    action.setDetail(result.getString("detail"));
                    row.add(result.getString("comments"));
                    action.setComments(result.getString("comments"));
                    date = result.getString("plannedStartDate");
                    row.add(date);
                    action.setPlannedStartDate(parseDate(date));
                    date = result.getString("plannedEndDate");
                    row.add(date);
                    action.setPlannedEndDate(parseDate(date));
                    date = result.getString("realEndDate");
                    row.add(date);
                    action.setRealEndDate(parseDate(date));
                    row.add(result.getString("progress"));
                    action.setProgress((byte) Integer.parseInt(result.getString("progress")));
                    status = getStatusName(result.getInt("status")); 
                    row.add(status);
                    action.setStatus(Status.valueOf(status));
                    duration = result.getString("duration"); 
                    row.add(duration);
                    if(duration == null)
                        action.setDuration((byte)0);
                    else
                        action.setDuration((byte) Integer.parseInt(duration));
                    row.add(null);
                    row.add(null);
                    dm.addRow(row);
                    list.add(action);
                }
                plan.setActionList(list);
            }
            planB.disconnection();
        }
        return new Object[]{meeting,dm};
    }
    
    public String getOwnerAcronym(String actionID) throws Exception{
        String query = "SELECT acronymName FROM planb.collaborator INNER JOIN"
                + " planb.collaborator_action ON employeeId=collaborator_id"
                + " AND action_id ="+actionID+";";
        planB.connection();
        ResultSet result = planB.selectQuery(query);
        result.next();
        String acronym = result.getString("acronymName"); 
        planB.disconnection();
        return acronym;
    }
    
    public String getStatusName(int status){
        Status st[] = Status.values();
        for(Status s:st){
            if(s.getValue()==status)
                return s.toString();
        }
        return null;
    }
    
    public Collaborator getCollaborator(String facilityID, String collaboratorAcronym){
        Facility facility = org.getFacility(facilityID);
        return facility.searchCollaborator(collaboratorAcronym,(byte) 2);
    }
    
    public static LocalDate parseDate(String date){
        if(date != null)
            return LocalDate.parse(date);
        return null;
    }
    
    private static String generateHash(String input) throws NoSuchAlgorithmException{
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++)
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        
        return sb.toString();
    }
    
    private Role getRole(int roleValue){
        Role roles[] = Role.values();
        for(Role role:roles){
            if(role.getValue() == roleValue)
                return role;
        }
        return null;
    }
    
    private ArrayList getCollaborators(String facilityID) throws SQLException{
        int collaboratorID,count=0;
        String query;
        Object[] results;
        ArrayList<Collaborator> list = new ArrayList();
        
        results = getCollaboratorIds(facilityID);
        if(results != null){
            while(count != results.length){
                collaboratorID = (int)results[count];
                query = "SELECT firstname, middlename,lastname,acronymName,charge"
                        + " FROM planb.collaborator WHERE employeeId="+collaboratorID+";";
                ResultSet result = planB.selectQuery(query);
                while(result.next()){
                    Collaborator collaborator = new Collaborator();
                    collaborator.setEmployeeId(collaboratorID);
                    collaborator.setFirstName(result.getString("firstname"));
                    collaborator.setMiddleName(result.getString("middlename"));
                    collaborator.setLastName(result.getString("lastname"));
                    collaborator.setCharge(result.getString("charge"));
                    collaborator.setAcronymName(result.getString("acronymName"));
                    list.add(collaborator);                
                }
                count++;
            }
            return list;
        }
        return null;
    }
    
    private ArrayList getMeetings(Facility facility) throws SQLException{
        int count=0;
        String query;
        Object[] results;
        ArrayList<Meeting> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        results = getMeetingIds(facility.getId());      
        if(results != null){
            while(count != results.length){
                query = "SELECT name,acronym,purpose,dateCreated FROM planb.meeting"
                        + " where meetingId="+(int)results[count]+";";
                ResultSet result = planB.selectQuery(query);
                result.next();
                Meeting meeting = new Meeting();
                meeting.setName(result.getString("name"));
                meeting.setAcronym(result.getString("acronym"));
                meeting.setPurpose(result.getString("purpose"));
                meeting.setDateCreated(LocalDateTime.parse(result.getString("dateCreated").substring(0,19),formatter));
                meeting.setActionPlan(getActionPlan(Integer.parseInt(results[count].toString())));
                meeting.setTeam(getTeam((int)results[count],facility));
                list.add(meeting);   
                count++;
            }
            return list;
        }
        return null;
    }
    
    private ActionPlan getActionPlan(int meetingID) throws SQLException{
        String query;
        ActionPlan actionPlan = new ActionPlan();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        query = "SELECT actionPlanId, dateCreated, dateModified, execution "
                + "FROM planb.actionplan INNER JOIN  planb.meeting_actionplan "
                + "ON actionPlanId=actionPlan_id AND meeting_id='"+meetingID+"';";

        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            rs.next();
            actionPlan.setId((short) rs.getInt("actionPlanId"));
            actionPlan.setDateCreated(LocalDateTime.parse(rs.getString("dateCreated").substring(0,19), formatter));
            actionPlan.setDateModified(LocalDateTime.parse(rs.getString("dateModified").substring(0,19), formatter));
            actionPlan.setExecution((byte)rs.getInt("execution"));
            query = "SELECT dateModified, actions, actionsCancelled,"
                + "actionsCompletedAfterApp,actionsCompletedApp,"
                + "actionsInProgress,actionsNearToDueDay,actionsOverdue "
                + "FROM planb.apsummary INNER JOIN planb.actionplan_apsummary ON "
                + "apSumaryId=apSummary_id and actionPlan_id="+actionPlan.getId()+";";
            rs = planB.selectQuery(query);
            if(rs != null){
                rs.next();
                APSummary apsummary = new APSummary();
                apsummary.setDate_modified(LocalDateTime.parse(rs.getString("dateModified").substring(0,19), formatter));
                apsummary.setActions(rs.getInt("actions"));
                apsummary.setActionsCompletedAfterApp(rs.getInt("actionsCompletedAfterApp"));
                apsummary.setActionsCompletedApp(rs.getInt("actionsCompletedApp"));
                apsummary.setActionsInProgress(rs.getInt("actionsInProgress"));
                apsummary.setActionsNearToDueDay(rs.getInt("actionsNearToDueDay"));
                apsummary.setActionsCancelled(rs.getInt("actionsCancelled"));
                apsummary.setActionsOverdue(rs.getInt("actionsOverdue"));
                actionPlan.setZeros(rs.getInt("actions"));
                actionPlan.setSummary(apsummary);
                query = "SELECT employeeId, firstname, middlename, lastname, "
                        + "acronymName, charge FROM planb.collaborator INNER JOIN"
                        + " planb.collaborator_actionplan ON "
                        + "employeeID=collaborator_id AND actionPlan_id="+(int)actionPlan.getId()+";";
                rs = planB.selectQuery(query);
                if(rs != null){
                    rs.next();
                    Collaborator owner = new Collaborator();
                    owner.setEmployeeId(rs.getInt("employeeId"));
                    owner.setFirstName(rs.getString("firstname"));
                    owner.setMiddleName(rs.getString("middlename"));
                    owner.setLastName(rs.getString("lastname"));
                    owner.setAcronymName(rs.getString("acronymName"));
                    owner.setCharge(rs.getString("charge"));
                    actionPlan.setOwner(owner);
                    actionPlan.setCurrentDate(LocalDateTime.now());
                }
            }
            return actionPlan;
        }
        return null;
    }
    
    private Object[] getMeetingIds(String facilityID) throws SQLException{
        String query;
        ArrayList list = new ArrayList();
        
        query = "SELECT meeting_id FROM planb.facility_meeting where facility_id='"+facilityID+"';";
        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            while(rs.next())
                list.add(rs.getInt("meeting_id"));
            return list.toArray();
        }
        return null;
    }
    
    public Object[] getMeetingsNames(){
        return org.getFacility("01").getMeetingsNames().toArray();
    }
    
    public Object[] getCollaboratorIds(String facilityID) throws SQLException{
        String query;
        ArrayList list = new ArrayList();
        
        query = "SELECT collaborator_id FROM planb.facility_collaborator WHERE facility_id='"+facilityID+"';";
        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            while(rs.next())
                list.add(rs.getInt("collaborator_id"));
            return list.toArray();
        }
        return null;
    }
    
    public String getNewActionId(String meetingName) throws Exception{
        short number;
        String query;
        Facility facility = org.getFacility("01"); 
        String id = facility.getId();
        Meeting meeting = facility.searchMeeting(meetingName); 
        String acronym = meeting.getAcronym();
        ActionPlan ap = meeting.getActionPlan();
        
        query = "SELECT COUNT(*) as number from planb.action INNER JOIN "
        + "planb.actionplan_action ON actionId=action_id and "
        + "actionPlan_id="+ap.getId()+";";
        planB.connection();
        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            rs.next();
            number = (short)rs.getInt("number");
            return aps.Action.generateId(id, acronym, number, ap.getZeros());
        }
        return null;
    }
    
    private WorkTeam getTeam(int meetingID, Facility facility) throws SQLException{
        String query = "SELECT employeeId FROM planb.collaborator "
                + "INNER JOIN planb.workteam_collaborator "
                + "ON employeeId = collaborator_id "
                + "INNER JOIN planb.workteam_meeting "
                + "ON workteam_collaborator.workTeam_id = workteam_meeting.workTeam_id "
                + "and meeting_id="+meetingID+";";
        ResultSet result = planB.selectQuery(query);
        if(result != null){
            WorkTeam workTeam= new WorkTeam();
            ArrayList<Collaborator> list = new ArrayList();
            while(result.next())
                list.add(facility.searchCollaborator(result.getInt("employeeId")));
            workTeam.setMembers(list);
            return workTeam;
        }
        return null;
    }
    
    public void addAction(String responsible, String detail, String comments, 
            String plannedStartDate, String plannedEndDate, 
            String status, byte progress, int duration, String meetingName) throws Exception{

        Facility facility = org.getFacility("01"); 
        Meeting meeting = facility.searchMeeting(meetingName);
        short number;
        Action action;
        String query = "SELECT COUNT(*) AS number FROM planb.action INNER JOIN "
                + "planb.actionplan_action ON actionId=action_id AND "
                + "actionPlan_id="+meeting.getActionPlan().getId()+";";
        planB.connection();
        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            rs.next();
            number = (short)rs.getInt("number");
            action = new Action("01", meeting.getAcronym(), number, meeting.getActionPlan().getZeros());
            action.setDetail(detail);
            action.setComments(comments);
            action.setResponsible(facility.searchCollaborator(responsible,(byte)1));
            action.setPlannedStartDate(parseDate(plannedStartDate));
            action.setPlannedEndDate(parseDate(plannedEndDate));
            action.setRealEndDate(null);
            action.setStatus(Status.valueOf(status));
            action.setProgress((byte)progress);
            action.setDuration(duration);
            meeting.getActionPlan().getActionList().add(action);
            saveActionToDatabase(action, meeting);
            Aps.getUI().addRowToTableContent(new Object[]{
                action.getID(),action.getResponsible().getAcronymName(),
                action.getDetail(),action.getComments(),action.getPlannedStartDate().toString(),
                action.getPlannedEndDate().toString(),null,
                action.getProgress(),action.getStatus().toString(),action.getDuration()
            });
        }
        
    }
    
    public void modifyAction(Object[] rowDataModified, String meetingName) throws SQLException, Exception{
        Facility facility = org.getFacility("01");
        Meeting meeting = facility.searchMeeting(meetingName);
        ActionPlan plan = meeting.getActionPlan();
        Action action;
        byte progress = -1;
        String itemId, actionDetail=null,actionComments=null,startDate=null,
                endDate=null,realDate=null;
        Status status = null;
        ArrayList<String> columns = new ArrayList();
        ArrayList<Object> list = new ArrayList();
        
        itemId = String.valueOf(rowDataModified[0]);
        if(rowDataModified[2] != null)
            actionDetail = String.valueOf(rowDataModified[2]);
        if(rowDataModified[3] != null)
            actionComments = String.valueOf(rowDataModified[3]);
        if(rowDataModified[4] != null)
            startDate = String.valueOf(rowDataModified[4]);
        if(rowDataModified[5] != null)
            endDate = String.valueOf(rowDataModified[5]);
        if(rowDataModified[6] != null)
            realDate = String.valueOf(rowDataModified[6]);
        if(rowDataModified[7] != null)
            progress = (byte) Integer.parseInt(rowDataModified[7].toString());
        if(rowDataModified[8] != null)
            status = Status.valueOf(String.valueOf(rowDataModified[8]));
        
        action = plan.searchAction(itemId);
        if(action != null){
            if(actionDetail != null){
                action.setDetail(actionDetail);
                columns.add("detail");
                list.add(rowDataModified[2]);
            }
            if(actionComments != null){
                action.setComments(actionComments);
                columns.add("comments");  
                list.add(rowDataModified[3]);
            }
            if(startDate != null){
                action.setPlannedStartDate(parseDate(startDate));
                columns.add("plannedStartDate");
                list.add(rowDataModified[4]);
            }
            if(endDate != null){
                action.setPlannedEndDate(parseDate(endDate));
                columns.add("plannedEndDate");
                list.add(rowDataModified[5]);
            }
            if(realDate != null){
                action.setRealEndDate(parseDate(realDate));
                columns.add("realEndDate");
                list.add(rowDataModified[6]);
            }
            if(progress != -1){
                action.setProgress(progress);
                columns.add("progress");
                list.add(rowDataModified[7]);
            }
            if(status != null){
                action.setStatus(status);
                columns.add("status");
                list.add(status.getValue());
            }
           updateActionToDatabase(itemId,columns,list);
           Aps.getUI().modifiedTableContent(rowDataModified);
        }
    }
    
    private void saveActionToDatabase(Action action, Meeting meeting) throws SQLException{
        String query, values;
        int isRowInserted,actionID,actionPlanID,employeeID;
        values = "('"+action.getID()+"','"+action.getDetail()+"','"+action.getComments()
                +"','"+action.getPlannedStartDate().toString()+"','"
                +action.getPlannedEndDate().toString()+"','"
                +action.getDateCreated().toLocalDate().toString()+" "
                +action.getDateCreated().toLocalTime().toString()+"',"
                +action.getStatus().getValue()+","+(int)action.getProgress()+")";
        query = "INSERT INTO planb.action (itemId,detail,comments,"
            + "plannedStartDate,plannedEndDate,dateCreated,status,progress) "
            + "VALUES "+values+";";
        isRowInserted = planB.insertQuery(query);
        
        query = "SELECT actionId FROM planb.action where itemId='"+action.getID()+"';";
        ResultSet rs = planB.selectQuery(query);
        rs.next();
        actionID = rs.getInt("actionId");
        actionPlanID = meeting.getActionPlan().getId();
        query = "INSERT INTO planb.actionplan_action (actionPlan_id,action_id) "
                + "VALUES ("+actionPlanID+","+actionID+");";
        isRowInserted = planB.insertQuery(query);
        employeeID = action.getResponsible().getEmployeeId();
        query = "INSERT INTO planb.collaborator_action (collaborator_id,action_id) "
                + "VALUES ("+employeeID+","+actionID+");";
        planB.insertQuery(query);
    }
    
    private boolean updateActionToDatabase(String actionID,ArrayList<String> columns, ArrayList<Object> list) throws SQLException, Exception{
        int isUpdated;
        String query, values="";
        if(columns != null){
            for(int i=0;i<columns.size();i++){
                if(columns.get(i).equalsIgnoreCase("status") ||
                        columns.get(i).equalsIgnoreCase("progress"))
                    if(values.equals(""))
                        values += columns.get(i)+"="+(int)list.get(i);
                    else
                        values += ","+columns.get(i)+"="+(int)list.get(i);
                else
                    if(values.equals(""))
                        values += columns.get(i)+"='"+String.valueOf(list.get(i))+"'";
                    else
                        values += ","+columns.get(i)+"="+String.valueOf(list.get(i));
            }
        }
        query = "UPDATE planb.action SET "+values+" WHERE itemId='"+actionID+"';";
        planB.connection();
        isUpdated = planB.updateQuery(query);
        if(isUpdated == 1)
            return true;
        return false;
    }
    
    public boolean deleteAction(String actionID, String meetingName) throws Exception{
        if(actionID != null){
            Facility facility = org.getFacility("01"); 
            Meeting meeting = facility.searchMeeting(meetingName);
            if(meeting.getActionPlan().deleteAction(actionID))
                deleteActionFromDatabase(actionID);
            return true;
        }
        return false;
    }
    
    private boolean deleteActionFromDatabase(String actionID) throws Exception{
        String query;
        planB.connection();
        
        if(actionID != null){
            query = "UPDATE planb.action SET isDeleted=1 WHERE itemId='"+actionID+"';";
            int is_updated = planB.updateQuery(query);
            if(is_updated == 1)
                return true;   
        }
        return false;
    }
    
    public String[] getTeamMembersNames(String meetingName){
        Facility facility = org.getFacility("01");
        Meeting meeting = facility.searchMeeting(meetingName);
        ArrayList<Collaborator> members = meeting.getTeam().getMembers();
        if(members != null){   
            int number_of_members = members.size();
            String[] names = new String[number_of_members];
            for(int i=0;i<number_of_members;i++){
                String collaborator_names = members.get(i).getFirstName()
                            +" "+ members.get(i).getLastName();
                names[i] = collaborator_names;
            }
            return names;
        }
        return null;
    }
}
