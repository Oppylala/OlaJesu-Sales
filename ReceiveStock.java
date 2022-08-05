package ReceiveStock;


import java.awt.Color;
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
import net.proteanit.sql.DbUtils;

/*
 * @author Oppylala
 */
public class ReceiveStock extends javax.swing.JFrame {

    public ReceiveStock() {
        initComponents();
        setLocationRelativeTo(null);
        connetConnection();
        receivedID();
        fillCompany();
        displayStock();
        hideSome();
        showDate();
        showTime();
        fillProducts();
        usern.setText(Account.CreateAccount.user.getText());
    }
     Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceiveStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveStock.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
  void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyy ");//("MM - dd - yyyy");      
        Date2.setText(s.format(d));
        inputDate.setDate(d);
    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh: mm: ss");
                Time2.setText(s.format(d));
            }

        }
        ).start();

    }
    
    private void hideSome(){
        qty.setVisible(false);
        total.setVisible(false);
        qty2.setVisible(false);
        bal2.setVisible(false);
    }
     private void displayStock(){
        try{
            String nom = "";
           String sql = "SELECT * FROM `Stock` WHERE StockId LIKE ?";
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, "%"+ nom.toUpperCase()+"%");
           ResultSet rs = pst.executeQuery();
           othertable.setModel(DbUtils.resultSetToTableModel(rs));
           if(rs!=null&&rs.next()){
               rs.beforeFirst();
               othertable.setModel(DbUtils.resultSetToTableModel(rs));
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
     private void fillProducts(){
     try{
      String sql= "select distinct productname from products order by '%" +companyname.getSelectedItem()+ "%'" ;
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("Productname");
          product.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
}
     private void fillCompany(){
     try{
      String sql= "select distinct companyname from products order by productid";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("Companyname");
          companyname.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
    }
     void showSalesDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyy");//("MM - dd - yyyy");
  //      inputDate.setText(s.format(d));
    }
    private void receivedID(){
    try{
        String sql = "SELECT max(`Receiveid`) FROM `ReceiveStock` ";
        Statement search = con.createStatement();
        ResultSet form = search.executeQuery(sql);
        int frm = 100;
        while(form.next()){
            frm = form.getInt(1);
       }
        orderid.setText(String.valueOf(frm + 1));
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
   
 private void addStockReceive(){  
        double a = Double.parseDouble(quantity.getText());
        double c = Double.parseDouble(sellingprice.getText());
        double stockvalue = a * c; 
        double finalstock = Math.round(stockvalue * 100.0)/100.0;
        if(quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Product name is Requied!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            
        }
        else if(companyname.getSelectedItem().equals("Company name")){
            JOptionPane.showMessageDialog(this, "Company Name is Required!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
          else if(product.getSelectedItem().equals("Products")){
            JOptionPane.showMessageDialog(this, "Selected Product Name is Require!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);           
        }    
          else{
            try{
           String sql = ("INSERT INTO `ReceiveStock` VALUE(?,?,?,?,?,?,?,?,?)");                     
           PreparedStatement pst = con.prepareStatement(sql);                          
           pst.setInt(1, Integer.parseInt(orderid.getText().toString()));        
           pst.setString(2, product.getSelectedItem().toString());
           pst.setString(3, companyname.getSelectedItem().toString());
           pst.setDouble(4, Double.parseDouble(quantity.getText()));
           pst.setDouble(5, c);     
           pst.setDouble(6, finalstock);
           pst.setDate(7, convertUtilDateToSqlDate(inputDate.getDate()));
           pst.setDate(8, convertUtilDateToSqlDate(expiryDate.getDate()));  
           pst.setString(9, usern.getText());
 
           pst.executeUpdate();
                                     
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
        }   
    }
 
 private void addStock(){  
        double a = Double.parseDouble(quantity.getText());
        double c = Double.parseDouble(sellingprice.getText());
        double stockValue = a * c;
        double finalstock = Math.round(stockValue * 100.0)/100.0;
       
        if(quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Product name is Requied!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            
        }
        else if(companyname.getSelectedItem().equals("Company name")){
            JOptionPane.showMessageDialog(this, "Company Name is Required!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
          else if(product.getSelectedItem().equals("Products")){
            JOptionPane.showMessageDialog(this, "Selected Product Name is Require!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);           
        }    
          else{
            try{
           String sql = ("INSERT INTO `Stock` VALUE(?,?,?,?,?,?,?,?)");                     
           PreparedStatement pst = con.prepareStatement(sql);                          
           pst.setInt(1, Integer.parseInt(orderid.getText().toString()));  
            pst.setString(2, companyname.getSelectedItem().toString());
           pst.setString(3, product.getSelectedItem().toString());        
           pst.setInt(4, Integer.parseInt(quantity.getText()));
           pst.setDouble(5, c);     
           pst.setDouble(6, finalstock);
           pst.setDate(7, convertUtilDateToSqlDate(expiryDate.getDate()));
           pst.setString(8, usern.getText());
           
                     pst.executeUpdate();
                         
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
        }   
    }
 private void updateStock(){  
        String Productname = product.getSelectedItem().toString();
        double a = Double.parseDouble(qty.getText());
        double c = Double.parseDouble(sellingprice.getText());
        double StockValu = a * c;
        double finalstock = Math.round(StockValu * 100.0)/100.0;
            try{
           String sql = ("UPDATE `Stock` SET `Quantity`='"+ qty.getText()+"',"
                   + "`Rate`='"+ Double.parseDouble(sellingprice.getText())+"', `StockValue`='"+finalstock+"', "
                   + "`ExpiryDate`='"+convertUtilDateToSqlDate(expiryDate.getDate())+"' "
                   + "WHERE `productname` = '" + Productname+"'");               
           PreparedStatement pst = con.prepareStatement(sql);
           pst.execute();
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
        }      
    private void searchStock(){
        if(quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Quantity is Requied!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);          
        }
        else if(companyname.getSelectedItem().equals("Company Name")){
            JOptionPane.showMessageDialog(this, "Company Name is Required!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
          else if(product.getSelectedItem().equals("Products")){
            JOptionPane.showMessageDialog(this, "Selected Product Name is Require!", "OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);           
        }    
          else{
            try{
            String sql = "SELECT * FROM `Stock` WHERE `Companyname` LIKE '%" + companyname.getSelectedItem() + "%' AND `ProductName` LIKE '%"+product.getSelectedItem()+"%'";
            PreparedStatement pst=con.prepareStatement(sql);         
            ResultSet rs=  pst.executeQuery();
            if(rs.next()){
                qty2.setText(rs.getString("Quantity")); 
                double q1 = Double.parseDouble(quantity.getText());
                double q2 = Double.parseDouble(qty2.getText());
                qty.setText(String.valueOf(q1+q2));
                qty2.setText("0");
                updateStock();
                addStockReceive();
                this.dispose();
                new ReceiveStock().setVisible(true);
           }else{
                addStock();
                addStockReceive();
                this.dispose();
                new ReceiveStock().setVisible(true);
            }          
             rs.close();    
        }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error Occur " + e.getMessage(), "OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE); 
        
         }
     
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        product = new javax.swing.JComboBox<>();
        companyname = new javax.swing.JComboBox<>();
        orderid = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        inputDate = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        expiryDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        sellingprice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        qty2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        bal2 = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnBack1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        othertable = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Time2 = new javax.swing.JLabel();
        Date2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        usern = new javax.swing.JLabel();
        loginas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Prestige12 BT", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 133, 6));
        jLabel26.setText("STOCKS RECEIVED");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 230, 30));

        jPanel13.setBackground(new java.awt.Color(0, 124, 0));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Receive Date");
        jPanel13.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, 20));

        jLabel21.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Company Name");
        jPanel13.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 30));

        jLabel22.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Product Name");
        jPanel13.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 30));

        jLabel23.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Quantity");
        jPanel13.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 30));

        product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Products" }));
        product.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productItemStateChanged(evt);
            }
        });
        jPanel13.add(product, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 150, -1));

        companyname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Company Name" }));
        companyname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                companynameItemStateChanged(evt);
            }
        });
        jPanel13.add(companyname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 150, -1));

        orderid.setEditable(false);
        orderid.setEnabled(false);
        orderid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                orderidKeyTyped(evt);
            }
        });
        jPanel13.add(orderid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 60, -1));

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantityKeyTyped(evt);
            }
        });
        jPanel13.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 60, -1));
        jPanel13.add(inputDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 150, -1));

        jLabel25.setFont(new java.awt.Font("Prestige12 BT", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Receive Id");
        jPanel13.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 34));
        jPanel13.add(expiryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 150, -1));

        jLabel7.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Expiry Date");
        jPanel13.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        sellingprice.setText("0");
        sellingprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellingpriceActionPerformed(evt);
            }
        });
        jPanel13.add(sellingprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 60, -1));

        jLabel10.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Rate");
        jPanel13.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        qty.setText("0");
        jPanel13.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 60, -1));

        qty2.setText("0");
        jPanel13.add(qty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 70, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SAVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        jPanel13.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 680, 40));
        jPanel13.add(bal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 40, -1));
        jPanel13.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 60, -1));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 770, 260));

        jPanel3.setBackground(new java.awt.Color(0, 74, 14));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack1.setBackground(new java.awt.Color(255, 255, 255));
        btnBack1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        btnBack1.setForeground(new java.awt.Color(255, 0, 0));
        btnBack1.setText("<< BACK");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnBack1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 270));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 160, 290));

        othertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(othertable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 950, 230));

        jLabel27.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 133, 6));
        jLabel27.setText("OLA-JESU NIG. ENTERPRISES");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 30));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Time2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Time2.setForeground(new java.awt.Color(255, 255, 255));
        Time2.setText("jLabel10");
        jPanel4.add(Time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 120, -1));

        Date2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Date2.setForeground(new java.awt.Color(255, 255, 255));
        Date2.setText("jLabel9");
        jPanel4.add(Date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 300, 40));

        jPanel6.setBackground(new java.awt.Color(0, 88, 12));
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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 230, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productItemStateChanged
        searchPrice();
    }//GEN-LAST:event_productItemStateChanged
        private void searchPrice(){
            try{
             String sql = "SELECT Price FROM `Products` Where `Productname` = '"+product.getSelectedItem()+"'";
        Statement search = con.createStatement();
        ResultSet form = search.executeQuery(sql);
        while(form.next()){
           sellingprice.setText(form.getString("Price"));
       }  
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e);
            }
        }
    private void companynameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_companynameItemStateChanged
     //   fillProducts();
    }//GEN-LAST:event_companynameItemStateChanged

    private void orderidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_orderidKeyTyped

    private void quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if(expiryDate.getDate()==null){
        JOptionPane.showMessageDialog(this, "Expiration Date is Required!","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
    }
      else{
          searchStock();
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        this.dispose();
        new Index.Mainpage().setVisible(true);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void sellingpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellingpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sellingpriceActionPerformed

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
            java.util.logging.Logger.getLogger(ReceiveStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiveStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiveStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiveStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ReceiveStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date2;
    private javax.swing.JLabel Time2;
    private javax.swing.JTextField bal2;
    private javax.swing.JButton btnBack1;
    private javax.swing.JComboBox<String> companyname;
    private com.toedter.calendar.JDateChooser expiryDate;
    private com.toedter.calendar.JDateChooser inputDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel loginas;
    private javax.swing.JTextField orderid;
    public static javax.swing.JTable othertable;
    private javax.swing.JComboBox<String> product;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty2;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField sellingprice;
    private javax.swing.JTextField total;
    public static javax.swing.JLabel usern;
    // End of variables declaration//GEN-END:variables
}
