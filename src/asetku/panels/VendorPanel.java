/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package asetku.panels;

import asetku.databases.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author freedcode
 */
public class VendorPanel extends javax.swing.JPanel {
    Connection connection = ConnectionManager.getInstance().getConnection();

    /**
     * Creates new form VendorPanel
     */
    public VendorPanel() {
        initComponents();
        initTabelVendor();
    }
    
    private void initTabelVendor() {
        Object[] header = new Object[]{"No.", "Nama Vendor", "Nomor Telepon", "Alamat Vendor"};
        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        tabelVendor.setModel(tableModel);
        String query = "SELECT * FROM vendor";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            int no = 1;
            while(result.next()) {
                String a = result.getString("nama");
                String b = result.getString("no_telepon");
                String c = result.getString("alamat");
                
                Object[] row = new Object[]{no, a, b, c};
                tableModel.addRow(row);
                no++;
            }
        } catch (SQLException e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelVendor = new javax.swing.JTable();

        jButton1.setText("Tambah Vendor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print");

        tabelVendor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelVendor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JTextField namaVendor = new JTextField();
        JTextField nomorTelepon = new JTextField();
        JTextArea alamatVendor = new JTextArea(5, 5);
        alamatVendor.setLineWrap(true);
        alamatVendor.setWrapStyleWord(true);
        
        JComponent[] inputs = new JComponent[]{
            new JLabel("Nama Vendor"),
            namaVendor,
            new JLabel("Nomor Telepon Vendor"),
            nomorTelepon,
            new JLabel("Alamat Vendor"),
            alamatVendor
        };
        int ok = JOptionPane.showConfirmDialog(this, inputs, "Tambah Data Vendor", JOptionPane.PLAIN_MESSAGE);
        
        if(ok == JOptionPane.OK_OPTION) {
            String query = "INSERT INTO vendor (nama, no_telepon, alamat) VALUES\n"
                    + "(?, ?, ?)";
            
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, namaVendor.getText());
                stmt.setString(2, nomorTelepon.getText());
                stmt.setString(3, alamatVendor.getText());
                
                stmt.execute();
                
                JOptionPane.showMessageDialog(this, "Berhasil menambah data vendor");
                initTabelVendor();
            } catch(SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelVendor;
    // End of variables declaration//GEN-END:variables
}