/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistema.Navegador;
import sistema.BancoDeDados;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *Tela para gerar relatórios de funcionários por Salário
 * @author Fabricio Almeida da Silva Nunes
 */
public class RelatoriosSalarios extends JPanel {
    JLabel labelTitle, labelDescription;
            
public RelatoriosSalarios(){
criarComponentes();
criarEventos();
Navegador.habilitarMenu();
}

private void criarComponentes() {
setLayout(null);
        
labelTitle = new JLabel("Relatórios", JLabel.CENTER);
labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20));
labelDescription = new JLabel("Este gráfico representa a quantidade de funcionários por faixas salariais.", JLabel.CENTER);
        
CategoryDataset dataGraph = this.criarDadosGrafico();
        
JFreeChart someChart = ChartFactory.createBarChart3D("", null , "Quantidade de Funcionários", dataGraph, 
PlotOrientation.VERTICAL, true, true, false);
CategoryPlot plot = (CategoryPlot) someChart.getCategoryPlot();
plot.setBackgroundPaint(null);
plot.setOutlinePaint(null);
someChart.setBackgroundPaint(null);

plot.getRangeAxis().setLowerBound(0);
plot.getRangeAxis().setRange(new Range(0 , 5));
plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
plot.getRangeAxis().setAutoRange(false);
Font font3 = new Font(labelTitle.getFont().getName(), Font.PLAIN, 10);
plot.getRangeAxis().setLabelFont(font3);

plot.getDomainAxis().setVisible(false);

BarRenderer renderer = (BarRenderer) plot.getRenderer();
renderer.setItemMargin(-2);

ChartPanel chartPanel = new ChartPanel(someChart) {
@Override
public Dimension getPreferredSize() {
return new Dimension(660, 340);
    }
 };
        
labelTitle.setBounds(20, 20, 660, 40);
labelDescription.setBounds(20, 50, 660, 40);
chartPanel.setBounds(20, 100, 660, 340);
        
add(labelTitle);
add(labelDescription);
add(chartPanel);
        
setVisible(true);
 }
private void criarEventos(){
    
 }

@SuppressWarnings("empty-statement")
private CategoryDataset criarDadosGrafico(){

DefaultCategoryDataset data = new DefaultCategoryDataset();

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
String query = "SELECT";
query = query + " COUNT(case when salario < 1000 then 1 end) AS Faixa_Salarial_1,";
query = query + " COUNT(case when salario >= 1000 AND salario < 2000 then 1 end) AS Faixa_Salarial_2,";
query = query + " COUNT(case when salario >= 2000 AND salario < 4000 then 1 end) AS Faixa_Salarial_3,";
query = query + " COUNT(case when salario >= 4000 then 1 end) AS Faixa_Salarial_4";
query = query + " FROM funcionarios";
resultados = instrucaoSQL.executeQuery(query);

while (resultados.next()) {
data.addValue(resultados.getInt("Faixa_Salarial_1"),"Até R$ 1.000,00", "< R$ 1.000,00");
data.addValue(resultados.getInt("Faixa_Salarial_2"),"De R$ 1.000,00 até R$ 2.000,00", "R$ 1.000,00 - R$ 2.000,00");
data.addValue(resultados.getInt("Faixa_Salarial_3"),"De R$ 2.000,00 até R$ 4.000,00", "R$ 2.000,00 - R$ 4.000,00");
data.addValue(resultados.getInt("Faixa_Salarial_4"),"A partir de R$ 4.000,00", "> R$ 4.000,00");
}
            
 return data;
            
}catch (SQLException ex) {
JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório por Salários.\n\n"+ex.getMessage());
Navegador.inicio();
}    
 return null;    
 }
}
