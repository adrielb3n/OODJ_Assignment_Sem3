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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

/**
 *
 * @author adrie
 */
public class ResolutionCenterDetail extends javax.swing.JFrame {
    /**
     * Creates new form Register
     */
    private String reportID, bookingID,desc, scheduler, reportDate, status;
    
    
    public ResolutionCenterDetail() {
        initComponents();
        setTitle("Resolution Center");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        closedButton.setVisible(false);
        cancelButton.setVisible(false);
        backButton.setVisible(false);
        schedulerDropdown.setVisible(false);
        updateButton.setVisible(false);
        cancelSchedulerButton.setVisible(false);

        // Create a table model
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Report ID");
//        model.addColumn("Booking ID");
//        model.addColumn("Description");
//        model.addColumn("Report Date");
//        model.addColumn("Scheduler");
//        model.addColumn("Status");
//
        // Load data from issues.txt and add it to the table model
        loadDataFromIssuesFile(IOMethods.REPORTTEXT);
//        
//        // Create table and add it to tablePanel
//        JTable table = new JTable(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        adjustColumnWidths(table);
        
        // Call method processIssuesFile and update label value
//        processIssuesFile(IOMethods.REPORTTEXT);
        
        salesDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSalesDashboard(evt);
            }
        });
        
        resolutionCenterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openResolutionCenter(evt);
            }
        });
        
        changeStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStatusButtons(evt);
            }
        });
    }
    

    private void updateStatusInFile(String reportID, String newStatus) {
        String filePath = "report.txt";
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 6 && data[0].equals(reportID)) {
                    // Update the status field (last field) to the new status
                    data[3] = newStatus;
                    line = String.join(";", data);
                }
                updatedContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading data from file: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Write the updated content back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(updatedContent.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing data to file: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showStatusButtons(java.awt.event.ActionEvent evt) {
        
        //added logic
        //manager can only close task if its status is "done" (scheduler done task) (HG Lim)
        if (this.status.equals("done"))
        {
            closedButton.setVisible(true);
            
        }
        cancelButton.setVisible(true);
        backButton.setVisible(true);
        changeStatusButton.setVisible(false);
        changeSchedulerButton.setVisible(false);
        
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtons(evt);
            }
        });
    }
    
    private void backButtons(java.awt.event.ActionEvent evt) {
        closedButton.setVisible(false);
        cancelButton.setVisible(false);
        backButton.setVisible(false);
        changeStatusButton.setVisible(true);
        changeSchedulerButton.setVisible(true);
    }
    
    
    public void setValue(String reportNum, String bookingID, String desc, String scheduler, String reportDate, String status) {
        reportIDValue.setText(reportNum); // Update the label with the booking ID
        bookingIDValue.setText(bookingID);
        hallNameValue.setText(desc);
        schedulerValue.setText(scheduler);
        statusValue.setText(status);
        reportDateVal.setText(reportDate);
        
        //update the attributes in the form (HG LIM)
        this.reportID = reportNum;
        this.bookingID = bookingID;
        this.desc = desc;
        this.scheduler = scheduler;
        this.reportDate = reportDate;
        this.status = status;
        
    }
    
    private void openSalesDashboard(java.awt.event.ActionEvent evt) {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        
        this.dispose();
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
    
    private void loadDataFromIssuesFile(String filePath) {
        
        int countUnassigned = 0;
        int countInProgress = 0;
        
        
        ArrayList<ArrayList<String>> reportList = IOMethods.readFile(filePath);
        for (ArrayList<String> report: reportList)
        {
          
            
            if (report.size() == 6 && "pending".equals(report.get(3)))
            {
                countInProgress++;
            }
            else if (report.size() == 6 && "unassigned".equals(report.get(3)))
            {
                countUnassigned++;
                
            }
            
            
            
       // Update GUI components
        inProgressValue.setText(String.valueOf(countInProgress));
        unAssignedValue.setText(String.valueOf(countUnassigned));

          
        }
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
        dashboardLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelBookingID = new javax.swing.JLabel();
        labelHallType = new javax.swing.JLabel();
        labelHallName = new javax.swing.JLabel();
        labelScheduler = new javax.swing.JLabel();
        reportIDValue = new javax.swing.JLabel();
        bookingIDValue = new javax.swing.JLabel();
        hallNameValue = new javax.swing.JLabel();
        schedulerValue = new javax.swing.JLabel();
        labelCustomerName = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        reportDateVal = new javax.swing.JLabel();
        statusValue = new javax.swing.JLabel();
        changeStatusButton = new javax.swing.JButton();
        closedButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        changeSchedulerButton = new javax.swing.JButton();
        schedulerDropdown = new javax.swing.JComboBox<>();
        updateButton = new javax.swing.JButton();
        cancelSchedulerButton = new javax.swing.JButton();

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
        inProgressLabel.setText("In Progress");

        inProgressValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        inProgressValue.setForeground(new java.awt.Color(255, 255, 255));
        inProgressValue.setText("0");

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
                        .addComponent(inProgressValue))
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
                    .addComponent(inProgressValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        dashboardLabel.setText("Center");

        labelBookingID.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        labelBookingID.setText("Report ID");

        labelHallType.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        labelHallType.setText("Booking ID");

        labelHallName.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        labelHallName.setText("Description");

        labelScheduler.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        labelScheduler.setText("Scheduler");

        reportIDValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        reportIDValue.setText("Booking ID");

        bookingIDValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        bookingIDValue.setText("Booking ID");

        hallNameValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        hallNameValue.setText("Booking ID");

        schedulerValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        schedulerValue.setText("Booking ID");

        labelCustomerName.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        labelCustomerName.setText("Report Date");

        labelStatus.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        labelStatus.setText("Status");

        reportDateVal.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        reportDateVal.setText("xxxx-xx-xx xx:xx:xx");

        statusValue.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        statusValue.setText("Status");

        changeStatusButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        changeStatusButton.setText("Change Status");
        changeStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeStatusButtonActionPerformed(evt);
            }
        });

        closedButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        closedButton.setText("Close Task");
        closedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closedButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        cancelButton.setText("Cancel Task");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(255, 51, 51));
        backButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        changeSchedulerButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        changeSchedulerButton.setText("Change Scheduler");
        changeSchedulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSchedulerButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        cancelSchedulerButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        cancelSchedulerButton.setText("Cancel");
        cancelSchedulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSchedulerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(schedulerDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                    .addComponent(labelScheduler)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(schedulerValue))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                    .addGap(107, 107, 107)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(reportIDValue)
                                                        .addComponent(bookingIDValue)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelCustomerName)
                                            .addComponent(labelStatus))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(updateButton)
                                            .addComponent(changeSchedulerButton))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cancelSchedulerButton)
                                            .addComponent(changeStatusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(37, 37, 37)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusValue)
                                    .addComponent(reportDateVal)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelHallName)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelBookingID)
                                    .addComponent(labelHallType)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(closedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(184, 184, 184)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(hallNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBookingID)
                    .addComponent(reportIDValue)
                    .addComponent(labelCustomerName)
                    .addComponent(reportDateVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHallType)
                    .addComponent(bookingIDValue)
                    .addComponent(labelStatus)
                    .addComponent(statusValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelScheduler)
                    .addComponent(schedulerValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schedulerDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(labelHallName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hallNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelSchedulerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeStatusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeSchedulerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(185, Short.MAX_VALUE))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void changeStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeStatusButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_changeStatusButtonActionPerformed

    private void closedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closedButtonActionPerformed
        // TODO add your handling code here:
        String reportID2 = reportIDValue.getText();
        
        if (this.status.equals("done"))
        {
            this.status = "closed";
            updateStatusInFile(reportID2, "closed");
            statusValue.setText("closed");
            JOptionPane.showMessageDialog(this, "Status updated to Closed", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            refresh(reportID, bookingID, desc, scheduler, reportDate, status);
        }
  
        
    }//GEN-LAST:event_closedButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        String reportID2 = reportIDValue.getText();
        System.out.println(reportID2);
        
        //set status as cancelled in txt file, update label
        this.status = "cancelled";
        updateStatusInFile(reportID2, "cancelled");
        statusValue.setText("cancelled");
        
        //set scheduler to empty value if cancelled in txt file,update label (HG Lim)
        updateSchedulerInFile(reportID, "");
        this.scheduler = "";
        
        JOptionPane.showMessageDialog(this, "Status updated to Cancelled", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
        //refresh page with new value (refer refresh function) (HG Lim)
        refresh(reportID, bookingID, desc, scheduler, reportDate, status);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed
    
    private void changeSchedulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSchedulerButtonActionPerformed
        // TODO add your handling code here:
    
        // load schedulers to the dropdown
        schedulerDropdown.removeAllItems();
        loadSchedulersFromFile(IOMethods.USERTEXT, schedulerDropdown);
        
        schedulerValue.setVisible(false);
        schedulerDropdown.setVisible(true);
        
        changeSchedulerButton.setVisible(false);
        changeStatusButton.setVisible(false);
        
        updateButton.setVisible(true);
        cancelSchedulerButton.setVisible(true);

        // saving scheduler name when selected
        schedulerDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedScheduler = (String) schedulerDropdown.getSelectedItem();
                schedulerValue.setText(selectedScheduler); // config scheduler value with new values
            }
        }
        

        
        );
    }//GEN-LAST:event_changeSchedulerButtonActionPerformed
  
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        
        //retrieve userID for scheduler
        String selectedScheduler = (String) schedulerDropdown.getSelectedItem();
        ArrayList<ArrayList<String>> userList = ResolutionCenterDetail.getUserList();
        String schedulerID = "";
        
        for (ArrayList<String> user : userList)
        {
            if (user.get(1).equals(selectedScheduler))
            {
                schedulerID = user.get(0);
                break;
            }
        }
        
        String reportID2 = reportIDValue.getText();

        schedulerValue.setText(selectedScheduler);
