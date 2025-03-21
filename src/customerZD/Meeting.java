/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package customerZD;

import java.util.ArrayList;
import javax.swing.BoxLayout;

/**
 *
 * @author ZD
 */
public class Meeting extends javax.swing.JFrame {

    /**
     * Creates new form Auditorium
     */
    
    Booking booking = new Booking();
    ArrayList<ArrayList<String>> meeting;
    String userID;
    
    public Meeting() {
        initComponents();
//        booking.SetHallList();
//        meeting = booking.getMeeting();
//        
//        jPanel1.removeAll(); 
//        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS)); // Vertical stacking
//        for (ArrayList<String> item : meeting){
//            HallCard card = new HallCard();
//            card.setId(item.get(0));
//            card.setName(item.get(1));
//            card.setPax(item.get(4));
//            card.setDesc(item.get(5));
//            card.setPrice(item.get(3));
//            jPanel1.add(card);
//        }
//        jPanel1.revalidate();
//        jPanel1.repaint();
    }
    
    public Meeting(String userID) {
        initComponents();
        
        this.userID = userID;
        booking.SetHallList();
        meeting = booking.getMeeting();
        
        jPanel1.removeAll(); 
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS)); // Vertical stacking
        for (ArrayList<String> item : meeting){
            HallCard card = new HallCard(this);
            card.setuserID(userID);
            card.sethallId(item.get(0));
            card.setName(item.get(1));
            card.setPax(item.get(4));
            card.setDesc(item.get(5));
            card.setPrice(item.get(3));
            jPanel1.add(card);
        }
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAuditorium = new javax.swing.JButton();
        btnHalls = new javax.swing.JButton();
        btnMeeting = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(232, 251, 255));

        btnAuditorium.setBackground(new java.awt.Color(224, 251, 255));
        btnAuditorium.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAuditorium.setForeground(new java.awt.Color(0, 0, 0));
        btnAuditorium.setText("Auditorium");
        btnAuditorium.setBorderPainted(false);
        btnAuditorium.setContentAreaFilled(false);
        btnAuditorium.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuditorium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuditoriumActionPerformed(evt);
            }
        });

        btnHalls.setBackground(new java.awt.Color(224, 251, 255));
        btnHalls.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHalls.setForeground(new java.awt.Color(0, 0, 0));
        btnHalls.setText("Banquet Hall");
        btnHalls.setBorderPainted(false);
        btnHalls.setContentAreaFilled(false);
        btnHalls.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHalls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHallsActionPerformed(evt);
            }
        });

        btnMeeting.setBackground(new java.awt.Color(224, 251, 255));
        btnMeeting.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMeeting.setForeground(new java.awt.Color(87, 114, 255));
        btnMeeting.setText("Meeting Room");
        btnMeeting.setBorderPainted(false);
        btnMeeting.setContentAreaFilled(false);
        btnMeeting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMeeting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeetingActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(224, 251, 255));
        btnHistory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHistory.setForeground(new java.awt.Color(0, 0, 0));
        btnHistory.setText("History");
        btnHistory.setBorderPainted(false);
        btnHistory.setContentAreaFilled(false);
        btnHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnProfile.setBackground(new java.awt.Color(224, 251, 255));
        btnProfile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(0, 0, 0));
        btnProfile.setText("Profile");
        btnProfile.setBorderPainted(false);
        btnProfile.setContentAreaFilled(false);
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAuditorium)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHalls)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMeeting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProfile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAuditorium)
                    .addComponent(btnHalls)
                    .addComponent(btnMeeting)
                    .addComponent(btnHistory)
                    .addComponent(btnProfile))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAuditoriumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriumActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Auditorium(userID).setVisible(true);
    }//GEN-LAST:event_btnAuditoriumActionPerformed

    private void btnHallsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHallsActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Hall(userID).setVisible(true);
    }//GEN-LAST:event_btnHallsActionPerformed

    private void btnMeetingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeetingActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Meeting(userID).setVisible(true);
    }//GEN-LAST:event_btnMeetingActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new History(userID).setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
        //disable this page to prevent multiple instances (HG Lim)
        this.setEnabled(false);
        
        new Profile(userID,this).setVisible(true);
    }//GEN-LAST:event_btnProfileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Meeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Meeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Meeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Meeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Meeting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAuditorium;
    private javax.swing.JButton btnHalls;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnMeeting;
    private javax.swing.JButton btnProfile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
