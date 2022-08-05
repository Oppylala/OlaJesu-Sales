package Products;


import java.awt.Color;
import java.awt.event.KeyEvent;
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
public class AddProduct extends javax.swing.JFrame {

    public AddProduct() {
        initComponents();
        connetConnection();
        productID();
        showSalesDate();
        displayProductTable();
        usern.setText(Account.CreateAccount.user.getText());
    }
    
     Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
   
     private void displayProductTable(){
        try{
            String nom = "";
           String sql = "SELECT * FROM `Products` WHERE Productid LIKE ?";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, "%"+ nom.toUpperCase()+"%");
           ResultSet rs = pst.executeQuery();
           producttable.setModel(DbUtils.resultSetToTableModel(rs));
           if(rs!=null&&rs.next()){
               rs.beforeFirst();
           producttable.setModel(DbUtils.resultSetToTableModel(rs));
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
     void showSalesDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyy");//("MM - dd - yyyy");
  //      inputDate.setText(s.format(d));
    }
    private void productID(){
    try{
        String sql = "SELECT max(`productId`) FROM `Products` ";
        Statement search = con.createStatement();
        ResultSet form = search.executeQuery(sql);
        int frm = 100;
        while(form.next()){
            frm = form.getInt(1);
       }
        productid.setText(String.valueOf(frm + 1));
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
   
 private void addProduct(){      
        if(productname.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Product name is Requied!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            productname.setCaretColor(Color.red);
        }
          else{
               String Productname = productname.getText();
            try{
           String sql = ("INSERT INTO `Products` VALUE(?,?,?)");                     
           PreparedStatement pst = con.prepareStatement(sql);                          
           pst.setInt(1, Integer.parseInt(productid.getText().toString()));        
           pst.setString(2, productname.getText());
           pst.setDouble(3, Double.parseDouble(price.getText()));
          
               String search = "SELECT `productname` FROM `Products` WHERE productname = '"+ Productname+"' ";
               PreparedStatement pre = con.prepareStatement(search);
               ResultSet R = pre.executeQuery();
               if(R.next()){
                    JOptionPane.showMessageDialog(this, "Product is already exist");
               }else{
                     pst.executeUpdate();
                     this.dispose();
                     new AddProduct().setVisible(true);
                  } 
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
        }   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errEDatejLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        createDrugJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productname = new javax.swing.JTextField();
        productid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBack1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        producttable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        usern = new javax.swing.JLabel();
        loginas = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        usern1 = new javax.swing.JLabel();
        loginas1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        errEDatejLabel4.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(errEDatejLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 158, 11));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createDrugJButton.setBackground(new java.awt.Color(0, 166, 0));
        createDrugJButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        createDrugJButton.setForeground(new java.awt.Color(255, 255, 255));
        createDrugJButton.setText("Add Product     ++");
        createDrugJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDrugJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(createDrugJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 630, 40));

        jLabel3.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 158, 7));
        jLabel3.setText("Product Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 158, 7));
        jLabel2.setText("Product ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));
        jPanel1.add(productname, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 250, -1));

        productid.setEnabled(false);
        jPanel1.add(productid, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 60, -1));

        jLabel1.setFont(new java.awt.Font("Americana XBd BT", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(160, 0, 0));
        jLabel1.setText("Add New Products");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Prestige12 BT", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 73, 0));
        jLabel5.setText("Ola-Jesu Nig. Enterprises");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack1.setBackground(new java.awt.Color(255, 255, 255));
        btnBack1.setFont(new java.awt.Font("Prestige12 BT", 1, 18)); // NOI18N
        btnBack1.setForeground(new java.awt.Color(255, 0, 51));
        btnBack1.setText("<< BACK");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 400));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 420));

        producttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(producttable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 790, 210));

        jLabel8.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 158, 7));
        jLabel8.setText("Price");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 140, -1));

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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 230, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createDrugJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDrugJButtonActionPerformed
        addProduct();
    }//GEN-LAST:event_createDrugJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AddProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton createDrugJButton;
    private javax.swing.JLabel errEDatejLabel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel loginas;
    public static javax.swing.JLabel loginas1;
    private javax.swing.JTextField price;
    private javax.swing.JTextField productid;
    private javax.swing.JTextField productname;
    public static javax.swing.JTable producttable;
    public static javax.swing.JLabel usern;
    public static javax.swing.JLabel usern1;
    // End of variables declaration//GEN-END:variables
}
