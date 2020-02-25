
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
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;

/**
 *tela para cosulta de dados na entidade cargos que busca a tablea cargod do BD
 * @author Fabricio Almeida da Silva Nunes
 */
public class CargosConsultar extends JPanel {
Cargo nowRole;  
JLabel labelTitle, labelRole;
JTextField fieldRole;
JButton buttonSearch, buttonEdit, buttonDelete;
DefaultListModel <Cargo> roleListModels = new DefaultListModel();
JList <Cargo> listRoles;   

public CargosConsultar(){
    criarComponentes();
    criarEventos();
}
public void criarComponentes(){
 setLayout(null);

labelTitle =  new JLabel("Consulta de Cargos", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20 ));
labelRole = new JLabel("Nome do Cargo: ", JLabel.LEFT);
fieldRole = new JTextField();
buttonSearch = new JButton("Pesquisar Cargo");
buttonEdit = new JButton("Editar Cargo");
buttonEdit.setEnabled(false);
buttonDelete = new JButton("Deletar Cargo");
buttonDelete.setEnabled(false);
roleListModels = new DefaultListModel();
listRoles = new JList(roleListModels);
listRoles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);


labelTitle.setBounds(20,100,660,40);
labelRole.setBounds(150,120,400,20);
fieldRole.setBounds(150, 140, 400, 40);
listRoles.setBounds(560, 140, 130, 40); 
buttonSearch.setBounds(150, 200, 400, 240);
buttonEdit.setBounds(560, 360, 130, 40); 
buttonDelete.setBounds(560, 400, 130, 40);

add(labelTitle);
add(labelRole);
add(fieldRole);
add(listRoles);
add(buttonSearch);
add(buttonEdit);
add(buttonDelete);

        
setVisible(true);   
 }
public void criarEventos(){
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarCargos(fieldRole.getText());
            }
        });
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.cargosEditar(nowRole);
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlDeletarCargo();
            }
        });
        listRoles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nowRole = listRoles.getSelectedValue();
                if(nowRole == null){
                    buttonEdit.setEnabled(false);
                    buttonDelete.setEnabled(false);
                }else{
                    buttonEdit.setEnabled(true);
                    buttonDelete.setEnabled(true);
                }
            }
        });    
 }
private void sqlPesquisarCargos(String nome){
    // conexão
    Connection conexao;
    // instrucao SQL
    Statement instrucaoSQL;
    // resultados
    ResultSet resultados;
        
    try {
    /* conectando ao banco de dados através do método getConnection da classe DriverManager, 
    que gerencia o controle do middleware*/
    conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);            
            
   // criando a instrução SQL utilizando método para criar statement de instrução
   instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
   /*consulta do cargo no BD com método execute na variaável instrucao 
   listar o cargo pela variável nome mapeada na entidade ORM para o BD*/
   resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome like '%"+nome+"%'");
            
            roleListModels.clear();

            while (resultados.next()) {                
                Cargo role = new Cargo();
                role.setID(resultados.getInt("id"));
                role.setNome(resultados.getString("nome"));
                
                roleListModels.addElement(role);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
private void sqlDeletarCargo(){
         int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Cargo "+nowRole.getNome()+"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if(confirmacao == JOptionPane.YES_OPTION){

            // conexão
            Connection conexao;
            // instrucao SQL
            Statement instrucaoSQL;
            // resultados
            ResultSet resultados;

            try {
                // conectando ao banco de dados
                conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);           

                // criando a instrução SQL 
                instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                /*execução de atualização no BD com método execute na variaável instrucao 
                para excluir o cargo no qual o id é igual a vaviável nowRole da classe Cargo*/
                instrucaoSQL.executeUpdate("DELETE cargos WHERE id="+nowRole.getID()+"");            

                JOptionPane.showMessageDialog(null, "Cargo" +nowRole.getID()+ "deletado com sucesso!");
                Navegador.inicio();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
                Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }   
 }
 }



