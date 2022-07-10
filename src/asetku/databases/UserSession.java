/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.databases;

import asetku.models.User;

/**
 *
 * @author dhafa
 */
public class UserSession {
    
    private static User currentUser = null;
    

    public static void setUser(User user) {
        UserSession.currentUser = user;
    }

    public static User getCurrentUser() {
        return UserSession.currentUser;
    }
}
