/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;

/**
 *
 * @author fabio
 */
public class Hamming {
    
    private String message;
    private int lengthM;

    public Hamming(String message) {
        this.message = message;
        this.lengthM = message.length();
    }
    
    
    public File createMessageFile(String nombre) throws IOException{
        ArrayList<String> solucion = new ArrayList<>();
        for(int t = 0; t < this.message.length(); t++){            
            String bin = wordToBit(""+this.message.charAt(t));
            //BitSet res = stringToBitSet(bin);
            long c = calculateNumberC(bin.length());
            ArrayList<Boolean> cValues = new ArrayList<>();
            for(int i = 1; i <=c; i++){
                ArrayList<Integer> cIndex = getIndexC(i,bin.length());
                boolean cAux = getValueOfC(cIndex, bin);
                cValues.add(cAux);


            }
            ArrayList<Boolean> codeWord = Hamming.getCodeWord(cValues, bin, bin.length());
            System.out.println("El codeword será: "+ arrToBin(codeWord));
            solucion.add(arrToBin(codeWord));

        }
        File f  = new File(nombre+".ham");
        BufferedWriter bf = new BufferedWriter(new FileWriter(f));
        for(String s : solucion){
            bf.write(s);
            bf.newLine();
        }
        bf.close();
        System.out.println("El archivo ha sido creado exitosamente en: ");
        System.out.println(f.getCanonicalPath());
        return f;
    }
    
    public String arrToBin(ArrayList<Boolean> code){
        String s="";
        for(Boolean b : code){
            if(b){
                s+="1";
            }
            else{
                s+="0";
            }
        }
        return s;
    }
    
    
    private  String normalize(String str){
        if(str.length() == 8){
            return str;
        }
        String newBin = "";
        int bits = 8-str.length();
        for(int i = 0; i < bits; i++){
            newBin+="0";
        }
        
        newBin+=str;
        return newBin;
    }
    
    private  String wordToBit(String message){
        String bin = "";
        for (int i = 0; i < message.length(); i++) {
            String norm = normalize(Integer.toBinaryString(message.charAt(i)));
            bin+= norm;
            //System.out.println("La letra "+ str.charAt(i)+" es "+ 
            //        norm );
        }
        System.out.println("El binario es: "+ bin+ " y el mensaje es "+ message);
        return bin;
    }
    
    
    
    public static  BitSet stringToBitSet(String str){
        int n = str.length();
        BitSet b = new BitSet(n);
        for(int i = 0; i < n; i++){
            boolean s = str.charAt(i) == '1';
            b.set(i,s);
        }
        System.out.println("El bitset es "+ bitsetToString(b));
        return b;
    }
     
    public static String bitsetToString(BitSet b){
        String res = "";
        for (int i = 0; i < b.length(); i++) {
            if(b.get(i)){
                res+="1";
            }
            else{
                res+="0";
            }
        }
        return res;
    }
    
    
    private int calculateNumberC(int length){
        int m = 1;
        long p = 2;
        while(p <length + m +1){
            m++;
            p*=2;
        }
        System.out.println("M será: "+ m);
        return m;
    }
    
    public static ArrayList<Integer> getIndexC(int indexC, int lengthCode){
        long longitud = (long) Math.pow(2, indexC-1);
        boolean sw = false;
        ArrayList<Integer> index = new ArrayList<>();
        int ciclo = 1;
        for(int i = lengthCode; i >= 0; i--){
            if(sw){
                index.add(lengthCode- i);
            }
            ciclo++;
            if(ciclo > longitud){
                sw = !sw;
                ciclo = 1;
            }
            
            
        }
        return index;
    }
    
    public static boolean getValueOfC(ArrayList<Integer> index, String cod){
        int n = cod.length();
        boolean xor = false;
        for (Integer i : index) {
            //System.out.println("El int es : "+ i);
            boolean x = cod.charAt(cod.length()-1- (i-1)) == '1';
            //System.out.println("X es : "+ x);
            xor = xor ^ x;
        }
        //System.out.println("Fin: ");
        return xor;
    }
    
    
    public static ArrayList<Boolean> getCodeWord(ArrayList<Boolean> c,String  cod, int length){
        int n = length + c.size();
        ArrayList<Boolean> codeWord = new ArrayList<>();
        //BitSet codeWord = new  BitSet(n);
        //System.out.println("El codeword es " + bitsetToString(codeWord));
        //System.out.println("N: "+ n);
        int point = 1, contc = 0;
        int id = 1;
        int idx = length -1;
        for(int i = n-1; i >=0; i--){
            if(id == point){
                codeWord.add(c.get(contc));
                //System.out.println("El valor que se agregó es: "+c.get(contc) );
                contc++;
                point*=2;
            }
            else{
                codeWord.add('1'==cod.charAt(idx));
                //System.out.println("El valor que se agregó es: "+cod.get(contc) );
                idx--;
            }
            //System.out.println("I: "+ i);
            
            id++;
        }
        Collections.reverse(codeWord);
        System.out.println(codeWord.toString());
        return codeWord;
    }
    
    
    
}
