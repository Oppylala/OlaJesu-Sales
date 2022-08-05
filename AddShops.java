
package Products;


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
import net.proteanit.sql.DbUtils;

/*
 * @author Oppylala
 */
public class AddShops extends javax.swing.JFrame {

    public AddShops() {
        initComponents();
        setLocationRelativeTo(null);
        connetConnection();
        outletID();
        displayOutTable();
        showDate();
        usern.setText(Account.CreateAccount.user.getText());
    }
    Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddShops.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(AddShops.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM - dd - yyyy ");//("EEE, d MMM yyy");
        date.setText(s.format(d));
        
    }
     private void outletID(){
    try{
        String sql = "SELECT max(`Shopid`) FROM `Shops` ";
        Statement search = con.createStatement();
        ResultSet form = search.executeQuery(sql);
        int frm = 100;
        while(form.next()){
            frm = form.getInt(1);
       }
        outletid.setText(String.valueOf(frm + 1));
    }catch(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage());
    }  
}
      private void displayOutTable(){
        try{
            String nom = "";
           String sql = "SELECT * FROM `Shops` WHERE Shopid LIKE ?";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, "%"+ nom.toUpperCase()+"%");
           ResultSet rs = pst.executeQuery();
           outlettable.setModel(DbUtils.resultSetToTableModel(rs));
           if(rs!=null&&rs.next()){
               rs.beforeFirst();
               outlettable.setModel(DbUtils.resultSetToTableModel(rs));
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
     public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
     if(date !=null){
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             return sqlDate;}
     return null;
    }
    private void addOutlets(){
       
        if(outletname.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Shop Name is Required!", "Ola_Jesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);          
        }
        else if(location.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Shop Location is Required!", "Ola_Jesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }               
        else{
            try{
           String sql = ("INSERT INTO `Shops` VALUE(?,?,?,?)");                     
           PreparedStatement pst = con.prepareStatement(sql);                          
           pst.setInt(1, Integer.parseInt(outletid.getText().toString()));        
           pst.setString(2, outletname.getText());
           pst.setString(3, location.getText());
           pst.setString(4, director.getText());
        
               String search = "SELECT * FROM `Shops` WHERE Shopid  = "+ outletid.getText();
               PreparedStatement pre = con.prepareStatement(search);
               ResultSet R = pre.executeQuery();
               if(R.next()){
                    JOptionPane.showMessageDialog(this, "Outlet is already exist");
               }else{
                     pst.executeUpdate();
                    this.dispose();
                    new AddShops().setVisible(true);
                  } 
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addPharmacyJButton = new javax.swing.JButton();
        location = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        director = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        outlettable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        outletname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        outletid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBack1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        usern = new javax.swing.JLabel();
        loginas = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        usern1 = new javax.swing.JLabel();
        loginas1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addPharmacyJButton.setBackground(new java.awt.Color(0, 159, 0));
        addPharmacyJButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addPharmacyJButton.setForeground(new java.awt.Color(255, 255, 255));
        addPharmacyJButton.setText("ADD OUTLETS +");
        addPharmacyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPharmacyJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addPharmacyJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 530, 40));
        jPanel1.add(location, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 140, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 177, 0));
        jLabel7.setText("Location Name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 177, 0));
        jLabel8.setText("Director");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, -1));
        jPanel1.add(director, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 140, -1));

        outlettable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(outlettable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 670, 210));

        jLabel5.setFont(new java.awt.Font("Prestige12 BT", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Ola-Jesu Nig. Enterprises");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(outletname, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 150, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 177, 0));
        jLabel4.setText("Outlet ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        outletid.setEditable(false);
        jPanel1.add(outletid, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 70, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 177, 0));
        jLabel6.setText("Outlet Name");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack1.setBackground(new java.awt.Color(255, 255, 255));
        btnBack1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBack1.setForeground(new java.awt.Color(255, 0, 51));
        btnBack1.setText("<< BACK");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 380));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, 400));

        jLabel9.setFont(new java.awt.Font("PrincetownD", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 144, 9));
        jLabel9.setText("Add shops");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 360, 40));

        date.setForeground(new java.awt.Color(102, 0, 102));
        date.setText("jLabel1");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, -1, -1));

        jPanel6.setBackground(new java.awt.Color(0, 142, 2));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usern.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        usern.setForeground(new java.awt.Color(255, 255, 255));
        usern.setText("Username");
        jPanel6.add(usern, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, 40));

        loginas.setFont(new java.awt.Font("HandelGotDLig", 0, 14)); // NOI18N
        loginas.setForeground(new java.awt.Color(255, 255, 255));
        loginas.setText("Welcome:");
        jPanel6.add(loginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel7.setBackground(new java.awt.Color(0, 142, 2));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usern1.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        usern1.setForeground(new java.awt.Color(255, 255, 255));
        usern1.setText("Username");
        jPanel7.add(usern1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, 40));

        loginas1.setFont(new java.awt.Font("HandelGotDLig", 0, 14)); // NOI18N
        loginas1.setForeground(new java.awt.Color(255, 255, 255));
        loginas1.setText("Welcome:");
        jPanel7.add(loginas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 230, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 230, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPharmacyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPharmacyJButtonActionPerformed
        addOutlets();
    }//GEN-LAST:event_addPharmacyJButtonActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        this.dispose();
        new Index.Mainpage().setVisible(true);
    }//GEN-LAST:event_btnBack1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddShops.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddShops.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddShops.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddShops.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AddShops().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPharmacyJButton;
    private javax.swing.JButton btnBack1;
    private javax.swing.JLabel date;
    private javax.swing.JTextField director;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField location;
    public static javax.swing.JLabel loginas;
    public static javax.swing.JLabel loginas1;
    private javax.swing.JTextField outletid;
    private javax.swing.JTextField outletname;
    public static javax.swing.JTable outlettable;
    public static javax.swing.JLabel usern;
    public static javax.swing.JLabel usern1;
    // End of variables declaration//GEN-END:variables
}
