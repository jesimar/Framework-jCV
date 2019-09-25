/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.image;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jesimar S. Arantes
 */
public class ImageFileChooser {
    
    private final JFileChooser fileChooserIMG;
    
    public ImageFileChooser(){
        fileChooserIMG = new JFileChooser(".");
        fileChooserIMG.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooserIMG.setMultiSelectionEnabled(false);        
        fileChooserIMG.setFileFilter(new FileNameExtensionFilter(
                "Files .png .jpg .jpeg .gif", "png", "jpg", "jpeg", "gif"));
    }
    
    public JFileChooser getImageFileChooser(){        
        return fileChooserIMG;
    }
    
    public int showOpenDialog(){
        return fileChooserIMG.showOpenDialog(null);
    }
    
    public File getSelectedFile(){
        return fileChooserIMG.getSelectedFile();
    }
    
}
