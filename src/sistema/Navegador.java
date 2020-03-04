
package sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import sistema.entidades.*;
import sistema.telas.*;


/**
 * Classe VIEW que gerencia a navegação pelo sistema entre todas as telas, administra o menu
 * e comunica todos os Swing ao DAO
 * @author Fabricio Almeida da Silva Nunes
 */
public class Navegador {
private static boolean menuBuilt;   
private static boolean menuEnabled;
private static JMenuBar menuBar;
private static JMenu menuFiles, menuEmployers, menuRoles, menuReports;
private static JMenuItem miExit, miEmployersQuery, miEmployersRegister, miRolesQuery, miRolesRegister;
private static JMenuItem miReportRoles, miReportWage;
        
public static void login(){
Sistema.tela= new Login();
Sistema.frame.setTitle("Funcionários Company SA");
Navegador.atualizarTela();
}
public static void inicio(){
Sistema.tela= new Inicio();
Sistema.frame.setTitle("Funcionários Company SA");
Navegador.atualizarTela();    
}
public static void funcionariosCadastrar(){
Sistema.tela= new FuncionariosInserir();
Sistema.frame.setTitle("Funcionários Company SA - Cadastrar cargos");
Navegador.atualizarTela();     
}
public static void funcionariosConsultar(){
Sistema.tela= new FuncionariosConsultar();
Sistema.frame.setTitle("Funcionários Company SA - Consultar cargos");
Navegador.atualizarTela();   
}
public static void funcionariosEditar(Funcionario employer){
Sistema.tela= new FuncionariosEditar(employer);
Sistema.frame.setTitle("Funcionários Company SA - Editar cargos");
Navegador.atualizarTela();     
}
public static void cargosCadastrar(){
Sistema.tela= new CargosInserir();
Sistema.frame.setTitle("Funcionários Company SA - Cadastrar cargos");
Navegador.atualizarTela();     
}
public static void cargosConsultar(){
Sistema.tela= new CargosConsultar();
Sistema.frame.setTitle("Funcionários Company SA - Consultar cargos");
Navegador.atualizarTela();     
}
public static void cargosEditar(Cargo role){
Sistema.tela= new CargosEditar(role);
Sistema.frame.setTitle("Funcionários Company SA - Editar cargos");
Navegador.atualizarTela();     
}
public static void relatorioCargos(){
Sistema.tela = new RelatoriosCargos();
Sistema.frame.setTitle("Funcionários Company SA - Relatórios de Funcionários por Cargos");
Navegador.atualizarTela();

}
public static void relatorioSalarios(){
    
}

private static void atualizarTela(){
Sistema.frame.getContentPane().removeAll();
Sistema.tela.setVisible(true);
Sistema.frame.add(Sistema.tela);

Sistema.frame.setVisible(true);
}
private static void construirMenu(){
  if(!menuBuilt){
  menuBuilt=true;
  menuBar = new JMenuBar();
  
  //menu arquivo
  menuFiles = new JMenu("Arquivo");
  menuBar.add(menuFiles);
  miExit = new JMenuItem("Sair");
  menuFiles.add(miExit);
  
  //menu funcionários
  menuEmployers = new JMenu("Funcionários");
  menuBar.add(menuEmployers);
  miEmployersRegister = new JMenuItem("Cadastrar Funcionários");
  menuEmployers.add(miEmployersRegister);
  miEmployersQuery = new JMenuItem("Consultar Funcionários");
  menuEmployers.add(miEmployersQuery);
  
  //menu cargos
  menuRoles = new JMenu("Cargos");
  menuBar.add(menuRoles);
  miRolesRegister = new JMenuItem("Cadastrar Cargos");
  menuRoles.add(miRolesRegister);
  miRolesQuery = new JMenuItem("Consultar Cargos");
  menuRoles.add(miRolesQuery);  
  
  //menu relatórios
  menuReports = new JMenu("Relatórios");
  menuBar.add(menuReports);
  miReportRoles = new JMenuItem("Relatórios por cargo");
  menuReports.add(miReportRoles);
  miReportWage = new JMenuItem("Relatórios por salário");
  menuReports.add(miReportWage);    
  criarEventosMenu();
  }  
}
public static void habilitarMenu(){
  if(!menuBuilt) construirMenu();
  if(!menuEnabled){
  menuEnabled=true;
  Sistema.frame.setJMenuBar(menuBar); 
  }

}
public static void desabilitarMenu(){
  if(!menuEnabled){
  menuEnabled=false;
  Sistema.frame.setJMenuBar(null);     
  }
}
private static void criarEventosMenu(){
   miExit.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   System.exit(0);    
   }
   });
   
//funcionário
   miEmployersRegister.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   funcionariosCadastrar();    
   }
   });
   miEmployersQuery.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   funcionariosConsultar();    
   }
   });
   
//cargos
   miRolesRegister.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   cargosCadastrar();    
   }
   });
   miRolesQuery.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   cargosConsultar();    
   }
   });

//relatórios
   miReportRoles.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   relatorioCargos();    
   }
   });
   miReportWage.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
   relatorioSalarios();    
   }
   });
   
}




}