//        statusValue.setText("pending");

        schedulerDropdown.setVisible(false);
        schedulerValue.setVisible(true);

        updateButton.setVisible(false);
        cancelSchedulerButton.setVisible(false);

        changeSchedulerButton.setVisible(true);
        changeStatusButton.setVisible(true);

        updateSchedulerInFile(reportID2, schedulerID);
        this.scheduler = selectedScheduler;
        this.status = "pending";
        JOptionPane.showMessageDialog(null,"Updated" ,"Updated", JOptionPane.INFORMATION_MESSAGE);
        
        this.dispose();
        new ResolutionCenter().setVisible(true);
//        refresh(reportID, bookingID, desc, scheduler, reportDate, status);

        
    }//GEN-LAST:event_updateButtonActionPerformed
    
    public void refresh(String reportID, String bookingID, String desc, String scheduler, String reportDate, String status)
    {
        this.dispose();
        ResolutionCenterDetail refresh = new ResolutionCenterDetail();
        refresh.setValue(reportID, bookingID, desc, scheduler, reportDate, status);
        refresh.loadDataFromIssuesFile(IOMethods.REPORTTEXT);
        refresh.setVisible(true);
        
    }
    
    
    
    private void updateSchedulerInFile(String reportID, String newScheduler) {
        
        
        //read reports from report text file
        ArrayList<ArrayList<String>> reportList = IOMethods.readFile(IOMethods.REPORTTEXT);
        
        //loop through
        for (ArrayList<String> report: reportList )
        {
            //case cancelled, set scheduler to empty, change status in text file 
            if (report.get(0).equals(reportID) && newScheduler.equals(""))
            {
                report.set(3, "cancelled");
                report.set(4, "");
            }
            //case assign scheduler, change scheduler, change status to pending in text file 
            else if (report.get(0).equals(reportID) && newScheduler.length() > 1)
            {
                report.set(3, "pending");
                report.set(4, newScheduler);
            }
        }
        //rewrite text file
        IOMethods.writeArrayListOfArrayList(IOMethods.REPORTTEXT, reportList);
        
        
//        String filePath = IOMethods.REPORTTEXT;
//        StringBuilder updatedContent = new StringBuilder();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(";");
//                if (data.length == 6 && data[0].equals(reportID)) {
//                    // Update scheduler val
//                    data[3] = "pending";
//                    data[4] = newScheduler;
//                    line = String.join(";", data);
//                }
//                updatedContent.append(line).append(System.lineSeparator());
//                statusValue.setText("pending");
//            }
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this, "Error reading data from file: " + e.getMessage(),
//                                          "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        // rewriting file with updated contents
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
//            bw.write(updatedContent.toString());
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this, "Error writing data to file: " + e.getMessage(),
//                                          "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
    
    private void cancelSchedulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSchedulerButtonActionPerformed
        // TODO add your handling code here:
        updateButton.setVisible(false);
        cancelSchedulerButton.setVisible(false);

        schedulerValue.setVisible(true);
        schedulerDropdown.setVisible(false);

        changeSchedulerButton.setVisible(true);
        changeStatusButton.setVisible(true);
    }//GEN-LAST:event_cancelSchedulerButtonActionPerformed
    
    private void loadSchedulersFromFile(String filePath, JComboBox<String> schedulerDropdown) {
        ;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 6 && "S".equals(data[5])) {
                    // add username with the role 's" from the textfile
                    schedulerDropdown.addItem(data[1]); // taking the user ID
                    System.out.println(data[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading schedulers from file: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //use to validate (hide master admin account)
        public static ArrayList<ArrayList<String>> getUserList()
    {
        ArrayList<ArrayList<String>> userList = IOMethods.readFile(IOMethods.USERTEXT);
        ArrayList<ArrayList<String>> filteredList = new ArrayList<>(userList.subList(1, userList.size()));
         
        return filteredList;
        
    }
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
            java.util.logging.Logger.getLogger(ResolutionCenterDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResolutionCenterDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResolutionCenterDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResolutionCenterDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ResolutionCenterDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel bookingIDValue;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelSchedulerButton;
    private javax.swing.JButton changeSchedulerButton;
    private javax.swing.JButton changeStatusButton;
    private javax.swing.JButton closedButton;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel hallNameValue;
    private javax.swing.JLabel inProgressLabel;
    private javax.swing.JLabel inProgressValue;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelBookingID;
    private javax.swing.JLabel labelCustomerName;
    private javax.swing.JLabel labelHallName;
    private javax.swing.JLabel labelHallType;
    private javax.swing.JLabel labelScheduler;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel reportDateVal;
    private javax.swing.JLabel reportIDValue;
    private javax.swing.JButton resolutionCenterBtn;
    private javax.swing.JButton salesDashboardBtn;
    private javax.swing.JLabel salesLabel;
    private javax.swing.JComboBox<String> schedulerDropdown;
    private javax.swing.JLabel schedulerValue;
    private javax.swing.JLabel statusValue;
    private javax.swing.JLabel unAssignedValue;
    private javax.swing.JLabel unassignedLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
