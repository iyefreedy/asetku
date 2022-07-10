/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this li\cense
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package asetku.panels;

import asetku.databases.ConnectionManager;
import asetku.models.Month;
import asetku.utils.Utilities;
import java.awt.event.ItemEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author freedcode
 */
public class HistoryPanel extends javax.swing.JPanel {
    Connection connection = ConnectionManager.getInstance().getConnection();

    /**
     * Creates new form HistoryPanel
     */
    public HistoryPanel() {
        initComponents();
        
        
        Calendar calendar = Calendar.getInstance();
        jComboBox1.setSelectedIndex(calendar.get(Calendar.MONTH));
        
        jComboBox1.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                Month month = (Month) e.getItem();
                initHistoryTable(month.getId());
            }
        });
        Month month = (Month) jComboBox1.getSelectedItem();
        initHistoryTable(month.getId());
    }
    
    private void initHistoryTable(String month) {
        Object[] header = new Object[]{"No.", "Kode Aset", "Nama Aset", "Branch Sebelum", "Divisi Sebelum", "Branch Sesudah", "Divisi Sesudah", "Tanggal Mutasi"};
        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        tabelHistory.setModel(tableModel);
        String query = "SELECT *\n" +
                        "FROM history_aset\n" +
                        "    JOIN aset ON history_aset.aset_id=aset.id\n" +
                        "    JOIN divisi AS d1 ON history_aset.divisi_lama_id=d1.id\n" +
                        "    JOIN divisi AS d2 ON history_aset.divisi_baru_id=d2.id\n" +
                        "    JOIN cabang AS c1 ON d1.cabang_id=c1.id\n" +
                        "    JOIN cabang AS c2 ON d2.cabang_id=c2.id\n" +
                        "WHERE MONTH(history_aset.created_at)=?";
//        String query = "SELECT * FROM history_aset\n"
//                + "JOIN aset ON history_aset.aset_id=aset.id\n"
//                + "JOIN divisi AS d1 ON history_aset.divisi_lama_id=d1.id\n"
//                + "JOIN divisi AS d2 ON history_aset.divisi_baru_id=d2.id\n"
//                + "JOIN cabang AS c1 ON d1.cabang_id=c1.id\n"
//                + "JOIN cabang AS c2 ON d2.cabang_id=c2.id\n"
//                + "WHERE MONTH(history_aset.created_at)=?";
        System.out.println(month);
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, month);
            ResultSet result = stmt.executeQuery();
            
            int i = 1;
            while(result.next()) {

                String a = result.getString("aset.kode_aset");
                String b = result.getString("aset.nama_aset");
                String c = result.getString("c1.nama");
                String d = result.getString("d1.nama");
                String e = result.getString("c2.nama");
                String f = result.getString("d2.nama");
                String g = result.getString("history_aset.created_at");
                
                Object[] row = new Object[]{i, a, b, c, d, e, f, g};
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelHistory = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Utilities.getMonths()));

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fileName = "src\\report\\report_mutasi.jasper";
        File file = new File(fileName);

        try {
            JasperReport reportReimburse = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportReimburse, null, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch(JRException e) {
            
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<Month> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelHistory;
    // End of variables declaration//GEN-END:variables
}