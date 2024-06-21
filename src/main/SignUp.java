/**
 * Guinto, Shelsey Mae L.
 * Hernandez, Anthony Rowie A.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

public class SignUp extends javax.swing.JFrame {
    
    private Login login;

    public SignUp() {
        login = new Login();

        initComponents();
        
        username_SField.setBackground(new java.awt.Color(0, 0, 0, 1));
        password_SField.setBackground(new java.awt.Color(0, 0, 0, 1));
        password_SField1.setBackground(new java.awt.Color(0, 0, 0, 1));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blue_panel2 = new javax.swing.JPanel();
        pink_panel2 = new javax.swing.JPanel();
        html_SignUp = new javax.swing.JLabel();
        description_SignUp = new javax.swing.JLabel();
        username_SignUp = new javax.swing.JLabel();
        username_SField = new javax.swing.JTextField();
        jSeparator1_S = new javax.swing.JSeparator();
        password_SignUp = new javax.swing.JLabel();
        password_SField = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        SCheckbox = new javax.swing.JCheckBox();
        CreateAccount_button = new javax.swing.JButton();
        copyright_S = new javax.swing.JLabel();
        message_signUp = new javax.swing.JLabel();
        message2_signUp = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        Confirmpassword_SignUp = new javax.swing.JLabel();
        password_SField1 = new javax.swing.JPasswordField();
        SCheckbox1 = new javax.swing.JCheckBox();
        image_SignUpForm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hyper-Tech Market Lab (HTML)");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        blue_panel2.setBackground(new java.awt.Color(12, 24, 68));
        blue_panel2.setPreferredSize(new java.awt.Dimension(1000, 600));

        pink_panel2.setBackground(new java.awt.Color(255, 245, 225));

        html_SignUp.setFont(new java.awt.Font("K26ToyBlocks123", 0, 48)); // NOI18N
        html_SignUp.setForeground(new java.awt.Color(12, 24, 68));
        html_SignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        html_SignUp.setText("HTML");

        description_SignUp.setFont(new java.awt.Font("Eras Light ITC", 0, 14)); // NOI18N
        description_SignUp.setForeground(new java.awt.Color(12, 24, 68));
        description_SignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description_SignUp.setText("Computer Accessories Ordering GUI System");

        username_SignUp.setForeground(new java.awt.Color(200, 0, 54));
        username_SignUp.setText("Username");

        username_SField.setForeground(new java.awt.Color(12, 24, 68));
        username_SField.setBorder(null);
        username_SField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_SFieldActionPerformed(evt);
            }
        });

        password_SignUp.setForeground(new java.awt.Color(200, 0, 54));
        password_SignUp.setText("Password");

        password_SField.setForeground(new java.awt.Color(12, 24, 68));
        password_SField.setBorder(null);
        password_SField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_SFieldActionPerformed(evt);
            }
        });

        SCheckbox.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        SCheckbox.setForeground(new java.awt.Color(255, 105, 105));
        SCheckbox.setText("Show password?");
        SCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCheckboxActionPerformed(evt);
            }
        });

        CreateAccount_button.setBackground(new java.awt.Color(255, 105, 105));
        CreateAccount_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CreateAccount_button.setForeground(new java.awt.Color(255, 245, 225));
        CreateAccount_button.setText("Create Account");
        CreateAccount_button.setPreferredSize(new java.awt.Dimension(72, 23));
        CreateAccount_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAccount_buttonActionPerformed(evt);
            }
        });

        copyright_S.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        copyright_S.setForeground(new java.awt.Color(255, 105, 105));
        copyright_S.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        copyright_S.setText("© June 2024");

        message_signUp.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        message_signUp.setForeground(new java.awt.Color(12, 24, 68));
        message_signUp.setText("By creating an account, you agree to Hyper-Tech Market Lab / HTML's");
        message_signUp.setToolTipText("");

        message2_signUp.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        message2_signUp.setForeground(new java.awt.Color(200, 0, 54));
        message2_signUp.setText("Conditions of Use and Privacy Notice.");
        message2_signUp.setToolTipText("");

        email.setText("Email");
        email.setUI(null);

        Confirmpassword_SignUp.setForeground(new java.awt.Color(200, 0, 54));
        Confirmpassword_SignUp.setText("Confirm Password");

        password_SField1.setForeground(new java.awt.Color(12, 24, 68));
        password_SField1.setBorder(null);
        password_SField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_SField1ActionPerformed(evt);
            }
        });

        SCheckbox1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        SCheckbox1.setForeground(new java.awt.Color(255, 105, 105));
        SCheckbox1.setText("Show password?");
        SCheckbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCheckbox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pink_panel2Layout = new javax.swing.GroupLayout(pink_panel2);
        pink_panel2.setLayout(pink_panel2Layout);
        pink_panel2Layout.setHorizontalGroup(
            pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pink_panel2Layout.createSequentialGroup()
                .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Confirmpassword_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SCheckbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pink_panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(SCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pink_panel2Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(html_SignUp, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                .addComponent(description_SignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(username_SignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(username_SField)
                                .addComponent(jSeparator1_S)
                                .addComponent(password_SignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(password_SField)
                                .addComponent(jSeparator1))))
                    .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(message_signUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(copyright_S, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreateAccount_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addComponent(message2_signUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addComponent(password_SField1)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pink_panel2Layout.setVerticalGroup(
            pink_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pink_panel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(html_SignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(username_SignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(username_SField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1_S, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password_SignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password_SField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Confirmpassword_SignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password_SField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SCheckbox1)
                .addGap(18, 18, 18)
                .addComponent(CreateAccount_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(message_signUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message2_signUp)
                .addGap(39, 39, 39)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(copyright_S)
                .addContainerGap())
        );

        image_SignUpForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/H T M L.png"))); // NOI18N

        javax.swing.GroupLayout blue_panel2Layout = new javax.swing.GroupLayout(blue_panel2);
        blue_panel2.setLayout(blue_panel2Layout);
        blue_panel2Layout.setHorizontalGroup(
            blue_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blue_panel2Layout.createSequentialGroup()
                .addComponent(pink_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image_SignUpForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        blue_panel2Layout.setVerticalGroup(
            blue_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blue_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_SignUpForm, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(pink_panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(blue_panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void username_SFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_SFieldActionPerformed
        
    }//GEN-LAST:event_username_SFieldActionPerformed

    private void password_SFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_SFieldActionPerformed
        
    }//GEN-LAST:event_password_SFieldActionPerformed

    private void SCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCheckboxActionPerformed
        // TODO add your handling code here:
        if (SCheckbox.isSelected()) {
        // Show the password as plain text
        password_SField.setEchoChar((char) 0);
    } else {
        // Hide the password and show it as dots
        password_SField.setEchoChar('*');
    }
    }//GEN-LAST:event_SCheckboxActionPerformed

    private void CreateAccount_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAccount_buttonActionPerformed
/**
 * This Action Event for the button uses methods from the Login to run the CreateAccount_button.
 */
        login.createFolder();
        login.readFile();
        login.countLines();
        login.addData(username_SField.getText(),password_SField.getText(), email.getText());
        login.addDataToDB(username_SField.getText(),password_SField.getText());
        new Login().setVisible(true);
        dispose();

    }//GEN-LAST:event_CreateAccount_buttonActionPerformed

    private void password_SField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_SField1ActionPerformed
       
    }//GEN-LAST:event_password_SField1ActionPerformed

    private void SCheckbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCheckbox1ActionPerformed
        // TODO add your handling code here
        // TODO add your handling code here:
        if (SCheckbox1.isSelected()) {
        // Show the password as plain text
        password_SField1.setEchoChar((char) 0);
    } else {
        // Hide the password and show it as dots
        password_SField1.setEchoChar('*');
    }
    }//GEN-LAST:event_SCheckbox1ActionPerformed

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
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Confirmpassword_SignUp;
    public javax.swing.JButton CreateAccount_button;
    public javax.swing.JCheckBox SCheckbox;
    public javax.swing.JCheckBox SCheckbox1;
    public javax.swing.JPanel blue_panel2;
    public javax.swing.JLabel copyright_S;
    public javax.swing.JLabel description_SignUp;
    public javax.swing.JTextField email;
    public javax.swing.JLabel html_SignUp;
    public javax.swing.JLabel image_SignUpForm;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator1_S;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel message2_signUp;
    public javax.swing.JLabel message_signUp;
    public javax.swing.JPasswordField password_SField;
    public javax.swing.JPasswordField password_SField1;
    public javax.swing.JLabel password_SignUp;
    public javax.swing.JPanel pink_panel2;
    public javax.swing.JTextField username_SField;
    public javax.swing.JLabel username_SignUp;
    // End of variables declaration//GEN-END:variables
}
