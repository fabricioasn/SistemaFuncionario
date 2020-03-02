/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora2;

/*Objetivo: Calculadora Simples
 * Acessar compiladores online:
 * [1] https://repl.it
 * [2] http://www.onlinecompiler.net/
 * Exercicios:
 * [1] Inserir as operacoes Subtracao, Multiplicao
 * [2] Inserir as operacoes MaiorQue e MenorQue
 * [3] Faca as seguintes operacoes apenas com chamadas:
 *     [a] 10 + 20 * 30
 *     [b] menorQue(maiorQue(10, 20),5)
 */

public class Calculadora {
    private double a;
    private double b;
    private double resultado;
    
  public Calculadora(){
   this.setA(0.0);
   this.setB(0.0);
   this.setResultado(0.0);
      
  }

    public Calculadora(double _a, double _b){
       this.setA(_a);
        this.setB(_b);
	this.setResultado(0.0);
     
      
      
  }
  
    public double getA() {
        return a;
    }

    public void setA(double _a) {
        this.a = _a;
    }

    public double getB() {
        return b;
    }

    public void setB(double _b) {
        this.b = _b;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
    public void Soma(){
       double r= this.getA()+this.getB();
       this.setResultado(r);
        
    }
  
      public void Subtracao() {
       double r= this.getA()- this.getB();
       this.setResultado(r);
        
    }
    
      public void Multiplica(){
       double r= this.getA() * this.getB();
       this.setResultado(r);
        
    }
    
      public void Divide(){
       double r= this.getA() / this.getB();
       this.setResultado(r);
        
    }
    
      
      public void maiorQue(){
	  double r = this.getA()>this.getB()?this.getA():this.getB();
	  this.setResultado(r);
  }  
      
  
      public void menorQue(){
	  double r = this.getA()<this.getB()?this.getA():this.getB();
	  this.setResultado(r);
  }
      
      
      
      
      
}

//--------------------------------------------------------------------------------