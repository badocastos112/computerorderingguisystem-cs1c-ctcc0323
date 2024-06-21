/**
 * Guinto, Shelsey Mae L.
 * Hernandez, Anthony Rowie A.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends javax.swing.JFrame {
    
File f = new File("C:\\Users\\ALLISANDRA\\Documents\\NetBeansProjects\\HTML (1)\\src\\data");
    String admin = "@admin";
    String adminPass = "0101";
    private String usr;
    private String pswd;
    int ln;
    String Username, Password;
    public Login() {
        
        initComponents();
        username_field.setBackground(new java.awt.Color(0, 0, 0, 1));
        password_field.setBackground(new java.awt.Color(0, 0, 0, 1));
        
        
      
    }
    void createFolder(){
        if(!f.exists()){
            f.mkdirs();
        }
    }
    void readFile(){
        try {
            FileReader fr = new FileReader(f+"\\logins.txt");
            System.out.println("File exists");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fw = new FileWriter(f+"\\logins.txt");
                System.out.println("File Created");
            } catch (IOException ex1) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    public Login(String f, int ln) {
        
        this.ln = ln;
    }

    void addData(String usr, String pswd, String mail) {
        boolean similarDataExists = false;

        try (RandomAccessFile raf = new RandomAccessFile(f + "\\logins.txt", "r")) {
            String line;
            while ((line = raf.readLine()) != null && !similarDataExists) {
                if (line.contains("Username:" + usr) && line.contains("Password:" + pswd)) {
                    similarDataExists = true;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!similarDataExists) {
            try (RandomAccessFile raf = new RandomAccessFile(f + "\\logins.txt", "rw")) {
                for (int i = 0; i < ln; i++) {
                    raf.readLine();
                }
                raf.writeBytes("\r\n");
                raf.writeBytes("\r\n");
                raf.writeBytes("Username:" + usr + "\r\n");
                raf.writeBytes("Password:" + pswd + "\r\n");
                raf.writeBytes("Email:" + mail);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Similar data already exists in the file.");
        }
    }

        void addDataToDB(String usr, String pswd) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/products?zeroDateTimeBehavior=CONVERT_TO_NULL",
                "root",
                "1234")) {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            pstmt.setString(1, usr);
            pstmt.setString(2, pswd);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement pstmt2 = conn.prepareStatement(query);
                pstmt2.setString(1, usr);
                pstmt2.setString(2, pswd);
                
                pstmt2.executeUpdate();

                System.out.println("Data added to database successfully!");
            } else {
                System.out.println("Similar data already exists in the database.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    void CheckData(String usr, String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            
                String line;
        boolean found = false;
        while ((line = raf.readLine()) != null) {
            Username = line.substring(9);
            Password = raf.readLine().substring(9);
                String Email = raf.readLine().substring(6);
            if (usr.equals(Username) && pswd.equals(Password)) {
                JOptionPane.showMessageDialog(null, "Password Matched");
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Wrong User/Password");
        }    

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void logic(String username, String password) {
    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/products?zeroDateTimeBehavior=CONVERT_TO_NULL",
            "root",
            "1234")) {

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    if (username.equals("@admin") && password.equals("0101")) {
                        new AdminDashboard().setVisible(true);
                    } else {
                        Dashboard dashboard = new Dashboard(username);
                        dashboard.setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.");
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database error. Please try again.");
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public String getUsername(String usr) {
        String username = usr;
        return username;
    }
        
    void countLines(){
        try {
            ln=1;
           RandomAccessFile raf = new RandomAccessFile(f+ "\\logins.txt", "rw");
            for(int i = 0; raf.readLine()!=null; i++){
                ln++;
           }
            System.out.println("Number of lines: "+ ln);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blue_panel = new javax.swing.JPanel();
        pink_panel2 = new javax.swing.JPanel();
        username_text = new javax.swing.JLabel();
        password_text = new javax.swing.JLabel();
        username_field = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        password_field = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBox1 = new javax.swing.JCheckBox();
        Login_button = new javax.swing.JButton();
        html_logintext = new javax.swing.JLabel();
        description_loginform = new javax.swing.JLabel();
        timeDate = new javax.swing.JLabel();
        SignUp_button1 = new javax.swing.JButton();
        Dont_message = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        OR = new javax.swing.JLabel();
        image_loginform = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hyper-Tech Market Lab (HTML)");
        setIconImages(null);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        blue_panel.setBackground(new java.awt.Color(12, 24, 68));
        blue_panel.setPreferredSize(new java.awt.Dimension(1000, 600));

        pink_panel2.setBackground(new java.awt.Color(255, 245, 225));

        username_text.setForeground(new java.awt.Color(200, 0, 54));
        username_text.setText("Username");

        password_text.setForeground(new java.awt.Color(200, 0, 54));
        password_text.setText("Password");

        username_field.setForeground(new java.awt.Color(12, 24, 68));
        username_field.setBorder(null);
        username_field.setMargin(new java.awt.Insets(0, 6, 2, 0));
        username_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_fieldActionPerformed(evt);
            }
        });

        password_field.setForeground(new java.awt.Color(12, 24, 68));
        password_field.setBorder(null);

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 105, 105));
        jCheckBox1.setText("Show password?");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        Login_button.setBackground(new java.awt.Color(200, 0, 54));
        Login_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Login_button.setForeground(new java.awt.Color(255, 245, 225));
        Login_button.setText("Log In");
        Login_button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Login_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_buttonActionPerformed(evt);
            }
        });

        html_logintext.setFont(new java.awt.Font("K26ToyBlocks123", 0, 48)); // NOI18N
        html_logintext.setForeground(new java.awt.Color(12, 24, 68));
        html_logintext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        html_logintext.setText("HTML");

        description_loginform.setFont(new java.awt.Font("Eras Light ITC", 0, 14)); // NOI18N
        description_loginform.setForeground(new java.awt.Color(12, 24, 68));
        description_loginform.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description_loginform.setText("Computer Accessories Ordering GUI System");

        timeDate.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        timeDate.setForeground(new java.awt.Color(255, 105, 105));
        timeDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeDate.setText("© June 2024");
        timeDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        SignUp_button1.setBackground(new java.awt.Color(255, 105, 105));
        SignUp_button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SignUp_button1.setForeground(new java.awt.Color(255, 245, 225));
        SignUp_button1.setText("Sign Up");
        SignUp_button1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SignUp_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUp_button1ActionPerformed(evt);
            }
        });

        Dont_message.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        Dont_message.setForeground(new java.awt.Color(255, 105, 105));
        Dont_message.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Dont_message.setText(" Don't have an account?");

        OR.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        OR.setForeground(new java.awt.Color(255, 105, 105));
        OR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OR.setText("OR");

        javax.swing.GroupLayout pink_panel2Layout = new javax.swing.GroupLayout(pink_panel2);
        pink_panel2.setLayout(pink_panel2Layout);
        pink_panel2Layout.setHorizontalGroup(
            pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pink_panel2Layout.createSequentialGroup()
                .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pink_panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(description_loginform, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pink_panel2Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Login_button, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(username_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(password_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(username_field)
                                    .addComponent(jSeparator1)
                                    .addComponent(password_field)
                                    .addComponent(jSeparator2)
                                    .addComponent(html_logintext, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                    .addComponent(timeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SignUp_button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Dont_message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pink_panel2Layout.createSequentialGroup()
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(OR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator4)))))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pink_panel2Layout.setVerticalGroup(
            pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pink_panel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(html_logintext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description_loginform, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(username_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(username_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Login_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OR, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dont_message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SignUp_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timeDate)
                .addContainerGap())
        );

        image_loginform.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/H T M L (2).png"))); // NOI18N

        javax.swing.GroupLayout blue_panelLayout = new javax.swing.GroupLayout(blue_panel);
        blue_panel.setLayout(blue_panelLayout);
        blue_panelLayout.setHorizontalGroup(
            blue_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blue_panelLayout.createSequentialGroup()
                .addComponent(pink_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image_loginform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        blue_panelLayout.setVerticalGroup(
            blue_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pink_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(blue_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_loginform, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(blue_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void username_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_fieldActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
         if (jCheckBox1.isSelected()) {
        // Show the password as plain text
        password_field.setEchoChar((char) 0);
    } else {
        // Hide the password and show it as dots
        password_field.setEchoChar('*');
    }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void Login_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_buttonActionPerformed
              createFolder();
        readFile();
        countLines();
        logic(username_field.getText(),password_field.getText());
    }//GEN-LAST:event_Login_buttonActionPerformed

    private void SignUp_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUp_button1ActionPerformed
       
        
        new SignUp().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SignUp_button1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Dont_message;
    public javax.swing.JButton Login_button;
    public javax.swing.JLabel OR;
    public javax.swing.JButton SignUp_button1;
    public javax.swing.JPanel blue_panel;
    public javax.swing.JLabel description_loginform;
    public javax.swing.JLabel html_logintext;
    public javax.swing.JLabel image_loginform;
    public javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JSeparator jSeparator4;
    public javax.swing.JPasswordField password_field;
    public javax.swing.JLabel password_text;
    public javax.swing.JPanel pink_panel2;
    public javax.swing.JLabel timeDate;
    public javax.swing.JTextField username_field;
    public javax.swing.JLabel username_text;
    // End of variables declaration//GEN-END:variables
}
