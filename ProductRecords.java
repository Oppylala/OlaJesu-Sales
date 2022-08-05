package Records;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/*
 * @author Oppylala
 */
public class ProductRecords extends javax.swing.JFrame {

    public ProductRecords() {
        initComponents();
        setLocationRelativeTo(null);
        connetConnection();
        fillCategory();
        fillCompany();
       
    }
     Connection con;   
     public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(ProductRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
     if(date !=null){
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             return sqlDate;}
     return null;
    }
    
     
     // Product Records Here.................
     private void fillCategory(){
     try{
      String sql= "select distinct Category from Products order by Category";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("category");
          Categoryname.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
     }
     private void fillCompany(){
     try{
      String sql= "select distinct Companyname from Products order by companyname";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("companyname");
          companyname.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
}
     private void displayProducts(){
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
     public void searchbyCategory(){          
        DefaultTableModel table = new DefaultTableModel();        
        table.addColumn("ProductID");
        table.addColumn("ProductName");
        table.addColumn("Company Name");
        table.addColumn("Category");
        table.addColumn("Representative");
        try{
            String sql = "SELECT * FROM `Products` Where Category LIKE '%" + Categoryname.getSelectedItem()+"%'";
            Statement s = con.createStatement();
            ResultSet R = s.executeQuery(sql);
            while(R.next()){
                table.addRow(new Object[]{                  
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),   
                    R.getString(5)
                });
            }         
           producttable.setModel(table);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }
     public void searchbyCompany(){          
        DefaultTableModel table = new DefaultTableModel();        
        table.addColumn("ProductID");
        table.addColumn("ProductName");
        table.addColumn("Company Name");
        table.addColumn("Category");
        table.addColumn("Representative");
        try{
            String sql = "SELECT * FROM `Products` Where Companyname LIKE '%" + companyname.getSelectedItem()+"%'";
            Statement s = con.createStatement();
            ResultSet R = s.executeQuery(sql);
            while(R.next()){
                table.addRow(new Object[]{                  
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),   
                    R.getString(5)
                });
            }         
           producttable.setModel(table);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }
     public void searchbyText(){          
        DefaultTableModel table = new DefaultTableModel();        
        table.addColumn("ProductID");
        table.addColumn("ProductName");
        table.addColumn("Company Name");
        table.addColumn("Category");
        table.addColumn("Representative");
        try{
            String sql = "SELECT * FROM `Products` Where productname LIKE '%" + producttext.getText()+"%'";
            Statement s = con.createStatement();
            ResultSet R = s.executeQuery(sql);
            while(R.next()){
                table.addRow(new Object[]{                 
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),   
                    R.getString(5)
                });
            }         
           producttable.setModel(table);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }
     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        producttable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        companyname = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        producttext = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Categoryname = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        producttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(producttable);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1080, 510));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(51, 0, 255));
        jButton5.setText("Display All");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 150, 50));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 0, 255));
        jButton7.setText(">>");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        jButton7.setPreferredSize(new java.awt.Dimension(41, 21));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 110, 40));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        companyname.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N
        companyname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Company Name" }));
        companyname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                companynameItemStateChanged(evt);
            }
        });
        jPanel6.add(companyname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 0, 255));
        jLabel12.setText("Search by Company Name");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 230, 70));

        producttext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                producttextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                producttextKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                producttextKeyTyped(evt);
            }
        });
        jPanel5.add(producttext, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 180, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Search by Product Name");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, 20));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categoryname.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N
        Categoryname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));
        Categoryname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CategorynameItemStateChanged(evt);
            }
        });
        jPanel7.add(Categoryname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 0, 255));
        jLabel13.setText("Search by Category");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 230, 70));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 620));

        jTabbedPane1.addTab("Product Records", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 1100, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        displayProducts();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.dispose();
        new Index.Mainpage().setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void companynameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_companynameItemStateChanged
       searchbyCompany(); 
    }//GEN-LAST:event_companynameItemStateChanged

    private void producttextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_producttextKeyTyped
       
    }//GEN-LAST:event_producttextKeyTyped

    private void CategorynameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CategorynameItemStateChanged
        searchbyCategory();
    }//GEN-LAST:event_CategorynameItemStateChanged

    private void producttextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_producttextKeyReleased
        searchbyText();
    }//GEN-LAST:event_producttextKeyReleased

    private void producttextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_producttextKeyPressed
        
    }//GEN-LAST:event_producttextKeyPressed

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
            java.util.logging.Logger.getLogger(ProductRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ProductRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Categoryname;
    private javax.swing.JComboBox<String> companyname;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable producttable;
    private javax.swing.JTextField producttext;
    // End of variables declaration//GEN-END:variables
}
