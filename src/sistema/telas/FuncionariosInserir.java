package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.*;

/**
 *Classe de criação da tela para inserção de Funcionários no BD
 * @author Fabricio Fabricio Almeida da Silva Nunes  
 */

public class FuncionariosInserir extends JPanel {
    
    JLabel labelTitle, labelEmployerName, labelEmployerSurname, labelBirthDate, labelEmail, labelRole, labelWage;
    JTextField fieldEmployerName, fieldEmployerSurname, fieldEmail;
    JFormattedTextField fieldBirthDate, fieldWage;
    JComboBox<Cargo> comboboxRole;
    JButton buttonRecord;
    MaskFormatter mkWage;
            
    public FuncionariosInserir(){
        criarComponentes();
        criarEventos();
        Navegador.habilitarMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelTitle = new JLabel("Cadastro de Funcionario", JLabel.CENTER);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20));      
        labelEmployerName = new JLabel("Nome:", JLabel.LEFT);
        fieldEmployerName = new JTextField();     
        labelEmployerSurname = new JLabel("Sobrenome:", JLabel.LEFT);
        fieldEmployerSurname = new JTextField();     
        labelBirthDate = new JLabel("Data de Nascimento:", JLabel.LEFT);
        fieldBirthDate = new JFormattedTextField();
        try {
            MaskFormatter dateMask= new MaskFormatter("##/##/####");
            dateMask.install(fieldBirthDate);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelEmail = new JLabel("E-mail:", JLabel.LEFT);
        fieldEmail = new JTextField();     
        labelRole = new JLabel("Cargo:", JLabel.LEFT);
        comboboxRole = new JComboBox();
        labelWage = new JLabel("Salário:", JLabel.LEFT);
        DecimalFormat formatter = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        fieldWage = new JFormattedTextField(formatter);
        fieldWage.setValue(0.00);
        buttonRecord = new JButton("Adicionar Funcionário.");
        
        labelTitle.setBounds(20, 20, 660, 40);
        labelEmployerName.setBounds(150, 80, 400, 20);
        fieldEmployerName.setBounds(150, 100, 400, 40);
        labelEmployerSurname.setBounds(150, 140, 400, 20);
        fieldEmployerSurname.setBounds(150, 160, 400, 40);
        labelBirthDate.setBounds(150, 200, 400, 20);
        fieldBirthDate.setBounds(150, 220, 400, 40);
        labelEmail.setBounds(150, 260, 400, 20);
        fieldEmail.setBounds(150, 280, 400, 40);
        labelRole.setBounds(150, 320, 400, 20);
        comboboxRole.setBounds(150, 340, 400, 40);
        labelWage.setBounds(150, 380, 400, 20);
        fieldWage.setBounds(150, 400, 400, 40);
        buttonRecord.setBounds(560, 400, 130, 40); 
        
        add(labelTitle);
        add(labelEmployerName);
        add(fieldEmployerName);
        add(labelEmployerSurname);
        add(fieldEmployerSurname);
        add(labelBirthDate);
        add(fieldBirthDate);
        add(labelEmail);
        add(fieldEmail);
        add(labelRole);
        add(comboboxRole);
        add(labelWage);
        add(fieldWage);
        add(buttonRecord);
        
        sqlCarregarCargos();
        
        setVisible(true);
    }

    private void criarEventos() {
        buttonRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionario nowEmployer = new Funcionario();
                nowEmployer.setNome(fieldEmployerName.getText());
                nowEmployer.setSobrenome(fieldEmployerSurname.getText());
                nowEmployer.setDataNascimento(fieldBirthDate.getText());
                nowEmployer.setEmail(fieldEmail.getText());
                Cargo selectedRole = (Cargo) comboboxRole.getSelectedItem();
                if(selectedRole != null) nowEmployer.setCargo(selectedRole.getID());
                
                nowEmployer.setSalario(Double.valueOf(fieldWage.getText().replace(",", ".")));
                
                sqlInserirFuncionario(nowEmployer);                        
            }
        });
    }

    private void sqlCarregarCargos() {        
        // conexão
        Connection conexao;
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;
        
        try {
            // conectando ao banco de dados
            conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);            
            
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * from cargos order by nome asc");
            comboboxRole.removeAll();          
            
            while (resultados.next()) {
                Cargo role = new Cargo();
                role.setID(resultados.getInt("id"));
                role.setNome(resultados.getString("nome"));
                comboboxRole.addItem(role);
            }
            comboboxRole.updateUI();
            
            conexao.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os cargos.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlInserirFuncionario(Funcionario employer) {
        
        // validando nome
        if(fieldEmployerName.getText().length() <= 3){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente.");
            return;
        }
        
        // validando sobrenome
        if(fieldEmployerSurname.getText().length() <= 3){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o sobrenome corretamente.");
            return;
        }
        
        // validando sobrenome
        if(Double.parseDouble(fieldWage.getText().replace(",", ".")) <= 100){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o salário corretamente.");
            return;
        }
        
        // validando email
        Boolean validEmail = false;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(fieldEmail.getText());
        validEmail = m.matches();
        
        if(!validEmail){
            JOptionPane.showMessageDialog(null, "Por favor, preencha o email corretamente.");
            return;
        }
        
        //interação com o BD MS SQL
        // conexão
        Connection conexao;
        // instrucao SQL
        PreparedStatement instrucaoSQL;
        
        try {
        // conexão com o BD usando a bariável conexao do tipo Connection
            conexao = DriverManager.getConnection(BancoDeDados.conectionString, BancoDeDados.user, BancoDeDados.password);
        //String de argumento para o a variável PreparedStatement InstrucaoSQL    
            String template = "INSERT INTO funcionarios (nome,sobrenome,dataNascimento,email,cargo,salario)";
            template = template+" VALUES (?,?,?,?,?,?)";
        /*instruões SQL criando preparedStatement pela execução do método create na variavel conexao
, para múltiplas instâncias de consultas SQL diferentes*/
            instrucaoSQL = conexao.prepareStatement(template);
        /*inserção de funcionário no BD com método setter da variaável instrucao 
para inserir cada campo das variáveis ORM entidades na tabela Funcionários.*/
            instrucaoSQL.setString(1, employer.getNome());
            instrucaoSQL.setString(2, employer.getSobrenome());
            instrucaoSQL.setString(3, employer.getDataNascimento());
            instrucaoSQL.setString(4, employer.getEmail());
            if(employer.getCargo() > 0){
                instrucaoSQL.setInt(5, employer.getCargo());
            }else{
                instrucaoSQL.setNull(5, java.sql.Types.INTEGER);
            }
            instrucaoSQL.setString(6, Double.toString(employer.getSalario()));
        // subida das atualizações do banco para inserir os dados através do método executeUpdate
            instrucaoSQL.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
            Navegador.inicio();
            
            conexao.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o Funcionário.");
            Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}