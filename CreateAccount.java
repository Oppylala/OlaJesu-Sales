
package Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Oppylala
 */
public class CreateAccount extends javax.swing.JFrame {

    public CreateAccount() {
        initComponents();
    setLocationRelativeTo(null);
    connetConnection();
    serial.setText("0");      
    showDate();
    showTime();
    UserID();
        
    }
    
     Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    private void UserID(){
    try{
        String sql = "SELECT max(`sn`) FROM `userregistration` ";
        Statement search = con.createStatement();
        ResultSet form = search.executeQuery(sql);
        int frm = 100;
        while(form.next()){
            frm = form.getInt(1);
       }
        serial.setText(String.valueOf(frm + 1));
    }catch(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage());
    }  
}
   
    
     public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
     if(date !=null){
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
     return sqlDate;}
     return null;
    }
 void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyy ");//("MM - dd - yyyy");
        Date1.setText(s.format(d));
        Date2.setText(s.format(d));
        RegDate.setDate(d);
    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh: mm: ss");
                Time1.setText(s.format(d));
                Time2.setText(s.format(d));
            }

        }
        ).start();

    }
    
    
    private void createAccount(){
        if(permit_no.getText().equals("")){
         JOptionPane.showMessageDialog(this, "OlaJesu Secret Code is Required!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         permit_no.requestFocus();
        }
        else if(fullname.getText().equals("")){
         JOptionPane.showMessageDialog(this, "Fullname is Required!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         fullname.requestFocus();
        }
        else if(username.getText().equals("")){
         JOptionPane.showMessageDialog(this, "Username is Required!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         user.requestFocus();
        }
        else if(password.getText().equals("")){
         JOptionPane.showMessageDialog(this, "Password is Required!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         password.requestFocus();
        }
        else if(password.getText().length() < 4){
         JOptionPane.showMessageDialog(this, "Your Password is too weak!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         password.requestFocus();
        }
        else if(!(password.getText().equals(confirm_password.getText()))){
         JOptionPane.showMessageDialog(this, "Your passwords are not corresponding!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         password.requestFocus();
        }
        else if(confirm_password.getText().equals("")){
         JOptionPane.showMessageDialog(this, "Confirm Password is Required!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         confirm_password.requestFocus();
        }
         else if(!(permit_no.getText().equals("A001"))){
         JOptionPane.showMessageDialog(this, "You are not an Admin! You are not allow to Register Users!", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
         permit_no.requestFocus();
        }
        
        else{
            try{
                 PreparedStatement pst = con.prepareStatement("INSERT INTO `userregistration` VALUES (?,?,?,?,?,?,?)");
                Statement stmt;
            stmt= con.createStatement();
             String sql1="SELECT `Username` FROM `userregistration` WHERE `Username`LIKE '%"+ username.getText()+"%'";
            ResultSet rs=stmt.executeQuery(sql1);
            if(rs.next()){
                JOptionPane.showMessageDialog( this, "User name already exists","OlaJesu Nig. Enterprises", JOptionPane.ERROR_MESSAGE);
                username.setText("");
                password.requestDefaultFocus();
           }
            else{
                 pst.setInt(1, Integer.parseInt(serial.getText().toString()));
                 pst.setString(2, permit_no.getText());
                 pst.setString(3, fullname.getText());
                 pst.setString(4, username.getText());
                 pst.setString(5, password.getText());
                 pst.setString(6, confirm_password.getText());
                 pst.setDate(7, convertUtilDateToSqlDate(RegDate.getDate()));
                
                 pst.executeUpdate();
            
           // JOptionPane.showMessageDialog(this,"Your Account has been created!","Pharmacy Management System",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new CreateAccount().setVisible(true);
                
            }          
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        }
    }

    private void login(){
       if(user.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Please Input the Username","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            user.requestFocus();
        }
        else if(pass.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Input the Password","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            pass.requestFocus();
        }
       
        else{
            try{
                String slq = "SELECT * FROM `userregistration` WHERE Username LIKE '%" + user.getText() + "%' && Password LIKE '%" + pass.getText() + "%'";
                PreparedStatement pst = con.prepareStatement(slq);
                ResultSet R = pst.executeQuery();
                if( R.next()){                   
                             this.dispose();
                              new Index.Mainpage().setVisible(true);
                }              
                else{
                    JOptionPane.showMessageDialog(this,"Incorrect Login Credentials!","OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
                    user.setText("");
                    pass.setText("");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e);
            }
        }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Time2 = new javax.swing.JLabel();
        Date2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        user = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        serial = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        permit_no = new javax.swing.JPasswordField();
        RegDate = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        fullname = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        confirm_password = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Time1 = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 0, 51));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Time2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Time2.setForeground(new java.awt.Color(255, 255, 255));
        Time2.setText("jLabel10");
        jPanel4.add(Time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 110, -1));

        Date2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Date2.setForeground(new java.awt.Color(255, 255, 255));
        Date2.setText("jLabel9");
        jPanel4.add(Date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 170, 70));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("OLA-JESU NIGERIA ENTERPRISES");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, -1));

        jPanel6.setBackground(new java.awt.Color(0, 0, 240));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tiffany Lt BT", 0, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("OlaJesu Nig. Enterprises");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 450, 40));

        jLabel14.setFont(new java.awt.Font("Tiffany Lt BT", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Opo_Malu Road, Sanngo, Saki");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 260, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 490, 570));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Password");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 100, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 0));
        jButton1.setText("Create New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 110, 30));

        jButton2.setBackground(new java.awt.Color(0, 0, 240));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 80, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("Forgot Passwod? Reset Your Password Here");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 320, -1));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("EXIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 340, 40));

        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel5.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 200, 30));

        jLabel13.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Username");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 110, 20));
        jPanel5.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 200, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 430, 270));

        jLabel15.setFont(new java.awt.Font("Tiffany Lt BT", 0, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 51));
        jLabel15.setText("OlaJesu Nig. Enterprises");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 430, 40));

        jLabel17.setFont(new java.awt.Font("Tiffany Lt BT", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("You are welcome to");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 210, 40));

        jTabbedPane1.addTab("Login", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Ola Jesu Secret Code");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, -1));

        jLabel5.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Fullname");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, -1));

        jLabel6.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Username");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 80, -1));

        jLabel26.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 255));
        jLabel26.setText("UserId");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, -1));

        jLabel11.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Password");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 100, -1));

        jLabel12.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Confirm Password");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 160, -1));

        serial.setEditable(false);
        serial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        serial.setEnabled(false);
        jPanel3.add(serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 120, -1));

        jLabel27.setFont(new java.awt.Font("Prestige12 BT", 3, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 255));
        jLabel27.setText("Registration Date");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 200, -1));
        jPanel3.add(permit_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 270, 30));
        jPanel3.add(RegDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 150, -1));

        jLabel16.setFont(new java.awt.Font("Bookman Old Style", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Register Your Details Here");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 330, -1));
        jPanel3.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 270, 30));
        jPanel3.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 270, 30));
        jPanel3.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 260, 30));
        jPanel3.add(confirm_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 280, 30));

        jButton4.setBackground(new java.awt.Color(0, 0, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Create Account");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 400, 50));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 400, 500));

        jPanel8.setBackground(new java.awt.Color(0, 0, 240));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tiffany Lt BT", 0, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("OlaJesu Nig. Enterprises");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 450, 40));

        jLabel19.setFont(new java.awt.Font("Tiffany Lt BT", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Opo_Malu Road, Sanngo, Saki");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 260, 30));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Time1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Time1.setForeground(new java.awt.Color(255, 255, 255));
        Time1.setText("jLabel10");
        jPanel7.add(Time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 110, -1));

        Date1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Date1.setForeground(new java.awt.Color(255, 255, 255));
        Date1.setText("jLabel9");
        jPanel7.add(Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        jPanel8.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 170, 70));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 590));

        jTabbedPane1.addTab("   Create Account", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 960, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        createAccount();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
        //    new ResetPassword().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        login();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, "Please Click Create Account above!","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userActionPerformed

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
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Date2;
    private com.toedter.calendar.JDateChooser RegDate;
    private javax.swing.JLabel Time1;
    private javax.swing.JLabel Time2;
    private javax.swing.JPasswordField confirm_password;
    private javax.swing.JTextField fullname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField permit_no;
    private javax.swing.JTextField serial;
    public static javax.swing.JTextField user;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
