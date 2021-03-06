
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
import sistema.entidades.Funcionario;

import java.util.Locale;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import sistema.BancoDeDados;
import sistema.entidades.*;
import sistema.Navegador;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.ParseException;
/**
 *Classe de criação da tela para inserção de Funcionários no BD
 * @author Fabricio Fabricio Almeida da Silva Nunes
 */
public class FuncionariosInserir extends JPanel {
JLabel labelTitle, labelEmployerName, labelEmployerSurname, labelBirthDate, labelEmail, labelRole, labelWage;
JTextField fieldEmployerName, fieldEmployerSurname, fieldEmail;
JButton buttonRecord;
JFormattedTextField fieldWage, fieldBirthDate;
JComboBox <Cargo> comboBoxRole;
MaskFormatter mkFormatWage;



public FuncionariosInserir(){
criarComponentes();
criarEventos();
Navegador.habilitarMenu();
}

public  void criarComponentes(){
setLayout(null);

labelTitle =  new JLabel("Cadastro do Funcionário", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20 ));
labelEmployerName = new JLabel("Nome do Funcionário: ", JLabel.LEFT);
fieldEmployerName = new JTextField();
labelEmployerSurname = new JLabel("Sobrenome do Funcionário: ", JLabel.LEFT);
fieldEmployerSurname = new JTextField();
labelBirthDate = new JLabel("Data de Nascimento do Funcionário: ", JLabel.LEFT);
fieldBirthDate = new JFormattedTextField();
        try {
            MaskFormatter dateMask= new MaskFormatter("##/##/####");
            dateMask.install(fieldBirthDate);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
labelEmail = new JLabel("E-mail do Funcionário: ", JLabel.LEFT);
fieldEmail = new JTextField();
comboBoxRole = new JComboBox();
labelWage = new JLabel("Salário do Funcionário: ", JLabel.LEFT);
DecimalFormat formatter = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
fieldWage = new JFormattedTextField(formatter);
fieldWage.setValue(0.00);
buttonRecord = new JButton("Adicionar Funcionário");

labelTitle.setBounds(20, 20, 660, 40);
labelEmployerName.setBounds(150, 80, 400, 20);
labelEmployerSurname.setBounds(150, 100, 400, 40);
labelBirthDate.setBounds(150, 200, 400, 20);
labelEmail.setBounds(150, 280, 400, 40);
labelWage.setBounds(150,120,400,20);
labelRole.setBounds(150, 320, 400, 20);
fieldEmployerName.setBounds(150, 100, 400, 40);
fieldEmployerSurname.setBounds(150,140,400,40);
fieldBirthDate.setBounds(150,140,400,40);
fieldEmail.setBounds(150,140,400,40);
fieldWage.setBounds(150,140,400,40);
buttonRecord.setBounds(560, 400, 130, 40); 
comboBoxRole.setBounds(150, 340, 400, 40);

add(labelTitle);
add(labelEmployerName);
add(labelEmployerSurname);
add(labelBirthDate);
add(labelEmail);
add(labelWage);
add(labelRole);
add(labelRole);
add(fieldEmployerName);
add(fieldEmployerSurname);
add(fieldBirthDate);
add(fieldEmail);
add(fieldWage);
add(comboBoxRole);
add(buttonRecord);

sqlCarregarCargos();
        
setVisible(true);    
}
public void criarEventos(){
    buttonRecord.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
      Funcionario nowEmployer = new Funcionario();
      nowEmployer.setNome(fieldEmployerName.getText());
      nowEmployer.setSobrenome(fieldEmployerSurname.getText());
      nowEmployer.setDataNascimento(fieldBirthDate.getText());
      nowEmployer.setEmail(fieldEmail.getText());
      Cargo selectedRole = (Cargo) comboBoxRole.getSelectedItem();
      if(selectedRole!=null) nowEmployer.setCargo(selectedRole.getID());
      nowEmployer.setSalario(Double.valueOf(fieldWage.getText().replace(",", ".")));    
      sqlInserirFuncionario(nowEmployer);
    }
});    
}

private void sqlCarregarCargos(){
//interação com o BD MS SQL
Connection conexao;
Statement instrucaoSQL;
ResultSet resultados;   

try{
// conexão com o BD usando a bariável conexao do tipo Connection
conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);

//instruões SQL criando statement pela execução do método create na variavel conexao
instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
/*consulta de cargo no BD com método instrução da variável resultados 
para pesquisar cargos*/
resultados=instrucaoSQL.executeQuery("SELECT * FROM cargos order by nome asc");
comboBoxRole.removeAll();
while(resultados.next()){
    Cargo role = new Cargo();
    role.setID(resultados.getInt("ID"));
    role.setNome(resultados.getString("nome"));
    comboBoxRole.addItem(role);
   }

}catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os cargos.");
    Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
    } 

  }
private void sqlInserirFuncionario(Funcionario nowEmployer){
    //validar nomes    
    if(fieldEmployerName.getText().length()<=3){
       JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
       return;
  }
    //validar sobrenomes
    if(fieldEmployerSurname.getText().length()<=3){
       JOptionPane.showMessageDialog(null, "Por favor, preencha o sobrenome corretamente.");
       return;
  }
    //validar salário
    if(Double.parseDouble(fieldWage.getText().replace(",",".")) <=100 ){
       JOptionPane.showMessageDialog(null, "Por favor, preencha o salário corretamente.");
       return;
  }
    //validar email
    boolean validEmail = false;
    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    Pattern p = Pattern.compile(ePattern);
    Matcher m = p.matcher(fieldEmail.getText());
    validEmail = m.matches();
    if(!validEmail){
       JOptionPane.showMessageDialog(null, "Por favor, preencha o E-mail corretamente.");
       return;
  }    
    
//interação com o BD MS SQL
Connection conexao;
PreparedStatement instrucaoSQL;
ResultSet resultados;

try{
// conexão com o BD usando a bariável conexao do tipo Connection
conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);

//String de argumento para o a variável PreparedStatement InstrucaoSQL
String Template = "INSERT INTO funcionarios (nome,sobrenome,dataNascimento, email, cargo, salario)";
Template = Template+"VALUES (?,?,?,?,?,?)";
/*instruões SQL criando preparedStatement pela execução do método create na variavel conexao
, para múltiplas instâncias de consultas SQL diferentes*/
instrucaoSQL = conexao.prepareStatement(Template);
/*inserção de funcionário no BD com método setter da variaável instrucao 
para inserir cada campo das variáveis ORM entidades na tabela Funcionários*/
instrucaoSQL.setString(1, nowEmployer.getNome());
instrucaoSQL.setString(1, nowEmployer.getSobrenome());
instrucaoSQL.setString(3, nowEmployer.getDataNascimento());
instrucaoSQL.setString(4, nowEmployer.getEmail());
if(nowEmployer.getCargo()>0){
   instrucaoSQL.setInt(5, nowEmployer.getCargo());
}else{
  instrucaoSQL.setNull(5, java.sql.Types.INTEGER);
}
instrucaoSQL.setString(5, Double.toString(nowEmployer.getSalario()));
// subida das atualizações do banco para inserir os dados através do método executeUpdate
instrucaoSQL.executeUpdate();

JOptionPane.showMessageDialog(null, "Funcionário '"+nowEmployer.getNome()+nowEmployer.getSobrenome()+"'adicionado com sucesso! ");
Navegador.inicio();
conexao.close();

}catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o funcionário.");
    Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

}
