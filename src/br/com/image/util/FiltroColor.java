/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.image.util;

/**
 *
 * @author Jesimar S. Arantes
 */
public enum FiltroColor {
    
    COLOR, BLACK_WHITE, SCALE_GRAY, GBR, GRB, RBG, BRG, BGR, 
    BLACK_WHITE_RND, COLOR_RND, SCALE_GRAY_RND, CYM, CMY, YCM, YMC, MYC, MCY,
    BW_AVERAGE_GEOMETRIC, SG_AVERAGE_GEOMETRIC;
    
    public static FiltroColor getFiltro(int index){
        if (index == 0){
            return COLOR;
        }else if (index == 1){
            return BLACK_WHITE;
        }else if (index == 2){
            return SCALE_GRAY;
        }else if (index == 3){
            return GBR;
        }else if (index == 4){
            return GRB;
        }else if (index == 5){
            return RBG;
        }else if (index == 6){
            return BRG;
        }else if (index == 7){
            return BGR;
        }else if (index == 8){
            return BLACK_WHITE_RND;
        }else if (index == 9){
            return COLOR_RND;
        }else if (index == 10){
            return SCALE_GRAY_RND;
        }else if (index == 11){
            return CMY;
        }else if (index == 12){
            return CYM;
        }else if (index == 13){
            return YCM;
        }else if (index == 14){
            return YMC;
        }else if (index == 15){
            return MYC;
        }else if (index == 16){
            return MCY;
        }else if (index == 17){
            return BW_AVERAGE_GEOMETRIC;
        }else if (index == 18){
            return SG_AVERAGE_GEOMETRIC;
        }
        
        return COLOR;
    };
}
