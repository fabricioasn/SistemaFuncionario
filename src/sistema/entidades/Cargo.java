/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.entidades;

/**
 * classe ORM para mapear a entidade relacional Cargo 
 * do DB Sistema_de_Funcionários
 *  * @author Fabricio Almeida da Silva Nunes
 */
public class Cargo {
private int ID; // variável destinado ao ID do cargo
private String nome; // variável destinado ao nome do cargo

public int getID(){
return ID;    
}
public void setID(int ID){
this.ID = ID;
}

public String getNome(){
return nome;    
}
public void setNome(String nome){
this.nome = nome;
}

@Override
public String toString(){
  return this.nome;
}

}
