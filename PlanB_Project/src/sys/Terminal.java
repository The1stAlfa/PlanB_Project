/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys;

/**
 *
 * @author AI-Saac
 */

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
        ArrayList<String> _list = new ArrayList();
	char comparator = '-';
	int counter = -1;
	
                for(int i=0;i<accesses.length();i++){
			if(accesses.charAt(i) == comparator){
				_list.add(accesses.substring(counter+1,i));
				counter = i;	
			}
		}
		
                return _list;
    }
   
    public void loadBusinessInformation() throws Exception{
        String query;
        Facility facility = new Facility();
        
        query = "SELECT id, name, acronym, city FROM planb.facility INNER JOIN "
                + "planb.facility_collaborator ON id = facility_id AND "
                + "collaborator_id="+(int)user.getEmployeeId()+";";
        planB.connection();
        ResultSet rs = planB.selectQuery(query);
        rs.next();
        if(rs != null){
            facility.setId(rs.getString("id"));
            facility.setName(rs.getString("name"));
            facility.setAcronym(rs.getString("acronym"));
            facility.setCity(rs.getString("city"));
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

        String query = "SELECT COUNT(*) AS is_user FROM planb.user WHERE username='"
                +username+"' AND password='"+hashedPassword+"';";
        planB.connection();
        ResultSet rs = planB.selectQuery(query);
       if(rs != null){
            rs.next();
            count = rs.getInt("is_user");
            if(count == 1){
                isAuthenticated = true;
                query = "SELECT email,role from planb.user where username='"+username+"';";
                rs = planB.selectQuery(query);
                rs.next();
                user.setUsername(username);
                user.setEmail(rs.getString("email"));
                user.setRole(getRole(rs.getInt("role")));
                query = "SELECT collaborator_id FROM planb.user_collaborator WHERE username='"+username+"';";
                rs = planB.selectQuery(query);
                rs.next();
                user.setEmployeeId(rs.getInt("collaborator_id"));
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
    
    public Object[] getTableContent(ActionItemFilter filter, String meeting_name) throws Exception{
        String id, responsible, date, status, duration;
        ActionPlan plan;
        Facility facility = org.getFacility("01");
        Meeting meeting = facility.searchMeeting(meeting_name);
        
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
            String query = "SELECT id,item_id,detail,comments,p_start_date,"
                    + "p_end_date, r_end_date,progress,status,"
                    + "timestampdiff(day,p_start_date,p_end_date) AS duration "
                    + "FROM planb.action INNER JOIN planb.actionplan_action ON "
                    + "id=action_id and is_deleted=0 and actionplan_id="+plan.getId()+";";
            planB.connection();
            ResultSet rs = planB.selectQuery(query);
            if(rs != null){
                while(rs.next()){
                    Action action = new Action();
                    Vector row = new Vector();
                    id = rs.getString("id");
                    row.add(rs.getString("item_id"));
                    action.setID(rs.getString("item_id"));
                    responsible = getOwnerAcronym(id);
                    row.add(responsible);
                    action.setResponsible(getCollaborator(facility.getId(),responsible));
                    row.add(rs.getString("detail"));
                    action.setDetail(rs.getString("detail"));
                    row.add(rs.getString("comments"));
                    action.setComments(rs.getString("comments"));
                    date = rs.getString("p_start_date");
                    row.add(date);
                    action.setPlannedStartDate(parseDate(date));
                    date = rs.getString("p_end_date");
                    row.add(date);
                    action.setPlannedEndDate(parseDate(date));
                    date = rs.getString("r_end_date");
                    row.add(date);
                    action.setRealEndDate(parseDate(date));
                    row.add(rs.getString("progress"));
                    action.setProgress((byte) Integer.parseInt(rs.getString("progress")));
                    status = getStatusName(rs.getInt("status")); 
                    row.add(status);
                    action.setStatus(Status.valueOf(status));
                    duration = rs.getString("duration"); 
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
        String query = "SELECT acronym_name FROM planb.collaborator INNER JOIN"
                + " planb.collaborator_action ON employee_id=collaborator_id"
                + " AND action_id ="+actionID+";";
        planB.connection();
        ResultSet rs = planB.selectQuery(query);
        rs.next();
        String acronym = rs.getString("acronym_name"); 
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
    
    public Collaborator getCollaborator(String facilityId, String collaboratorAcronym){
        Facility facility = org.getFacility(facilityId);
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
    
    private Role getRole(int role){
        Role r[] = Role.values();
        for(Role _r:r){
            if(_r.getValue() == role)
                return _r;
        }
        return null;
    }
    
    private ArrayList getCollaborators(String facilityId) throws SQLException{
        int collaboratorId,count=0;
        String query;
        Object[] result;
        ArrayList<Collaborator> list = new ArrayList();
        
        result = getCollaboratorIds(facilityId);
        if(result != null){
            while(count != result.length){
                collaboratorId = (int)result[count];
                query = "SELECT firstname, middlename,lastname,acronym_name,charge"
                        + " FROM planb.collaborator WHERE employee_id="+collaboratorId+";";
                ResultSet rs = planB.selectQuery(query);
                while(rs.next()){
                    Collaborator collaborator = new Collaborator();
                    collaborator.setEmployeeId(collaboratorId);
                    collaborator.setFirstName(rs.getString("firstname"));
                    collaborator.setMiddleName(rs.getString("middlename"));
                    collaborator.setLastName(rs.getString("lastname"));
                    collaborator.setCharge(rs.getString("charge"));
                    collaborator.setAcronymName(rs.getString("acronym_name"));
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
        Object[] result;
        ArrayList<Meeting> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        result = getMeetingIds(facility.getId());      
        if(result != null){
            while(count != result.length){
                query = "SELECT name,acronym,purpose,date_created FROM planb.meeting"
                        + " where meeting_id="+(int)result[count]+";";
                ResultSet rs = planB.selectQuery(query);
                rs.next();
                Meeting meeting = new Meeting();
                meeting.setName(rs.getString("name"));
                meeting.setAcronym(rs.getString("acronym"));
                meeting.setPurpose(rs.getString("purpose"));
                meeting.setDateCreated(LocalDateTime.parse(rs.getString("date_created").substring(0,19),formatter));
                meeting.setActionPlan(getActionPlan(Integer.parseInt(result[count].toString())));
                meeting.setTeam(getTeam((int)result[count],facility));
                list.add(meeting);   
                count++;
            }
            return list;
        }
        return null;
    }
    
    private ActionPlan getActionPlan(int meetingId) throws SQLException{
        String query;
        ActionPlan actionPlan = new ActionPlan();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        query = "SELECT id, date_created, date_modified, execution "
                + "FROM planb.actionplan INNER JOIN  planb.meeting_actionplan "
                + "ON id=actionplan_id AND meeting_id='"+meetingId+"';";

        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            rs.next();
            actionPlan.setId((short) rs.getInt("id"));
            actionPlan.setDateCreated(LocalDateTime.parse(rs.getString("date_created").substring(0,19), formatter));
            actionPlan.setDateModified(LocalDateTime.parse(rs.getString("date_modified").substring(0,19), formatter));
            actionPlan.setExecution((byte)rs.getInt("execution"));
            query = "SELECT date_modified, actions, actions_cancelled,"
                + "actions_completed_after_app,actions_completed_app,"
                + "actions_in_progress,actions_near_to_due_day,actions_overdue "
                + "FROM planb.apsummary INNER JOIN planb.actionplan_apsummary ON "
                + "id=apsummary_id and actionplan_id="+actionPlan.getId()+";";
            rs = planB.selectQuery(query);
            if(rs != null){
                rs.next();
                APSummary apsummary = new APSummary();
                apsummary.setDate_modified(LocalDateTime.parse(rs.getString("date_modified").substring(0,19), formatter));
                apsummary.setActions(rs.getInt("actions"));
                apsummary.setActionsCompletedAfterApp(rs.getInt("actions_completed_after_app"));
                apsummary.setActionsCompletedApp(rs.getInt("actions_completed_app"));
                apsummary.setActionsInProgress(rs.getInt("actions_in_progress"));
                apsummary.setActionsNearToDueDay(rs.getInt("actions_near_to_due_day"));
                apsummary.setActionsCancelled(rs.getInt("actions_cancelled"));
                apsummary.setActionsOverdue(rs.getInt("actions_overdue"));
                actionPlan.setZeros(rs.getInt("actions"));
                actionPlan.setSummary(apsummary);
                query = "SELECT employee_id, firstname, middlename, lastname, "
                        + "acronym_name, charge FROM planb.collaborator INNER JOIN"
                        + " planb.collaborator_actionplan ON "
                        + "employee_id=collaborator_id AND actionplan_id="+(int)actionPlan.getId()+";";
                rs = planB.selectQuery(query);
                if(rs != null){
                    rs.next();
                    Collaborator owner = new Collaborator();
                    owner.setEmployeeId(rs.getInt("employee_id"));
                    owner.setFirstName(rs.getString("firstname"));
                    owner.setMiddleName(rs.getString("middlename"));
                    owner.setLastName(rs.getString("lastname"));
                    owner.setAcronymName(rs.getString("acronym_name"));
                    owner.setCharge(rs.getString("charge"));
                    actionPlan.setOwner(owner);
                    actionPlan.setCurrentDate(LocalDateTime.now());
                }
            }
            return actionPlan;
        }
        return null;
    }
    
    private Object[] getMeetingIds(String facilityId) throws SQLException{
        String query;
        ArrayList list = new ArrayList();
        
        query = "SELECT meeting_id FROM planb.facility_meeting where facility_id='"+facilityId+"';";
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
    
    public Object[] getCollaboratorIds(String facilityId) throws SQLException{
        String query;
        ArrayList list = new ArrayList();
        
        query = "SELECT collaborator_id FROM planb.facility_collaborator WHERE facility_id='"+facilityId+"';";
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
        + "planb.actionplan_action ON id=action_id and "
        + "actionplan_id="+ap.getId()+";";
        planB.connection();
        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            rs.next();
            number = (short)rs.getInt("number");
            return aps.Action.generateId(id, acronym, number, ap.getZeros());
        }
        return null;
    }
    
    private WorkTeam getTeam(int meetingId, Facility facility) throws SQLException{
        String query = "SELECT employee_id FROM planb.collaborator "
                + "INNER JOIN planb.workteam_collaborator "
                + "ON collaborator.employee_id = workteam_collaborator.collaborator_id "
                + "INNER JOIN planb.workteam_meeting "
                + "ON workteam_collaborator.workteam_id = workteam_meeting.workteam_id "
                + "and meeting_id="+meetingId+";";
        ResultSet rs = planB.selectQuery(query);
        if(rs != null){
            WorkTeam work_team= new WorkTeam();
            ArrayList<Collaborator> list = new ArrayList();
            while(rs.next())
                list.add(facility.searchCollaborator(rs.getInt("employee_id")));
            work_team.setMembers(list);
            return work_team;
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
        String query = "SELECT COUNT(*) as number from planb.action INNER JOIN "
                + "planb.actionplan_action ON id=action_id and "
                + "actionplan_id="+meeting.getActionPlan().getId()+";";
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
                columns.add("p_start_date");
                list.add(rowDataModified[4]);
            }
            if(endDate != null){
                action.setPlannedEndDate(parseDate(endDate));
                columns.add("p_end_date");
                list.add(rowDataModified[5]);
            }
            if(realDate != null){
                action.setRealEndDate(parseDate(realDate));
                columns.add("r_end_date");
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
        int is_row_inserted,action_id,actionplan_id,employee_id;
        values = "('"+action.getID()+"','"+action.getDetail()+"','"+action.getComments()
                +"','"+action.getPlannedStartDate().toString()+"','"
                +action.getPlannedEndDate().toString()+"','"
                +action.getDateCreated().toLocalDate().toString()+" "
                +action.getDateCreated().toLocalTime().toString()+"',"
                +action.getStatus().getValue()+","+(int)action.getProgress()+")";
        query = "INSERT INTO planb.action (item_id,detail,comments,"
            + "p_start_date,p_end_date,date_created,status,progress) "
            + "VALUES "+values+";";
        is_row_inserted = planB.insertQuery(query);
        
        query = "SELECT id FROM planb.action where item_id='"+action.getID()+"';";
        ResultSet rs = planB.selectQuery(query);
        rs.next();
        action_id = rs.getInt("id");
        actionplan_id = meeting.getActionPlan().getId();
        query = "INSERT INTO planb.actionplan_action (actionplan_id,action_id) "
                + "VALUES ("+actionplan_id+","+action_id+");";
        is_row_inserted = planB.insertQuery(query);
        employee_id = action.getResponsible().getEmployeeId();
        query = "INSERT INTO planb.collaborator_action (collaborator_id,action_id) "
                + "VALUES ("+employee_id+","+action_id+");";
        planB.insertQuery(query);
    }
    
    private boolean updateActionToDatabase(String action_id,ArrayList<String> columns, ArrayList<Object> list) throws SQLException, Exception{
        int is_updated;
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
        query = "UPDATE planb.action SET "+values+" WHERE item_id='"+action_id+"';";
        planB.connection();
        is_updated = planB.updateQuery(query);
        if(is_updated == 1)
            return true;
        return false;
    }
    
    public boolean deleteAction(String action_id, String meeting_name) throws Exception{
        if(action_id != null){
            Facility facility = org.getFacility("01"); 
            Meeting meeting = facility.searchMeeting(meeting_name);
            if(meeting.getActionPlan().deleteAction(action_id))
                deleteActionFromDatabase(action_id);
            return true;
        }
        return false;
    }
    
    private boolean deleteActionFromDatabase(String action_id) throws Exception{
        String query;
        planB.connection();
        
        if(action_id != null){
            query = "UPDATE planb.action SET is_deleted=1 WHERE item_id='"+action_id+"';";
            int is_updated = planB.updateQuery(query);
            if(is_updated == 1)
                return true;   
        }
        return false;
    }
    
    public String[] getTeamMembersNames(String meeting_name){
        Facility facility = org.getFacility("01");
        Meeting meeting = facility.searchMeeting(meeting_name);
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
