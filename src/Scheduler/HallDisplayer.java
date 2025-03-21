/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Scheduler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Hg
 */
public class HallDisplayer extends javax.swing.JPanel {

    /**
     * Creates new form HallDisplayer
     */
    
    //get  current tab and user ID from main Form
    private int page;
    private String userID;
    private String hallID;
    private String hallName;
    private double pricePerHour;
    private String hallType;
    private String description;
    private int capacity = 0;
    private String imagePath;
    DecimalFormat df;
    JFrame frame;
    
    public HallDisplayer() {
        initComponents();
    }
    
    public HallDisplayer(int page,ArrayList<String> hall,JFrame frame,String userID)
    {
       initComponents();
       
       this.page = page;
       this.userID = userID;
       this.frame = frame;
       df = new DecimalFormat("#.00");
       this.hallID = hall.get(0);
       this.hallName = hall.get(1);
       this.hallType = hall.get(2);
       this.pricePerHour = Double.parseDouble(hall.get(3));
       this.capacity = Integer.parseInt(hall.get(4));
       this.description = hall.get(5);
       
       labelName.setText(hallName);
       
       
    //split lines if more than 7 words
    // Split lines if more than 7 words
    int spaceCount = 0;
    int index = -1;
    for (int i = 0; i < description.length(); i++) 
    {
        if (description.charAt(i) == ' ') {
            spaceCount++;
            if (spaceCount == 8) {  // 8th space indicates 7 words
                index = i;
                break;
            }
        }
    }

    if (index != -1) 
    {
        // Create substrings
        String firstPart = description.substring(0, index);
        String secondPart = description.substring(index + 1);

        // Split the second part if it has more than 7 words (total more than 14 words)
        spaceCount = 0;
        int secondIndex = -1;

        for (int i = 0; i < secondPart.length(); i++) 
        {
            if (secondPart.charAt(i) == ' ') {
                spaceCount++;
                if (spaceCount == 8) {  // 7th space in the second part
                    secondIndex = i;
                    break;
                }
            }
        }

        if (secondIndex != -1) 
        {
            String newSecondPart = secondPart.substring(0, secondIndex);
            String thirdPart = secondPart.substring(secondIndex + 1);
            labelDescription.setText(firstPart);
            labelDescription2.setText(newSecondPart);
            labelDescription3.setText(thirdPart);
        } 
        else 
        {
            labelDescription.setText(firstPart);
            labelDescription2.setText(secondPart);
            labelDescription3.setText("");
        }
    }   
    else 
    {
        labelDescription.setText(description);
        labelDescription2.setText("");
        labelDescription3.setText("");
    }

       labelPrice.setText("RM " + df.format(pricePerHour) + " per hour");
       labelCapacity.setText("Capacity: " + capacity + " people");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelName = new javax.swing.JLabel();
        labelDescription = new javax.swing.JLabel();
        labelPrice = new javax.swing.JLabel();
        buttonEdit = new javax.swing.JButton();
        buttonSchedule = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelDescription3 = new javax.swing.JLabel();
        labelDescription2 = new javax.swing.JLabel();
        labelCapacity = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(510, 150));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(550, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelName.setBackground(new java.awt.Color(0, 0, 0));
        labelName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelName.setText("jLabel1");
        add(labelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        labelDescription.setForeground(new java.awt.Color(102, 102, 102));
        labelDescription.setText("jLabel2");
        add(labelDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        labelPrice.setText("jLabel3");
        add(labelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        buttonEdit.setBackground(new java.awt.Color(0, 204, 0));
        buttonEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonEdit.setText("Edit");
        buttonEdit.setMaximumSize(new java.awt.Dimension(85, 32));
        buttonEdit.setMinimumSize(new java.awt.Dimension(85, 32));
        buttonEdit.setPreferredSize(new java.awt.Dimension(85, 32));
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        add(buttonEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 100, -1));

        buttonSchedule.setBackground(new java.awt.Color(153, 153, 255));
        buttonSchedule.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonSchedule.setForeground(new java.awt.Color(255, 255, 255));
        buttonSchedule.setText("Schedule");
        buttonSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonScheduleActionPerformed(evt);
            }
        });
        add(buttonSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 100, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 20));
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 540, 20));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        labelDescription3.setForeground(new java.awt.Color(102, 102, 102));
        labelDescription3.setText("jLabel2");
        add(labelDescription3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        labelDescription2.setForeground(new java.awt.Color(102, 102, 102));
        labelDescription2.setText("jLabel2");
        add(labelDescription2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        labelCapacity.setText("jLabel2");
        add(labelCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
    // TODO add your handling code here:
      ArrayList<String> hallString = new ArrayList<String>();
      hallString.add(hallID);
      hallString.add(hallName);
      hallString.add(hallType);
      hallString.add(""+pricePerHour);
      hallString.add(""+capacity);
      hallString.add(description);
     
      frame.dispose();
      new Scheduler_editHall(page,userID,hallString).setVisible(true);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonScheduleActionPerformed
        // TODO add your handling code here:
        
        frame.dispose();
        
        //pass userID afterwards after combine
        new Scheduler_Scheduling(page,userID,hallID,hallName).setVisible(true);
    }//GEN-LAST:event_buttonScheduleActionPerformed

    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Scheduler_mainPageHardCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Scheduler_mainPageHardCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Scheduler_mainPageHardCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Scheduler_mainPageHardCode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                new HallDisplayer().setVisible(true);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonSchedule;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelCapacity;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelDescription2;
    private javax.swing.JLabel labelDescription3;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPrice;
    // End of variables declaration//GEN-END:variables
}
