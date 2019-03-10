/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class Redes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Scanner scan = new Scanner(System.in);
        String mes = scan.nextLine();
        Hamming hamm = new Hamming(mes);
        try {
            hamm.createMessageFile();
        } catch (IOException ex) {
            System.out.println("Hubo algun error");
        }*/
        File f = new File("prueba.ham");
        try {
            Scanner scanf = new Scanner(f);
            String line = scanf.nextLine();
            Correccion cr = new Correccion(line);
            cr.verifyErrors();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Redes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
}
