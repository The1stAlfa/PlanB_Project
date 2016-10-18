/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.<a href="
 * https://icons8.com/web-app/98/Plus-Math">Plus math icon credits</a>
 */

package main.java.ui;

import main.java.sys.Aps;
import aps.APSummary;
import aps.ActionItemFilter;
import aps.ActionPlan;
import aps.Collaborator;
import aps.Meeting;
import aps.WorkTeam;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

/**
 * @palette
 * PALETTE
 * +----------------------------------+------------------------------ -+----------------------------------+------------------------------------+----------------------------------------+
 * |         COLORS         | HEX.CODE|          ANALOG COLORS         |         COMPLEMENTARY COLORS     |         MONOCROMATIC COLORS        |             COMPOUND COLORS            |
 * +----------------------------------+--------------------------------+----------------------------------+------------------------------------+----------------------------------------+
 * |------------------------|---------|--------------------------------|----------------------------------|------------------------------------|----------------------------------------|
 * | BACKGROUND BLACK       | #303132 |
 * | ABSOLUTE WHITE         | #FFFFFF |                                |                                  |                                    |                                        |
 * | ABSOLUTE BLACK         | #000000 |                                |                                  |                                    |                                        |
 * | HOLCIM WHITE           | #FCFEFC |R#FF0000 ORA#E8AE0C             |GR#00FF00-19FF19 MAG#FF007F-B20059|GRA#CACCCA W#FDFFFD GR#587F58-B0FFB0|GR#5A995A GRE#B9B5CC W#FEFFFC PUI#DABCFF|
 * | HOLCIM RED             | #FC4344 |R#FF0D0D-#E80C0C R#FF4949       |GR#00FF47-00B232 R#FF0001         |BR#7F4848 PI#FF9091 R#FF4445-CC3637 |BR#995A5A W#FFFCFF YEL#EBFFBC OC#CCCCB5 | 
 * | HOLCIM BLACK           | #232323 |R#FF0000                        |                                  |W#CCCCCC-FFFFFF BR#7F5959 PI#FFB2B2 |                                        |
 * | HOLCIM GRAY            | #9B9B9B |R#FF0D0D                        |                                  |                                    |                                        |
 * | HOLCIM SECOND RED      | #E83434 |                                |                                  |                                    |                                        |
 * | HOLCIM BROWN           | #692628 |R#FF0F0D-#E80C0F                |                                  |                                    |                                        |
 * | HOLCIM GRAY REFLECTIVE | #D1D3D1 |YEL#E8AE0C BLU#160CE8 GR#0DFF96 |GR#00FF00 MAG#FF007F-B20059       |PI#FFA9AB R#FF5C61-CC4A4E BR#7F5456 |GR#5A995A PU#DABCFF-B9B5CC              |
 * | HOLCIM DARK GRAY       | #4C4B4C |ORA#FF530D ORA#E82C0C MAG#E80C7A|MAG#FF00FF GR#3EB200-59FF00       |PU#7F587F-FFAFFF W#FFFCFF-CCC9CC    |PU#995A99 0C#CCC7B5                     |
 * | HOLCIM SECOND BROWN    | #453D3F |R#FF1E0D                        |R#FF0040-FF1953 GR#00FF24-00B219  |                                    |R#995A6A OC#FEFFBC-CCCB5 W#FFFCFF       |
 * +----------------------------------+--------------------------------+----------------------------------+------------------------------------+----------------------------------------+
 */

/**
 *
 * @author LoboAlfa2.0
 */

public class UITerminal extends JFrame{
    private Dimension nativeScreenSize;
    private int value_at_changed, xPosition=0, yPosition=0, selectedRow=-1;
    private JMenuBar mainMenu;
    private JMenuItem dashboardMenu, meetingMenu, actionPlanMenu, teamMenu,
            profileMenu,settingMenu,menuItem;
    private JPanel mainPanel, menuPanel, titleBarPanel, optionsContentPanel,
            frameButtonsPanel;
    private JPanel dashboardPanel, actionPlanPanel, meetingPanel;
    private JLabel apsLogoLabel, resizeLabel, minimizeLabel, closeLabel;
    private JLabel initImageLabel;
    private String resource, meetingName;
    private JLabel apLabel, owLabel, parLabel, meetLabel, actLabel, compLabel, 
                compAppLabel, overAppLabel, actContentLabel, owContentLabel,
                compContentLabel, compAppContentLabel, overAppContentLabel, 
                performanceLabel, exeLabel, performanceContentLabel, 
                exeContentLabel;
    private JPanel filterPanel, apInformationPanel, actionListPanel, 
            gapPanel1,gapPanel2,pagePanel;
    private JLabel addActionLabel, modifyActionLabel, deleteActionLabel;
    private JComboBox filterContentComboBox,filterCombobox,meetComboBox;
    private JTable jTable1; 
    private JScrollPane jScrollPane1, jScrollPane2;
    private JTextArea parTextArea;
    private boolean menuFlag = false, resizeFlag = false, clickedFlag = false;

       
    public UITerminal() throws IOException, FontFormatException, Exception{
        initComponents(); 
    }
            
