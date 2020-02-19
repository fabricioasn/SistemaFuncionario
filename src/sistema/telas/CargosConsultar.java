
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
JList <Cargo> listaCargos;   

public CargosConsultar(){
    
}

}
