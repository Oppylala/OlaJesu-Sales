package Records;

import ReceiveStock.*;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;

/*
 * @author Oppylala
 */
public class ProductReport extends javax.swing.JFrame {

    public ProductReport() {
        initComponents();
        setLocationRelativeTo(null);
       fillOutlet();
       connetConnection();
       outletname.setSelectedItem(Supply.outletname.getSelectedItem());
       showDate();
       displayDailySalesReport();
       
    }
    
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
     if(date !=null){
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
             return sqlDate;}
     return null;
    }
    
     Connection con;   
     public void connetConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/OlaJesuData", "root", "oppylala2525");
        } catch (SQLException ex) {
            Logger.getLogger(ProductReport.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyy ");//("MM - dd - yyyy");      
        supplyfrom.setDate(d);
        supplyto.setDate(d);
    }
     private void fillOutlet(){
     try{
  Class.forName("com.mysql.jdbc.Driver");
  Connection con = DriverManager.getConnection("jdbc:mysql:///OlaJesuData", "root", "oppylala2525");
      String sql= "select distinct Outletname from Outlets order by Outletname";
      PreparedStatement pst=con.prepareStatement(sql);
      ResultSet rs=pst.executeQuery();
      while(rs.next()){
          String add=rs.getString("Outletname");
          outletname.addItem(add);      
         }
        }catch(Exception  ex){
           JOptionPane.showMessageDialog(this,ex); 
        } 
      }
   // Generating Daily Sales  Report Here ..............................//
       private void displayDailySalesReport(){
             SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
         String dfrom = date1.format(supplyfrom.getDate());
         
          SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
         String dto = date2.format(supplyto.getDate());
         String name = outletname.getSelectedItem().toString();
         
         HashMap a = new HashMap();
         a.put("supplyfrom", dfrom);
         a.put("supplyto", dto);
         a.put("supplyoutlet",name);
         
         supplywaybilltable.removeAll();
         supplywaybilltable.repaint();
         supplywaybilltable.revalidate();
          try{         
           JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Oppylala\\Documents\\NetBeansProjects\\OlaJesuEnterprises\\src\\Supply\\Waybill.jrxml");
           JasperReport jreport = JasperCompileManager.compileReport(jdesign);
           JasperPrint jprint = JasperFillManager.fillReport(jreport, a, con);
           JRViewer v = new JRViewer(jprint);
           supplywaybilltable.setLayout(new BorderLayout());
           supplywaybilltable.add(v);
     
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        supplyto = new com.toedter.calendar.JDateChooser();
        supplyfrom = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        outletname = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        supplywaybilltable = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 255));
        jButton13.setText(">>");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jButton13.setPreferredSize(new java.awt.Dimension(41, 21));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 120, 50));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Prestige12 BT", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 255));
        jLabel23.setText("Search With Dates");
        jPanel17.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 255));
        jLabel24.setText("To");
        jPanel17.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));
        jPanel17.add(supplyto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 180, -1));
        jPanel17.add(supplyfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 180, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 255));
        jLabel25.setText("From");
        jPanel17.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 480, 60));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        outletname.setFont(new java.awt.Font("Prestige12 BT", 0, 14)); // NOI18N
        outletname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Outletname" }));
        outletname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                outletnameItemStateChanged(evt);
            }
        });
        jPanel18.add(outletname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 255));
        jLabel26.setText("Search by Productname");
        jPanel18.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 230, 60));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Bookman Old Style", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 255));
        jButton3.setText("Search");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 160, 60));

        supplywaybilltable.setBackground(new java.awt.Color(255, 255, 255));
        supplywaybilltable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(supplywaybilltable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1080, 530));

        jTabbedPane1.addTab("Product Reports", jPanel16);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
     this.dispose();
     new Supply().setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void outletnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_outletnameItemStateChanged
       
    }//GEN-LAST:event_outletnameItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    displayDailySalesReport();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ProductReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> outletname;
    private com.toedter.calendar.JDateChooser supplyfrom;
    private com.toedter.calendar.JDateChooser supplyto;
    private javax.swing.JPanel supplywaybilltable;
    // End of variables declaration//GEN-END:variables
}
