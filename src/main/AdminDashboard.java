/**
 * Guinto, Shelsey Mae L.
 * Hernandez, Anthony Rowie A.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminDashboard extends javax.swing.JFrame {

        Connection conn = null;
        
//displays data table
    public void displayDataInJTable(JTable tableInventory) {
    try {
        // Establishing a connection to your database
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/products?zeroDateTimeBehavior=CONVERT_TO_NULL", 
                "root", 
              "1234");
        System.out.println("Database connection established successfully.");

        // SQL query to fetch data from product table
        String query = "SELECT * FROM product";

        // Create a statement object
        Statement stmt = conn.createStatement();

        // Execute a SELECT statement
        ResultSet rs = stmt.executeQuery(query);

        // Get the table model
        DefaultTableModel model = (DefaultTableModel) tableInventory.getModel();

        // Clear the table model
        model.setRowCount(0);

        // Set the column names in the table model
        String[] columnNames = {"Name", "Code", "Price"};
        model.setColumnIdentifiers(columnNames);

        // Add rows to the table model
        while (rs.next()) {
            Object[] row = new Object[4];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
            
            model.addRow(row);
        }

        // Close the database resources
        rs.close();
        stmt.close();
        conn.close();

    } catch (ClassNotFoundException e) {
        System.err.println("MySQL JDBC driver not found: " + e.getMessage());
    } catch (SQLException e) {
        System.err.println("Database error: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Unknown error: " + e.getMessage());
    }
    }
    
////edit profile
//    void EditProfile(){
//        COnnection conn = null;
//        
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = (COnnection) DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/products?zeroDateTimeBehavior=CONVERT_TO_NULL", 
//                    "root", 
//                  "1234");
//
//            // Get the current username from the JLabel
//            String currentUsername = enterExistingUsername.getText();
//
//            // Get the new username from the JTextField
//            String newUsername = newUserFieldEdit.getText();
//
//            // Get the old password from the JTextField
//            String oldPassword = enterOldPassField.getText();
//
//            // Get the new password from the JTextField
//            String newPassword = enterNewPassField.getText();
//
//            // Verify the old password
//            if (verifyOldPassword(oldPassword, currentUsername)) {
//                // Update the username and password in the database
//                updateProfile(currentUsername, newUsername, newPassword);
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid old password. Please try again.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }   catch (ClassNotFoundException ex) {
//                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//
////veriify old password
//    private boolean verifyOldPassword(String oldPassword, String currentUsername) {
//        Connection conn = null;
//        try {
//            // Create a prepared statement to query the database
//            PreparedStatement pstmt = conn.prepareStatement("SELECT password FROM users WHERE username =?");
//            pstmt.setString(1, currentUsername);
//
//            // Execute the query
//            ResultSet rs = pstmt.executeQuery();
//
//            // Check if the old password matches the one in the database
//            if (rs.next()) {
//                String dbPassword = rs.getString("password");
//                if (oldPassword.equals(dbPassword)) {
//                    return true;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
////update profile method
//    private void updateProfile(String currentUsername, String newUsername, String newPassword) {
//        Connection conn = null;
//        try {
//            // Create a prepared statement to update the database
//            PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET username =?, password =? WHERE username =?");
//            pstmt.setString(1, newUsername);
//            pstmt.setString(2, newPassword);
//            pstmt.setString(3, currentUsername);
//
//            // Execute the update
//            pstmt.executeUpdate();
//
//            // Update the JLabel with the new username
//            usernameTXT.setText(newUsername);
//
//            JOptionPane.showMessageDialog(null, "Profile updated successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
////edit password
//    void EditPassword(){
//        Connection conn = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = (Connection) DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/products?zeroDateTimeBehavior=CONVERT_TO_NULL", 
//                    "root", 
//                  "1234");
//
//            // Get the username from the JTextField
//            String username = enterExistingUsername.getText();
//
//            // Get the new password from the JTextField
//            String newPassword = enterNewPassField.getText();
//
//            // Get the confirm password from the JTextField
//            String confirmPassword = enterOldPassField.getText();
//
//            // Verify the username and old password
//            if (verifyUserAndPassword(username)) {
//                // Check if the new password and confirm password match
//                if (newPassword.equals(confirmPassword)) {
//                    // Update the password in the database
//                    updatePassword(username, newPassword);
//                } else {
//                    JOptionPane.showMessageDialog(null, "New password and confirm password do not match. Please try again.");
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid username. Please try again.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }   catch (ClassNotFoundException ex) {
//                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//
////verify user and password
//    private boolean verifyUserAndPassword(String username) {
//        Connection conn = null;
//        try {
//            // Create a prepared statement to query the database
//            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username =?");
//            pstmt.setString(1, username);
//
//            // Execute the query
//            ResultSet rs = pstmt.executeQuery();
//
//            // Check if the username exists
//            if (rs.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
////update password
//    private void updatePassword(String username, String newPassword) {
//    Connection conn = null;
//    try {
//        // Create a prepared statement to update the database
//        PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET password =? WHERE username =?");
//        pstmt.setString(1, newPassword);
//        pstmt.setString(2, username);
//
//        // Execute the update
//        pstmt.executeUpdate();
//
//        JOptionPane.showMessageDialog(null, "Password updated successfully!");
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
    
    public AdminDashboard() {
        initComponents();
         newUserFieldEdit.setBackground(new java.awt.Color(0, 0, 0, 1));
         enterPassEditUser.setBackground(new java.awt.Color(0, 0, 0, 1));
         enterOldPassField.setBackground(new java.awt.Color(0, 0, 0, 1));
         enterNewPassField.setBackground(new java.awt.Color(0, 0, 0, 1));

        cartPanel.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void installComponents() {

            }
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0;
            }
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        DBluepanel = new javax.swing.JPanel();
        cartPanel = new javax.swing.JTabbedPane();
        dashboardPanel = new javax.swing.JScrollPane();
        productImages = new javax.swing.JPanel();
        DHTML2 = new javax.swing.JLabel();
        dIMG = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Ddescription = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        KWI1 = new javax.swing.JPanel();
        KWI1img = new javax.swing.JLabel();
        KW2 = new javax.swing.JPanel();
        KW2img = new javax.swing.JLabel();
        MWI3 = new javax.swing.JPanel();
        MWI3img = new javax.swing.JLabel();
        MW4 = new javax.swing.JPanel();
        MW4img = new javax.swing.JLabel();
        W720n5 = new javax.swing.JPanel();
        W720n5img = new javax.swing.JLabel();
        W1080n6 = new javax.swing.JPanel();
        W1080n6img = new javax.swing.JLabel();
        HWI7 = new javax.swing.JPanel();
        HWI7img = new javax.swing.JLabel();
        HW8 = new javax.swing.JPanel();
        HW8img = new javax.swing.JLabel();
        SWI9 = new javax.swing.JPanel();
        SWI9img = new javax.swing.JLabel();
        SW10 = new javax.swing.JPanel();
        SW10img = new javax.swing.JLabel();
        MS19n11 = new javax.swing.JPanel();
        MS19n11img = new javax.swing.JLabel();
        MS22n12 = new javax.swing.JPanel();
        MS22n12img = new javax.swing.JLabel();
        MS24n13 = new javax.swing.JPanel();
        MS24n13img = new javax.swing.JLabel();
        EHD14 = new javax.swing.JPanel();
        EHD14img = new javax.swing.JLabel();
        MIWI15 = new javax.swing.JPanel();
        MIWI15img = new javax.swing.JLabel();
        MIW16 = new javax.swing.JPanel();
        MIW16img = new javax.swing.JLabel();
        CK17 = new javax.swing.JPanel();
        CK17img = new javax.swing.JLabel();
        P18 = new javax.swing.JPanel();
        P18img = new javax.swing.JLabel();
        ComingSoon = new javax.swing.JLabel();
        producttxt1 = new javax.swing.JLabel();
        producttxt2 = new javax.swing.JLabel();
        producttxt5 = new javax.swing.JLabel();
        producttxt4 = new javax.swing.JLabel();
        producttxt3 = new javax.swing.JLabel();
        producttxt6 = new javax.swing.JLabel();
        producttxt7 = new javax.swing.JLabel();
        producttxt8 = new javax.swing.JLabel();
        producttxt9 = new javax.swing.JLabel();
        producttxt10 = new javax.swing.JLabel();
        producttxt11 = new javax.swing.JLabel();
        producttxt12 = new javax.swing.JLabel();
        producttxt13 = new javax.swing.JLabel();
        producttxt14 = new javax.swing.JLabel();
        producttxt15 = new javax.swing.JLabel();
        producttxt16 = new javax.swing.JLabel();
        producttxt17 = new javax.swing.JLabel();
        priceText1 = new javax.swing.JLabel();
        priceText2 = new javax.swing.JLabel();
        priceText3 = new javax.swing.JLabel();
        priceText4 = new javax.swing.JLabel();
        priceText5 = new javax.swing.JLabel();
        priceText6 = new javax.swing.JLabel();
        priceText7 = new javax.swing.JLabel();
        priceText8 = new javax.swing.JLabel();
        priceText9 = new javax.swing.JLabel();
        priceText10 = new javax.swing.JLabel();
        priceText11 = new javax.swing.JLabel();
        priceText12 = new javax.swing.JLabel();
        priceText13 = new javax.swing.JLabel();
        priceText14 = new javax.swing.JLabel();
        priceText15 = new javax.swing.JLabel();
        priceText16 = new javax.swing.JLabel();
        priceText17 = new javax.swing.JLabel();
        addToCart1 = new javax.swing.JCheckBox();
        addToCart2 = new javax.swing.JCheckBox();
        addToCart3 = new javax.swing.JCheckBox();
        addToCart4 = new javax.swing.JCheckBox();
        addToCart5 = new javax.swing.JCheckBox();
        addToCart6 = new javax.swing.JCheckBox();
        addToCart7 = new javax.swing.JCheckBox();
        addToCart8 = new javax.swing.JCheckBox();
        addToCart9 = new javax.swing.JCheckBox();
        addToCart10 = new javax.swing.JCheckBox();
        addToCart11 = new javax.swing.JCheckBox();
        addToCart12 = new javax.swing.JCheckBox();
        addToCart13 = new javax.swing.JCheckBox();
        addToCart14 = new javax.swing.JCheckBox();
        addToCart15 = new javax.swing.JCheckBox();
        addToCart16 = new javax.swing.JCheckBox();
        addToCart17 = new javax.swing.JCheckBox();
        productSeperator1 = new javax.swing.JSeparator();
        productSeperator2 = new javax.swing.JSeparator();
        productSeperator4 = new javax.swing.JSeparator();
        productSeperator3 = new javax.swing.JSeparator();
        productSeperator5 = new javax.swing.JSeparator();
        productSeperator6 = new javax.swing.JSeparator();
        productSeperator7 = new javax.swing.JSeparator();
        productSeperator8 = new javax.swing.JSeparator();
        productSeperator9 = new javax.swing.JSeparator();
        PCcopyright = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ScrollInventory = new javax.swing.JScrollPane();
        inventoryManagementPanel = new javax.swing.JPanel();
        headerInventoryIMG = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableInventory = new javax.swing.JTable();
        shoppingCartPanel = new javax.swing.JPanel();
        HTMLCartTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        orderTotaltTXT = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        TotalOrder = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        purchasePanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        invoiceTXT = new javax.swing.JTextArea();
        htmltextInvoice = new javax.swing.JLabel();
        asterisk2 = new javax.swing.JLabel();
        asterisk1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        totalInvoicetxt = new javax.swing.JLabel();
        totalInvoicePHP = new javax.swing.JLabel();
        scrollOrderTracking = new javax.swing.JScrollPane();
        orderTrackingPanel = new javax.swing.JPanel();
        ScrollOrderTracking = new javax.swing.JScrollPane();
        oderTrackingAdminTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        confirmPurchasedButton = new javax.swing.JButton();
        accountSettingsPanel = new javax.swing.JPanel();
        profileIconIMG = new javax.swing.JLabel();
        editProfiletxt = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        edituserimg = new javax.swing.JLabel();
        editpassimg = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        ASusernametxt = new javax.swing.JLabel();
        enterExistingUsername = new javax.swing.JLabel();
        ASusernametxt1 = new javax.swing.JLabel();
        enterExistingUsernameforPass = new javax.swing.JLabel();
        ASusernametxt2 = new javax.swing.JLabel();
        newUserFieldEdit = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        ASusernametxt3 = new javax.swing.JLabel();
        enterPassEditUser = new javax.swing.JPasswordField();
        jSeparator14 = new javax.swing.JSeparator();
        ASusernametxt4 = new javax.swing.JLabel();
        enterOldPassField = new javax.swing.JPasswordField();
        jSeparator15 = new javax.swing.JSeparator();
        ASusernametxt5 = new javax.swing.JLabel();
        enterNewPassField = new javax.swing.JPasswordField();
        jSeparator16 = new javax.swing.JSeparator();
        showPassEditpassword = new javax.swing.JCheckBox();
        showPassEditUser = new javax.swing.JCheckBox();
        confirmUsername = new javax.swing.JButton();
        confirmPassword = new javax.swing.JButton();
        DHTML = new javax.swing.JLabel();
        DSeparator2 = new javax.swing.JSeparator();
        productCat = new javax.swing.JButton();
        SCart = new javax.swing.JButton();
        Purchased = new javax.swing.JButton();
        orderTrackingbutton = new javax.swing.JButton();
        JSettings = new javax.swing.JButton();
        DLogout = new javax.swing.JButton();
        usernameTXT = new javax.swing.JLabel();
        inventoryManagementbutton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hyper-Tech Market Lab (HTML)");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DBluepanel.setBackground(new java.awt.Color(12, 24, 68));

        cartPanel.setPreferredSize(new java.awt.Dimension(850, 1200));

        dashboardPanel.setBackground(new java.awt.Color(255, 255, 255));
        dashboardPanel.setBorder(null);
        dashboardPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        productImages.setBackground(new java.awt.Color(255, 255, 255));
        productImages.setAutoscrolls(true);
        productImages.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productImages.setPreferredSize(new java.awt.Dimension(1000, 5300));

        DHTML2.setFont(new java.awt.Font("K26ToyBlocks123", 0, 40)); // NOI18N
        DHTML2.setForeground(new java.awt.Color(200, 0, 54));
        DHTML2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DHTML2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header2.png"))); // NOI18N

        dIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/img.png"))); // NOI18N

        Ddescription.setEditable(false);
        Ddescription.setBackground(new java.awt.Color(12, 24, 68));
        Ddescription.setColumns(20);
        Ddescription.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        Ddescription.setForeground(new java.awt.Color(255, 245, 225));
        Ddescription.setLineWrap(true);
        Ddescription.setRows(5);
        Ddescription.setText("                                                   Discover the latest in tech at Hyper-Tech Market Lab! Find unbeatable deals on computer accessories, from high-performance keyboards and gaming mouse to cutting-edge monitors and headsets. Shop now for quality products and fast shipping. Elevate your tech experience with Hyper-Tech Market Lab – your go-to online tech store!");
        Ddescription.setWrapStyleWord(true);
        Ddescription.setBorder(null);
        Ddescription.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(Ddescription);

        jSeparator1.setForeground(new java.awt.Color(12, 24, 68));

        jSeparator2.setForeground(new java.awt.Color(12, 24, 68));

        KWI1.setBackground(new java.awt.Color(12, 24, 68));
        KWI1.setPreferredSize(new java.awt.Dimension(350, 300));

        KWI1img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/KWI1.png"))); // NOI18N

        javax.swing.GroupLayout KWI1Layout = new javax.swing.GroupLayout(KWI1);
        KWI1.setLayout(KWI1Layout);
        KWI1Layout.setHorizontalGroup(
            KWI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KWI1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(KWI1img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        KWI1Layout.setVerticalGroup(
            KWI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KWI1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(KWI1img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        KW2.setBackground(new java.awt.Color(12, 24, 68));
        KW2.setPreferredSize(new java.awt.Dimension(350, 300));

        KW2img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/KW2.png"))); // NOI18N

        javax.swing.GroupLayout KW2Layout = new javax.swing.GroupLayout(KW2);
        KW2.setLayout(KW2Layout);
        KW2Layout.setHorizontalGroup(
            KW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KW2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(KW2img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        KW2Layout.setVerticalGroup(
            KW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KW2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(KW2img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MWI3.setBackground(new java.awt.Color(12, 24, 68));

        MWI3img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MWI3.png"))); // NOI18N

        javax.swing.GroupLayout MWI3Layout = new javax.swing.GroupLayout(MWI3);
        MWI3.setLayout(MWI3Layout);
        MWI3Layout.setHorizontalGroup(
            MWI3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MWI3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MWI3img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MWI3Layout.setVerticalGroup(
            MWI3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MWI3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MWI3img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MW4.setBackground(new java.awt.Color(12, 24, 68));
        MW4.setPreferredSize(new java.awt.Dimension(350, 312));

        MW4img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MW4.png"))); // NOI18N

        javax.swing.GroupLayout MW4Layout = new javax.swing.GroupLayout(MW4);
        MW4.setLayout(MW4Layout);
        MW4Layout.setHorizontalGroup(
            MW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MW4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MW4img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MW4Layout.setVerticalGroup(
            MW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MW4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MW4img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        W720n5.setBackground(new java.awt.Color(12, 24, 68));

        W720n5img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/W720n5.png"))); // NOI18N

        javax.swing.GroupLayout W720n5Layout = new javax.swing.GroupLayout(W720n5);
        W720n5.setLayout(W720n5Layout);
        W720n5Layout.setHorizontalGroup(
            W720n5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, W720n5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(W720n5img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        W720n5Layout.setVerticalGroup(
            W720n5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, W720n5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(W720n5img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        W1080n6.setBackground(new java.awt.Color(12, 24, 68));
        W1080n6.setPreferredSize(new java.awt.Dimension(350, 312));

        W1080n6img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/W1080n6.png"))); // NOI18N

        javax.swing.GroupLayout W1080n6Layout = new javax.swing.GroupLayout(W1080n6);
        W1080n6.setLayout(W1080n6Layout);
        W1080n6Layout.setHorizontalGroup(
            W1080n6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, W1080n6Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(W1080n6img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        W1080n6Layout.setVerticalGroup(
            W1080n6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, W1080n6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(W1080n6img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        HWI7.setBackground(new java.awt.Color(12, 24, 68));

        HWI7img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HWI7.png"))); // NOI18N

        javax.swing.GroupLayout HWI7Layout = new javax.swing.GroupLayout(HWI7);
        HWI7.setLayout(HWI7Layout);
        HWI7Layout.setHorizontalGroup(
            HWI7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HWI7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(HWI7img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        HWI7Layout.setVerticalGroup(
            HWI7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HWI7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HWI7img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        HW8.setBackground(new java.awt.Color(12, 24, 68));
        HW8.setPreferredSize(new java.awt.Dimension(350, 312));

        HW8img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HW8.png"))); // NOI18N

        javax.swing.GroupLayout HW8Layout = new javax.swing.GroupLayout(HW8);
        HW8.setLayout(HW8Layout);
        HW8Layout.setHorizontalGroup(
            HW8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HW8Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(HW8img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        HW8Layout.setVerticalGroup(
            HW8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HW8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HW8img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        SWI9.setBackground(new java.awt.Color(12, 24, 68));

        SWI9img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SWI9.png"))); // NOI18N

        javax.swing.GroupLayout SWI9Layout = new javax.swing.GroupLayout(SWI9);
        SWI9.setLayout(SWI9Layout);
        SWI9Layout.setHorizontalGroup(
            SWI9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SWI9Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(SWI9img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        SWI9Layout.setVerticalGroup(
            SWI9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SWI9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SWI9img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        SW10.setBackground(new java.awt.Color(12, 24, 68));
        SW10.setPreferredSize(new java.awt.Dimension(350, 312));

        SW10img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SW10.png"))); // NOI18N

        javax.swing.GroupLayout SW10Layout = new javax.swing.GroupLayout(SW10);
        SW10.setLayout(SW10Layout);
        SW10Layout.setHorizontalGroup(
            SW10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SW10Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(SW10img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        SW10Layout.setVerticalGroup(
            SW10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SW10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SW10img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MS19n11.setBackground(new java.awt.Color(12, 24, 68));

        MS19n11img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MS19n11.png"))); // NOI18N

        javax.swing.GroupLayout MS19n11Layout = new javax.swing.GroupLayout(MS19n11);
        MS19n11.setLayout(MS19n11Layout);
        MS19n11Layout.setHorizontalGroup(
            MS19n11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MS19n11Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MS19n11img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MS19n11Layout.setVerticalGroup(
            MS19n11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MS19n11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MS19n11img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MS22n12.setBackground(new java.awt.Color(12, 24, 68));
        MS22n12.setPreferredSize(new java.awt.Dimension(350, 312));

        MS22n12img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MS22n12.png"))); // NOI18N

        javax.swing.GroupLayout MS22n12Layout = new javax.swing.GroupLayout(MS22n12);
        MS22n12.setLayout(MS22n12Layout);
        MS22n12Layout.setHorizontalGroup(
            MS22n12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MS22n12Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MS22n12img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MS22n12Layout.setVerticalGroup(
            MS22n12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MS22n12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MS22n12img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MS24n13.setBackground(new java.awt.Color(12, 24, 68));

        MS24n13img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MS24n13.png"))); // NOI18N

        javax.swing.GroupLayout MS24n13Layout = new javax.swing.GroupLayout(MS24n13);
        MS24n13.setLayout(MS24n13Layout);
        MS24n13Layout.setHorizontalGroup(
            MS24n13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MS24n13Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MS24n13img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MS24n13Layout.setVerticalGroup(
            MS24n13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MS24n13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MS24n13img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        EHD14.setBackground(new java.awt.Color(12, 24, 68));
        EHD14.setPreferredSize(new java.awt.Dimension(350, 312));

        EHD14img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EHD14.png"))); // NOI18N

        javax.swing.GroupLayout EHD14Layout = new javax.swing.GroupLayout(EHD14);
        EHD14.setLayout(EHD14Layout);
        EHD14Layout.setHorizontalGroup(
            EHD14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EHD14Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(EHD14img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        EHD14Layout.setVerticalGroup(
            EHD14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EHD14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EHD14img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MIWI15.setBackground(new java.awt.Color(12, 24, 68));

        MIWI15img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MIWI15.png"))); // NOI18N

        javax.swing.GroupLayout MIWI15Layout = new javax.swing.GroupLayout(MIWI15);
        MIWI15.setLayout(MIWI15Layout);
        MIWI15Layout.setHorizontalGroup(
            MIWI15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MIWI15Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MIWI15img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MIWI15Layout.setVerticalGroup(
            MIWI15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MIWI15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MIWI15img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        MIW16.setBackground(new java.awt.Color(12, 24, 68));
        MIW16.setPreferredSize(new java.awt.Dimension(350, 312));

        MIW16img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MIW16.png"))); // NOI18N

        javax.swing.GroupLayout MIW16Layout = new javax.swing.GroupLayout(MIW16);
        MIW16.setLayout(MIW16Layout);
        MIW16Layout.setHorizontalGroup(
            MIW16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MIW16Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(MIW16img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MIW16Layout.setVerticalGroup(
            MIW16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MIW16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MIW16img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        CK17.setBackground(new java.awt.Color(12, 24, 68));

        CK17img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CK17.png"))); // NOI18N

        javax.swing.GroupLayout CK17Layout = new javax.swing.GroupLayout(CK17);
        CK17.setLayout(CK17Layout);
        CK17Layout.setHorizontalGroup(
            CK17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CK17Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(CK17img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        CK17Layout.setVerticalGroup(
            CK17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CK17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CK17img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        P18.setBackground(new java.awt.Color(12, 24, 68));
        P18.setPreferredSize(new java.awt.Dimension(350, 312));

        P18img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/P18.png"))); // NOI18N

        javax.swing.GroupLayout P18Layout = new javax.swing.GroupLayout(P18);
        P18.setLayout(P18Layout);
        P18Layout.setHorizontalGroup(
            P18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P18Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(P18img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        P18Layout.setVerticalGroup(
            P18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(P18img, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        ComingSoon.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        ComingSoon.setForeground(new java.awt.Color(200, 0, 54));
        ComingSoon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ComingSoon.setText("Coming Soon. . .");

        producttxt1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt1.setForeground(new java.awt.Color(200, 0, 54));
        producttxt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt1.setText("Keyboard Wired");

        producttxt2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt2.setForeground(new java.awt.Color(200, 0, 54));
        producttxt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt2.setText("Keyboard Wireless");

        producttxt5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt5.setForeground(new java.awt.Color(200, 0, 54));
        producttxt5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt5.setText("Webcam 720p");

        producttxt4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt4.setForeground(new java.awt.Color(200, 0, 54));
        producttxt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt4.setText("Mouse Wireless");

        producttxt3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt3.setForeground(new java.awt.Color(200, 0, 54));
        producttxt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt3.setText("Mouse Wired");

        producttxt6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt6.setForeground(new java.awt.Color(200, 0, 54));
        producttxt6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt6.setText("Webcam 1080p");

        producttxt7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt7.setForeground(new java.awt.Color(200, 0, 54));
        producttxt7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt7.setText("Headphone wired");

        producttxt8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt8.setForeground(new java.awt.Color(200, 0, 54));
        producttxt8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt8.setText("Headphone wireless");

        producttxt9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt9.setForeground(new java.awt.Color(200, 0, 54));
        producttxt9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt9.setText("Speaker wired");

        producttxt10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt10.setForeground(new java.awt.Color(200, 0, 54));
        producttxt10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt10.setText("Speaker wireless");

        producttxt11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt11.setForeground(new java.awt.Color(200, 0, 54));
        producttxt11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt11.setText("Monitor 19\"");

        producttxt12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt12.setForeground(new java.awt.Color(200, 0, 54));
        producttxt12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt12.setText("Monitor 22\"");

        producttxt13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt13.setForeground(new java.awt.Color(200, 0, 54));
        producttxt13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt13.setText("Monitor 24\"");

        producttxt14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt14.setForeground(new java.awt.Color(200, 0, 54));
        producttxt14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt14.setText("External Hard Drive (1tb)");

        producttxt15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt15.setForeground(new java.awt.Color(200, 0, 54));
        producttxt15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt15.setText("Microphone wired");

        producttxt16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt16.setForeground(new java.awt.Color(200, 0, 54));
        producttxt16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt16.setText("Microphone wireless");

        producttxt17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        producttxt17.setForeground(new java.awt.Color(200, 0, 54));
        producttxt17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producttxt17.setText("Cleaning Kit");

        priceText1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText1.setForeground(new java.awt.Color(200, 0, 54));
        priceText1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText1.setText("PHP 299");

        priceText2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText2.setForeground(new java.awt.Color(200, 0, 54));
        priceText2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText2.setText("PHP 499");

        priceText3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText3.setForeground(new java.awt.Color(200, 0, 54));
        priceText3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText3.setText("PHP 180");

        priceText4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText4.setForeground(new java.awt.Color(200, 0, 54));
        priceText4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText4.setText("PHP 320");

        priceText5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText5.setForeground(new java.awt.Color(200, 0, 54));
        priceText5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText5.setText("PHP 699");

        priceText6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText6.setForeground(new java.awt.Color(200, 0, 54));
        priceText6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText6.setText("PHP 999");

        priceText7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText7.setForeground(new java.awt.Color(200, 0, 54));
        priceText7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText7.setText("PHP 450");

        priceText8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText8.setForeground(new java.awt.Color(200, 0, 54));
        priceText8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText8.setText("PHP 800");

        priceText9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText9.setForeground(new java.awt.Color(200, 0, 54));
        priceText9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText9.setText("PHP 349");

        priceText10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText10.setForeground(new java.awt.Color(200, 0, 54));
        priceText10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText10.setText("PHP 619");

        priceText11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText11.setForeground(new java.awt.Color(200, 0, 54));
        priceText11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText11.setText("PHP 3499");

        priceText12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText12.setForeground(new java.awt.Color(200, 0, 54));
        priceText12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText12.setText("PHP 4799");

        priceText13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText13.setForeground(new java.awt.Color(200, 0, 54));
        priceText13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText13.setText("PHP 5999");

        priceText14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText14.setForeground(new java.awt.Color(200, 0, 54));
        priceText14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText14.setText("PHP 1999");

        priceText15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText15.setForeground(new java.awt.Color(200, 0, 54));
        priceText15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText15.setText("PHP 189");

        priceText16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText16.setForeground(new java.awt.Color(200, 0, 54));
        priceText16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText16.setText("PHP 319");

        priceText17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceText17.setForeground(new java.awt.Color(200, 0, 54));
        priceText17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceText17.setText("PHP 99");

        addToCart1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart1.setForeground(new java.awt.Color(12, 24, 68));
        addToCart1.setText("Add to cart");
        addToCart1.setToolTipText("");
        addToCart1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart2.setForeground(new java.awt.Color(12, 24, 68));
        addToCart2.setText("Add to cart");
        addToCart2.setToolTipText("");
        addToCart2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart3.setForeground(new java.awt.Color(12, 24, 68));
        addToCart3.setText("Add to cart");
        addToCart3.setToolTipText("");
        addToCart3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart4.setForeground(new java.awt.Color(12, 24, 68));
        addToCart4.setText("Add to cart");
        addToCart4.setToolTipText("");
        addToCart4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart5.setForeground(new java.awt.Color(12, 24, 68));
        addToCart5.setText("Add to cart");
        addToCart5.setToolTipText("");
        addToCart5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart6.setForeground(new java.awt.Color(12, 24, 68));
        addToCart6.setText("Add to cart");
        addToCart6.setToolTipText("");
        addToCart6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart7.setForeground(new java.awt.Color(12, 24, 68));
        addToCart7.setText("Add to cart");
        addToCart7.setToolTipText("");
        addToCart7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart8.setForeground(new java.awt.Color(12, 24, 68));
        addToCart8.setText("Add to cart");
        addToCart8.setToolTipText("");
        addToCart8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart9.setForeground(new java.awt.Color(12, 24, 68));
        addToCart9.setText("Add to cart");
        addToCart9.setToolTipText("");
        addToCart9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart10.setForeground(new java.awt.Color(12, 24, 68));
        addToCart10.setText("Add to cart");
        addToCart10.setToolTipText("");
        addToCart10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart11.setForeground(new java.awt.Color(12, 24, 68));
        addToCart11.setText("Add to cart");
        addToCart11.setToolTipText("");
        addToCart11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart12.setForeground(new java.awt.Color(12, 24, 68));
        addToCart12.setText("Add to cart");
        addToCart12.setToolTipText("");
        addToCart12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart13.setForeground(new java.awt.Color(12, 24, 68));
        addToCart13.setText("Add to cart");
        addToCart13.setToolTipText("");
        addToCart13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart14.setForeground(new java.awt.Color(12, 24, 68));
        addToCart14.setText("Add to cart");
        addToCart14.setToolTipText("");
        addToCart14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart15.setForeground(new java.awt.Color(12, 24, 68));
        addToCart15.setText("Add to cart");
        addToCart15.setToolTipText("");
        addToCart15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart16.setForeground(new java.awt.Color(12, 24, 68));
        addToCart16.setText("Add to cart");
        addToCart16.setToolTipText("");
        addToCart16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        addToCart17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCart17.setForeground(new java.awt.Color(12, 24, 68));
        addToCart17.setText("Add to cart");
        addToCart17.setToolTipText("");
        addToCart17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PCcopyright.setForeground(new java.awt.Color(200, 0, 54));
        PCcopyright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PCcopyright.setText("© June 2024");

        jLabel3.setFont(new java.awt.Font("KG Defying Gravity", 0, 60)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(200, 0, 54));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Products");

        jLabel4.setFont(new java.awt.Font("KG Defying Gravity", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(200, 0, 54));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hyper-Tech Market Lab");

        javax.swing.GroupLayout productImagesLayout = new javax.swing.GroupLayout(productImages);
        productImages.setLayout(productImagesLayout);
        productImagesLayout.setHorizontalGroup(
            productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productImagesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productImagesLayout.createSequentialGroup()
                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(productImagesLayout.createSequentialGroup()
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(DHTML2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(productImagesLayout.createSequentialGroup()
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(producttxt1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(KWI1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(producttxt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addGap(31, 31, 31)
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(priceText2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(KW2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addToCart2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(productImagesLayout.createSequentialGroup()
                                            .addComponent(producttxt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(producttxt4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(12, 12, 12))
                                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(productImagesLayout.createSequentialGroup()
                                                .addComponent(producttxt15, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(producttxt16, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(productImagesLayout.createSequentialGroup()
                                                .addComponent(MIWI15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(MIW16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(productImagesLayout.createSequentialGroup()
                                                .addComponent(priceText3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(priceText4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(productImagesLayout.createSequentialGroup()
                                                .addComponent(MWI3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(MW4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(addToCart4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(productImagesLayout.createSequentialGroup()
                                                .addComponent(priceText5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(priceText6, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addComponent(W720n5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(33, 33, 33)
                                                    .addComponent(W1080n6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addGap(2, 2, 2)
                                                    .addComponent(producttxt5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(producttxt6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(producttxt7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(HWI7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(HW8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(producttxt8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(producttxt9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(SWI9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(SW10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(producttxt10, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(priceText8, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(priceText10, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(addToCart6, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(addToCart8, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(addToCart10, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(productImagesLayout.createSequentialGroup()
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(CK17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(addToCart17, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(31, 31, 31)
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(ComingSoon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(P18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(addToCart14, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addComponent(MS19n11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(33, 33, 33)
                                                    .addComponent(MS22n12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addComponent(producttxt11, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(producttxt12, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(productImagesLayout.createSequentialGroup()
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(producttxt13, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(MS24n13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(EHD14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(producttxt14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(priceText14, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(productSeperator6, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(productImagesLayout.createSequentialGroup()
                                        .addComponent(productSeperator2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)))
                                .addGroup(productImagesLayout.createSequentialGroup()
                                    .addComponent(productSeperator5, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(50, 50, 50)))
                            .addGroup(productImagesLayout.createSequentialGroup()
                                .addComponent(productSeperator7, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)))
                        .addContainerGap(195, Short.MAX_VALUE))
                    .addGroup(productImagesLayout.createSequentialGroup()
                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addToCart16, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(producttxt17, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(priceText1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(productImagesLayout.createSequentialGroup()
                                    .addGap(383, 383, 383)
                                    .addComponent(priceText16, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(productImagesLayout.createSequentialGroup()
                        .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PCcopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(productImagesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addToCart15, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToCart13, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToCart9, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToCart7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToCart5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToCart3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceText17, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceText15, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceText13, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToCart1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(productImagesLayout.createSequentialGroup()
                                            .addComponent(addToCart11, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(addToCart12, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productImagesLayout.createSequentialGroup()
                                            .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(priceText11, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(priceText9, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(priceText7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(31, 31, 31)
                                            .addComponent(priceText12, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(productSeperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productSeperator4, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productSeperator3, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productSeperator8, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(productSeperator9, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        productImagesLayout.setVerticalGroup(
            productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productImagesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(DHTML2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productImagesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productImagesLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt1)
                    .addComponent(producttxt2))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(KWI1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(KW2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText1)
                    .addComponent(priceText2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart1)
                    .addComponent(addToCart2))
                .addGap(18, 18, 18)
                .addComponent(productSeperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt4)
                    .addComponent(producttxt3))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MWI3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MW4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText3)
                    .addComponent(priceText4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart3)
                    .addComponent(addToCart4))
                .addGap(18, 18, 18)
                .addComponent(productSeperator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt5)
                    .addComponent(producttxt6))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(W720n5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(W1080n6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText5)
                    .addComponent(priceText6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart5)
                    .addComponent(addToCart6))
                .addGap(18, 18, 18)
                .addComponent(productSeperator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(producttxt7)
                    .addComponent(producttxt8))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HWI7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HW8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText7)
                    .addComponent(priceText8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart7)
                    .addComponent(addToCart8))
                .addGap(18, 18, 18)
                .addComponent(productSeperator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt9)
                    .addComponent(producttxt10))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SWI9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SW10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText9)
                    .addComponent(priceText10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart9)
                    .addComponent(addToCart10))
                .addGap(18, 18, 18)
                .addComponent(productSeperator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt11)
                    .addComponent(producttxt12))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MS19n11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MS22n12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText11)
                    .addComponent(priceText12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart11)
                    .addComponent(addToCart12))
                .addGap(18, 18, 18)
                .addComponent(productSeperator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt13)
                    .addComponent(producttxt14))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MS24n13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EHD14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText13)
                    .addComponent(priceText14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart13)
                    .addComponent(addToCart14))
                .addGap(18, 18, 18)
                .addComponent(productSeperator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producttxt15)
                    .addComponent(producttxt16))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MIWI15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MIW16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText15)
                    .addComponent(priceText16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCart15)
                    .addComponent(addToCart16))
                .addGap(18, 18, 18)
                .addComponent(productSeperator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(producttxt17)
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CK17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceText17)
                    .addGroup(productImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComingSoon)
                        .addComponent(addToCart17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(productSeperator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PCcopyright)
                .addGap(114, 114, 114))
        );

        dashboardPanel.setViewportView(productImages);

        cartPanel.addTab("Dashboard", dashboardPanel);

        ScrollInventory.setBorder(null);
        ScrollInventory.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        inventoryManagementPanel.setBackground(new java.awt.Color(255, 255, 255));
        inventoryManagementPanel.setPreferredSize(new java.awt.Dimension(800, 1000));

        headerInventoryIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/headerInventoryManagement.png"))); // NOI18N

        tableInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Code", "Price"
            }
        ));
        jScrollPane7.setViewportView(tableInventory);

        javax.swing.GroupLayout inventoryManagementPanelLayout = new javax.swing.GroupLayout(inventoryManagementPanel);
        inventoryManagementPanel.setLayout(inventoryManagementPanelLayout);
        inventoryManagementPanelLayout.setHorizontalGroup(
            inventoryManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryManagementPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(inventoryManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(headerInventoryIMG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        inventoryManagementPanelLayout.setVerticalGroup(
            inventoryManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryManagementPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(headerInventoryIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
        );

        ScrollInventory.setViewportView(inventoryManagementPanel);

        cartPanel.addTab("Inventory Management", ScrollInventory);

        shoppingCartPanel.setBackground(new java.awt.Color(255, 255, 255));

        HTMLCartTxt.setFont(new java.awt.Font("KG Defying Gravity", 1, 36)); // NOI18N
        HTMLCartTxt.setForeground(new java.awt.Color(200, 0, 54));
        HTMLCartTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HTMLCartTxt.setText("My Shopping Cart");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Shopping Cart.png"))); // NOI18N

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Code", "Price", "Quantity", "Remove", "Check Out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        cartTable.setGridColor(new java.awt.Color(255, 255, 255));
        cartTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        cartTable.setShowGrid(false);
        cartTable.setShowHorizontalLines(true);
        cartTable.setShowVerticalLines(true);
        jScrollPane3.setViewportView(cartTable);

        orderTotaltTXT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        orderTotaltTXT.setForeground(new java.awt.Color(200, 0, 54));
        orderTotaltTXT.setText("Order Total:");

        jButton1.setBackground(new java.awt.Color(12, 24, 68));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 245, 225));
        jButton1.setText("Place Order");

        TotalOrder.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TotalOrder.setForeground(new java.awt.Color(200, 0, 54));
        TotalOrder.setText("PHP ");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout shoppingCartPanelLayout = new javax.swing.GroupLayout(shoppingCartPanel);
        shoppingCartPanel.setLayout(shoppingCartPanelLayout);
        shoppingCartPanelLayout.setHorizontalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                                .addComponent(orderTotaltTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)))
                    .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                            .addComponent(HTMLCartTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        shoppingCartPanelLayout.setVerticalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HTMLCartTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderTotaltTXT)
                            .addComponent(TotalOrder))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        cartPanel.addTab("Shopping Cart", shoppingCartPanel);

        purchasePanel.setBackground(new java.awt.Color(255, 255, 255));
        purchasePanel.setForeground(new java.awt.Color(200, 0, 54));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setToolTipText("");
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        invoiceTXT.setEditable(false);
        invoiceTXT.setBackground(new java.awt.Color(12, 24, 68));
        invoiceTXT.setColumns(20);
        invoiceTXT.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        invoiceTXT.setForeground(new java.awt.Color(253, 255, 226));
        invoiceTXT.setRows(5);
        invoiceTXT.setText("                            INVOICE");
        jScrollPane4.setViewportView(invoiceTXT);

        htmltextInvoice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        htmltextInvoice.setForeground(new java.awt.Color(200, 0, 54));
        htmltextInvoice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        htmltextInvoice.setText("Hyper-Tech Market Lab (HTML) - Computer Accessories GUI System");

        asterisk2.setForeground(new java.awt.Color(102, 102, 102));
        asterisk2.setText("****************************************************************************************************************************************************");

        asterisk1.setForeground(new java.awt.Color(102, 102, 102));
        asterisk1.setText("****************************************************************************************************************************************************");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("**Terms & Conditions**\nBy using Hyper-Tech Market Lab's online ordering service, you agree to comply with our policies. Your personal data is securely collected, used, and stored in accordance with our privacy policy. Unauthorized use of our service is prohibited. For more details, please review our full terms on our website.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jScrollPane5.setViewportView(jTextArea1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT", "CODE", "QTY", "TOTAL"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        totalInvoicetxt.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        totalInvoicetxt.setForeground(new java.awt.Color(200, 0, 54));
        totalInvoicetxt.setText("TOTAL:");

        totalInvoicePHP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        totalInvoicePHP.setForeground(new java.awt.Color(200, 0, 54));
        totalInvoicePHP.setText("PHP");

        javax.swing.GroupLayout purchasePanelLayout = new javax.swing.GroupLayout(purchasePanel);
        purchasePanel.setLayout(purchasePanelLayout);
        purchasePanelLayout.setHorizontalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purchasePanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(asterisk1))
                            .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(asterisk2)
                                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(htmltextInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)))))
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(purchasePanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(totalInvoicetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalInvoicePHP, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        purchasePanelLayout.setVerticalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(asterisk1)
                        .addGap(2, 2, 2)
                        .addComponent(htmltextInvoice)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purchasePanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalInvoicetxt)
                                    .addComponent(totalInvoicePHP))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE))
                            .addGroup(purchasePanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addComponent(asterisk2)
                        .addGap(31, 31, 31))
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        cartPanel.addTab("Purchase", purchasePanel);

        scrollOrderTracking.setBorder(null);
        scrollOrderTracking.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        orderTrackingPanel.setBackground(new java.awt.Color(255, 255, 255));
        orderTrackingPanel.setPreferredSize(new java.awt.Dimension(800, 1000));

        oderTrackingAdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Users", "Status", "Product(s)", "Total"
            }
        ));
        ScrollOrderTracking.setViewportView(oderTrackingAdminTable);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header4.png"))); // NOI18N

        confirmPurchasedButton.setBackground(new java.awt.Color(200, 0, 54));
        confirmPurchasedButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        confirmPurchasedButton.setForeground(new java.awt.Color(255, 245, 225));
        confirmPurchasedButton.setText("Confirm Purchased");
        confirmPurchasedButton.setToolTipText("");

        javax.swing.GroupLayout orderTrackingPanelLayout = new javax.swing.GroupLayout(orderTrackingPanel);
        orderTrackingPanel.setLayout(orderTrackingPanelLayout);
        orderTrackingPanelLayout.setHorizontalGroup(
            orderTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderTrackingPanelLayout.createSequentialGroup()
                .addGroup(orderTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderTrackingPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(orderTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ScrollOrderTracking)))
                    .addGroup(orderTrackingPanelLayout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(confirmPurchasedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        orderTrackingPanelLayout.setVerticalGroup(
            orderTrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderTrackingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ScrollOrderTracking, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmPurchasedButton)
                .addGap(470, 470, 470))
        );

        scrollOrderTracking.setViewportView(orderTrackingPanel);

        cartPanel.addTab("Order Tracking", scrollOrderTracking);

        accountSettingsPanel.setBackground(new java.awt.Color(255, 255, 255));

        profileIconIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profileIcon.png"))); // NOI18N

        editProfiletxt.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        editProfiletxt.setForeground(new java.awt.Color(12, 24, 68));
        editProfiletxt.setText("Edit Profile");

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        edituserimg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edituserimg.setForeground(new java.awt.Color(200, 0, 54));
        edituserimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edituserimg.setText("Edit Username");

        editpassimg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editpassimg.setForeground(new java.awt.Color(200, 0, 54));
        editpassimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editpassimg.setText("Edit Password");

        ASusernametxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ASusernametxt.setForeground(new java.awt.Color(200, 0, 54));
        ASusernametxt.setText("Username");

        enterExistingUsername.setForeground(new java.awt.Color(12, 24, 68));
        enterExistingUsername.setText("@admin");

        ASusernametxt1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ASusernametxt1.setForeground(new java.awt.Color(200, 0, 54));
        ASusernametxt1.setText("Username");

        enterExistingUsernameforPass.setForeground(new java.awt.Color(12, 24, 68));
        enterExistingUsernameforPass.setText("@admin");

        ASusernametxt2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ASusernametxt2.setForeground(new java.awt.Color(200, 0, 54));
        ASusernametxt2.setText("Enter new username");

        newUserFieldEdit.setForeground(new java.awt.Color(12, 24, 68));
        newUserFieldEdit.setBorder(null);
        newUserFieldEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserFieldEditActionPerformed(evt);
            }
        });

        ASusernametxt3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ASusernametxt3.setForeground(new java.awt.Color(200, 0, 54));
        ASusernametxt3.setText("Password");

        enterPassEditUser.setBorder(null);

        ASusernametxt4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ASusernametxt4.setForeground(new java.awt.Color(200, 0, 54));
        ASusernametxt4.setText("Enter Old Password");

        enterOldPassField.setForeground(new java.awt.Color(12, 24, 68));
        enterOldPassField.setBorder(null);

        ASusernametxt5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ASusernametxt5.setForeground(new java.awt.Color(200, 0, 54));
        ASusernametxt5.setText("Enter New Password");

        enterNewPassField.setForeground(new java.awt.Color(12, 24, 68));
        enterNewPassField.setBorder(null);

        showPassEditpassword.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        showPassEditpassword.setForeground(new java.awt.Color(255, 105, 105));
        showPassEditpassword.setText("Show Password?");
        showPassEditpassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showPassEditpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassEditpasswordActionPerformed(evt);
            }
        });

        showPassEditUser.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        showPassEditUser.setForeground(new java.awt.Color(255, 105, 105));
        showPassEditUser.setText("Show Password?");
        showPassEditUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showPassEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassEditUserActionPerformed(evt);
            }
        });

        confirmUsername.setBackground(new java.awt.Color(200, 0, 54));
        confirmUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        confirmUsername.setForeground(new java.awt.Color(255, 245, 225));
        confirmUsername.setText("Confirm");

        confirmPassword.setBackground(new java.awt.Color(200, 0, 54));
        confirmPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        confirmPassword.setForeground(new java.awt.Color(255, 245, 225));
        confirmPassword.setText("Confirm");

        javax.swing.GroupLayout accountSettingsPanelLayout = new javax.swing.GroupLayout(accountSettingsPanel);
        accountSettingsPanel.setLayout(accountSettingsPanelLayout);
        accountSettingsPanelLayout.setHorizontalGroup(
            accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(profileIconIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(editProfiletxt, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                        .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountSettingsPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(showPassEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ASusernametxt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(enterExistingUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                        .addComponent(ASusernametxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                        .addComponent(ASusernametxt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                        .addComponent(newUserFieldEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(enterPassEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                        .addComponent(jSeparator14)))
                                .addGap(31, 31, 31))
                            .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(edituserimg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(confirmUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)))
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(editpassimg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(showPassEditpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, accountSettingsPanelLayout.createSequentialGroup()
                                            .addGap(34, 34, 34)
                                            .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jSeparator12)
                                                .addComponent(enterExistingUsernameforPass, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                                .addComponent(ASusernametxt1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                                .addComponent(ASusernametxt4, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                                .addComponent(enterOldPassField)
                                                .addComponent(jSeparator15)
                                                .addComponent(ASusernametxt5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                                .addComponent(enterNewPassField)
                                                .addComponent(jSeparator16))))))
                            .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        accountSettingsPanelLayout.setVerticalGroup(
            accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(profileIconIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(edituserimg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ASusernametxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enterExistingUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ASusernametxt2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newUserFieldEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ASusernametxt3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enterPassEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPassEditUser)
                        .addGap(18, 18, 18)
                        .addComponent(confirmUsername)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(editProfiletxt)
                        .addGap(77, 77, 77)
                        .addGroup(accountSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(accountSettingsPanelLayout.createSequentialGroup()
                                .addComponent(editpassimg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ASusernametxt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterExistingUsernameforPass)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ASusernametxt4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterOldPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ASusernametxt5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterNewPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showPassEditpassword)
                                .addGap(18, 18, 18)
                                .addComponent(confirmPassword)
                                .addGap(0, 74, Short.MAX_VALUE))
                            .addComponent(jSeparator9))))
                .addContainerGap())
        );

        cartPanel.addTab("Account Settings", accountSettingsPanel);

        DHTML.setFont(new java.awt.Font("K26ToyBlocks123", 0, 36)); // NOI18N
        DHTML.setForeground(new java.awt.Color(255, 245, 225));
        DHTML.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DHTML.setText("HTML");

        productCat.setText("Product Catalog");
        productCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCatActionPerformed(evt);
            }
        });

        SCart.setText("Shopping Cart");
        SCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCartActionPerformed(evt);
            }
        });

        Purchased.setText("Purchase");
        Purchased.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchasedActionPerformed(evt);
            }
        });

        orderTrackingbutton.setText("Order Tracking");
        orderTrackingbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderTrackingbuttonActionPerformed(evt);
            }
        });

        JSettings.setText("Account Settings");
        JSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JSettingsActionPerformed(evt);
            }
        });

        DLogout.setBackground(new java.awt.Color(102, 102, 102));
        DLogout.setForeground(new java.awt.Color(255, 255, 255));
        DLogout.setText("Log Out");
        DLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DLogoutActionPerformed(evt);
            }
        });

        usernameTXT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameTXT.setForeground(new java.awt.Color(255, 245, 225));
        usernameTXT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameTXT.setText("@admin");

        inventoryManagementbutton.setText("Inventory Management");
        inventoryManagementbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryManagementbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DBluepanelLayout = new javax.swing.GroupLayout(DBluepanel);
        DBluepanel.setLayout(DBluepanelLayout);
        DBluepanelLayout.setHorizontalGroup(
            DBluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DBluepanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(DBluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DHTML, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DSeparator2)
                    .addComponent(productCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Purchased, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderTrackingbutton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameTXT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventoryManagementbutton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DBluepanelLayout.setVerticalGroup(
            DBluepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBluepanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(DHTML, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usernameTXT)
                .addGap(39, 39, 39)
                .addComponent(productCat)
                .addGap(18, 18, 18)
                .addComponent(inventoryManagementbutton)
                .addGap(18, 18, 18)
                .addComponent(SCart)
                .addGap(18, 18, 18)
                .addComponent(Purchased)
                .addGap(18, 18, 18)
                .addComponent(orderTrackingbutton)
                .addGap(18, 18, 18)
                .addComponent(JSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(DLogout)
                .addGap(28, 28, 28))
            .addComponent(cartPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(DBluepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DLogoutActionPerformed

      Object[] options = {"Log Out", "Cancel"};
    
    int response = JOptionPane.showOptionDialog(
        null,
        "Are you sure you want to log out?",
        "Confirm Log Out",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[1]
    );

    if (response == JOptionPane.YES_OPTION) {
        new Login().setVisible(true);
        this.dispose();

    }
      
        
    }//GEN-LAST:event_DLogoutActionPerformed

    private void productCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCatActionPerformed
        // TODO add your handling code here:
        cartPanel.setSelectedIndex(0);
        
    }//GEN-LAST:event_productCatActionPerformed

    private void SCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCartActionPerformed
        // TODO add your handling code here:
        cartPanel.setSelectedIndex(2);
    }//GEN-LAST:event_SCartActionPerformed

    private void PurchasedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchasedActionPerformed
        // TODO add your handling code here:
        cartPanel.setSelectedIndex(3);
    }//GEN-LAST:event_PurchasedActionPerformed

    private void orderTrackingbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderTrackingbuttonActionPerformed
        // TODO add your handling code here:
        cartPanel.setSelectedIndex(4);
    }//GEN-LAST:event_orderTrackingbuttonActionPerformed

    private void JSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JSettingsActionPerformed

        cartPanel.setSelectedIndex(5);
    }//GEN-LAST:event_JSettingsActionPerformed

    private void newUserFieldEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserFieldEditActionPerformed

    }//GEN-LAST:event_newUserFieldEditActionPerformed

    private void showPassEditpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassEditpasswordActionPerformed

    if (showPassEditpassword.isSelected()) {

        enterNewPassField.setEchoChar((char) 0);
    } else {

        enterNewPassField.setEchoChar('*');
    }
    }//GEN-LAST:event_showPassEditpasswordActionPerformed

    private void showPassEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassEditUserActionPerformed

    if (showPassEditUser.isSelected()) {

        enterPassEditUser.setEchoChar((char) 0);
    } else {

        enterPassEditUser.setEchoChar('*');
    }        
    }//GEN-LAST:event_showPassEditUserActionPerformed

    private void inventoryManagementbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryManagementbuttonActionPerformed
        displayDataInJTable(tableInventory);
        cartPanel.setSelectedIndex(1);
    }//GEN-LAST:event_inventoryManagementbuttonActionPerformed
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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel ASusernametxt;
    public javax.swing.JLabel ASusernametxt1;
    public javax.swing.JLabel ASusernametxt2;
    public javax.swing.JLabel ASusernametxt3;
    public javax.swing.JLabel ASusernametxt4;
    public javax.swing.JLabel ASusernametxt5;
    public javax.swing.JPanel CK17;
    public javax.swing.JLabel CK17img;
    public javax.swing.JLabel ComingSoon;
    public javax.swing.JPanel DBluepanel;
    public javax.swing.JLabel DHTML;
    public javax.swing.JLabel DHTML2;
    public javax.swing.JButton DLogout;
    public javax.swing.JSeparator DSeparator2;
    public javax.swing.JTextArea Ddescription;
    public javax.swing.JPanel EHD14;
    public javax.swing.JLabel EHD14img;
    public javax.swing.JLabel HTMLCartTxt;
    public javax.swing.JPanel HW8;
    public javax.swing.JLabel HW8img;
    public javax.swing.JPanel HWI7;
    public javax.swing.JLabel HWI7img;
    public javax.swing.JButton JSettings;
    public javax.swing.JPanel KW2;
    public javax.swing.JLabel KW2img;
    public javax.swing.JPanel KWI1;
    public javax.swing.JLabel KWI1img;
    public javax.swing.JPanel MIW16;
    public javax.swing.JLabel MIW16img;
    public javax.swing.JPanel MIWI15;
    public javax.swing.JLabel MIWI15img;
    public javax.swing.JPanel MS19n11;
    public javax.swing.JLabel MS19n11img;
    public javax.swing.JPanel MS22n12;
    public javax.swing.JLabel MS22n12img;
    public javax.swing.JPanel MS24n13;
    public javax.swing.JLabel MS24n13img;
    public javax.swing.JPanel MW4;
    public javax.swing.JLabel MW4img;
    public javax.swing.JPanel MWI3;
    public javax.swing.JLabel MWI3img;
    public javax.swing.JPanel P18;
    public javax.swing.JLabel P18img;
    public javax.swing.JLabel PCcopyright;
    public javax.swing.JButton Purchased;
    public javax.swing.JButton SCart;
    public javax.swing.JPanel SW10;
    public javax.swing.JLabel SW10img;
    public javax.swing.JPanel SWI9;
    public javax.swing.JLabel SWI9img;
    public javax.swing.JScrollPane ScrollInventory;
    public javax.swing.JScrollPane ScrollOrderTracking;
    public javax.swing.JLabel TotalOrder;
    public javax.swing.JPanel W1080n6;
    public javax.swing.JLabel W1080n6img;
    public javax.swing.JPanel W720n5;
    public javax.swing.JLabel W720n5img;
    public javax.swing.JPanel accountSettingsPanel;
    public javax.swing.JCheckBox addToCart1;
    public javax.swing.JCheckBox addToCart10;
    public javax.swing.JCheckBox addToCart11;
    public javax.swing.JCheckBox addToCart12;
    public javax.swing.JCheckBox addToCart13;
    public javax.swing.JCheckBox addToCart14;
    public javax.swing.JCheckBox addToCart15;
    public javax.swing.JCheckBox addToCart16;
    public javax.swing.JCheckBox addToCart17;
    public javax.swing.JCheckBox addToCart2;
    public javax.swing.JCheckBox addToCart3;
    public javax.swing.JCheckBox addToCart4;
    public javax.swing.JCheckBox addToCart5;
    public javax.swing.JCheckBox addToCart6;
    public javax.swing.JCheckBox addToCart7;
    public javax.swing.JCheckBox addToCart8;
    public javax.swing.JCheckBox addToCart9;
    public javax.swing.JLabel asterisk1;
    public javax.swing.JLabel asterisk2;
    public javax.swing.JTabbedPane cartPanel;
    public javax.swing.JTable cartTable;
    public javax.swing.JButton confirmPassword;
    public javax.swing.JButton confirmPurchasedButton;
    public javax.swing.JButton confirmUsername;
    public javax.swing.JLabel dIMG;
    public javax.swing.JScrollPane dashboardPanel;
    public javax.swing.JLabel editProfiletxt;
    public javax.swing.JLabel editpassimg;
    public javax.swing.JLabel edituserimg;
    public javax.swing.JLabel enterExistingUsername;
    public javax.swing.JLabel enterExistingUsernameforPass;
    public javax.swing.JPasswordField enterNewPassField;
    public javax.swing.JPasswordField enterOldPassField;
    public javax.swing.JPasswordField enterPassEditUser;
    public javax.swing.JLabel headerInventoryIMG;
    public javax.swing.JLabel htmltextInvoice;
    public javax.swing.JPanel inventoryManagementPanel;
    public javax.swing.JButton inventoryManagementbutton;
    public javax.swing.JTextArea invoiceTXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    public javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField newUserFieldEdit;
    public javax.swing.JTable oderTrackingAdminTable;
    public javax.swing.JLabel orderTotaltTXT;
    public javax.swing.JPanel orderTrackingPanel;
    public javax.swing.JButton orderTrackingbutton;
    public javax.swing.JLabel priceText1;
    public javax.swing.JLabel priceText10;
    public javax.swing.JLabel priceText11;
    public javax.swing.JLabel priceText12;
    public javax.swing.JLabel priceText13;
    public javax.swing.JLabel priceText14;
    public javax.swing.JLabel priceText15;
    public javax.swing.JLabel priceText16;
    public javax.swing.JLabel priceText17;
    public javax.swing.JLabel priceText2;
    public javax.swing.JLabel priceText3;
    public javax.swing.JLabel priceText4;
    public javax.swing.JLabel priceText5;
    public javax.swing.JLabel priceText6;
    public javax.swing.JLabel priceText7;
    public javax.swing.JLabel priceText8;
    public javax.swing.JLabel priceText9;
    public javax.swing.JButton productCat;
    public javax.swing.JPanel productImages;
    public javax.swing.JSeparator productSeperator1;
    public javax.swing.JSeparator productSeperator2;
    public javax.swing.JSeparator productSeperator3;
    public javax.swing.JSeparator productSeperator4;
    public javax.swing.JSeparator productSeperator5;
    public javax.swing.JSeparator productSeperator6;
    public javax.swing.JSeparator productSeperator7;
    public javax.swing.JSeparator productSeperator8;
    public javax.swing.JSeparator productSeperator9;
    public javax.swing.JLabel producttxt1;
    public javax.swing.JLabel producttxt10;
    public javax.swing.JLabel producttxt11;
    public javax.swing.JLabel producttxt12;
    public javax.swing.JLabel producttxt13;
    public javax.swing.JLabel producttxt14;
    public javax.swing.JLabel producttxt15;
    public javax.swing.JLabel producttxt16;
    public javax.swing.JLabel producttxt17;
    public javax.swing.JLabel producttxt2;
    public javax.swing.JLabel producttxt3;
    public javax.swing.JLabel producttxt4;
    public javax.swing.JLabel producttxt5;
    public javax.swing.JLabel producttxt6;
    public javax.swing.JLabel producttxt7;
    public javax.swing.JLabel producttxt8;
    public javax.swing.JLabel producttxt9;
    public javax.swing.JLabel profileIconIMG;
    public javax.swing.JPanel purchasePanel;
    public javax.swing.JScrollPane scrollOrderTracking;
    public javax.swing.JPanel shoppingCartPanel;
    public javax.swing.JCheckBox showPassEditUser;
    public javax.swing.JCheckBox showPassEditpassword;
    public javax.swing.JTable tableInventory;
    public javax.swing.JLabel totalInvoicePHP;
    public javax.swing.JLabel totalInvoicetxt;
    public javax.swing.JLabel usernameTXT;
    // End of variables declaration//GEN-END:variables
}
