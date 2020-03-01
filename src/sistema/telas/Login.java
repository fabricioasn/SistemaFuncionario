

package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import sistema.Navegador;

/**
 *Classe de definição da tela de Login do sistema de cadastro
 * dos funcionários no banco SQL SERVER usando persistÊncia JDBC
 * @author Fabricio
 */
public class Login extends JPanel {
JLabel labelUser;
JTextField fieldUser;
JLabel labelPswd;
JPasswordField fieldPswd;
JButton buttonEntry;    

public Login(){
criarComponentes();
criarEventos();
}

private void criarComponentes(){
setLayout(null); // sem gerenciador de layout

//Instanciação dos itens de exibição, com textos e alinhamentos específicos. 
JLabel labelTitle =  new JLabel("Seja bem-vindo ao sistema da Company S/A!", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 18 ));
labelUser = new JLabel("Usuário: ", JLabel.LEFT);
fieldUser = new JTextField();
labelPswd = new JLabel("Senha: ", JLabel.LEFT);
fieldPswd = new JPasswordField();
buttonEntry = new JButton("Entrar");

// Definição do posicionamento e tamanho de cada componente
labelTitle.setBounds(20,100,660,40);
labelUser.setBounds(250,220,200,20);
fieldUser.setBounds(250,240,200,40);
labelPswd.setBounds(250,280,200,20);        
fieldPswd.setBounds(250,300,200,40);
buttonEntry.setBounds(250,350,200,40);


add(labelTitle);
add(labelUser);
add(fieldUser);
add(labelPswd);
add(fieldPswd);
add(buttonEntry);
        
setVisible(true);
}

private void criarEventos(){
buttonEntry.addActionListener(new ActionListener(){ 
    @Override
    public void actionPerformed(ActionEvent e){
     //validação das credenciais no banco para permissão do acesso ao sistema
     if(fieldUser.getText().equals("Admin")&& new String(fieldPswd.getPassword()).equals("dbAdmin4321!")){
       Navegador.inicio();
     }else{
         JOptionPane.showMessageDialog(null, "Acesso não permitido! Credenciais(Login ou Senha)inválidas.");
     }
     }
   }
);
}
}
