/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.databases;

import asetku.models.Asset;
import asetku.models.Branch;
import asetku.models.Category;
import asetku.models.Divisi;
import asetku.models.Vendor;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yap
 */
public class DatabaseServices {
    
    Connection connection = null;
    
    public DatabaseServices(Connection connection) {
        this.connection = connection;
    }
    
    public Category[] getCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM kategori";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()) {
                Category category = new Category(result.getString("id"), result.getString("nama"));
                categories.add(category);
            }
            
            Category[] categoryArr = new Category[categories.size()];
            
            return categories.toArray(categoryArr);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return new Category[]{};
    }
    
    public Divisi[] getDivisions(String branchId) {
        List<Divisi> divisions = new ArrayList<>();
        String query = "SELECT * FROM divisi";
        if(!branchId.equals("")) {
            query += "\n"
                    + "WHERE divisi.cabang_id=" + branchId;
        }
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()) {
                Divisi divisi = new Divisi(result.getString("id"), result.getString("nama"));
                divisions.add(divisi);
                System.out.println(divisi.getNama());
            }
            
            Divisi[] categoryArr = new Divisi[divisions.size()];
            
            return divisions.toArray(categoryArr);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return new Divisi[]{};
    }
    
    public Vendor[] getVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM vendor";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            
            ResultSet result = stmt.executeQuery();
            
            while(result.next()) {
                Vendor vendor = new Vendor(result.getString("id"), result.getString("nama"));
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        Vendor[] arrVendor = new Vendor[vendors.size()];
        
        return vendors.toArray(arrVendor);
    }
    
    public Branch[] getBranches() {
        List<Branch> branches = new ArrayList<>();
        String query = "SELECT * FROM cabang";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            
            while(result.next()) {
                Branch branch = new Branch(result.getString("id"), result.getString("nama"));
                branches.add(branch);
            }
            
            Branch[] categoryArr = new Branch[branches.size()];
            
            return branches.toArray(categoryArr);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return new Branch[]{};
    }
    
    public Object[][] getAssets() {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM assets";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            
            ResultSet result = stmt.executeQuery();
            
            while(result.next()) {
                Asset asset = new Asset(
                        result.getString("id"),
                        result.getString("nama_aset"),
                        result.getString("kode_aset"),
                        result.getString("category_id"),
                        result.getString("nominal"),
                        result.getString("keterangan"),
                        result.getString("current_branch_id"),
                        result.getString("current_divisi_id")
                );
                assets.add(asset);
            }
            
            Asset[] assetArr = new Asset[assets.size()];
            
            return new Object[][] {assets.toArray(assetArr)};
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return new Object[][]{};
    }
}
