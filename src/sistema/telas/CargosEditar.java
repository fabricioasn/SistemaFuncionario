
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
import sistema.Navegador;

/**
 *Classe de criação da tela para edição de cargos já inseridos no BD
 * @author Fabricio Fabricio Almeida da Silva Nunes
 */
public class CargosEditar extends JPanel {
    Cargo nowRole;
    JLabel labelTitle, labelRole;
    JTextField fieldRole;
    JButton buttonRecord;
public CargosEditar(Cargo role){
nowRole = role;
criarComponentes();
criarEventos();
Navegador.habilitarMenu();
}
private void criarComponentes(){
setLayout(null);

labelTitle =  new JLabel("Editar Cargo", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20 ));
labelRole = new JLabel("Nome do cargo: ", JLabel.LEFT);
fieldRole = new JTextField();
buttonRecord = new JButton("Salvar");

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
      Cargo newRole = new Cargo();
      newRole.setNome(fieldRole.getText());
      
      sqlAtualizarCargo();
    }
 });
 }

private void sqlAtualizarCargo(){
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
instrucaoSQL.executeUpdate("UPDATE cargos set nome ='"+fieldRole.getText()+"'WHERE ID="+nowRole.getID()+" ");
JOptionPane.showMessageDialog(null, "Cargo '"+nowRole.getID()+"'atualizado com sucesso! ");
conexao.close();

}catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o cargo.");
    Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
    }
    
 }
    
}
