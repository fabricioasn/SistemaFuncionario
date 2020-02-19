
package sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import sistema.telas.Login;
import sistema.telas.Inicio;
import sistema.telas.CargosInserir;
/**
 *Sistema simples de funcinários com JDBC SQL SERVER
 * @author Fabricio Almeida da Silva Nunes
 * Trabalho final do curso da fundação bradesco de java avançado
 */
public class Sistema {
//criação de variáveis estáticas do painel e frame javax.swing    
    public static JPanel tela;
    public static JFrame frame;
    
    public static void main(String[] args) {
    criarComponentes(); 
    }
    private static void criarComponentes(){
    frame = new JFrame("Sistema");
    frame.setSize(800,600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    
    //tela de login chamada da classe Login no pacote telas
    tela = new Inicio();
    tela.setVisible(true);
    frame.add(tela);
    
    frame.setVisible(true);
    
    
    
    }
}
