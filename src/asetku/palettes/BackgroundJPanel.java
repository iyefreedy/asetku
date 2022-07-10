/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asetku.palettes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author dhafa
 */
public class BackgroundJPanel extends JPanel {
    Image image;

    public BackgroundJPanel() {
        try {                
          image = ImageIO.read(new File("src/assets/bg_asetku.jpg"));
       } catch (IOException ex) {
            System.err.println(ex.toString());
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
