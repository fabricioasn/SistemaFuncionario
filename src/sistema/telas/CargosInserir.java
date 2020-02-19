
package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import sistema.BancoDeDados;
import sistema.entidades.Cargo;

/**
 *Classe de criação da tela para inserção de cargos no BD
 * @author Fabricio Fabricio Almeida da Silva Nunes
 */
public class CargosInserir extends JPanel {
JLabel labelTitle, labelRole;
JTextField fieldRole;
JButton buttonRecord;

public CargosInserir(){
criarComponentes();
criarEventos();
}

private void criarComponentes(){
setLayout(null);

JLabel labelTitle =  new JLabel("Cadastro do Cargo", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20 ));
labelRole = new JLabel("Nome do cargo: ", JLabel.LEFT);
fieldRole = new JTextField();
buttonRecord = new JButton("Adicionar Cargo");

labelTitle.setBounds(20,100,660,40);
labelRole.setBounds(150,120,400,20);
fieldRole.setBounds(150,140,400,40);
buttonRecord.setBounds(250,380,200,40);

add(labelTitle);
add(labelRole);
add(fieldRole);
add(buttonRecord);
        
setVisible(true);

}

private void criarEventos(){
    buttonRecord.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
      Cargo novoCargo = new Cargo();
      novoCargo.setNome(fieldRole.getText());
      
      sqlInserirCargo(novoCargo);
    }
});
}

private void sqlInserirCargo(Cargo novoCargo){
    if(fieldRole.getText().length()<=3){
       JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
       return;
    }
//interação com o BD MS SQL
Connection conexao;
Statement instrucaoSQL;
ResultSet resultados;

try{
// conexão com o BD usando a bariável conexao do tipo Connection
conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);

//instruões SQL criando statement pela execução do método create na variavel conexao
instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
/*execução de atualização no BD com método execute na variaável instrucao 
para inserir objeto novoCargo da classe Cargo*/
instrucaoSQL.executeUpdate("INSERT INTO cargos (nome) VALUES('"+novoCargo.getNome()+"')");
JOptionPane.showMessageDialog(null, "Cargo '"+novoCargo.getNome()+"'adicionado com sucesso! ");

}catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o cargo.");
    Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
    }
    
   }

}
