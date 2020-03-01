/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.entidades;

import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * classe ORM para mapear a entidade relacional Funcionário 
 * do DB Sistema_de_Funcionários
 *  * @author Fabricio Almeida da Silva Nunes
 */
public class Funcionario {
private int ID; // variável que armazena ID do funcionário
private String nome; // variável que armazena nome do funcionário
private String sobrenome; // variável que armazena sobrenome do funcionário
private String dataNascimento; // variável que armazena dat_nascimento
private String email; // variável que armazena email
private int cargo; // variável que armazena cargo do funcionário
private double salario; // variável que armazena salário do funcionário
//conversão da data inserida no cmapo fieldBirthDate de util.date para String

SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

@Override
public String toString(){
return this.nome + " " + this.sobrenome;    
}

}
