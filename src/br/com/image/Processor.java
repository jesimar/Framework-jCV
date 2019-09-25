/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.image;

import br.com.image.util.Constants;
import br.com.image.util.FiltroColor;
import br.com.image.util.FiltroText;
import br.com.image.util.Util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Jesimar S. Arantes
 */
public class Processor {
    
    private static char matrizChar[][];
    private static final int SIZE = 1000;  
    
    public static void printImageNormal(Graphics g, ImageIcon imageIcon){
        Graphics2D g2 = (Graphics2D)g;  
        if (imageIcon != null){
            g2.drawImage(imageIcon.getImage(), 0, 0, 
                    Constants.WIDTH_FRAME - 20, Constants.HEIGHT_FRAME - 100, null);
        }
    }
    
    public static BufferedImage printImageProcessor(Graphics g, ImageIcon imageIcon, 
            int shift, int sizeFont, boolean isText, FiltroColor filtroColor){        
        
        BufferedImage buffer;
        if (isText){            
            buffer = drawImgChar(imageIcon, shift, sizeFont, filtroColor);
        }else{
            buffer = drawImgSquare(imageIcon, shift, filtroColor);
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(new ImageIcon(buffer).getImage(), 0, 0, 
                Constants.WIDTH_FRAME - 20, Constants.HEIGHT_FRAME - 100, null);
        return buffer;
    }
    
    private static BufferedImage drawImgChar(ImageIcon img, int shift, int sizeFont, 
           FiltroColor filtroColor){
        BufferedImage buffer = new BufferedImage(img.getIconWidth(), 
                img.getIconHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = buffer.createGraphics();    
        g2.drawImage(img.getImage(), 0, 0, img.getIconWidth(), img.getIconHeight(), null);
        g2.setFont(new Font(Font.DIALOG, Font.PLAIN, sizeFont));
        int k = 0;
        for (int i = 0; i < img.getIconWidth(); i+=shift){
            int w = 0;
            for (int j = 0; j < img.getIconHeight(); j+=shift){ 
                int color = buffer.getRGB(i, j);                
                g2.setColor(Color.WHITE);
                g2.fillRect(i, j, shift, shift);
                setColor(g2, filtroColor, color);
                g2.drawString(""+matrizChar[k][w], i, j);                
                w = (w + 1)%SIZE;
            }
            k = (k + 1)%SIZE;
        }
        return buffer;               
    }
    
    private static BufferedImage drawImgSquare(ImageIcon img, int shift, FiltroColor filtro){
        BufferedImage buffer = new BufferedImage(img.getIconWidth(), 
                img.getIconHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = buffer.createGraphics();
        g2.drawImage(img.getImage(), 0, 0, null);                             
        
        for (int i = 0; i < buffer.getWidth(); i=i+shift){
            for (int j = 0; j < buffer.getHeight(); j=j+shift){
                int color = buffer.getRGB(i, j);
                setColor(g2, filtro, color);
                g2.fillRect(i - shift,j - shift, shift, shift);
            }
        }   
        return buffer;
    }
    
    private static void setColor(Graphics2D g2, FiltroColor filtro, int color){        
        int r = (color << 8) >> 24 & 255;
        int g = (color << 16) >> 24 & 255;
        int b = (color << 24) >> 24 & 255;                
        if (filtro == FiltroColor.COLOR){
            g2.setColor(new Color(color));                    
        }else if (filtro == FiltroColor.BLACK_WHITE){
            g2.setColor(Util.getColorToBW(r, g, b));
        }else if (filtro == FiltroColor.SCALE_GRAY){
            g2.setColor(Util.getColorToEscalarCinza(r, g, b));
        }else if (filtro == FiltroColor.BGR){
            g2.setColor(new Color(b,g,r));
        }else if (filtro == FiltroColor.BRG){
            g2.setColor(new Color(b,r,g));
        }else if (filtro == FiltroColor.GBR){
            g2.setColor(new Color(g,b,r));
        }else if (filtro == FiltroColor.GRB){
            g2.setColor(new Color(g,r,b));
        }else if (filtro == FiltroColor.RBG){
            g2.setColor(new Color(r,b,g));
        }else if (filtro == FiltroColor.BLACK_WHITE_RND){
            g2.setColor(Util.getColorRND_BW());
        }else if (filtro == FiltroColor.COLOR_RND){
            g2.setColor(Util.getColorRND_RGB());
        }else if (filtro == FiltroColor.SCALE_GRAY_RND){
            g2.setColor(Util.getColorRNDEscalaCinza());
        }else if (filtro == FiltroColor.CYM){
            g2.setColor(Util.getColorRGBtoCYM(r, g, b));
        }else if (filtro == FiltroColor.CMY){
            g2.setColor(Util.getColorRGBtoCMY(r, g, b));
        }else if (filtro == FiltroColor.YCM){
            g2.setColor(Util.getColorRGBtoYCM(r, g, b));
        }else if (filtro == FiltroColor.YMC){
            g2.setColor(Util.getColorRGBtoYMC(r, g, b));
        }else if (filtro == FiltroColor.MCY){
            g2.setColor(Util.getColorRGBtoMCY(r, g, b));
        }else if (filtro == FiltroColor.MYC){
            g2.setColor(Util.getColorRGBtoMYC(r, g, b));
        }else if (filtro == FiltroColor.BW_AVERAGE_GEOMETRIC){
            g2.setColor(Util.getColorToBW_Geometric(r, g, b));
        }else if (filtro == FiltroColor.SG_AVERAGE_GEOMETRIC){
            g2.setColor(Util.getColorToEscalarCinzaGeometric(r, g, b));
        }else {
            g2.setColor(new Color(color));            
        }
    }
    
    public static void preencheMatrizChar(String string, FiltroText filtro){
        matrizChar = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){  
                if (filtro == FiltroText.TEXT_INPUT){
                    matrizChar[i][j] = Util.getChar(string, i);
                }else if (filtro == FiltroText.TEXT_RND_AtoZ){
                    matrizChar[i][j] = Util.getCharRNDAtoZ();
                }else if (filtro == FiltroText.TEXT_RND_atoz){
                    matrizChar[i][j] = Util.getCharRNDatoz();
                }else if (filtro == FiltroText.TEXT_RND_0to9){
                    matrizChar[i][j] = Util.getCharRND0to9();
                }
            }
        }        
    }
    
}
