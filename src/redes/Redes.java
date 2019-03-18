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
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("prueba1.txt");
        Scanner scan = new Scanner(f);
        String mes = scan.nextLine();
        Hamming hamm = new Hamming(mes);
        try {
            hamm.createMessageFile("prueba1");
        } catch (IOException ex) {
            System.out.println("Hubo algun error");
        }
        System.out.println("-------------------------------------------");
        f = new File("prueba1.ham");
        try {
            Scanner scanf = new Scanner(f);
            while(scanf.hasNext()){
                String line = scanf.nextLine();
                Correccion cr = new Correccion(line);
                cr.verifyErrors();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Redes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
}
