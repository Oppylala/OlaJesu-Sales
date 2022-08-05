package Records;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/*
 @author Oppylala
 */
public class StockBalance extends javax.swing.JFrame {

    public StockBalance() {
        initComponents();
        setLocationRelativeTo(null);
        fillProductname1();              
    }
    
     private void displayLowQuantity(){      
        int value = 0;
        for(int i =0; i<stockbalancetable.getRowCount(); i++){
       /*     if(i==0){
                value = Integer.parseInt(stocktable.getValueAt(i, 3).toString());
            }
            else{   */
                int val = Integer.parseInt(stockbalancetable.getValueAt(i, 3).toString());
                if(val<30){
                   // value = val;
                    JOptionPane.showMessageDialog(this,  stockbalancetable.getValueAt(i, 2)+ "  is low!    \n" + stockbalancetable.getValueAt(i, 3)+"    Left");
               // }
            }         
        }    
     }
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
     if(date !=null){
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             return sqlDate;}
     return null;
    }
  
      // Received Stock Records Here.................
     private void calculateReceive(){      
        double ReceiveBal = 0;
        for(int i =0; i<stockbalancetable.getRowCount(); i++){
            ReceiveBal = ReceiveBal + Double.parseDouble(stockbalancetable.getValueAt(i, 5).toString());
        }
        stockbal.setText("N "+ NumberFormat.getIntegerInstance().format(ReceiveBal));
       }
     private void fillProductname1(){
     try{
  Class.forName("com.mysql.jdbc.Driver");
  Connection con = DriverManager.getConnection("jdbc:mysql:///BackEnd", "root", "oppylala2525");
      String sql= "select distinct Productname from Stock order by Productname";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("Productname");
          productreceive.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
     }
     
     private void displayStockBalance(){
        try{
            String nom = "";
            Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql:///BackEnd", "root", "oppylala2525");
           String sql = "SELECT * FROM `Stock` WHERE stockid LIKE ?";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, "%"+ nom.toUpperCase()+"%");
           ResultSet rs = pst.executeQuery();
           stockbalancetable.setModel(DbUtils.resultSetToTableModel(rs));
           if(rs!=null&&rs.next()){
               rs.beforeFirst();
               stockbalancetable.setModel(DbUtils.resultSetToTableModel(rs));
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }  
    
     public void searchStockBalancebyName(){          
        DefaultTableModel table = new DefaultTableModel();        
        table.addColumn("StockID");
        table.addColumn("ProductName");
        table.addColumn("Company Name");
        table.addColumn("Availability");
        table.addColumn("Selling Price");
        table.addColumn("Stock Balance");
        table.addColumn("Expiry");
        table.addColumn("Input Date");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql:///BackEnd","root","oppylala2525");
            String sql = "SELECT * FROM `Stock` Where productname LIKE '%" + productreceive.getSelectedItem()+"%'";
            Statement s = con.createStatement();
            ResultSet R = s.executeQuery(sql);
            while(R.next()){
                table.addRow(new Object[]{                 
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),   
                    R.getString(5),
                    R.getString(6),
                    R.getString(7),
                    R.getString(8)
                        
                });
            }         
           stockbalancetable.setModel(table);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }
     
     private void searchOrderDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(receivefrom.getDate().getTime());
        String date1 = sdf.format(receiveto.getDate().getTime());
   try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///BackEnd", "root", "oppylala2525");
            String sql = "SELECT * FROM `StockBalance` WHERE `inputDate` BETWEEN ? AND ? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setDate(1, convertUtilDateToSqlDate(receivefrom.getDate()));
            pst.setDate(2, convertUtilDateToSqlDate(receiveto.getDate()));
          
            ResultSet rs = pst.executeQuery();
            stockbalancetable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        stockbalancetable = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        receiveto = new com.toedter.calendar.JDateChooser();
        receivefrom = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        productreceive = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        stockbal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stockbalancetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(stockbalancetable);

        jPanel13.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1130, 490));

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Bookman Old Style", 0, 16)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 255));
        jButton10.setText("Display All");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 130, 50));

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 255));
        jButton11.setText(">>");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jButton11.setPreferredSize(new java.awt.Dimension(41, 21));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 130, 50));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 255));
        jLabel19.setText("Search With Dates");
        jPanel14.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("To");
        jPanel14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));
        jPanel14.add(receiveto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 180, -1));
        jPanel14.add(receivefrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 180, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 255));
        jLabel21.setText("From");
        jPanel14.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 480, 70));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productreceive.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N
        productreceive.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Productname" }));
        productreceive.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productreceiveItemStateChanged(evt);
            }
        });
        jPanel15.add(productreceive, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 255));
        jLabel22.setText("Search by Productname");
        jPanel15.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel13.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 240, 70));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Bookman Old Style", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 255));
        jButton2.setText("Search");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 130, 60));

        stockbal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        stockbal.setForeground(new java.awt.Color(0, 0, 255));
        stockbal.setText("jLabel1");
        jPanel13.add(stockbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Total Store Value :");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, -1));

        jTabbedPane1.addTab("Stock Balance", jPanel12);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 1170, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        displayStockBalance();
        calculateReceive();
        displayLowQuantity();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        this.dispose();
        new Index.Mainpage().setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void productreceiveItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productreceiveItemStateChanged
        searchStockBalancebyName();
        calculateReceive();
    }//GEN-LAST:event_productreceiveItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if(receivefrom.getDate()==null){
            JOptionPane.showMessageDialog(this, "Please Select the Dates");
        }
        else if(receiveto.getDate()==null){
            JOptionPane.showMessageDialog(this, "Please Select the Dates");
        }else{
        searchOrderDate();
        calculateReceive();
        }
         productreceive.setSelectedItem("Productname");
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(StockBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new StockBalance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> productreceive;
    private com.toedter.calendar.JDateChooser receivefrom;
    private com.toedter.calendar.JDateChooser receiveto;
    private javax.swing.JLabel stockbal;
    private javax.swing.JTable stockbalancetable;
    // End of variables declaration//GEN-END:variables
}
