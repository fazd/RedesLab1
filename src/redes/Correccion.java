/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.util.ArrayList;
import java.util.BitSet;
import static redes.Hamming.getIndexC;
import static redes.Hamming.getValueOfC;

/**
 *
 * @author fabio
 */
public class Correccion {

    private String mess;
    private ArrayList<Boolean> cValues;
    private String pal;
    private int c;
    
    public Correccion(String mess) {
        this.mess = mess;
        pal = "";
        cValues = new ArrayList<>();
        c = 0;
    }

    private void getC(){
        int p = 1;
        
        while (p <= mess.length()) {
            p *= 2;
            c++;
        }
    }
    
    private void generatePalAndCValues(){
        int length = mess.length();
        getC();
        System.out.println("C: " + c);
        int p;
        p = 1;
        int i = length - 1;
        while (i >= 0) {
            if (length - i - 1 == p - 1) {
                char car = mess.charAt(i);
                p = p * 2;
                if (car == '1') {
                    cValues.add(true);
                } else {
                    cValues.add(false);
                }
            } else {
                char car = mess.charAt(i);
                pal = car + pal;
            }
            i--;
        }
        System.out.println("La pal es: " + pal);
        System.out.println("CValues es: " + cValues.toString());
    }
    
    
    
    
    
    public void verifyErrors() {
        generatePalAndCValues();
        BitSet res = Hamming.stringToBitSet(pal);
        ArrayList<Boolean> obt = new ArrayList<>();
        ArrayList<Boolean> errors = new ArrayList<>();
        System.out.println("Pal: "+ pal);
        for (int i = 1; i <= c; i++) {
            ArrayList<Integer> cIndex = getIndexC(i, pal.length());
            boolean cAux = getValueOfC(cIndex, pal);
            //System.out.println("Caux: "+ cAux);
            obt.add(cAux);
            errors.add(cAux^cValues.get(i-1));
        }
        int bitErr = 0;
        int k =1;
        for(Boolean b : errors){
            if(b){
                bitErr+=k;
            }
            k*=2;
        }
        if(bitErr == 0){
            System.out.println("No hay bit errado");
        }
        else{
            System.out.println("El bit errado es "+ bitErr);
        }
        
        
        

        
    }

    public String arrToBin(ArrayList<Boolean> code) {
        String s = "";
        for (Boolean b : code) {
            if (b) {
                s += "1";
            } else {
                s += "0";
            }
        }
        return s;
    }

}
