/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora2;

import java.util.Scanner;



public class Calculadora2 {
     

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);  
        Calculadora calc= null;  
         boolean controle=true;
         do{ 	  
         try{
	  System.out.println("Digite o primeiro valor");
	  double a = scanner.nextDouble();
	  System.out.println("Digite o segundo valor");
	  double b = scanner.nextDouble();	  
          //doubl();e a=10, b=20;
          calc= new Calculadora(a,b);
          calc.Soma();
	  double r = calc.getResultado();
	  System.out.println("O resultado da soma eh:"+r);
	  //------------------------------------------------------
	  calc.Divide();
	  r = calc.getResultado();
	  System.out.println("O resultado da divisao eh:"+r);	  
	  //------------------------------------------------------	  
	  calc.maiorQue();
	  r = calc.getResultado();
	  System.out.println("O resultado maiorQue eh:"+r);	  
	  //------------------------------------------------------
	  }catch(java.util.InputMismatchException e){
		  controle=false;
                 System.out.println("Aviso de Excecao: sao permitidos apenas numeros!");
		  System.out.println("Por favor, execute o programa novamente.");
        
        }
        }while(controle=true);  
    
}  
