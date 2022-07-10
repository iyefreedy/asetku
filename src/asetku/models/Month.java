/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.models;

import java.util.Objects;

/**
 *
 * @author freedcode
 */
public class Month {
    String id;
    String name;
    
    public Month(String id, String month) {
        this.id = id;
        this.name = month;
    }

    public String getId() {
        return id;
    }
    

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Month other = (Month) obj;
        return Objects.equals(this.id, other.id);
    }
}
