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
public enum FiltroText {
    
    TEXT_INPUT, TEXT_RND_AtoZ, TEXT_RND_atoz, TEXT_RND_0to9;
    
    public static FiltroText getFiltro(int index){
        if (index == 0){
            return TEXT_INPUT;
        }else if (index == 1){
            return TEXT_RND_AtoZ;
        }else if (index == 2){
            return TEXT_RND_atoz;
        }else if (index == 3){
            return TEXT_RND_0to9;
        }
        return TEXT_INPUT;
    };
}
