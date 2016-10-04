/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys;

import ui.LoginForm;
import ui.SplashWindow;
import ui.UserInterfaceTerminal;
import aps.Organization;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.UIManager;

/**
 *
 * @author AI-Saac
 */
public class Aps {

    /**
     *
     */
    private static  Organization org = new Organization();
    private static Terminal terminalAPS;
    private static UserInterfaceTerminal terminalUI;
    
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void main(String[] args) throws InterruptedException, 
            InvocationTargetException,
            Exception {
        terminalAPS = new Terminal();
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginForm login = new LoginForm();
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
                login.setVisible(true);
            } catch (Exception ex) {
                System.out.println("MALito");
            }
        });
    }
    
    public static Organization getOrganization(){
        return org;
    }
    
    /**
     *
     * @return
     */
    public static Terminal getTerminal(){
        return terminalAPS;
    }
    
    /**
     *
     * @return
     */
    public static UserInterfaceTerminal getUI(){
        return terminalUI;
    }
    
    /**
     *
     * @throws java.io.IOException
     * @throws java.awt.FontFormatException
     */
    public static void initSystem() throws IOException, FontFormatException{
        try{
            SplashWindow w = new SplashWindow("src\\images\\planB-182x263.png", 
                new Frame(), 2500);
            terminalAPS.loadBusinessInformation();
            terminalUI = new UserInterfaceTerminal();
        }
        catch(Exception e){
        }
    }
}