/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package asetku.panels;

import asetku.databases.ConnectionManager;
import asetku.databases.DatabaseServices;
import asetku.utils.Utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dhafa
 */
public class AllAsetPanel extends javax.swing.JPanel {
    Connection connection = ConnectionManager.getInstance().getConnection();
    DatabaseServices services;
    /**
     * Creates new form AsetPanel
     */
    public AllAsetPanel() {
        initComponents();
        
        // Start connection
        services = new DatabaseServices(connection);
        initAssetsTable("");
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                initAssetsTable(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                initAssetsTable(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                initAssetsTable(searchField.getText());
            }
        });
        
    }
    
    private void initAssetsTable(String search) {
        Object[] header = new Object[]{"No.", "Kode Aset", "Nama Aset", "Harga Aset", "Cabang", "Divisi"};
        DefaultTableModel tableModel = new DefaultTableModel(null, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        asetTable.setModel(tableModel);
        
        String query = "SELECT * FROM aset\n"
                + "JOIN divisi ON aset.divisi_id=divisi.id\n"
                + "JOIN cabang ON divisi.cabang_id=cabang.id\n"
                + "WHERE aset.nama_aset LIKE ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + search + "%");
            ResultSet result = stmt.executeQuery();
            int i = 1;
            while(result.next()) {
                
                String a = result.getString("kode_aset");
                String b = result.getString("nama_aset");
                String c = Utilities.getFormatCurrency().format(result.getDouble("harga_aset"));
                String d = result.getString("cabang.nama");
                String e = result.getString("divisi.nama");
                
                Object[] row = new Object[]{i, a, b, c, d, e};
                
                tableModel.addRow(row);
                
                i++;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        asetTable = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();

        asetTable.setModel(new javax.swing.table.DefaultTableModel(
            null,
            new String [] {
                "Title 1"
            }
        ));
        asetTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        asetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                asetTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(asetTable);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "30", "40", "50" }));

        jLabel1.setText("Show");

        jLabel2.setText("Entries");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void asetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asetTableMouseClicked
       
    }//GEN-LAST:event_asetTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable asetTable;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}