
package sistema.telas;

import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *Tela inicial para escolha das opções do sistema
 * @author Fabricio Almeida da Silva Nunes
 */
public class Inicio extends JPanel {
JLabel labelTitle;

public Inicio(){
criarComponentes();
criarEventos();
}

public void criarComponentes(){
setLayout(null);
labelTitle = new JLabel("Escolha uma opção no menu superior", JLabel.CENTER);
labelTitle.setBounds(20, 100, 660, 40);

add(labelTitle);

setVisible(true);
}

private void criarEventos(){
    
}

}
