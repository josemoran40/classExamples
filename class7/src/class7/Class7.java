/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class7;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author jose_
 */
public class Class7 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
	
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
 
        dataset.addValue(20, "Semestre 1", "Compi 1");
        dataset.addValue(40, "Semestre 1", "Orga");
        dataset.addValue(56, "Semestre 1", "EDD");
        dataset.addValue(19, "Semestre 1", "IO1");
 
        dataset.addValue(25, "Semestre 2", "Compi 1");
        dataset.addValue(41, "Semestre 2", "Orga");
        dataset.addValue(35, "Semestre 2", "EDD");
        dataset.addValue(42, "Semestre 2", "IO1");
        
		dataset.addValue(19, "Semestre 3", "Compi 1");
        dataset.addValue(27, "Semestre 3", "Orga");
        dataset.addValue(52, "Semestre 3", "EDD");
        dataset.addValue(39, "Semestre 3", "IO1");
		
	
 
        JFreeChart barChart = ChartFactory.createBarChart(
                "Grafica de Barras", 
                "Semestre", 
                "Curso", 
                dataset,
                PlotOrientation.VERTICAL, true, true, false);
 
        ChartPanel panel = new ChartPanel(barChart);
        panel.setPreferredSize(new java.awt.Dimension(560, 367));
 
        
        JFrame ventana = new JFrame("Grafica");
        ventana.setVisible(true);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        ventana.add(panel);
	}
	
}
