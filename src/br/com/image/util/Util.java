/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.image.util;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesimar S. Arantes
 */
public class Util {
    
    public static Color getColorToEscalarCinza(int r, int g, int b){
        int media = (r+g+b)/3;
        return new Color(media, media, media);
    }
    
    public static Color getColorToEscalarCinzaGeometric(int r, int g, int b){
        int media = (int)Math.pow(r*g*b, 1.0/3.0);
        return new Color(media, media, media);
    }
    
    public static Color getColorToBW(int r, int g, int b){
        int media = (r+g+b)/3;
        if (media > 127){
            return Color.WHITE;
        }else{
            return Color.BLACK;
        }
    }
    
    public static Color getColorToBW_Geometric(int r, int g, int b){
        int media = (int)Math.pow(r*g*b, 1.0/3.0);
        if (media > 127){
            return Color.WHITE;
        }else{
            return Color.BLACK;
        }
    }  
    
    public static Color getColorRNDEscalaCinza(){
        float rnd = (float)Math.random();
        return new Color(rnd, rnd, rnd);
    }
    
    public static Color getColorRND_BW(){
        int rnd = 255*new Random().nextInt(2);
        return new Color(rnd, rnd, rnd);
    }
    
    public static Color getColorRND_RGB(){
        float rndR = (float)Math.random();
        float rndG = (float)Math.random();
        float rndB = (float)Math.random();
        return new Color(rndR, rndG, rndB);
    } 
    
    public static Color getColorRGBtoCMY(int r, int g, int b){        
        return new Color(255 - r, 255 - g, 255 - b);
    }
    
    public static Color getColorRGBtoCYM(int r, int g, int b){        
        return new Color(255 - r, 255 - b, 255 - g);
    }
    
    public static Color getColorRGBtoYCM(int r, int g, int b){        
        return new Color(255 - b, 255 - r, 255 - g);
    }
    
    public static Color getColorRGBtoYMC(int r, int g, int b){        
        return new Color(255 - b, 255 - g, 255 - r);
    }
    
    public static Color getColorRGBtoMCY(int r, int g, int b){        
        return new Color(255 - g, 255 - r, 255 - b);
    }
    
    public static Color getColorRGBtoMYC(int r, int g, int b){        
        return new Color(255 - g, 255 - b, 255 - r);
    }
    
    public static char getCharRNDAtoZ(){
        char rndChar = (char)((Math.random()*26) + 'A');
        return rndChar;
    }
    
    public static char getCharRNDatoz(){
        char rndChar = (char)((Math.random()*26) + 'a');
        return rndChar;
    }
    
    public static char getCharRND0to9(){
        char rndChar = (char)((Math.random()*10) + '0');
        return rndChar;
    }
    
    public static char getChar(String str, int i){ 
        if (str.length() > 0){
            i = i % str.length();            
            return str.charAt(i);
        }else{
            return ' ';
        }
    }
    
    public static char getCharRND(char min, int ammount){
        char rndChar = (char)((Math.random()*ammount) + min);
        return rndChar;
    }
    
    public static int getInteiroRND(int min, int max){
        int rnd = (int)(Math.random()*max + min);
        return rnd;
    }
    
    public static int getInteiroRND0a9(){
        int rnd = (int)(Math.random()*10);
        return rnd;
    }    
    
}
