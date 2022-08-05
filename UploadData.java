
package UploadData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Oppylala
 */
public class UploadData extends javax.swing.JFrame {

    String filename;
    public UploadData() {
        initComponents();
        connectionCon();
        setLocationRelativeTo(null);
      //  usern.setText(Account.CreateAccount.user.getText());
    }

    Connection con;
    public void connectionCon(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql:///BackEnd", "root", "oppylala2525");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        usern = new javax.swing.JLabel();
        loginas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        browseproduct = new javax.swing.JButton();
        productbutton = new javax.swing.JButton();
        path = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 95, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Prestige12 BT", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Upload Data Here");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 250, 34));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("OlaJesu Nig. Enterprises");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText(">>");
        jButton7.setPreferredSize(new java.awt.Dimension(41, 21));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 100, 40));

        jPanel6.setBackground(new java.awt.Color(0, 72, 10));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usern.setFont(new java.awt.Font("HandelGotDLig", 0, 14)); // NOI18N
        usern.setForeground(new java.awt.Color(255, 255, 255));
        usern.setText("Username");
        jPanel6.add(usern, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, 40));

        loginas.setFont(new java.awt.Font("HandelGotDLig", 0, 14)); // NOI18N
        loginas.setForeground(new java.awt.Color(255, 255, 255));
        loginas.setText("User:");
        jPanel6.add(loginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 970, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        browseproduct.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        browseproduct.setText("Browse Products");
        browseproduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseproductActionPerformed(evt);
            }
        });
        jPanel2.add(browseproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 200, 40));

        productbutton.setBackground(new java.awt.Color(2, 126, 18));
        productbutton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        productbutton.setForeground(new java.awt.Color(255, 255, 255));
        productbutton.setText("Upload Product File");
        productbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(productbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 600, 60));
        jPanel2.add(path, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 600, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 970, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseproductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseproductActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("files","csv","xls");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        path.setText(filename);

    }//GEN-LAST:event_browseproductActionPerformed

    private void productbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productbuttonActionPerformed
        if(path.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Browse the File Source!","OlaJesu Nig Enterprises",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
        try{
            BufferedReader read = new BufferedReader(new FileReader(filename));
            String data;
            while((data = read.readLine()) !=null){
                String[] value = data.split(",");
                try{
                    PreparedStatement stmt = con.prepareStatement("INSERT INTO `Products` VALUES (?,?,?,?,?,?)");
                    stmt.setString(1, value[0]);
                    stmt.setString(2, value[1]);    
                    stmt.setString(3, value[2]);
                    stmt.setString(4, value[3]); 
                    stmt.setString(5, value[4]);
                    stmt.setString(6, value[5]);
                    stmt.executeUpdate();                  
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, e);
                }         
            }
             JOptionPane.showMessageDialog(this, "Products Uploaded Successfully", "OlaJesu Nig.Enterprises",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new UploadData().setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
        }
    }//GEN-LAST:event_productbuttonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.dispose();
        new Index.Mainpage().setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(UploadData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseproduct;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    public static javax.swing.JLabel loginas;
    private javax.swing.JTextField path;
    private javax.swing.JButton productbutton;
    public static javax.swing.JLabel usern;
    // End of variables declaration//GEN-END:variables
}
