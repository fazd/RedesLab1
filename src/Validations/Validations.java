/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class Validations {

    public static boolean valChar(char c){
        if(c>='A' && c<='Z'){
            return true;
        }
        if(c >= 'a' && c<='z'){
            return true;
        }
        if(c =='.' || c ==',' || c == ';' || c==':'){
            return true;
        }
        return false;
    }
    
    
    public static boolean ValidFileEnvio(File f, int numChar){
        try {
            Scanner scan = new Scanner(f);
            int cont = 0;
            String linea="";
            while(scan.hasNext()){
                linea = scan.nextLine();
                cont++;
            }
            if(cont != 1){
                JOptionPane.showMessageDialog(null,"El archivo no contiene la estructura deseada",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(linea.length() != numChar){
                JOptionPane.showMessageDialog(null,"El archivo contiene mas caracteres de los permitidos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            for(int i = 0; i < linea.length(); i++){
                if(!valChar(linea.charAt(i))){
                    JOptionPane.showMessageDialog(null,"El archivo contiene caracteres invalidos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"El archivo Tiene problemas","Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean ValidFileHamm(File f, int numChar){
        try {
            Scanner scan = new Scanner(f);
            int cont = 0;
            String linea="";
            while(scan.hasNext()){
                linea = scan.nextLine();
                cont++;
                for(int i = 0; i < linea.length(); i++){
                    if(linea.charAt(i) != '0' && linea.charAt(i)!='1'){
                        JOptionPane.showMessageDialog(null,"El archivo contiene caracteres invalidos",
                            "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }   
            }
            if(cont !=2){
                JOptionPane.showMessageDialog(null,"El archivo no contiene la estructura deseada",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(linea.length() != numChar){
                JOptionPane.showMessageDialog(null,"El archivo contiene mas caracteres de los permitidos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"El archivo Tiene problemas","Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    
    
}
