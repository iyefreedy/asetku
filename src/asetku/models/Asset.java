/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.models;

/**
 *
 * @author yap
 */
public class Asset {
    String id;
    String namaAset;
    String kodeAset;
    String categoryId;
    String nominal;
    String keterangan;
    String currentBranchId;
    String currentDivisiId;

    public Asset() {
    }

    public Asset(String id, String namaAset, String kodeAset, String categoryId, String nominal, String keterangan, String currentBranchId, String currentDivisiId) {
        this.id = id;
        this.namaAset = namaAset;
        this.kodeAset = kodeAset;
        this.categoryId = categoryId;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.currentBranchId = currentBranchId;
        this.currentDivisiId = currentDivisiId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNamaAset() {
        return namaAset;
    }

    public String getKodeAset() {
        return kodeAset;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getNominal() {
        return nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getCurrentBranchId() {
        return currentBranchId;
    }

    public String getCurrentDivisiId() {
        return currentDivisiId;
    }
    
    
}
