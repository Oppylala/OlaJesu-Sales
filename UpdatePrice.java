package Products;

import Index.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * @author Oppylala
 */
public class UpdatePrice extends javax.swing.JFrame {

    public UpdatePrice() {
        initComponents();
        connetConnection();
        stockvalue.setVisible(false);
        paidbal.setVisible(false);
       user.setText(Account.CreateAccount.user.getText());
    }
     Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdatePrice.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(UpdatePrice.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
        if (date != null) {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
        }
        return null;
    }

   

    private void deleteProduct() {
        int P = JOptionPane.showConfirmDialog(null, " Are you sure you want to delete the Product ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (P == 0) {
            try {
                String sql = "delete from Products where ProductId LIKE '%" + productID.getText() + "%'";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Delete Successully", "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        } else {

        }
    }

    private void updateStockBalance() {
        String product = productname.getText();
        int a = Integer.parseInt(availability.getText());
        int c = Integer.parseInt(newprice.getText());
        int selltotal = a * c;     
            try {
                PreparedStatement update = con.prepareStatement("UPDATE `Stock` SET `Rate`='" + Integer.parseInt(newprice.getText()) + "' ,`StockValue`='" + selltotal + "'  WHERE `productname` = '" + product + "'");
                update.execute();
             //   JOptionPane.showMessageDialog(this, "Price Updated Successfully", "Ilamin Global Enterprises", JOptionPane.INFORMATION_MESSAGE);
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);    
        }
    }
       private void updatePaymentBal(){  
        int Former = Integer.parseInt(formerprice.getText());
        int New = Integer.parseInt(newprice.getText());
        int Qty = Integer.parseInt(availability.getText());
        int FormerBalance = Integer.parseInt(paidbal.getText());
        int stock = Integer.parseInt(stockvalue.getText());
    if(New > Former){
        int TotalBal = New - Former;
        int Total = TotalBal * Qty;
            int StockValue = Total + stock;
            int BalanceValue = StockValue - FormerBalance;
             try{
           String sql = ("UPDATE `paymentbalance` SET `StockValue`= "+ StockValue +" , `Balance` = "+BalanceValue+" WHERE `Outletname` LIKE '%"+ outletname.getText() +"%'");                     
           PreparedStatement pst = con.prepareStatement(sql);
           pst.execute();
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           } 
    }
    else{
        int TotalBal1 = Former - New;
        int Total = TotalBal1 * Qty;
            int StockValue =   stock - Total;
             int BalanceValue = StockValue - FormerBalance;
             try{
           String sql = ("UPDATE `paymentbalance` SET `StockValue`= "+ StockValue +" , `Balance` = "+BalanceValue+"  WHERE `Outletname` LIKE '%"+ outletname.getText() +"%'");                     
           PreparedStatement pst = con.prepareStatement(sql);
           pst.execute();
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           } 
    }
  
          
        }      
            
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        createDrugJButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        availability = new javax.swing.JTextField();
        formerprice = new javax.swing.JTextField();
        newprice = new javax.swing.JTextField();
        productname = new javax.swing.JTextField();
        productID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        stockvalue = new javax.swing.JTextField();
        paidbal = new javax.swing.JTextField();
        outletname = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
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

        jLabel1.setFont(new java.awt.Font("Prestige12 BT", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 0));
        jLabel1.setText("Update Price");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        createDrugJButton1.setBackground(new java.awt.Color(0, 0, 0));
        createDrugJButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        createDrugJButton1.setForeground(new java.awt.Color(255, 255, 255));
        createDrugJButton1.setText("Update Price");
        createDrugJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDrugJButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(createDrugJButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 690, 60));

        jPanel2.setBackground(new java.awt.Color(60, 144, 11));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Product ID");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Product Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Former Price");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("New Price");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        availability.setEditable(false);
        availability.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                availabilityKeyTyped(evt);
            }
        });
        jPanel2.add(availability, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 50, -1));

        formerprice.setEditable(false);
        formerprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formerpriceKeyTyped(evt);
            }
        });
        jPanel2.add(formerprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 43, -1));
        jPanel2.add(newprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 50, -1));
        jPanel2.add(productname, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 130, -1));
        jPanel2.add(productID, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 40, -1));

        jLabel11.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Quantity");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        stockvalue.setText("0");
        jPanel2.add(stockvalue, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 110, -1));

        paidbal.setText("0");
        jPanel2.add(paidbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 70, -1));

        outletname.setForeground(new java.awt.Color(255, 255, 255));
        outletname.setText("MainShop");
        jPanel2.add(outletname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 690, 220));

        user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        user.setForeground(new java.awt.Color(0, 108, 0));
        user.setText("jLabel13");
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("<<  Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 290));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 160, 310));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 690, 60));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new ManagePrice().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void availabilityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_availabilityKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_availabilityKeyTyped

    private void createDrugJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDrugJButton1ActionPerformed
        if(newprice.getText().equals("")){
            JOptionPane.showMessageDialog(this,"New Price is Required!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
        updateStockBalance();        
        this.dispose();
        new Products.ManagePrice().setVisible(true);
        }
    }//GEN-LAST:event_createDrugJButton1ActionPerformed

    private void formerpriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formerpriceKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_formerpriceKeyTyped

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
            java.util.logging.Logger.getLogger(UpdatePrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new UpdatePrice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField availability;
    private javax.swing.JButton createDrugJButton1;
    public static javax.swing.JTextField formerprice;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public static javax.swing.JLabel loginas;
    public static javax.swing.JLabel loginas1;
    public static javax.swing.JTextField newprice;
    private javax.swing.JLabel outletname;
    public static javax.swing.JTextField paidbal;
    public static javax.swing.JTextField productID;
    public static javax.swing.JTextField productname;
    public static javax.swing.JTextField stockvalue;
    private javax.swing.JLabel user;
    public static javax.swing.JLabel usern;
    public static javax.swing.JLabel usern1;
    // End of variables declaration//GEN-END:variables
}
