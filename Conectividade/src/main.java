
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabricio
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      String servidor= "jdbc:sqlserver://localhost:1433;databaseName=Loja_De_Automoveis";
      String usuario= "Administrator"; 
      String senha= "admin";
      
      Connection conexao;
      
      Statement instrucaoSQL;  
      
      ResultSet resultados;
      
        try {
            conexao= DriverManager.getConnection(servidor, usuario, senha);  
            
            instrucaoSQL= conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
            resultados= instrucaoSQL.executeQuery("SELECT*FROM Carros");  
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());    
            
        }
      
      
      
      
        
        
        
        
        
    }
    
}
