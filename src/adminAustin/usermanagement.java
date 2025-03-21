/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adminAustin;

import Classes.IOMethods;
import austincell.TableActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class usermanagement extends javax.swing.JFrame {

    /**
     * Creates new form user management
     */
    public usermanagement() {
        initComponents();
        addrow();
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
        jLabel2 = new javax.swing.JLabel();
        Userbtn = new javax.swing.JButton();
        bookingbtn = new javax.swing.JButton();
        Schedulerbtn1 = new javax.swing.JButton();
        searchtxt1 = new javax.swing.JTextField();
        searchbtn1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        customertbl = new javax.swing.JTable();
        Deletebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        Userbtn.setText("User");
        Userbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserbtnActionPerformed(evt);
            }
        });

        bookingbtn.setText("Booking");
        bookingbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingbtnActionPerformed(evt);
            }
        });

        Schedulerbtn1.setText("Scheduler ");
        Schedulerbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Schedulerbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Userbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Schedulerbtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookingbtn)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Userbtn)
                    .addComponent(bookingbtn)
                    .addComponent(Schedulerbtn1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchtxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtxt1ActionPerformed(evt);
            }
        });

        searchbtn1.setText("Search");
        searchbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtn1ActionPerformed(evt);
            }
        });

        customertbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(customertbl);

        Deletebtn.setText("Delete");
        Deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchtxt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchbtn1)))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchbtn1)
                    .addComponent(searchtxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Deletebtn)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserbtnActionPerformed
        // TOD
        usermanagement next = new usermanagement();
        next.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_UserbtnActionPerformed

    private void bookingbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingbtnActionPerformed
        // TODO add your handling code here:
        bookingmanagement next = new bookingmanagement();
        next.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bookingbtnActionPerformed

    private void Schedulerbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Schedulerbtn1ActionPerformed
        // TODO add your handling code here:
        Schedulestaffmanagement next = new Schedulestaffmanagement();
        next.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Schedulerbtn1ActionPerformed

    private void searchbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtn1ActionPerformed
        // TODO add your handling code here:
        //if (searchtxt1.getText() != null) 
        
        //changed logic to validate if the textbox is empty
        if (searchtxt1.getText().length()>0)
        {
            ArrayList<String[]> data = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader("user.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.trim().split(";");
                    
                    //filter by name
                    if (parts[5].equals("C") && parts[1].equals(searchtxt1.getText())){data.add(parts);}
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            Object[] columnNames = {"User ID", "Username", "Phone number","Email"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create model with column names

            for (String[] rowData : data) {
                ArrayList<String> list = new ArrayList<>(Arrays.asList(rowData));
                list.remove(5);
                String[] newArray = list.toArray(new String[0]);
                model.addRow(newArray);
            }
           customertbl.setModel(model);
           
       }
       //refresh customer list if textbox is empty (HG LIM)
       else 
        {
            JOptionPane.showMessageDialog(null, "Resetting table...");
            System.out.println("Empty");
            addrow();
        }
        
        //focus cursor after search results are displayed (HG Lim)
        searchtxt1.requestFocus();
    }//GEN-LAST:event_searchbtn1ActionPerformed

    private void searchtxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxt1ActionPerformed

    private void DeletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletebtnActionPerformed
        // TODO add your handling code here:
  
        Admin a = new Admin();
        
        int selectedRow = customertbl.getSelectedRow();
        System.out.println(selectedRow);
        
        
        //validation before deletion (HG Lim)
        
        //case if no rows selected, print message
        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Please select a row before deleting.", "No rows selected", JOptionPane.WARNING_MESSAGE);
        }
        //case if row selected, display confirmation message 
        else if (JOptionPane.showConfirmDialog(null, "Delete customer " + customertbl.getValueAt(selectedRow, 1) + "?", "Delete user",JOptionPane.WARNING_MESSAGE)==0)
        {
            String userID = (String) customertbl.getValueAt(selectedRow, 0);
            a.deleteUser(userID);
            this.dispose();
            new usermanagement().setVisible(true);
                    
        }
        
        

        //change later
//        ArrayList<ArrayList<String>> userdata = IOMethods.readFile(IOMethods.USERTEXT);
//        for (int row = 0; row < userdata.size(); row++) {
//            System.out.println(row);
//            if (userdata.get(row).get(0).equals(userid)) {
//                userdata.remove(row);
//            }
//        }
           
            
//            FileWriter fw = new FileWriter("user.txt");
//            for (int i = 0; i < userdata.size(); i++) {
//                for (int x = 0; x <= 5; x++){
//                    fw.write(userdata.get(i).get(x) + ";");  
//                }
//            }
//
//            fw.close();
//            this.dispose();
//            new usermanagement().setVisible(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_DeletebtnActionPerformed

    /**
     * @param args the command line arguments
     */
    private void addrow() {
//        ArrayList<String[]> data = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.trim().split(";");
//                if (parts[5].equals("C")){data.add(parts);}
//            }
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        
        Admin a = new Admin();
        ArrayList<ArrayList<String>>  userList = a.getUserList();
        Iterator<ArrayList<String>> iterator = userList.iterator();

        while (iterator.hasNext()) {
            ArrayList<String> customer = iterator.next();

            // Remove customers where the 6th element is not "C"
            if (!customer.get(5).equals("C")) {
                iterator.remove(); // Safe removal using the iterator
            } else {
                // If customer is not removed, remove the 6th element
                customer.remove(4);
//                customer.remove(5);
                
            }
        }
        
        System.out.println(userList);

        Object[] columnNames = {"User ID", "Username", "Phone number","Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create model with column names

        for (List<String> customer : userList) {
            
            String[] newArray = customer.toArray(new String[0]);
            model.addRow(newArray);
        }
       customertbl.setModel(model);
       
       searchtxt1.requestFocus();
    }
    
    
    
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
            java.util.logging.Logger.getLogger(usermanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usermanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usermanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usermanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usermanagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Deletebtn;
    private javax.swing.JButton Schedulerbtn1;
    private javax.swing.JButton Userbtn;
    private javax.swing.JButton bookingbtn;
    private javax.swing.JTable customertbl;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchbtn1;
    private javax.swing.JTextField searchtxt1;
    // End of variables declaration//GEN-END:variables
}
