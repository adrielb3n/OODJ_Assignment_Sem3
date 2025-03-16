package Scheduler;

import Classes.Hall;
import Classes.IOMethods;
import Classes.Scheduler;
import adminAustin.Login;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Scheduler_mainPageHardCode {

    private JFrame frame;
    private String userID;
    ArrayList<ArrayList<String>> hallList;
    ArrayList<ArrayList<String>> taskList;
    private int selectIndex =  0;
    private int page = 0;
    private int filter_status = 0;
    

    public Scheduler_mainPageHardCode() 
    {
       
        refresh(0);
        Scheduler.pushSchedule();
        
        
        //create index files during initiation of program
        //move to login page (hmm...) 
        File f = new File(IOMethods.INDEXTEXT);
        if (!f.exists())
        {
            System.out.println("file doesnt exists");
            IOMethods.initiateIndex();
        }
        
    }
    public Scheduler_mainPageHardCode(String userID) 
    {
       
        this.userID = userID;
        refresh(0);
        Scheduler.pushSchedule();
        
        //create index files during initiation of program
        //move to login page (hmm...) 
        File f = new File(IOMethods.INDEXTEXT);
        if (!f.exists())
        {
            System.out.println("file doesnt exists");
            IOMethods.initiateIndex();
        }
        
    }
    
    Scheduler_mainPageHardCode(String userID,int tab) 
    {
        this.userID = userID;
        refresh(tab);
        Scheduler.pushSchedule();
        
        
        //create index files during initiation of program
        //move to login page (hmm...) 
        File f = new File(IOMethods.INDEXTEXT);
        if (!f.exists())
        {
            System.out.println("file doesnt exists");
            IOMethods.initiateIndex();
        }
        
       
        
    }
    
 private void initComponents(int tab,ArrayList<ArrayList<String>> hallList) {
    frame = new JFrame("Scheduler");
    
    //To overcome the limitations of Java Swing, this is the approach taken
    /*
    1. Create Frame
    2. Create TabbedPane
    3. Create Top Pane, add Logout button, setLayout null
    
    4. Create 2 Panes for the content in the tabbedPane content
        4.1 Top Pane is for buttons, and textboxes (controlPane), setLayout null
        4.2 Bottom Pane is for scrollPane (audiScroll)
    5.Create a Pane to hold all the looped controls （scrollStatic)
        (Looped Controls = HallDisplayer）
    6. Add controlPane and audiScroll to scrollStatic,setLayout borderLayout.NORTH
        and borderLayout.CENTER
    
    7. Create panel (p1) for tabbedPane, setLayout borderLayout
    
    8. Add scrollStatic to p1, setLayout borderLayout.CENTER
    9. Copy paste code for BanquetHall and ConferenceRoom, but change VariableName
    */

    // Logout Button
    Button btnLogout = new Button("Logout");
    btnLogout.setBackground(Color.red);
    btnLogout.setForeground(Color.white);
    btnLogout.setBounds(468, 20, 78, 28);
    btnLogout.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            logout();
        }
    });
    
    

    // Top Pane
    JPanel topPane = new JPanel();
    topPane.setBounds(0, 0, 574, 65);
    topPane.setBackground(new Color(204, 255, 255));
    topPane.setLayout(null);
    topPane.add(btnLogout);
    

    //Auditorium Tab dynamic switching code
    JPanel p1 = new JPanel();
    p1.setLayout(new BorderLayout());
    p1.add(tabPaneCreation(tab,"A", hallList), BorderLayout.CENTER);

    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());

    JPanel p3 = new JPanel();
    p3.setLayout(new BorderLayout());
    
    JPanel p4 = new JPanel();
    p4.setLayout(new BorderLayout());
    
    
    // TabbedPane creation
    JTabbedPane menu = new JTabbedPane();
    
    menu.setBounds(5, 90, 550, 460);
    
    //Add ChangeListener
    menu.addChangeListener(new ChangeListener()
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            selectIndex = menu.getSelectedIndex();
            if (selectIndex == 0)
            {
                page = 0;
                p1.removeAll();
                p1.add(tabPaneCreation(page,"A",hallList), BorderLayout.CENTER);
            }
            else if (selectIndex == 1)
            {
                page = 1;
                p2.removeAll();
                p2.add(tabPaneCreation(page,"B",hallList), BorderLayout.CENTER);
            }
            else if (selectIndex == 2)
            {
                page = 2;
                p3.removeAll();
                p3.add(tabPaneCreation(page,"C",hallList), BorderLayout.CENTER);
            }
            else if (selectIndex == 3)
            {
                page = 3;
                p4.removeAll();
                p4.add(tabPaneCreation(page,"M",taskList));
            }
        }
    });
    
    
    // Adding tabs
    menu.add("Auditorium", p1);
    menu.add("Banquet Hall", p2); // Example placeholder for other panels
    menu.add("Conference Room", p3);
    menu.add("Tasks", p4);
    
    menu.setSelectedIndex(tab);
    
    
    frame.add(topPane, BorderLayout.NORTH);
    frame.add(menu, BorderLayout.CENTER);

    frame.setSize(580, 600);
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);
}
 
 
 //tab creation function 
    private JPanel tabPaneCreation(int page,String hall, ArrayList<ArrayList<String>> hallList)
    {
        JPanel creation = new  JPanel();
        if (hall.equals("A") || hall.equals("B") || hall.equals("C"))
        {
            
            Button addBtnAudi = new Button("Add");
            addBtnAudi.setBackground(new Color(85, 82, 255));
            addBtnAudi.setForeground(Color.white);
            addBtnAudi.setFont(new java.awt.Font("Segoe UI", 1, 12));
            addBtnAudi.setBounds(460, 5, 61, 29);
            addBtnAudi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    if (hall.equals("A"))
                    {
                        addAudi();
                    }
                    else if (hall.equals("B"))
                    {
                        addBanquet();
                    }
                    else if (hall.equals("C"))
                    {
                        addConference();
                    }
                }
            });




            //DateTimePicker (Dont know how)
            
            JLabel audiStartLabel = new JLabel("Enter Start DateTime: ");
            audiStartLabel.setBounds(10, 10, 180, 25);

            
            DatePickerSettings dateSettings = new DatePickerSettings();
            
            //set end time settings (from 8 - 5)
            TimePickerSettings datetimeSettings = new TimePickerSettings();
            datetimeSettings.generatePotentialMenuTimes(TimeIncrement.OneHour, LocalTime.of(8, 0), LocalTime.of(17, 0));
            
            //set end time settings (from 9 - 6)
            TimePickerSettings timeSettings = new TimePickerSettings();
            timeSettings.generatePotentialMenuTimes(TimeIncrement.OneHour, LocalTime.of(9,0), LocalTime.of(18,0));
            
            datetimeSettings.setAllowKeyboardEditing(false);
            timeSettings.setAllowKeyboardEditing(false);
            
            
             DateTimePicker mainPageStartDateTime = new DateTimePicker(dateSettings,datetimeSettings);
             mainPageStartDateTime.setBounds(150, 10, 250, 25);
             
            
             
                         
             LocalDate now = LocalDate.now();
            dateSettings.setDateRangeLimits(now, null);
            dateSettings.setAllowKeyboardEditing(false);

            JLabel audiEndLabel = new JLabel("Enter End DateTime: ");
            audiEndLabel.setBounds(10, 50, 180, 25);

             TimePicker audiEndTime = new TimePicker(timeSettings);
             audiEndTime.setBounds(150, 50, 150, 25);

             
            //Filter Button for Auditorium Pane
            Button filterBtnAudi = new Button();
            if (filter_status == 0)
            {
                filterBtnAudi.setLabel("Filter");
            }
            else if (filter_status == 1)
            {
                filterBtnAudi.setLabel("Reset");
            }
            filterBtnAudi.setBackground(new Color(85, 82, 255));
            filterBtnAudi.setForeground(Color.white);
            filterBtnAudi.setFont(new java.awt.Font("Segoe UI", 1, 12));
            filterBtnAudi.setBounds(460, 50, 61, 29);
            filterBtnAudi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    filterByDate(page,mainPageStartDateTime,audiEndTime,userID);
                }
            });
             
             
            //scrollPane codes are here
            int audiPaneCount = 0;
            int audiPaneHeight = 170;
            
            
            JLabel noHall = new JLabel();
            // Auditorium Panel
            JPanel audiListPane = new JPanel();
            audiListPane.setLayout(new BoxLayout(audiListPane, BoxLayout.Y_AXIS)); // Layout for stacking
            for (ArrayList<String> row : hallList) {
                if (row.get(2).equals(hall)) 
                {
                    audiPaneCount = audiPaneCount + 1;
                    HallDisplayer hallDisp = new HallDisplayer(page,row, frame, userID);
                    audiListPane.add(hallDisp);

                }
            }
            //set text if no halls
            if (audiPaneCount == 0)
            {
                noHall.setFont(new Font("Arial", Font.BOLD, 18));
                noHall.setText("No records");
                
                //set horizontal and vertical alignments to center the label
                noHall.setHorizontalAlignment(SwingConstants.CENTER);
                noHall.setVerticalAlignment(SwingConstants.CENTER);
            }
            
            
            //create flexible height for pane containing scrollPane
            //calculate height  required (PaneCount)* paneHeight
            int audiRequiredHeight = ((audiPaneCount) * audiPaneHeight);
            //set Height as the calculated height
            audiListPane.setPreferredSize(new Dimension(450, audiRequiredHeight));

            //add controls to this pane (top part of the control)
            JPanel audiControlPane = new JPanel();
            audiControlPane.setBounds(0, 0, 530, 50);
            audiControlPane.setPreferredSize(new Dimension(525,100));
            audiControlPane.setBackground(Color.white);
            audiControlPane.setLayout(null);
            audiControlPane.add(addBtnAudi);
            audiControlPane.add(filterBtnAudi);
            audiControlPane.add(audiStartLabel);
            audiControlPane.add(audiEndLabel);
            audiControlPane.add(mainPageStartDateTime);
            audiControlPane.add(audiEndTime);


            //bottom part (scrollPane that displays hall information)
            JScrollPane audiScroll = new JScrollPane(audiListPane);
            audiScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            

            JPanel audiScrollStatic = new JPanel();
            audiScrollStatic.setBounds(5, 10, 575, 400);
            audiScrollStatic.setLayout(new BorderLayout());
            audiScrollStatic.add(audiControlPane,BorderLayout.NORTH);
            
            //if-else to display hall cards or label (no halls)
            if (audiPaneCount == 0)
            {
                audiScrollStatic.add(noHall);
                
            }
            else
            {
                audiScrollStatic.add(audiScroll,BorderLayout.CENTER);
                
            }

            creation = audiScrollStatic;
        }
        //code for tasks pane
        else if (hall.equals("M"))
        {   
            int taskPaneCount = 0;
            int taskPaneHeight = 203;
            
            taskList = IOMethods.readFile(IOMethods.REPORTTEXT);
            //Jlabel to display "No task" message
            JLabel noTask = new JLabel();

            //JPanel to store task panels
            JPanel taskPane1 = new JPanel();
            taskPane1.setLayout(new BoxLayout(taskPane1, BoxLayout.Y_AXIS));
            for (ArrayList<String> taskRow : taskList) {
                 if ((taskRow.get(3).strip().equals("pending") || taskRow.get(3).equals("scheduled") ) && taskRow.get(4).equals(userID)) 
                 {
                    taskPaneCount = taskPaneCount + 1;
                    TaskDisplayer taskDisp = new TaskDisplayer(taskRow, frame);
                    taskPane1.add(taskDisp);
                }
            }
            
            if (taskPaneCount == 0)
            {
                noTask.setFont(new Font("Arial", Font.BOLD, 18));
                noTask.setText("No tasks assigned :)");
                
                //set horizontal and vertical alignments to center the label
                noTask.setHorizontalAlignment(SwingConstants.CENTER);
                noTask.setVerticalAlignment(SwingConstants.CENTER);
                
                
            }
            
             //calculate height  required (PaneCount)* paneHeight
            int taskRequiredHeight = ((taskPaneCount )* taskPaneHeight);
            //set Height as the calculated height
            taskPane1.setPreferredSize(new Dimension(530, taskRequiredHeight));
            
            
            JScrollPane taskScroll = new JScrollPane(taskPane1); 
            taskScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            taskScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            
            
            JPanel taskScrollStatic = new JPanel();
            taskScrollStatic.setBounds(5, 10, 675, 400);
            taskScrollStatic.setLayout(new BorderLayout());
            
            //if-else to display task cards or label (no tasks)
            if (taskPaneCount == 0)
            {
                taskScrollStatic.add(noTask);
            }
            else
            {
                taskScrollStatic.add(taskScroll,BorderLayout.CENTER);
            }
            creation = taskScrollStatic;
            //do something
        }
        return creation;
    }
    
    //refresh the hallList when page is focused after deleting an entry
        

    private void logout() {
        frame.dispose();
        new Login().setVisible(true);
    }
    
    private void addAudi()
    {
        frame.dispose();
        new Scheduler_addHall(page, userID,'A',hallList).setVisible(true);
    }
    
        private void addBanquet()
    {
        frame.dispose();
        new Scheduler_addHall(page, userID,'B',hallList).setVisible(true);
    }
        
    private void addConference()
    {
        frame.dispose();
        new Scheduler_addHall(page, userID,'C',hallList).setVisible(true);
    }
    
    private void refresh(int tab)
    {   
        Hall hall = new Hall();
        hallList = hall.getHallList();
        taskList = IOMethods.readFile(IOMethods.REPORTTEXT);
        initComponents(tab,hallList);
    }
         