    private void initComponents() throws FontFormatException, Exception{
        resource = "src/images/";
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowListener();
        nativeScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setMinimumSize(new Dimension(867,662));
        this.setResizable(true);
        UIManager.put("ToolTip.background", Color.decode("#303132"));
        UIManager.put("ToolTip.foreground", Color.decode("#C9CDD1"));
        Border border = BorderFactory.createMatteBorder(1,1,1,1,Color.decode("#3B3C3D"));
        UIManager.put("ToolTip.border", border);
        setIconImage(new ImageIcon(getClass().getResource("../images/planB-27x32.png")).getImage());
        createTitleBarPanel();
        createMainMenu();
        createOptionsContentPanel();
       
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.decode("#F8FAF8")));
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        setContentPane(mainPanel);
        getContentPane().setBackground(Color.decode("#000000"));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(titleBarPanel, BorderLayout.NORTH);
        mainPanel.add(mainMenu, BorderLayout.WEST);
        mainPanel.add(optionsContentPanel, BorderLayout.CENTER);
        addFonts();
        pack();
        maximize(6);
        setVisible(true);
    }
    
    private void createTitleBarPanel(){
        titleBarPanel = new JPanel();
        titleBarPanel.setLayout(new BorderLayout());    
        titleBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
        titleBarPanel.setPreferredSize(new Dimension(867,30));
        titleBarPanel.setBackground(Color.decode("#FCFEFC"));
        titleBarPanel.addMouseListener(new MouseAdapter(){});
        MoveMouseListener ml = new MoveMouseListener(titleBarPanel);
        titleBarPanel.addMouseListener(ml);
        titleBarPanel.addMouseMotionListener(ml);
        
        frameButtonsPanel = new JPanel();
        apsLogoLabel = new JLabel("PlanB System 1.0");
        apsLogoLabel.setIcon(new ImageIcon(resource+"planB-18x21.png"));
        apsLogoLabel.setIconTextGap(3);
        Border border = BorderFactory.createEmptyBorder(0,4,0,0);
        apsLogoLabel.setBorder(border);
        resizeLabel = new JLabel(new ImageIcon(resource+"tabB.png"), JLabel.CENTER);
        minimizeLabel = new JLabel(new ImageIcon(resource+"minusB.png"), JLabel.CENTER);
        closeLabel = new JLabel(new ImageIcon(resource+"closeB.png"), JLabel.CENTER);
        
        apsLogoLabel.setForeground(Color.decode("#707070"));
        apsLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        apsLogoLabel.setFont(new Font("Roboto-Thin",Font.BOLD,12));
        apsLogoLabel.setHorizontalTextPosition(JLabel.RIGHT);
        
        resizeLabel.setOpaque(true);
        resizeLabel.setBackground(Color.decode("#FCFEFC"));
        resizeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resizeLabel.setMaximumSize(new Dimension(47,30));
        resizeLabel.setPreferredSize(new Dimension(47,30));
        resizeLabel.setToolTipText("Minimize:size");
        resizeLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                resizeLabel.setBackground(Color.decode("#E2E5E2"));
                resizeLabel.repaint();
                
            }
            @Override
            public void mouseExited(MouseEvent e){
                resizeLabel.setBackground(Color.decode("#FCFEFC"));
                resizeLabel.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                resizeWindow();
            }  
        });
        
        minimizeLabel.setOpaque(true);
        minimizeLabel.setBackground(Color.decode("#FCFEFC"));
        minimizeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeLabel.setMaximumSize(new Dimension(47,30));
        minimizeLabel.setPreferredSize(new Dimension(47,30));
        minimizeLabel.setToolTipText("Minimize");
        minimizeLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                minimizeLabel.setBackground(Color.decode("#E2E5E2"));
                //minimizeLabel.setIcon(new ImageIcon(resource+"minusW.png"));
                minimizeLabel.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e){
                minimizeLabel.setBackground(Color.decode("#FCFEFC"));
                minimizeLabel.setIcon(new ImageIcon(resource+"minusB.png"));
                minimizeLabel.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                minimizeWindow();
            }  
        });
        
        closeLabel.setOpaque(true);
        closeLabel.setBackground(Color.decode("#FCFEFC"));
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setMaximumSize(new Dimension(47,30));
        closeLabel.setPreferredSize(new Dimension(47,30));
        closeLabel.setToolTipText("Close");
        closeLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                closeLabel.setBackground(Color.decode("#FC4344"));
                closeLabel.setIcon(new ImageIcon(resource+"closeW.png"));
                closeLabel.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e){
                closeLabel.setBackground(Color.decode("#FCFEFC"));
                closeLabel.setIcon(new ImageIcon(resource+"closeB.png"));
                closeLabel.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }  
        });
        
        frameButtonsPanel.setLayout(new BoxLayout(frameButtonsPanel, BoxLayout.X_AXIS));
        frameButtonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        frameButtonsPanel.setPreferredSize(new Dimension(nativeScreenSize.width, 30));
        frameButtonsPanel.setBackground(Color.decode("#FCFEFC"));
        frameButtonsPanel.add(apsLogoLabel);
        frameButtonsPanel.add(Box.createHorizontalGlue());
        frameButtonsPanel.add(minimizeLabel);
        frameButtonsPanel.add(resizeLabel);
        frameButtonsPanel.add(closeLabel);
        
        titleBarPanel.add(frameButtonsPanel, BorderLayout.LINE_START);
    }
    
    private JMenuItem createMenuItem(JMenuItem item, String iconName){
        item.setBackground(Color.decode("#303132"));
        item.setFont(new Font("Roboto-Regular", Font.PLAIN, 14));
        //FFFBF5
        item.setForeground(Color.decode("#C9CDD1"));
        item.setIconTextGap(13);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE,78));
        item.setPreferredSize(new Dimension(165,55));
        item.setIcon(new ImageIcon(resource+iconName));
        if(item.equals(this.menuItem))
            item.setToolTipText("Maximize Navigation Bar");
        else
            item.setToolTipText(item.getText());
        return item;
    }
    
    private void menuEvents(JMenuItem item, String iconName, String iconName2){
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //3E4242
                item.setBackground(Color.decode("#3B3C3D"));
                item.setIcon(new ImageIcon(resource+iconName));
                item.setForeground(Color.decode("#FFFBF5"));
                item.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e){
                item.setBackground(Color.decode("#303132"));
                item.setIcon(new ImageIcon(resource+iconName2));
                item.setForeground(Color.decode("#C9CDD1"));
                item.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                if(item.equals(menuItem)){
                    if(!menuFlag){
                        mainMenu.setPreferredSize(new Dimension(165,600));
                        //menuItem.setToolTipText("<html><body style=\"background-color:#303132;color:#C9CDD1;border=0\">"
                        //        + " Minimize Navigation Bar </body></ht‌​ml>");
                        menuItem.setToolTipText("Minimize Navigation Bar");
                        mainPanel.revalidate();
                        menuFlag = true;
                    }
                    else{
                        mainMenu.setPreferredSize(new Dimension(65,600));
                        mainPanel.revalidate();
                        menuItem.setToolTipText("Maximize Navigation Bar");
                        menuFlag = false;
                    }
                }
                else if(item.equals(dashboardMenu)){
                    optionsContentPanel.removeAll();
                    optionsContentPanel.add(dashboardPanel, BorderLayout.CENTER);
                    pack();
                    optionsContentPanel.repaint();
                }
                else if(item.equals(meetingMenu)){
                    
                }
                else if(item.equals(actionPlanMenu)){
                    optionsContentPanel.removeAll();
                    optionsContentPanel.add(actionPlanPanel, BorderLayout.CENTER);
                    pack();
                    optionsContentPanel.repaint();
                }
                else if(item.equals(teamMenu)){
                    
                }
            }
        }); 
    }
   
    private void createMainMenu(){ 
        mainMenu = new JMenuBar();
        dashboardMenu = new JMenuItem(" DashBoard"){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        meetingMenu = new JMenuItem(" Meeting"){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        actionPlanMenu = new JMenuItem(" Action Plan"){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        teamMenu = new JMenuItem(" Team"){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        profileMenu = new JMenuItem(" Profile"){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        settingMenu = new JMenuItem(" Settings"){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        menuItem = new JMenuItem(""){ 
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(5, -15);
            }
        };
        
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.PAGE_AXIS));
        mainMenu.setMaximumSize(new Dimension(165,Integer.MAX_VALUE));
        mainMenu.setPreferredSize(new Dimension(65,600));
        mainMenu.setMinimumSize(new Dimension(50,50));
        mainMenu.setBackground(Color.decode("#303132"));
        mainMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.decode("#202020")));
        
        mainMenu.add(createMenuItem(menuItem, "menu.png"));
        mainMenu.add(createMenuItem(dashboardMenu, "combo.png"));
        mainMenu.add(createMenuItem(meetingMenu, "Podium.png"));
        mainMenu.add(createMenuItem(actionPlanMenu, "Overtime.png"));
        mainMenu.add(createMenuItem(teamMenu, "1Team.png"));
        mainMenu.add(createMenuItem(profileMenu, "User.png"));
        mainMenu.add(Box.createVerticalGlue());
        mainMenu.add(createMenuItem(settingMenu, "gear.png"));
        settingMenu.setMargin(new Insets(0,10,5,0));
        
        menuEvents(menuItem, "menu2.png", "menu.png");
        menuEvents(dashboardMenu, "combo2.png", "combo.png");
        menuEvents(meetingMenu, "podium2.png", "Podium.png");
        menuEvents(actionPlanMenu, "Overtime2.png", "Overtime.png");
        menuEvents(teamMenu, "1Team2.png", "1Team.png");
        menuEvents(profileMenu, "User2.png", "User.png");
        menuEvents(settingMenu, "gear2.png", "gear.png");        
        
    }
    
    private void createOptionsContentPanel() throws Exception{
        optionsContentPanel = new JPanel();
        optionsContentPanel.setBackground(Color.decode("#FCFEFC"));
        optionsContentPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        optionsContentPanel.setPreferredSize(new Dimension(750,600));
        optionsContentPanel.setSize(optionsContentPanel.getPreferredSize());
        optionsContentPanel.setLayout(new BorderLayout());
        
        createDashboardPanel();
        createActionPlanPanel();
        
        initImageLabel = new JLabel(new ImageIcon(resource+"holcim-logo.png"));
        initImageLabel.setPreferredSize(new Dimension(500,328));
        initImageLabel.setSize(initImageLabel.getPreferredSize());
        optionsContentPanel.add(initImageLabel,BorderLayout.CENTER);
        optionsContentPanel.add(titleBarPanel, BorderLayout.NORTH);
        pack();
                
    }
    
    private void createDashboardPanel(){
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());
        dashboardPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        dashboardPanel.setBackground(Color.decode("#FCFEFC"));
        
        initImageLabel = new JLabel(new ImageIcon(resource+"holcim-logo.png"), JLabel.CENTER);
        initImageLabel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        initImageLabel.setPreferredSize(new Dimension(500,328));
        initImageLabel.setBackground(Color.decode("#FCFEFC"));
        
        dashboardPanel.add(initImageLabel,BorderLayout.CENTER);
        
    }
    
    private void createActionPlanPanel() throws Exception{
        GridBagConstraints gbc = new GridBagConstraints();
        meetComboBox = new JComboBox();
        jTable1 = new JTable();
        parTextArea = new JTextArea();
        
        
        actionPlanPanel = new JPanel();
        actionPlanPanel.setLayout(new BorderLayout());
        actionPlanPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        actionPlanPanel.setBackground(Color.decode("#FCFEFC"));
        
        apInformationPanel = new JPanel();
        apInformationPanel.setLayout(new GridBagLayout());
        apInformationPanel.setBackground(Color.decode("#FCFEFC"));
        actionListPanel = new JPanel();
        actionListPanel.setLayout(new BorderLayout());
        actionListPanel.setPreferredSize(new Dimension(300,300));
        actionListPanel.setBackground(Color.decode("#FCFEFC"));
        //**********************************************************************
        //  ActionPlan Information Panel Components
        //**********************************************************************
        apLabel = new JLabel("Action Plan");
        apLabel.setFont(new Font("Dialog", 1, 24)); // NOI18N
        apLabel.setHorizontalAlignment(SwingConstants.CENTER);
        apLabel.setVerticalTextPosition(SwingConstants.TOP);
        
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.insets = new Insets(0, 5, 0, 5);
        apInformationPanel.add(apLabel, gbc);
        
        owLabel = new JLabel("Owner");
        owLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        owLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        owLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        owLabel.setMaximumSize(new Dimension(45, 16));
        owLabel.setMinimumSize(new Dimension(45, 16));
        owLabel.setPreferredSize(new Dimension(45, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 2, 2, 3);
        apInformationPanel.add(owLabel, gbc);

        parLabel = new JLabel("Participants");
        parLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        parLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        parLabel.setVerticalAlignment(SwingConstants.TOP);
        parLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        parLabel.setPreferredSize(new Dimension(82, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 4, 2, 3);
        apInformationPanel.add(parLabel, gbc);
        
        meetLabel = new JLabel("Meeting");
        meetLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        meetLabel.setHorizontalAlignment(SwingConstants.LEFT);
        meetLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 2, 2, 3);
        apInformationPanel.add(meetLabel, gbc);
        
        meetComboBox.setModel(new DefaultComboBoxModel<>(Aps.getTerminal().getMeetingsNames()));
        meetComboBox.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(204, 204, 204), null, null));
        meetComboBox.setMaximumSize(new Dimension(170, 24));
        meetComboBox.setPreferredSize(new Dimension(170, 24));
        meetComboBox.setSelectedIndex(-1);
        meetComboBox.addItemListener((ItemEvent event) -> {
            meetingSelected(event);
            try {
                Object[] object = Aps.getTerminal().getTableContent(ActionItemFilter.ALL, meetingName);
                Meeting meeting = (Meeting)object[0];
                jTable1.setModel((TableModel)object[1]);                
                setColumnWidth();
                centerColumnContent();
                
                if(meeting != null){
                    ActionPlan plan = meeting.getActionPlan();
                    APSummary summary = plan.getSummary();
                    owContentLabel.setText(plan.getOwner().getFirstName()+" "
                            +plan.getOwner().getLastName());
                    actContentLabel.setText(String.valueOf(summary.getActions()));
                    compContentLabel.setText(String.valueOf(summary.getActionsCompleted()));
                    compAppContentLabel.setText(String.valueOf(summary.getActionsCompletedApp()));
                    overAppContentLabel.setText(String.valueOf(summary.getActionsOverdue()));
                    parTextArea.setText(getParticipantsAcronyms(meeting.getTeam(),meeting.getAditionalParticipants()));
                    int team_performance = 100;
                    if(summary.getActionsOverdue() == 0 && summary.getActionsCompletedApp() > 0)
                        performanceContentLabel.setText(String.valueOf(team_performance)+"%");
                    else{
                        team_performance = (int)Math.round(((float)summary.getActionsCompletedApp()/
                                ((float)summary.getActions()))*100);
                        performanceContentLabel.setText(String.valueOf(team_performance)+"%");
                    }
                    int execution = (int)Math.round((float)(summary.getActionsCompleted()/(float)summary.getActions())*100);
                    exeContentLabel.setText(String.valueOf(execution)+"%");
                    if(team_performance <= 70){
                        performanceContentLabel.setBackground(Color.decode("#E80C0C"));
                        performanceContentLabel.setForeground(Color.decode("#FCFEFC"));
                    }
                    else if(71 <= team_performance || team_performance <= 89){
                        performanceContentLabel.setBackground(Color.YELLOW);
                        performanceContentLabel.setForeground(Color.decode("#303132"));
                    }
                    else{
                        performanceContentLabel.setBackground(Color.decode("#00FF47"));
                        performanceContentLabel.setForeground(Color.decode("#303132"));
                    }
                }
            }
            catch (Exception ex) {
                Logger.getLogger(UITerminal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.insets = new Insets(0, 2, 2, 2);
        apInformationPanel.add(meetComboBox, gbc);

        actLabel = new JLabel("Actions");
        actLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        actLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc =  new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 2, 2, 2);
        apInformationPanel.add(actLabel, gbc);

        compLabel = new JLabel("Completed");
        compLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        compLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 2, 2, 2);
        apInformationPanel.add(compLabel, gbc);

        compAppLabel = new JLabel("Completed APP");
        compAppLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        compAppLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        compAppLabel.setVerticalAlignment(SwingConstants.TOP);
        compAppLabel.setPreferredSize(new Dimension(105, 24));
        compAppLabel.setVerticalTextPosition(SwingConstants.TOP);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(0, 4, 2, 2);
        apInformationPanel.add(compAppLabel, gbc);
        
        overAppLabel = new JLabel("OverDue APP");
        overAppLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        overAppLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 2, 2, 2);
        apInformationPanel.add(overAppLabel, gbc);

        actContentLabel = new JLabel();
        actContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        actContentLabel.setPreferredSize(new Dimension(40, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        apInformationPanel.add(actContentLabel, gbc);

        owContentLabel = new JLabel();
        owContentLabel.setHorizontalAlignment(SwingConstants.LEFT);
        owContentLabel.setLabelFor(owLabel);
        owContentLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        owContentLabel.setPreferredSize(new Dimension(150, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 2;
        gbc.ipady = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 4, 3, 2);
        apInformationPanel.add(owContentLabel, gbc);
        
        compContentLabel = new JLabel();
        compContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        compContentLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 2, 2, 2);
        apInformationPanel.add(compContentLabel, gbc);

        compAppContentLabel = new JLabel();
        compAppContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        compAppContentLabel.setVerticalAlignment(SwingConstants.TOP);
        compAppContentLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        compAppContentLabel.setVerticalTextPosition(SwingConstants.TOP);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        apInformationPanel.add(compAppContentLabel, gbc);

        overAppContentLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        apInformationPanel.add(overAppContentLabel, gbc);

        performanceLabel = new JLabel("Team Preformance APP");
        performanceLabel.setFont(new Font("Dialog", 1, 14)); // NOI18N
        performanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        performanceLabel.setPreferredSize(new Dimension(164, 16));
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        gbc.insets = new Insets(0, 12, 2, 2);
        apInformationPanel.add(performanceLabel, gbc);
        
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setBackground(Color.decode("#FCFEFC"));
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 2));
        jScrollPane1.setPreferredSize(new Dimension(170, 25));

        parTextArea.setText("");
        parTextArea.setBackground(Color.decode("#FCFEFC"));
        parTextArea.setFont(new Font("Dialog", 1, 12));
        parTextArea.setEditable(false);
        parTextArea.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        parTextArea.setLineWrap(true);
        parTextArea.setWrapStyleWord(true);
        parTextArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(parTextArea);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        apInformationPanel.add(jScrollPane1, gbc);

        exeLabel = new JLabel("Overall Execution");
        exeLabel.setFont(new Font("Dialog", 1, 14));
        exeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        apInformationPanel.add(exeLabel, gbc);

        performanceContentLabel = new JLabel(); 
        performanceContentLabel.setBackground(new Color(55, 55, 55));
        performanceContentLabel.setFont(new Font("Dialog", 1, 18)); // NOI18N
        performanceContentLabel.setForeground(new Color(255, 255, 255));
        performanceContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        performanceContentLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        performanceContentLabel.setMaximumSize(new Dimension(77, 24));
        performanceContentLabel.setOpaque(true);
        performanceContentLabel.setPreferredSize(new Dimension(77, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 12, 2, 2);
        apInformationPanel.add(performanceContentLabel, gbc);

        exeContentLabel = new JLabel();
        exeContentLabel.setBackground(new Color(55, 55, 55));
        exeContentLabel.setFont(new Font("Dialog", 1, 18)); // NOI18N
        exeContentLabel.setForeground(new Color(255, 255, 255));
        exeContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exeContentLabel.setOpaque(true);
        exeContentLabel.setPreferredSize(new Dimension(77, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 12, 2, 2);
        apInformationPanel.add(exeContentLabel, gbc);
        
        //**********************************************************************
        //  Action List Table Components
        //**********************************************************************
        //  Filter Panel
        createFilterPanel();
        //  Action List Table
        jTable1.setModel(new DefaultTableModel(null, new String [] {
                "ID", "Resp.", "Detail", "Comments", 
                "P.Start Date", "P.End Date", "R.End Date",
                "Prog. %", "Status", "Dur."
            }));
        jTable1.setMinimumSize(new Dimension(300, 200));
        jTable1.setBackground(Color.decode("#FCFEFC"));
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setRowHeight(30);
        jTable1.setGridColor(Color.decode("#FCFEFC"));
        jTable1.setFillsViewportHeight(true);
        //jTable1.setFocusable(false);
        jTable1.setSelectionBackground(Color.decode("#303132"));
        jTable1.setSelectionForeground(Color.decode("#C9CDD1"));
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setColumnWidth();
        jTable1.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if (e.getClickCount() == 2) {
                    if(row != -1){
                        EditActionForm editAction = new EditActionForm(getJFrame(),
                            Aps.getTerminal(),meetingName, getSelectedRowData());
                        editAction.setLocationRelativeTo(getJFrame());
                    }
                }
            }
        });
        jTable1.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent arg0) {
                //jTable1.getSelectionModel().clearSelection();
                //jTable1.getSelectionModel().removeSelectionInterval(jTable1.getSelectedRow(), jTable1.getSelectedRow());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setViewportView(jTable1);
        jScrollPane2.getViewport().setBackground(Color.decode("#FCFEFC"));
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        actionListPanel.add(filterPanel, BorderLayout.NORTH);
        actionListPanel.add(jScrollPane2, BorderLayout.CENTER);
        
        gapPanel1 = new JPanel();
        gapPanel1.setPreferredSize(new Dimension(10,300));
        gapPanel1.setMinimumSize(new Dimension(10,300));
        gapPanel1.setOpaque(false);
        gapPanel2 = new JPanel();
        gapPanel2.setPreferredSize(new Dimension(10,300));
        gapPanel2.setMinimumSize(new Dimension(10,300));
        gapPanel2.setOpaque(false);
        pagePanel = new JPanel();
        pagePanel.setPreferredSize(new Dimension(Short.MAX_VALUE,15));
        actionPlanPanel.add(apInformationPanel, BorderLayout.NORTH);
        actionPlanPanel.add(actionListPanel, BorderLayout.CENTER);
        actionPlanPanel.add(gapPanel1, BorderLayout.WEST);
        actionPlanPanel.add(gapPanel2, BorderLayout.EAST);
        actionPlanPanel.add(pagePanel, BorderLayout.SOUTH);
        
    }
    
    private void minimizeWindow(){
        this.setState(ICONIFIED);
    }
    
    private void resizeWindow(){
        if(!resizeFlag){
            Dimension d = this.getMinimumSize();
            this.setPreferredSize(this.getMinimumSize());
            xPosition = (nativeScreenSize.width/2)-(d.width/2);
            yPosition = (nativeScreenSize.height/2)-(d.height/2);
            this.setLocation(xPosition,yPosition);
            frameButtonsPanel.setPreferredSize(new Dimension(d.width,30));
            resizeLabel.setIcon(new ImageIcon(resource+"resize.png"));
            resizeLabel.setToolTipText("Maximize");
            pack();
            resizeFlag = true;
        }
        else{ // Restoring statements
            this.setLocation(0,0);
            this.setPreferredSize(nativeScreenSize);
            frameButtonsPanel.setPreferredSize(new Dimension(nativeScreenSize.width,30));
            resizeLabel.setIcon(new ImageIcon(resource+"tabB.png"));
            resizeLabel.setToolTipText("Minimize:Size");
            maximize(6);
            revalidate();
            resizeFlag = false;
        }
    }
        
    public JFrame getJFrame(){
        return this;
    }
    
    public synchronized void maximize(int state){
        if ((state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) { 
            Insets screenInsets = getToolkit().getScreenInsets(getGraphicsConfiguration());
            Rectangle screenSize = getGraphicsConfiguration().getBounds();
            Rectangle maxBounds = new Rectangle(screenInsets.left + screenSize.x, 
                    screenInsets.top + screenSize.y, 
                    screenSize.x + screenSize.width - screenInsets.right - screenInsets.left,
                    screenSize.y + screenSize.height - screenInsets.bottom - screenInsets.top); 
            this.setMaximizedBounds(maxBounds); 
        } 
        this.setExtendedState(state);
    }
    
    public void addFonts() throws FontFormatException{
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\fonts\\diane_de_france\\Diane de France.ttf")));
        }
        catch (IOException e) {
            //Handle exception
            System.out.println("NO CARGAN LAS FUENTES");
        }
    }
    
    private void meetingSelected(ItemEvent event){
        meetingName = event.getItem().toString();
    }
    
    private void setAPSumary(ActionPlan plan){
        
    }
    
    private void createFilterPanel(){
        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.LINE_AXIS));
        filterPanel.setPreferredSize(new Dimension(actionPlanPanel.getMaximumSize().height, 40));
        JLabel filterLabel = new JLabel("Filter by");
        filterLabel.setHorizontalAlignment(JLabel.CENTER);
        filterLabel.setPreferredSize(new Dimension(60,filterPanel.getPreferredSize().height));
        filterCombobox = new JComboBox();
        filterCombobox.setMaximumSize(new Dimension(140,26));
        filterCombobox.setModel(new DefaultComboBoxModel<>(new Object[]
            {"DATE", "RESPONSIBLE","STATUS"}
        ));

        filterPanel.add(filterLabel);
        filterPanel.add(filterCombobox);
        filterPanel.add(Box.createRigidArea(new Dimension(20,26)));
        filterContentComboBox = new JComboBox();
        filterContentComboBox.setMaximumSize(new Dimension(140,26));
        filterPanel.add(filterContentComboBox);
        filterPanel.add(Box.createHorizontalGlue());
        addActionLabel = new JLabel();
        addActionLabel.setIcon(new ImageIcon(resource+"plus-24.png"));
        addActionLabel.setPreferredSize(new Dimension(35,filterPanel.getPreferredSize().height));
        addActionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                addActionLabel.setIcon(new ImageIcon(resource+"plusGreen-24.png"));
            }
            @Override
            public void mouseExited(MouseEvent e){
                if(!clickedFlag)
                    addActionLabel.setIcon(new ImageIcon(resource+"plus-24.png"));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                if(meetComboBox.getSelectedIndex() != -1){
                    AddActionForm add_action;
                    try {
                        clickedFlag = true;
                        addActionLabel.setIcon(new ImageIcon(resource+"plusGreen-24.png"));
                        add_action = new AddActionForm(getJFrame(),Aps.getTerminal(),meetingName);
                        add_action.setLocationRelativeTo(getJFrame());
                    } catch (Exception ex) {
                        Logger.getLogger(UITerminal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(getJFrame(),
                            "<html><center>No Meeting has been selected. Select one first.</html>",
                            "Validation",JOptionPane.ERROR_MESSAGE);
                }
            }
        }); 
        modifyActionLabel = new JLabel();
        modifyActionLabel.setIcon(new ImageIcon(resource+"edit-24.png"));
        modifyActionLabel.setPreferredSize(new Dimension(35,filterPanel.getPreferredSize().height));
        modifyActionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){}
            @Override
            public void mouseExited(MouseEvent e){}
            @Override
            public void mouseClicked(MouseEvent e){
                if(meetComboBox.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(getJFrame(),
                            "<html><center>No Meeting has been selected. Select one first.</html>",
                            "Validation",JOptionPane.ERROR_MESSAGE);
                }
                else if(jTable1.getSelectedRowCount() == 0){
                    JOptionPane.showMessageDialog(getJFrame(),
                            "<html><center>No Action has been selected. Select one first.</html>",
                            "Validation",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    EditActionForm edit_action = new EditActionForm(getJFrame(),
                            Aps.getTerminal(),meetingName, getSelectedRowData());
                    edit_action.setLocationRelativeTo(getJFrame());
                    jTable1.getSelectionModel().clearSelection();
                }
            }
        }); 
        deleteActionLabel = new JLabel();
        deleteActionLabel.setIcon(new ImageIcon(resource+"delete-24.png"));
        deleteActionLabel.setPreferredSize(new Dimension(35, filterPanel.getPreferredSize().height));
        deleteActionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){}
            @Override
            public void mouseExited(MouseEvent e){}
            @Override
            public void mouseClicked(MouseEvent e){
                if(meetComboBox.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(getJFrame(),
                            "<html><center>No Meeting has been selected. Select one first.</html>",
                            "Validation",JOptionPane.ERROR_MESSAGE);
                }
                else if(jTable1.getSelectedRowCount() == 0){
                    JOptionPane.showMessageDialog(getJFrame(),
                            "<html><center>No Action has been selected. Select one first.</html>",
                            "Validation",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Object[] options = { "Yes", "No" };
                    if(JOptionPane.showOptionDialog(getJFrame(),
                        "<html><center>Are you sure to delete the selected Action?",
                        "Delete Action",JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0){
                        int row_index = jTable1.getSelectedRow();
                        TableModel model = jTable1.getModel();
                        try {
                            boolean is_deleted = Aps.getTerminal().deleteAction(
                                    String.valueOf(model.getValueAt(row_index, 0)),meetingName);
                            if(is_deleted){
                                ((DefaultTableModel)model).removeRow(row_index);
                                jTable1.getSelectionModel().clearSelection();
                                jTable1.repaint();
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(UITerminal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    else{
                    }
                }
            }
        });
        filterPanel.add(addActionLabel);
        filterPanel.add(modifyActionLabel);
        filterPanel.add(deleteActionLabel);
    }
    
    private void centerColumnContent(){
        for(int i=0; i<10;i++){
            jTable1.getColumnModel().getColumn(i).setCellRenderer(new TableCellRenderer(){
                private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
                @Override
                public Component getTableCellRendererComponent(JTable table, 
                        Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if(!(column == 2 || column ==3) )
                        DEFAULT_RENDERER.setHorizontalAlignment(SwingConstants.CENTER);
                    c.setBackground(row%2==0 ? Color.WHITE : Color.LIGHT_GRAY);
                    return c;
                }
            });
        }
    }
    
    private void setColumnWidth(){       
        jTable1.getColumnModel().getColumn(0).setMaxWidth(75);  //ID
        jTable1.getColumnModel().getColumn(1).setMaxWidth(40);  //RESPONSIBLE
        jTable1.getColumnModel().getColumn(4).setMaxWidth(73);  //P.START DATE
        jTable1.getColumnModel().getColumn(5).setMaxWidth(73);  //P.FINISH DATE
        jTable1.getColumnModel().getColumn(6).setMaxWidth(73);  //R.FINISH DATE
        jTable1.getColumnModel().getColumn(7).setMaxWidth(50);  //PROGRESS
        jTable1.getColumnModel().getColumn(8).setMinWidth(120); //STATUS
        jTable1.getColumnModel().getColumn(9).setMaxWidth(40);  //DURATION
    }
    
    public void addRowToTableContent(Object[] row){
        DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
        dm.addRow(row);
        jTable1.setModel(dm);
        jTable1.repaint();
    }
    
    public void modifiedTableContent(Object[] rowDataModified){
        DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
        int rowIndex = jTable1.getSelectedRow();
        for(int columnIndex=1;columnIndex<rowDataModified.length;columnIndex++){
            Object data = rowDataModified[columnIndex];
            if(data != null)
                if(jTable1.getRowSorter() != null)
                    System.out.println("IS SORTER");
                dm.setValueAt(data, rowIndex, columnIndex);
        }
        jTable1.repaint();
    }
    
    public void disableFrame(){
        this.setEnabled(false);
    }
    
    private Object[] getSelectedRowData(){
        Object[] rowData = new Object[10];
        int rowIndex = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        
        if(jTable1.getRowSorter() != null)
            rowIndex = jTable1.convertRowIndexToModel(rowIndex);
        
        for(int columnIndex = 0;columnIndex < 10;columnIndex++)
            rowData[columnIndex] = model.getValueAt(rowIndex, columnIndex);
        return rowData;
    }
    
    private String getParticipantsAcronyms(WorkTeam workteam, ArrayList<Collaborator> adtParticipants){
        String s = "";
        ArrayList<Collaborator> collaborators = (ArrayList<Collaborator>)workteam.getMembers().clone();
        if(!adtParticipants.isEmpty())
            collaborators.addAll((ArrayList<Collaborator>)adtParticipants.clone());
        
        for(int i=0;i<collaborators.size();i++){
            if(i == collaborators.size()-1)
                s = s+ collaborators.get(i).getAcronymName();
            else
                s = s+ collaborators.get(i).getAcronymName()+", ";
        }
        return s;
    }
    
    private void setWindowListener(){
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e){}

            @Override
            public void windowClosing(WindowEvent e){
                //parent.setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e){}

            @Override
            public void windowIconified(WindowEvent e){}

            @Override
            public void windowDeiconified(WindowEvent e){}

            @Override
            public void windowActivated(WindowEvent e){}

            @Override
            public void windowDeactivated(WindowEvent e){}
        });
    }
    
    public Component getJComponent(String name){
        if(name.equals("addActionLabel"))
            return addActionLabel;
        return null;
    }
    
    public void setFlag(boolean bool){
        clickedFlag = bool;
    }
}
