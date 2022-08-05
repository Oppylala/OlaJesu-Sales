
package Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Oppylala
 */
public class ResetPassword extends javax.swing.JFrame {

    public ResetPassword() {
        initComponents();
        setLocationRelativeTo(null);
        connetConnection();
    }
     Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ResetPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/MshopSaki", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(ResetPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    private void searchUsername(){
        if(username.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Your Username is required!","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            username.requestFocus();
            return;
        }
        else{
            try{
            String sql = "SELECT * FROM `Userregistration` WHERE `Username` LIKE '%" + username.getText() + "%'";
            PreparedStatement pst=con.prepareStatement(sql);
          
            ResultSet rs=  pst.executeQuery();
            if(rs.next()){
                fullname.setText(rs.getString("Fullname"));
                
           }
            else{
                JOptionPane.showMessageDialog(this, "Data is not Found! ", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
                fullname.setText("");
              
            }
             rs.close();
      
        }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error Occur " + e.getMessage(), "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE); 
        
         }
     } 
        }
        
private void resetPassword(){
         if(username.getText().equals("")){
             JOptionPane.showMessageDialog(this,"Username is Required!","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
             username.requestFocus();
         }
        
          else{
              try{
            PreparedStatement bal = con.prepareStatement("UPDATE `userregistration` SET `Password`= '%"+newpassword.getText()+"%',`confirm`= '%"+newpassword.getText()+"%' WHERE `Username` LIKE '%"+ username.getText()+"%'");
            bal.execute();
                JOptionPane.showMessageDialog(this,"Your Password has being changed Successfully!","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
               this.dispose();
                new CreateAccount().setVisible(true);
         
        }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error Occur " + e.getMessage(), "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE); 
        }  
         }
     } 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ClearButton = new javax.swing.JButton();
        save_payment = new javax.swing.JButton();
        SearchButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Time1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        newpassword = new javax.swing.JPasswordField();
        confirmpassword = new javax.swing.JPasswordField();
        fullname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ClearButton.setBackground(new java.awt.Color(0, 0, 0));
        ClearButton.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        ClearButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ClearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 200, 50));

        save_payment.setBackground(new java.awt.Color(0, 0, 0));
        save_payment.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        save_payment.setForeground(new java.awt.Color(255, 255, 255));
        save_payment.setText("Reset Password");
        save_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_paymentActionPerformed(evt);
            }
        });
        jPanel1.add(save_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 190, 50));

        SearchButton.setBackground(new java.awt.Color(0, 0, 0));
        SearchButton.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        SearchButton.setForeground(new java.awt.Color(255, 255, 255));
        SearchButton.setText("Search Username");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, 210, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Time1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Time1.setForeground(new java.awt.Color(255, 255, 255));
        Time1.setText("jLabel10");
        jPanel2.add(Time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 110, -1));

        Date1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Date1.setForeground(new java.awt.Color(255, 255, 255));
        Date1.setText("jLabel9");
        jPanel2.add(Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 170, 70));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Swis721 Md BT", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Confirm Password:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, -1, -1));

        jLabel19.setFont(new java.awt.Font("Swis721 Md BT", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("New Password");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        jPanel3.add(newpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 150, -1));
        jPanel3.add(confirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 150, -1));

        fullname.setEditable(false);
        jPanel3.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 260, -1));

        jLabel10.setFont(new java.awt.Font("Swis721 Md BT", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fullname");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 80, -1));

        jLabel3.setFont(new java.awt.Font("Swis721 Md BT", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Enter your Username here");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));
        jPanel3.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 110, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Reset your Password Here");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 220, 30));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 240, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 670, 240));

        btnBack.setBackground(new java.awt.Color(0, 0, 0));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 0, 51));
        btnBack.setText("<< BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 150, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/signup.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void save_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_paymentActionPerformed
        resetPassword();
    }//GEN-LAST:event_save_paymentActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed

    }//GEN-LAST:event_ClearButtonActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        searchUsername();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
        new CreateAccount().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ClearButton;
    private javax.swing.JLabel Date1;
    private javax.swing.JButton SearchButton;
    private javax.swing.JLabel Time1;
    private javax.swing.JButton btnBack;
    private javax.swing.JPasswordField confirmpassword;
    public javax.swing.JTextField fullname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField newpassword;
    public javax.swing.JButton save_payment;
    public static javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