private void filterByDate(int page,DateTimePicker startDateTimePicker, TimePicker endTimePicker, String hallType) {
    ArrayList<ArrayList<String>> bookingList = IOMethods.readFile(IOMethods.BOOKINGTEXT);
    LocalDate startDate = startDateTimePicker.getDatePicker().getDate();
    LocalTime startTimeSelected = startDateTimePicker.getTimePicker().getTime();
    LocalTime endTimeSelected = endTimePicker.getTime();

    if (filter_status == 0)
    {
        
        // Check if dates/times are set
        if (startDate != null && startTimeSelected != null && endTimeSelected != null) {
            //store empty halls Hall ID 
            ArrayList<String> filteredList = new ArrayList<>();

            for (ArrayList<String> row : hallList)
            {
                filteredList.add(row.get(0));
            }

          // check if startTime > endTime or endTime < StartTime 
          if (startTimeSelected.isAfter(endTimeSelected)|| endTimeSelected.isBefore(startTimeSelected))
          {
              JOptionPane.showMessageDialog(null, "Invalid time range, please try again");
          }
          else
          {
            /*

              Logic for filtering dates:

              1. Get all hall ID from hall List
              2. check if time overlapping
              3. if overlapping, remove from hall ID list
              4. use the filtered list to get hall rows from text file
              5. assign to components

            */

            for (ArrayList<String> row : bookingList)
            {

                if (row.get(3).equals(startDate.toString()))
                {
                    LocalTime bookStartTime = LocalTime.parse(row.get(4));
                    LocalTime bookEndTime = LocalTime.parse(row.get(5));
                    if ((startTimeSelected.isBefore(bookEndTime) && (bookStartTime).isBefore(endTimeSelected)))
                    {
                        for (String hallID : new ArrayList<>(filteredList))
                        {
                            if (row.get(1).equals(hallID))
                            {
                                //left here for testing purposes :)
                                System.out.println(row.get(1));
                                filteredList.remove(hallID);
                            }
                        }
                    }
                }  
            }

            // Update the UI with filtered results
            ArrayList<ArrayList<String>> filteredHalls= updateHallDisplay(filteredList);
            filter_status = 1;
            frame.dispose();
            tabPaneCreation(page,hallType,filteredHalls);
            initComponents(page,filteredHalls);
              System.out.println(filter_status);

          }
        } 
        else 
        {
            JOptionPane.showMessageDialog(frame, "Please select valid start and end date/time.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }
    else
    {   
        filter_status = 0;
        frame.dispose();
        refresh(page);
    }
}

    private ArrayList<ArrayList<String>> updateHallDisplay(ArrayList<String> filteredList) 
    {
       
        ArrayList<ArrayList<String>> filteredHall = new ArrayList<>();
        for (String hallID : filteredList)
        {
            for (ArrayList<String> row : this.hallList)
            {
                if (row.get(0).equals(hallID))
                {
                   filteredHall.add(row);
                }
            }
        }
        return filteredHall;
    }
              
            
            
    public static void main(String[] args) {
        new Scheduler_mainPageHardCode();

    }
}
