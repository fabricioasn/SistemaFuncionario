/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.telas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistema.Navegador;
import sistema.BancoDeDados;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


/**
 * Tela para gerar relatórios de funcionários por cargo
 * @author Fabricio
 */
public class RelatoriosCargos extends JPanel {
    JLabel labelTitle, labelDescription;
            
    public RelatoriosCargos(){
        criarComponentes();
        criarEventos();
        Navegador.habilitarMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelTitle = new JLabel("Relatórios", JLabel.CENTER);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 20));
        labelDescription = new JLabel("Este gráfico representa a quantidade de funcionários por cargo", JLabel.CENTER);
        
        DefaultPieDataset dadosGrafico = this.criarDadosGrafico();
        
        JFreeChart someChart = ChartFactory.createPieChart3D("", dadosGrafico, false, true, false);
        PiePlot plot = (PiePlot) someChart.getPlot();
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        someChart.setBackgroundPaint(null);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})",
                new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        
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

    private void criarEventos() {
        
    }

    private DefaultPieDataset criarDadosGrafico() {
        
        DefaultPieDataset dados = new DefaultPieDataset();
           
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
            String query = "SELECT cargos.nome, count(*) as quantidade FROM cargos,funcionarios";
            query = query + " WHERE cargos.id = funcionarios.cargo group by cargos.nome order by nome ASC";
            resultados = instrucaoSQL.executeQuery(query);

            while (resultados.next()) {
                dados.setValue(resultados.getString("nome"), resultados.getInt("quantidade"));
            }
            
            return dados;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n"+ex.getMessage());
            Navegador.inicio();
        }
        
        return null;
    }
    
}
