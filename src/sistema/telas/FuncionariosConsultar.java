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
import sistema.entidades.*;

/**
 *Tela para cosulta de dados na entidade funcionários que busca a table cargos do BD
 * @author Fabricio Almeida da Silva Nunes
 */
public class FuncionariosConsultar extends JPanel {
    Funcionario nowEmployer;
    JLabel labelTitle, labelEmployer;
    JTextField fieldEmployer;
    JButton buttonSearch, buttonEdit, buttonDelete;
    DefaultListModel <Funcionario> employerListModel = new DefaultListModel();
    JList <Funcionario> listEmployers;
    
public FuncionariosConsultar(){
   criarComponentes();
   criarEventos();
   Navegador.habilitarMenu();
   }
private void criarComponentes(){
setLayout(null);

labelTitle =  new JLabel("Consulta de Funcionários", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20 ));
labelEmployer = new JLabel("Nome do Funcionário: ", JLabel.LEFT);
fieldEmployer = new JTextField();
buttonSearch = new JButton("Pesquisar Funcionário");
buttonEdit = new JButton("Editar Funcionário");
buttonEdit.setEnabled(false);
buttonDelete = new JButton("Deletar Funcionário");
buttonDelete.setEnabled(false);
employerListModel = new DefaultListModel();
listEmployers = new JList(employerListModel);
listEmployers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);


labelTitle.setBounds(20,20,660,40);
labelEmployer.setBounds(150,120,400,20);
fieldEmployer.setBounds(150, 140, 400, 40);
listEmployers.setBounds(150, 200, 400, 240); 
buttonSearch.setBounds(580, 180, 130, 60);
buttonEdit.setBounds(580, 380, 130, 60); 
buttonDelete.setBounds(580, 420, 130, 60);

add(labelTitle);
add(labelEmployer);
add(fieldEmployer);
add(listEmployers);
add(buttonSearch);
add(buttonEdit);
add(buttonDelete);

setVisible(true);
}
private void criarEventos(){
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarFuncionarios(fieldEmployer.getText());
            }
        });
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Navegador.funcionariosEditar(nowEmployer);
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlDeletarFuncionarios();
            }
        });
        listEmployers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nowEmployer = listEmployers.getSelectedValue();
                if(nowEmployer == null){
                    buttonEdit.setEnabled(false);
                    buttonDelete.setEnabled(false);
                }else{
                    buttonEdit.setEnabled(true);
                    buttonDelete.setEnabled(true);
                }
            }
        });     
}

private void sqlPesquisarFuncionarios(String nome){
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
   /*consulta do funcionário no BD com método execute na variaável instrucao 
   listar o funcionário pela variável nome mapeada na entidade ORM para o BD*/
   resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome like '%"+nome+"%' order by nome ASC");
            
            employerListModel.clear();

            while (resultados.next()) {                
                Funcionario employer = new Funcionario();
                employer.setID(resultados.getInt("id"));
                employer.setNome(resultados.getString("nome"));
                employer.setSobrenome(resultados.getString("sobrenome"));
                employer.setDataNascimento(resultados.getString("dataNascimento"));
                employer.setEmail(resultados.getString("email"));
            if(resultados.getString("cargo")!= null)employer.setCargo(Integer.parseInt(resultados.getString("cargo")));
                employer.setSalario(Double.parseDouble(resultados.getString("salario")));
                employerListModel.addElement(employer);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os Cargos.");
            Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }    
}
private void sqlDeletarFuncionarios(){
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Funcionário "+nowEmployer.getNome()+"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if(confirmacao == JOptionPane.YES_OPTION){

            // conexão
            Connection conexao;
            // instrucao SQL
            Statement instrucaoSQL;


            try {
                // conectando ao banco de dados
                conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);           

                // criando a instrução SQL 
                instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                /*execução de atualização no BD com método execute na variaável instrucao 
                para excluir o funcionário no qual o id é igual a vaviável nowEmployer da classe Funcionário*/
                instrucaoSQL.executeUpdate("DELETE funcionarios WHERE id="+nowEmployer.getID()+"");            

                JOptionPane.showMessageDialog(null, "Funcionario" +nowEmployer.getNome()+ "deletado com sucesso!");
                Navegador.inicio();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Funcionário.");
                Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }    
}
    
}
