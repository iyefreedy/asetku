/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.utils;

import asetku.models.Month;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freedcode
 */
public class Utilities {
    public static DecimalFormat getFormatCurrency() {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        return kursIndonesia;
    }
    
    public static Month[] getMonths() {
        String[] objects = new String[]{"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        List<Month> months = new ArrayList<>();
        for(int i = 1; i <= 12; i++) {
            Month month = new Month(String.valueOf(i), objects[i-1]);
            months.add(month);
        }
        
        Month[] monthArr = new Month[months.size()];
        
        return months.toArray(monthArr);
    }
}
