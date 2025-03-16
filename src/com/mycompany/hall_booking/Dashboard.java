/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hall_booking;

import Classes.IOMethods;
import adminAustin.Login;
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
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    public Dashboard() {
        initComponents();
        setTitle("Data Table Example");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Booking ID");
        model.addColumn("Hall ID");
        model.addColumn("UserID");
        model.addColumn("Check-in Date");
        model.addColumn("Check-in Time");
        model.addColumn("Check-out Time");
        model.addColumn("pax");
        model.addColumn("Event Type");
        model.addColumn("Remarks");
        model.addColumn("Price");
        model.addColumn("Booking Date");

        // Load data from file and calculate total price
        double totalPrice = loadDataFromFile(IOMethods.BOOKINGTEXT, model);

        // Set the priceLabel text with total price
        priceLabel.setText(String.valueOf(totalPrice));

        // Create table and add it to tablePanel
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        tablePanel.setPreferredSize(new Dimension(1000, 400));
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        adjustColumnWidths(table);
        
        
        // Use BorderLayout for tablePanel to center the table
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Call method processIssuesFile and update label value
        processIssuesFile("report.txt");
        
        resolutionCenterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openResolutionCenter(evt);
            }
        });

    }
    
    private void openResolutionCenter(java.awt.event.ActionEvent evt) {
        ResolutionCenter resolutionCenter = new ResolutionCenter();
        resolutionCenter.setVisible(true);
        
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
    
    private double loadDataFromFile(String filePath, DefaultTableModel model)
    {
        double total = 0.0; // Variable to hold the total price
        
        ArrayList<ArrayList<String>> bookingList = IOMethods.readFile(filePath);
        
        for (ArrayList<String> booking: bookingList)
        {
            if (booking.get(6).equals("M"))
            {
                bookingList.remove(booking);
            }
        }
        
        for (ArrayList<String> filteredBookingList: bookingList)
        {
            model.addRow(filteredBookingList.toArray());
            try 
            {
                double price = Double.parseDouble(filteredBookingList.get(9));
                total += price; // Add to total
            } 
            catch (NumberFormatException e) 
            {
                // Handle the case where price is not a valid double
                System.err.println("Invalid price value: " + filteredBookingList.get(9));
            }
        }


//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(";");
//                model.addRow(data);
//                
                // Assuming the "Price" is in the 9th column (index 8)
                
            

        
        return total; // Return the calculated total
    }
    
    private void processIssuesFile(String filePath) {
        int countInProgress = 0;
        int countUnassigned = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                // checking status "pending"
                if (data.length == 6 && "pending".equals(data[3])) {
                    countInProgress++;
                }
                // Checking status "unassigned"
                else if (data.length == 6 && "unassigned".equals(data[3]))
                {
                    countUnassigned++;
                    
                }

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data from file: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Update GUI components
        inProgressValue.setText(String.valueOf(countInProgress));
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
        inProgressValue = new javax.swing.JLabel();
        unAssignedValue = new javax.swing.JLabel();
        resolutionCenterBtn1 = new javax.swing.JButton();
        dashboardLabel = new javax.swing.JLabel();
        totalRevenueBox = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        tablePanel = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        salesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        salesLabel.setText("Sales");

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

        inProgressValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        inProgressValue.setForeground(new java.awt.Color(255, 255, 255));
        inProgressValue.setText("0");

        unAssignedValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        unAssignedValue.setForeground(new java.awt.Color(255, 255, 255));
        unAssignedValue.setText("0");

        resolutionCenterBtn1.setBackground(new java.awt.Color(255, 51, 51));
        resolutionCenterBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        resolutionCenterBtn1.setForeground(new java.awt.Color(255, 255, 255));
        resolutionCenterBtn1.setText("Logout");
        resolutionCenterBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolutionCenterBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(resolutionCenterBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(inProgressLabel)
                            .addGap(24, 24, 24)
                            .addComponent(inProgressValue))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(unassignedLabel)
                            .addGap(21, 21, 21)
                            .addComponent(unAssignedValue))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(resolutionCenterBtn)
                                .addComponent(salesDashboardBtn)))))
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
                    .addComponent(inProgressValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resolutionCenterBtn1)
                .addGap(94, 94, 94))
        );

        dashboardLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        dashboardLabel.setText("Dashboard");

        totalRevenueBox.setBackground(new java.awt.Color(122, 102, 174));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Revenue");

        priceLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        priceLabel.setText("0");

        javax.swing.GroupLayout totalRevenueBoxLayout = new javax.swing.GroupLayout(totalRevenueBox);
        totalRevenueBox.setLayout(totalRevenueBoxLayout);
        totalRevenueBoxLayout.setHorizontalGroup(
            totalRevenueBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalRevenueBoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(32, 32, 32))
            .addGroup(totalRevenueBoxLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(priceLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totalRevenueBoxLayout.setVerticalGroup(
            totalRevenueBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalRevenueBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(priceLabel)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dashboardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesLabel)
                        .addComponent(totalRevenueBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(totalRevenueBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
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

    private void resolutionCenterBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolutionCenterBtn1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
                
    }//GEN-LAST:event_resolutionCenterBtn1ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel inProgressLabel;
    private javax.swing.JLabel inProgressValue;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton resolutionCenterBtn;
    private javax.swing.JButton resolutionCenterBtn1;
    private javax.swing.JButton salesDashboardBtn;
    private javax.swing.JLabel salesLabel;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel totalRevenueBox;
    private javax.swing.JLabel unAssignedValue;
    private javax.swing.JLabel unassignedLabel;
    // End of variables declaration//GEN-END:variables
}
