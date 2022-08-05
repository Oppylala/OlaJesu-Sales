package ReceiveStock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/*
  @author Oppylala
 */
public class Supply extends javax.swing.JFrame {

    DefaultTableModel transfermode = new DefaultTableModel();
    public Supply() {
        initComponents();
        setLocationRelativeTo(null);
        connetConnection();
        showTime();
        showDate();
        fillProduct();
        fillOutlet();
        SupplyID();
        hideSome();
        usern.setText(Account.CreateAccount.user.getText());
    }
     Connection con;
    public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supply.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/BackEnd", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(Supply.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    private void hideSome(){
        prebal.setVisible(false);
        outletqty.setVisible(false);
        quantitypayment.setVisible(false);
        stockqty.setVisible(false);
        stockprice2.setVisible(false);
        stockupdateqty.setVisible(false);
        productname1.setVisible(false);
        Totalqty.setVisible(false);
        Totalamount.setVisible(false);
        pricefromtable.setVisible(false);
        qtyfromtable.setVisible(false);
        qtyfromdata.setVisible(false);
        product.setVisible(false);
    }
    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM - dd - yyyy ");//("EEE, d MMM yyy");
        Date.setText(s.format(d));
        supplydate.setDate(d);
    }

    void showTime() {
        new Timer(0, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh: mm: ss");
                Time.setText(s.format(d));
                
            }
        }
        ).start();
    }
      public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
     if(date !=null){
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             return sqlDate;}
     return null;
    }
      private void SupplyID(){
    try{
        String sql = "SELECT max(`Supplyid`) FROM `SupplyStock` ";
        Statement search = con.createStatement();
        ResultSet form = search.executeQuery(sql);
        int frm = 100;
        while(form.next()){
            frm = form.getInt(1);
       }
        stockid.setText(String.valueOf(frm + 1));
    }catch(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage());
    }  
}
    private void fillProduct(){
     try{
      String sql= "select distinct Productname from Stock order by Productname";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("productname");
          productname.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
      }
      private void fillOutlet(){
     try{
      String sql= "select distinct Shopname from Shops order by Shopname";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("Shopname");
          outletname.addItem(add);      
         }
    
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
      }
        private void fillReps(){
     try{
      String sql= "select distinct Representative from Products order by Representative";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("Representative");
          outletname.addItem(add);         
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
      }
      
         private void searchLocation(){
             String Outlets = outletname.getSelectedItem().toString();
      try{
            String sql = "SELECT * FROM `Shops` WHERE Shopname = '" + Outlets+"'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet R = pst.executeQuery();
           while(R.next()){
              headofshop.setText(R.getString("Director"));            
                  }
          }catch(Exception e){
              JOptionPane.showMessageDialog(this, e);
          }
  }
         
          
         // Seearch Product Details Here and Fill the Price
         public void searchProductDetails(){
             String Products = productname.getSelectedItem().toString();
          try{
            String sql = "SELECT * FROM `Stock` WHERE productname = '" + Products+"'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet R = pst.executeQuery();
           while(R.next()){
               productname1.setText(R.getString("productname"));
              price.setText(R.getString("Rate"));
              stockprice2.setText(R.getString("Rate"));
              stockqty.setText(R.getString("Quantity"));
                  }
           
          }catch(Exception e){
              JOptionPane.showMessageDialog(this, e);
          }
    }
         // Add The Details to the Table Here.....
         public void addTable(){
             for(int j=0; j<supplytable.getRowCount(); j++){
            if(productname.getSelectedItem().equals(supplytable.getValueAt(j, 1)) && quantity.getText().equals(supplytable.getValueAt(j, 2)) ){
                JOptionPane.showMessageDialog(this, "The Product is already added!");
                return;
             } }
        
        if(productname.getSelectedItem().equals("") && quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Fill the empty spaces");
        } 
        else{
          for(int l=0; l<supplytable.getRowCount(); l++){
            if(productname.getSelectedItem().equals(supplytable.getValueAt(l, 1)) && quantity.getText().equals(supplytable.getValueAt(l, 2))){
                JOptionPane.showMessageDialog(this, "The Product is already added!");
             
            amount.setText("");
            price.setText("");
            productname.setSelectedItem("Products");
           quantity.setText("");
            return;
             }    
          }
            transfermode = (DefaultTableModel)supplytable.getModel();
            transfermode.addRow(new Object[]{
                stockid.getText(),
                productname.getSelectedItem(),
                quantity.getText(),
                price.getText(),
                amount.getText(),
                usern.getText()
        });
            double sum1 = 0;
        for(int i=0; i<supplytable.getRowCount(); i++){
            sum1 = sum1 + Double.parseDouble(supplytable.getValueAt(i, 4).toString());
            totalamount.setText(""+sum1);
            amount.setText("");
            price.setText("");
            productname.setSelectedItem("Products"); 
            quantity.setText("");
        }
           upDate();
    }
         }
         
         // Update Stock Here From the Table
          private void upDate(){
                double Price1 = Double.parseDouble(stockprice2.getText());
               double qty1 = Double.parseDouble(stockupdateqty.getText());
               double total = Price1 * qty1;   
               String Productname = productname1.getText().toString();
          try{
           PreparedStatement update = con.prepareStatement("UPDATE `Stock` SET `Quantity`='"+qty1+"',"
                   + "`StockValue`='"+ total +"' WHERE `Productname` = '" + Productname +"'");
           update.execute();          
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
        }
          
          // Remove Data from Table Here...
          public void removeData(){
               String Productname = product.getText().toString();
               double Price = Double.parseDouble(pricefromtable.getText());
               int StockUpdate = Integer.parseInt(Totalamount.getText());
               if(supplytable.getSelectedRow() !=-1){          
         try{
           PreparedStatement update = con.prepareStatement("UPDATE `Stock` SET `Quantity`="+Integer.parseInt(Totalqty.getText())+","
                   + "`StockValue`="+ StockUpdate +" WHERE `Productname` = '" + Productname +"'");
           transfermode.removeRow(supplytable.getSelectedRow());
           update.execute();
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }  
        }
        else{
            JOptionPane.showMessageDialog(this, "You are not select any Row!");
        }
        double sum = 0;
        for(int i=0; i<supplytable.getRowCount(); i++){
            sum = sum + Double.parseDouble(supplytable.getValueAt(i, 4).toString());
        }
        totalamount.setText(Double.toString(sum));
    }
          
          public void addSupplyStock(){
         DefaultTableModel mod = (DefaultTableModel)supplytable.getModel();              
        try{
           Statement st = con.createStatement();
           for(int i=0; i<mod.getRowCount(); i++){
               String ID = mod.getValueAt(i, 0).toString();
               String Name = mod.getValueAt(i, 1).toString();
               String Qty = mod.getValueAt(i, 2).toString();
               String Price = mod.getValueAt(i, 3).toString();
               String Amount = mod.getValueAt(i, 4).toString();
               String sql1 = "INSERT INTO `supplyStock`(`SupplyId`, `ShopName`, `Director`, `Productname`, `Quantity`, `Rate`, `StockValue`, `SupplyDate`, `Agent`) "
                       + "VALUES ('"+ ID +"','"+outletname.getSelectedItem()+"',"
                       + "'"+ headofshop.getText()+"','"+ Name+"','"+ Qty+"','"+ Price+"','"+ Amount +"',"
                       + "'"+ convertUtilDateToSqlDate(supplydate.getDate()) +"','"+ usern.getText() +"')";
               st.addBatch(sql1);
               
           }
               int[] rowsInserted = st.executeBatch(); 
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, e);
           }
          }
            
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        amount = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        price = new javax.swing.JTextField();
        removecredit = new javax.swing.JButton();
        totalamount = new javax.swing.JTextField();
        stockid = new javax.swing.JTextField();
        stockqty = new javax.swing.JTextField();
        stockupdateqty = new javax.swing.JTextField();
        stockprice2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        supplydate = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        usern = new javax.swing.JLabel();
        creditloginas = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        Date = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        productname1 = new javax.swing.JTextField();
        productname = new javax.swing.JComboBox<>();
        outletname = new javax.swing.JComboBox<>();
        headofshop = new javax.swing.JTextField();
        OK = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        quantitypayment = new javax.swing.JTextField();
        outletqty = new javax.swing.JTextField();
        prebal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplytable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Totalqty = new javax.swing.JTextField();
        Totalamount = new javax.swing.JTextField();
        pricefromtable = new javax.swing.JTextField();
        qtyfromtable = new javax.swing.JTextField();
        qtyfromdata = new javax.swing.JTextField();
        product = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 100, -1));

        jButton7.setBackground(new java.awt.Color(51, 119, 14));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("ADD/INSERT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, 200, 50));
        jPanel3.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 90, -1));

        removecredit.setBackground(new java.awt.Color(255, 5, 5));
        removecredit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        removecredit.setForeground(new java.awt.Color(255, 255, 255));
        removecredit.setText("REMOVE");
        removecredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removecreditActionPerformed(evt);
            }
        });
        jPanel3.add(removecredit, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, 200, 40));

        totalamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalamountKeyTyped(evt);
            }
        });
        jPanel3.add(totalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 110, 30));

        stockid.setEditable(false);
        jPanel3.add(stockid, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 60, -1));

        stockqty.setText("0");
        stockqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockqtyActionPerformed(evt);
            }
        });
        jPanel3.add(stockqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 40, -1));
        jPanel3.add(stockupdateqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 30, -1));

        stockprice2.setText("0");
        jPanel3.add(stockprice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 40, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Location");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 100, -1));

        jPanel9.setBackground(new java.awt.Color(23, 140, 14));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Supply Date");
        jPanel9.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 80, -1));
        jPanel9.add(supplydate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 270, 60));

        jPanel11.setBackground(new java.awt.Color(23, 140, 14));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usern.setFont(new java.awt.Font("HandelGotDLig", 0, 14)); // NOI18N
        usern.setForeground(new java.awt.Color(255, 255, 255));
        usern.setText("Username");
        jPanel11.add(usern, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, 40));

        creditloginas.setFont(new java.awt.Font("HandelGotDLig", 0, 14)); // NOI18N
        creditloginas.setForeground(new java.awt.Color(255, 255, 255));
        creditloginas.setText("User:");
        jPanel11.add(creditloginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 230, 40));

        jButton12.setBackground(new java.awt.Color(0, 0, 0));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText(">>");
        jButton12.setPreferredSize(new java.awt.Dimension(41, 21));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 120, 40));

        jPanel10.setBackground(new java.awt.Color(23, 140, 14));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 255, 255));
        Date.setText("jLabel9");
        jPanel10.add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 160, -1));

        Time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Time.setForeground(new java.awt.Color(255, 255, 255));
        Time.setText("jLabel10");
        jPanel10.add(Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 42, 90, 20));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 190, 70));

        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
        });
        jPanel3.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 80, -1));

        jPanel4.setBackground(new java.awt.Color(0, 123, 11));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Name");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Qty");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Price");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Amount");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, -1, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total AMount");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 110, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 780, 30));
        jPanel3.add(productname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 110, -1));

        productname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Products" }));
        productname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productnameItemStateChanged(evt);
            }
        });
        productname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productnameActionPerformed(evt);
            }
        });
        jPanel3.add(productname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 150, -1));

        outletname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Outletname" }));
        outletname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outletnameItemStateChanged(evt);
            }
        });
        jPanel3.add(outletname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 170, -1));

        headofshop.setEditable(false);
        headofshop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                headofshopKeyReleased(evt);
            }
        });
        jPanel3.add(headofshop, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 220, -1));

        OK.setBackground(new java.awt.Color(0, 0, 0));
        OK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OK.setForeground(new java.awt.Color(255, 255, 255));
        OK.setText("SAVE & PRINT WAYBILL             >>");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        jPanel3.add(OK, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 1030, 50));

        jPanel14.setBackground(new java.awt.Color(23, 140, 14));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("CentSchbook Mono BT", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("OlaJesu Nig. Enterprises");
        jPanel14.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -2, 210, 30));

        jPanel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Outlet Name");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Stock TransferID");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        quantitypayment.setText("0");
        jPanel3.add(quantitypayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 50, -1));

        outletqty.setText("0");
        jPanel3.add(outletqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 70, -1));

        prebal.setText("0");
        jPanel3.add(prebal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 80, -1));

        supplytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SupplyId", "Product Name", "Quantity", "Price", "Amount", "Operator"
            }
        ));
        supplytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplytableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(supplytable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 1030, 330));

        jPanel1.setBackground(new java.awt.Color(0, 139, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 360, 100));
        jPanel3.add(Totalqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 460, 70, -1));
        jPanel3.add(Totalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 480, 70, -1));

        pricefromtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricefromtableActionPerformed(evt);
            }
        });
        jPanel3.add(pricefromtable, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 500, 60, -1));

        qtyfromtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyfromtableActionPerformed(evt);
            }
        });
        jPanel3.add(qtyfromtable, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 530, 60, -1));
        jPanel3.add(qtyfromdata, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 530, 60, -1));
        jPanel3.add(product, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 560, 100, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(outletname.getSelectedItem().equals("Outletname")){
            JOptionPane.showMessageDialog(this, "Select Outlet Name!", "Ola-Jesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(quantity.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Product Quantity is Required!", "Ola-Jesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }            
        else{   
            addTable();
           SupplyID();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void removecreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removecreditActionPerformed
        if(Totalqty.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Select the Product to Remove!","OlaJesu Nig. Enterprises", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
        removeData();
        price.setText("");
        quantity.setText("");
        Totalqty.setText("");
        Totalamount.setText("");
        pricefromtable.setText("");
        qtyfromtable.setText("");
        qtyfromdata.setText("");
        product.setText(""); 
        } 
    }//GEN-LAST:event_removecreditActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.dispose();
        new Index.Mainpage().setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
        int qty1 = Integer.parseInt(quantity.getText());
        int qty2 = Integer.parseInt(stockqty.getText());
        if(quantity.getText().equals("")){
       //     JOptionPane.showMessageDialog(this,"Enter Quantity","Ola-Jesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
            amount.setText("");
        }
        else if(qty1>qty2){
            JOptionPane.showMessageDialog(this, "Low Stock!  "+qty2+"  Remain","OlaJesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
             int qt = Integer.parseInt(quantity.getText());
        double p = Double.parseDouble(price.getText());
        double s = qt * p;
        
        amount.setText(String.format("%.2f",s));

        int Qt = Integer.parseInt(quantity.getText());
        int quanty = Integer.parseInt(stockqty.getText());

        int tot = quanty - Qt;

        stockupdateqty.setText(String.valueOf(tot));
        if(Integer.parseInt(stockupdateqty.getText())<0){
            JOptionPane.showMessageDialog(this, productname.getSelectedItem()+"   Quantity is Low! \n"+stockqty.getText()+" Left","Sales Management",JOptionPane.INFORMATION_MESSAGE);
            quantity.setText("0");
            amount.setText("");
        }
        }
       
    }//GEN-LAST:event_quantityKeyReleased

    private void productnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productnameItemStateChanged
         amount.setText("");
            price.setText("");
          // productname.setSelectedItem("Products");
           quantity.setText("");
        searchProductDetails();
    }//GEN-LAST:event_productnameItemStateChanged

    private void outletnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outletnameItemStateChanged
       searchLocation();
      
    }//GEN-LAST:event_outletnameItemStateChanged

    private void headofshopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_headofshopKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_headofshopKeyReleased

    private void stockqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockqtyActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
     JOptionPane.showMessageDialog(this, "Print Waybill?","Ola-Jesu Nig. Enterprises",JOptionPane.INFORMATION_MESSAGE);
    addSupplyStock();
    this.dispose();
    new Waybill().setVisible(true);
    }//GEN-LAST:event_OKActionPerformed

    private void productnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productnameActionPerformed

    private void totalamountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalamountKeyTyped
      
    }//GEN-LAST:event_totalamountKeyTyped

    private void supplytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplytableMouseClicked
     
       try{
            int row= supplytable.getSelectedRow();
            String table_click= supplytable.getModel().getValueAt(row, 1).toString();
            String sql= "select * from Stock where Productname LIKE '%" + table_click + "%'";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=  pst.executeQuery();
            if(rs.next()){               
                qtyfromdata.setText(rs.getString("Quantity"));              
 }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex);
        }  
        int i = supplytable.getSelectedRow();
        TableModel model = supplytable.getModel();
        qtyfromtable.setText(model.getValueAt(i, 2).toString());
        pricefromtable.setText(model.getValueAt(i, 3).toString());
        product.setText(model.getValueAt(i, 1).toString());
       
               int qtydata = Integer.parseInt(qtyfromdata.getText());
               int qtytable = Integer.parseInt(qtyfromtable.getText());
               int amount = Integer.parseInt(pricefromtable.getText()); 
               int qtysum = qtydata + qtytable;
                
                int totalamount = qtysum * amount;      
           Totalqty.setText(qtysum +""); 
           Totalamount.setText(totalamount+"");
    }//GEN-LAST:event_supplytableMouseClicked

    private void pricefromtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricefromtableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricefromtableActionPerformed

    private void qtyfromtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyfromtableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyfromtableActionPerformed
    private void displayData(){         
            for(int i=0; i<supplytable.getRowCount(); i++){
                int qt = Integer.parseInt(supplytable.getValueAt(i, 2).toString());
                int pric = Integer.parseInt(supplytable.getValueAt(i, 3).toString());
                quantity.setText(""+qt);  
                price.setText(""+pric);
            }
                
                
                int qt1 = Integer.parseInt(quantity.getText());
                int qt2 = Integer.parseInt(stockqty.getText());
                int Price = Integer.parseInt(price.getText());
                int update = Integer.parseInt(stockupdateqty.getText());
                
                
                int totalqty = qt1 + update;
                stockupdateqty.setText(String.valueOf(totalqty));    
                
                int amount = totalqty * Price;
                stockprice2.setText(String.valueOf(amount));
            
      }
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
            java.util.logging.Logger.getLogger(Supply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Supply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Supply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Supply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Supply().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JButton OK;
    private javax.swing.JLabel Time;
    private javax.swing.JTextField Totalamount;
    private javax.swing.JTextField Totalqty;
    private javax.swing.JTextField amount;
    public static javax.swing.JLabel creditloginas;
    private javax.swing.JTextField headofshop;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JComboBox<String> outletname;
    private javax.swing.JTextField outletqty;
    private javax.swing.JTextField prebal;
    private javax.swing.JTextField price;
    private javax.swing.JTextField pricefromtable;
    private javax.swing.JTextField product;
    private javax.swing.JComboBox<String> productname;
    private javax.swing.JTextField productname1;
    private javax.swing.JTextField qtyfromdata;
    private javax.swing.JTextField qtyfromtable;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField quantitypayment;
    private javax.swing.JButton removecredit;
    private javax.swing.JTextField stockid;
    private javax.swing.JTextField stockprice2;
    private javax.swing.JTextField stockqty;
    private javax.swing.JTextField stockupdateqty;
    private com.toedter.calendar.JDateChooser supplydate;
    private javax.swing.JTable supplytable;
    private javax.swing.JTextField totalamount;
    public static javax.swing.JLabel usern;
    // End of variables declaration//GEN-END:variables
}
