/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hall_booking;

import Classes.IOMethods;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
/**
 *
 * @author adrie
 */
public class ResolutionCenter extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    public ResolutionCenter() {
        initComponents();
        setTitle("Resolution Center");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Report ID");
        model.addColumn("Booking ID");
        model.addColumn("Description");
        model.addColumn("Status");
        model.addColumn("Scheduler");
        model.addColumn("Report Date");

        // Load data from issues.txt and add it to the table model
        loadDataFromIssuesFile("report.txt", model);

        // Create table and add it to tablePanel
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        tablePanel.setPreferredSize(new Dimension(700, 400));

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        adjustColumnWidths(table);

        // Use BorderLayout for tablePanel to center the table
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Call method processIssuesFile and update label value
           processIssuesFile("report.txt");
        
        salesDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openResolutionCenter(evt);
            }
        });
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row != -1) { // Ensure a row is selected
                    String reportNum = model.getValueAt(row, 0).toString(); // Get Customer ID
                    String bookingID = model.getValueAt(row, 1).toString();
                    String desc = model.getValueAt(row, 2).toString();
                    String reportDate = model.getValueAt(row, 5).toString();
                    String scheduler = model.getValueAt(row, 4).toString();
                    String status = model.getValueAt(row, 3).toString();
                    openResolutionCenterDetail(reportNum, bookingID, desc,scheduler, reportDate, status);
                }
            }
        });


    }
    
    private void openResolutionCenterDetail(String reportNum, String bookingID, String desc, String scheduler, String reportDate,String status) {
        ResolutionCenterDetail detail = new ResolutionCenterDetail();

        // Pass bookingID to ResolutionCenterDetail
        detail.setValue(reportNum, bookingID, desc, scheduler, reportDate, status);
        detail.setVisible(true); // Make sure this is called before dispose()
        
        this.dispose(); // Close the current window
    }

    
    private void openResolutionCenter(java.awt.event.ActionEvent evt) {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        
        this.dispose();
    }
    
    private void adjustColumnWidths(JTable table) {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                // Cap the width to the max allowed width
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth(preferredWidth);
        }
    }
    
    private void loadDataFromIssuesFile(String filePath, DefaultTableModel model) 
    {
        ArrayList<ArrayList<String>> reportList = IOMethods.readFile(IOMethods.REPORTTEXT);
        ArrayList<ArrayList<String>> userList= ResolutionCenterDetail.getUserList();

        System.out.println(reportList);
        for (ArrayList<String> report: reportList)
        {
            String schedulerID = report.get(4);
            for (ArrayList<String> user: userList)
            {
//                boolean match = false;
                if (schedulerID.equals(user.get(0)))
                {
                   report.set(4, user.get(1));
//                   match = true;
                   break;
                }    
            }
            model.addRow(report.toArray());
            
        }
 
    }

    
    private void processIssuesFile(String filePath) {
        int countInProgress = 0;
        int countUnassigned = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                // checking status "In Progress"
                if (data.length == 6 && "pending".equals(data[3])) {
                    countInProgress++;
                }

                // Checking if got scheduler working
                if (data.length == 6 && "unassigned".equals(data[3])) {
                    countUnassigned++;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data from file: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println(countInProgress);
        System.out.println(countUnassigned);
        // Update GUI components
        inProgress.setText(String.valueOf(countInProgress));
        unAssignedValue.setText(String.valueOf(countUnassigned));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        salesLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        salesDashboardBtn = new javax.swing.JButton();
        resolutionCenterBtn = new javax.swing.JButton();
        unassignedLabel = new javax.swing.JLabel();
        inProgressLabel = new javax.swing.JLabel();
        inProgress = new javax.swing.JLabel();
        unAssignedValue = new javax.swing.JLabel();
        dashboardLabel = new javax.swing.JLabel();
        tablePanel = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        salesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        salesLabel.setText("Resolution");

        jPanel2.setBackground(new java.awt.Color(66, 55, 98));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome");

        salesDashboardBtn.setText("Sales Dashboard");

        resolutionCenterBtn.setText("Resolution Center");
        resolutionCenterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolutionCenterBtnActionPerformed(evt);
            }
        });

        unassignedLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        unassignedLabel.setForeground(new java.awt.Color(255, 255, 255));
        unassignedLabel.setText("Unassigned");

        inProgressLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        inProgressLabel.setForeground(new java.awt.Color(255, 255, 255));
        inProgressLabel.setText("Pending");

        inProgress.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        inProgress.setForeground(new java.awt.Color(255, 255, 255));
        inProgress.setText("0");

        unAssignedValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        unAssignedValue.setForeground(new java.awt.Color(255, 255, 255));
        unAssignedValue.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(salesDashboardBtn)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(inProgressLabel)
                        .addGap(24, 24, 24)
                        .addComponent(inProgress))
                    .addComponent(resolutionCenterBtn)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(unassignedLabel)
                        .addGap(21, 21, 21)
                        .addComponent(unAssignedValue)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(salesDashboardBtn)
                .addGap(18, 18, 18)
                .addComponent(resolutionCenterBtn)
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unassignedLabel)
                    .addComponent(unAssignedValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inProgressLabel)
                    .addComponent(inProgress))
                .addContainerGap(387, Short.MAX_VALUE))
        );

        dashboardLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        dashboardLabel.setText("Center");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dashboardLabel)
                    .addComponent(salesLabel)
                    .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardLabel)
                .addGap(18, 18, 18)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resolutionCenterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolutionCenterBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resolutionCenterBtnActionPerformed
    
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
            java.util.logging.Logger.getLogger(ResolutionCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResolutionCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResolutionCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResolutionCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResolutionCenter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel inProgress;
    private javax.swing.JLabel inProgressLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton resolutionCenterBtn;
    private javax.swing.JButton salesDashboardBtn;
    private javax.swing.JLabel salesLabel;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel unAssignedValue;
    private javax.swing.JLabel unassignedLabel;
    // End of variables declaration//GEN-END:variables
}
