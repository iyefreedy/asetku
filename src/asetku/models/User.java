/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.models;

import asetku.databases.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yap
 */
public class User  {
    String id;
    String nip;
    String nama;
    String roleId;
    String branchId;
    String divisiId;

    public void setUser(String id, String nip, String nama, String roleId, String branchId, String divisiId) {
        this.id = id;
        this.nip = nip;
        this.nama = nama;
        this.roleId = roleId;
        this.branchId = branchId;
        this.divisiId = divisiId;
    }

    public String getId() {
        return id;
    }

    public String getNip() {
        return nip;
    }

    public String getNama() {
        return nama;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getDivisiId() {
        return divisiId;
    }    
    
    public User getUser() {
        if(this.id == null) {
            return null;
        }
        
        Connection conn = ConnectionManager.getInstance().getConnection();
        String query = "SELECT users.*, divisi.id, cabang.id\n"
                + "FROM users JOIN divisi ON users.divisi_id=divisi.id\n"
                + "JOIN cabang ON divisi.cabang_id=cabang.id\n"
                + "WHERE users.id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);

            ResultSet result = stmt.executeQuery();
            User user = new User();
            while(result.next()) {
                user.setUser(
                    result.getString("id"),
                    result.getString("nip"),
                    result.getString("nama"),
                    result.getString("role_id"),
                    result.getString("branch_id"),
                    result.getString("divisi_id")
                );
            }
            
            return user;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return null;
    }
    
    
}
