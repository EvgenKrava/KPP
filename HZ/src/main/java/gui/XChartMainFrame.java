// xchart-...jar in classpath
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class XChartMainFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldA;
	List<Double> xData;
	List<Double> yData;
	private XYChart chart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XChartMainFrame frame = new XChartMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public XChartMainFrame() {
		setResizable(false);
		setTitle("XChart Test Plot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setHgap(15);
		contentPane.add(panelButtons, BorderLayout.SOUTH);

		xData = new CopyOnWriteArrayList<Double>();
		yData = new CopyOnWriteArrayList<Double>();
		JButton btnNewButtonPlot = new JButton("Plot");
		btnNewButtonPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				score();
				chart.updateXYSeries("Function", xData, yData, null);
				repaint();
			}
		});
		panelButtons.add(btnNewButtonPlot);

		JButton btnNewButtonExit = new JButton("Exit");
		btnNewButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelButtons.add(btnNewButtonExit);

		JPanel panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("a:");
		panelData.add(lblNewLabel);

		textFieldA = new JTextField();
		textFieldA.setText("1.0");
		panelData.add(textFieldA);
		textFieldA.setColumns(10);

		XChartPanel<XYChart> chartPanel = createChart();
		contentPane.add(chartPanel, BorderLayout.CENTER);
	}

	private double f(double a, double x) {
		return Math.sin(a * x) / x;
	}

	private void score() {
		double start = -9.0;
		double stop = 9.0;
		double step = 0.01;
		double a = 0;

		a = Double.parseDouble(textFieldA.getText());
		xData.clear();
		yData.clear();
		for (double x = start; x < stop; x += step) {
			xData.add(x);
			yData.add(f(a, x));
		}
	}

	private XChartPanel createChart() {
		score();

		// XYChart chart = new
		// XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
		chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").build();
		// chart.getStyler().setYAxisMin(-2.);
		// chart.getStyler().setYAxisMax(4.);
		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		XYSeries series = chart.addSeries("Function", xData, yData);
		series.setMarker(SeriesMarkers.NONE);
		// series.setMarker(SeriesMarkers.DIAMOND);

		XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);

		return chartPanel;
	}

}
