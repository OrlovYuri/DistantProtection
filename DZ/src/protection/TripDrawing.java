package protection;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TripDrawing {
	private XYSeries resistanceChar = new XYSeries("Характеристика срабатывания",false);
	private XYSeries resistanceAB = new XYSeries("Годограф АВ",false);
	private XYSeries resistanceBC = new XYSeries("Годограф BC",false);
	private XYSeries resistanceCA = new XYSeries("Годограф CA",false);
	private double samples = 1;
	private int samplesBuffer = (int) (80/samples);
	private int sum = 0;

	@SuppressWarnings("deprecation")
	public TripDrawing() {
		JFrame frame = new JFrame();
		JFreeChart chart = createCombinedChart();
		ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
		panel.setPreferredSize(new java.awt.Dimension(500, 270));
		frame.getContentPane().add(new ChartPanel(chart)); 
		frame.setSize(800,800); 
		frame.setContentPane(panel);
		panel.setLayout(null);
		frame.show(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resistanceChar.add(0,0);
		resistanceChar.add(Protection.getR1(),Protection.getXtrip());
		resistanceChar.add(Protection.getR3(),Protection.getXtrip());
		resistanceChar.add(Protection.getR2(),Protection.getX2());
		resistanceChar.add(0,0);
		resistanceAB.add(3,3);
		resistanceAB.add(5,5);
	}

	private JFreeChart createCombinedChart() {
		// Данные для графиков:
		XYSeriesCollection data = new XYSeriesCollection();	
		XYSeriesCollection dataTrip = new XYSeriesCollection();	
		data.addSeries(resistanceChar); 
		data.addSeries(resistanceAB);
		data.addSeries(resistanceBC);
		data.addSeries(resistanceCA);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// Видимость фигур в узловых точках:
		renderer.setSeriesShapesVisible(0, false); 
		renderer.setSeriesShapesVisible(1, false); 
		renderer.setSeriesShapesVisible(2, false);
		renderer.setSeriesShapesVisible(3, false);  
		// Цвета графиков:
		renderer.setSeriesPaint(0, Color.black);
		renderer.setSeriesPaint(1, Color.yellow);
		renderer.setSeriesPaint(2, Color.green);
		renderer.setSeriesPaint(3, Color.red);
		
		NumberAxis rangeAxis = new NumberAxis("X, Ом");
		rangeAxis.setAutoRange(true);
		XYPlot subplot = new XYPlot(data, null, rangeAxis, renderer);  

		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("R, Ом"));
		plot.add(subplot);
		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setDomainCrosshairVisible(true);
		return new JFreeChart("TripSeries", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public void setData(double rab, double xab, double rbc, double xbc, double rca, double xca){
		if(sum == samplesBuffer) { 
			sum = 0;
			resistanceAB.add(rab, xab);
			resistanceBC.add(rbc, xbc);
			resistanceCA.add(rca, xca);
		}
		sum++;
	}
}