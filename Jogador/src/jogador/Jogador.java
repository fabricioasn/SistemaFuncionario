/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogador;

import java.util.Scanner;

/**
 *
 * @author Fabricio
 */
public class Jogador {



    public static void main(String[] args) {
    Scanner scan= new Scanner(System.in);
      double [][] notas= new double[2][4];
      
        System.out.println("Digite as 4 notas dos dois cursos. /n");
        
        for (int i = 0; i <notas.length; i++) {
            for (int j = 0; j<notas[i].length; j++) {
              notas[i][j]= scan.nextDouble();  
            } 
            
        }
        
        for (int i = 0; i <notas.length; i++) {
            for (int j = 0; j<notas[i].length; j++) { 
                System.out.println("A nota do curso " +i+ "é: " + notas[i][j]);        
                
            }
        }
        
        for (int i = 0; i <args.length; i++) {
            System.out.println(args[i]);    
            
        }
        
    }
    
}
