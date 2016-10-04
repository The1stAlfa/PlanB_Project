/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jordone3
 */
public class DataBase {
    private Connection con;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DBMS = "mysql";
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "planb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public void DataBase(){
    }
    
   
    public boolean connection ()throws Exception{
        try {
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException ce){
        }    
        try{
            this.con = DriverManager.getConnection("jdbc:" + DBMS + "://" 
                    + HOST + ":" + PORT + "/" + DATABASE, USER, PASSWORD);                                
            return true;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            return false;
        }
    }
    
    public boolean disconnection()
    {
        try{
            this.con.close();
            return(true);
        }
        catch(Exception e){
            return(false);
        }    
    }

    protected ResultSet selectQuery(String query) throws SQLException{
        PreparedStatement s = null;
        ResultSet rs = null;
        try{
            s = this.con.prepareStatement(query);
            rs = s.executeQuery(query); 
        }
        catch(SQLException e){
            
        }
        return rs;
    }
    
    protected int insertQuery(String query) throws SQLException{
        PreparedStatement s = null;
        int rowsAffected = 0;
        try{
            s = this.con.prepareStatement(query);
            rowsAffected = s.executeUpdate();
            return rowsAffected;
        }
        catch(SQLException e){
            
        }
        finally{
            s.close();
        }
        return rowsAffected;
    }
    
    protected int updateQuery(String query) throws SQLException{
        PreparedStatement s = null;
        int rowsAffected = 0;
        try{
            s = this.con.prepareStatement(query);
            rowsAffected = s.executeUpdate();
            return rowsAffected;
        }
        catch(SQLException e){
            
        }
        finally{
            s.close();
        }
        return rowsAffected;
    }
    
    protected int deleteQuery(String query) throws SQLException{
        PreparedStatement s = null;
        int rowsAffected = 0;
        try{
            s = this.con.prepareStatement(query);
            rowsAffected = s.executeUpdate();
            return rowsAffected;
        }
        catch(SQLException e){
            
        }
        finally{
            s.close();
        }
        return rowsAffected;
    }
}