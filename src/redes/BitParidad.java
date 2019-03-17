/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.util.BitSet;

/**
 *
 * @author Daniel
 */
public class BitParidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String palabra = "CA";
        String dataword = asciiToBinary(palabra);
        String codeword = asciiToBinary(palabra)+ XOR(dataword);
        System.out.println(codeword);
        
        String palabra2 = "01110101";
        if(XOR(palabra2)==1){
            System.out.println("Incorrecto");
        }else{
            System.out.println("Correcto");
        }
    }
    public static String asciiToBinary(String asciiString){  
        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }
    public static BitSet stringToBitSet(String binary) {
        BitSet bitset = new BitSet(binary.length());
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                bitset.set(i);
            }
        }
        return bitset;
    }
    public static int XOR (String dataword){
        int c = 0;
        for(int i = 0; i < dataword.length(); i++){
            if(dataword.charAt(i)=='1')
                c++;
        }
        if(c%2==0)
            return 0;
        return 1;
    }
}
