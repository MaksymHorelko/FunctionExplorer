package main;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import differentiation.impl.NumericalDifferentiator;
import fileService.CsvDataService;
import fileService.impl.CsvDataServiceImpl;
import fileService.impl.TableFileReader;
import function.AppFunction;
import function.DataPoint;
import function.impl.AppFunctionService;
import generator.impl.ValuesGeneratorImpl;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFunction;
	private JComboBox<String> comboBoxInputMode;
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldFilePath;

	private double start = -5;
	private double stop = 5;
	private double step = 0.1;
	private JTextField textFieldStart;
	private JTextField textFieldStep;
	private JTextField textFieldStop;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("FunctionExplorer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInputMode = new JLabel("Input Mode:");
		lblInputMode.setBounds(12, 12, 102, 15);
		contentPane.add(lblInputMode);

		comboBoxInputMode = new JComboBox<String>();
		comboBoxInputMode.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Manual Function", "Manual Values", "From File" }));
		comboBoxInputMode.setBounds(102, 9, 150, 20);
		contentPane.add(comboBoxInputMode);

		JLabel lblFunction = new JLabel("Function:");
		lblFunction.setBounds(12, 40, 70, 15);
		contentPane.add(lblFunction);

		textFieldFunction = new JTextField();
		textFieldFunction.setBounds(85, 38, 200, 20);
		contentPane.add(textFieldFunction);
		textFieldFunction.setColumns(10);

		JLabel lblX = new JLabel("x:");
		lblX.setBounds(12, 41, 20, 15);
		contentPane.add(lblX);

		textFieldX = new JTextField();
		textFieldX.setBounds(40, 39, 60, 20);
		contentPane.add(textFieldX);
		textFieldX.setColumns(10);

		JLabel lblY = new JLabel("y:");
		lblY.setBounds(120, 41, 20, 15);
		contentPane.add(lblY);

		textFieldY = new JTextField();
		textFieldY.setBounds(150, 39, 60, 20);
		contentPane.add(textFieldY);
		textFieldY.setColumns(10);

		JLabel lblFilePath = new JLabel("FilePath:");
		lblFilePath.setBounds(12, 41, 70, 15);
		contentPane.add(lblFilePath);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(85, 39, 200, 20);
		contentPane.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnPlot = new JButton("Plot");
		btnPlot.setBounds(300, 38, 70, 20);
		contentPane.add(btnPlot);

		// start | stop | step
		JLabel lblStart = new JLabel("start: ");
		lblStart.setBounds(12, 75, 70, 15);
		contentPane.add(lblStart);

		textFieldStart = new JTextField();
		textFieldStart.setBounds(55, 73, 70, 19);
		contentPane.add(textFieldStart);
		textFieldStart.setColumns(10);

		JLabel lblStop = new JLabel("stop: ");
		lblStop.setBounds(135, 75, 70, 15);
		contentPane.add(lblStop);

		JLabel lblStep = new JLabel("step: ");
		lblStep.setBounds(257, 75, 70, 15);
		contentPane.add(lblStep);

		textFieldStep = new JTextField();
		textFieldStep.setBounds(300, 73, 70, 19);
		contentPane.add(textFieldStep);
		textFieldStep.setColumns(10);

		textFieldStop = new JTextField();
		textFieldStop.setBounds(177, 73, 70, 19);
		contentPane.add(textFieldStop);
		textFieldStop.setColumns(10);

		// Create the chart panel
		JPanel chartPanel = new JPanel();
		chartPanel.setBounds(12, 130, 760, 460);
		contentPane.add(chartPanel);

		textFieldFunction.setVisible(true);
		lblFunction.setVisible(true);
		textFieldX.setVisible(false);
		lblX.setVisible(false);
		textFieldY.setVisible(false);
		lblY.setVisible(false);
		textFieldFilePath.setVisible(false);
		lblFilePath.setVisible(false);

		btnPlot.addActionListener(e -> {
			JFreeChart chart = createEmptyChart();
			switch (comboBoxInputMode.getSelectedIndex()) {
			case 0:
				try {
					if (!(isfieldIsEmpty(textFieldStart) || isfieldIsEmpty(textFieldStop)
							|| isfieldIsEmpty(textFieldStep))) {
						start = Double.parseDouble(textFieldStart.getText());
						stop = Double.parseDouble(textFieldStop.getText());
						step = Double.parseDouble(textFieldStep.getText());
						if (!isValidFunctionBounds()) {
							JOptionPane.showMessageDialog(null, "Невірно вказані значення  аргументу", "Помилка!",
									JOptionPane.ERROR_MESSAGE);
							break;
						}

					} else {
						JOptionPane.showMessageDialog(null, "Невірно вказані значення  аргументу", "Помилка!",
								JOptionPane.ERROR_MESSAGE);
						break;
					}

					String function = textFieldFunction.getText();
					chart = createChart(new FunctionExplorer().analyticFunctionFromString(function));

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				break;

			case 1:

				try {
					ArrayList<Double> xList = parseNumbers(textFieldX.getText());
					ArrayList<Double> yList = parseNumbers(textFieldY.getText());
					chart = createChart(xList, yList);

				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				break;

			case 2:

				String filePath = textFieldFilePath.getText();
				if (doesFileExist(filePath)) {
					chart = createChart(filePath);
				} else {
					JOptionPane.showMessageDialog(null, "Файл не знайдений", "Помилка!", JOptionPane.ERROR_MESSAGE);
				}

				break;

			default:
				break;
			}

			ChartPanel panel = new ChartPanel(chart);
			chartPanel.removeAll();
			chartPanel.add(panel);
			chartPanel.revalidate();
			chartPanel.repaint();
		});

		comboBoxInputMode.addActionListener(e -> {
			switch (comboBoxInputMode.getSelectedIndex()) {
			case 0:
				textFieldStart.setVisible(true);
				lblStart.setVisible(true);
				textFieldStop.setVisible(true);
				lblStop.setVisible(true);
				textFieldStep.setVisible(true);
				lblStep.setVisible(true);

				textFieldFunction.setVisible(true);
				lblFunction.setVisible(true);
				textFieldX.setVisible(false);
				lblX.setVisible(false);
				textFieldY.setVisible(false);
				lblY.setVisible(false);
				textFieldFilePath.setVisible(false);
				lblFilePath.setVisible(false);
				break;
			case 1:
				textFieldFunction.setVisible(false);
				lblFunction.setVisible(false);
				textFieldX.setVisible(true);
				lblX.setVisible(true);
				textFieldY.setVisible(true);
				lblY.setVisible(true);
				textFieldFilePath.setVisible(false);
				lblFilePath.setVisible(false);

				textFieldStart.setVisible(false);
				lblStart.setVisible(false);
				textFieldStop.setVisible(false);
				lblStop.setVisible(false);
				textFieldStep.setVisible(false);
				lblStep.setVisible(false);

				break;
			case 2:
				textFieldFunction.setVisible(false);
				lblFunction.setVisible(false);
				textFieldX.setVisible(false);
				lblX.setVisible(false);
				textFieldY.setVisible(false);
				lblY.setVisible(false);
				textFieldFilePath.setVisible(true);
				lblFilePath.setVisible(true);

				textFieldStart.setVisible(false);
				lblStart.setVisible(false);
				textFieldStop.setVisible(false);
				lblStop.setVisible(false);
				textFieldStep.setVisible(false);
				lblStep.setVisible(false);

				break;
			default:
				break;
			}
		});
	}

	private boolean isfieldIsEmpty(JTextField textField) {
		String text = textField.getText();
		if (text == null || text.isEmpty() || text.isBlank())
			return true;
		try {
			Double.parseDouble(text);
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private boolean isValidFunctionBounds() {
		return stop > start ? stop > step : false;
	}

	private boolean doesFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists() && !file.isDirectory();
	}

	private JFreeChart createChart(XYSeriesCollection dataSet) {
		return ChartFactory.createXYLineChart("", "X", "Y", dataSet, PlotOrientation.VERTICAL, rootPaneCheckingEnabled,
				rootPaneCheckingEnabled, rootPaneCheckingEnabled);
	}

	private JFreeChart createEmptyChart() {
		return ChartFactory.createXYLineChart("", "X", "Y", new XYSeriesCollection(), PlotOrientation.VERTICAL,
				rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
	}

	private JFreeChart createChart(AppFunction appFunction) {
		try {

			List<Double> xValues = new ValuesGeneratorImpl().generate(start, stop, step);
			List<Double> yValues = evaluateFunction(xValues, appFunction);
			List<Double> yValuesDerivative = evaluateDerivative(xValues, appFunction);

			XYSeriesCollection dataset = createDataSet(xValues, yValues, yValuesDerivative);

			JFreeChart chart = ChartFactory.createXYLineChart("", "X", "Y", dataset, PlotOrientation.VERTICAL,
					rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
			return chart;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return createEmptyChart();
	}

	private JFreeChart createChart(String filePath) {
		try {
			if (filePath.contains(".csv")) {
				CsvDataService csvService = new CsvDataServiceImpl();
				TreeSet<DataPoint> set = csvService.readDataToTreeSet(filePath);
				ArrayList<Double> xValues = new ArrayList<>();
				ArrayList<Double> yValues = new ArrayList<>();
				for (DataPoint point : set) {
					xValues.add(point.getX());
					yValues.add(point.getY());
				}
				return createChart(xValues, yValues);
			}

			else {
				TableFileReader fileReader = new TableFileReader();

				List<DataPoint> points = fileReader.read(filePath);
				ArrayList<Double> xValues = new ArrayList<>();
				ArrayList<Double> yValues = new ArrayList<>();
				for (DataPoint point : points) {
					xValues.add(point.getX());
					yValues.add(point.getY());
				}
				return createChart(xValues, yValues);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return createEmptyChart();
	}

	private JFreeChart createChart(ArrayList<Double> x, ArrayList<Double> y) {
		return createChart(createDataSet(x, y, new FunctionExplorer().differentiateTableFunction(x, y)));
	}

	private XYSeriesCollection createDataSet(List<Double> xValues, List<Double> yValues,
			List<Double> yValuesDerivative) {
		XYSeries series1 = new XYSeries("f(x)");
		XYSeries series2 = new XYSeries("derivative");

		for (int i = 0; i < xValues.size(); i++) {
			series1.add(xValues.get(i), yValues.get(i));
			series2.add(xValues.get(i), yValuesDerivative.get(i));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;
	}

	private List<Double> evaluateDerivative(List<Double> xValues, AppFunction function) {
		try {

			return new NumericalDifferentiator().differentiate(xValues, function);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Double>();
		}
	}

	private List<Double> evaluateFunction(List<Double> xValues, AppFunction function) {
		try {
			return new AppFunctionService().getYValues(xValues, function);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ArrayList<Double>();
		}
	}

	public ArrayList<Double> parseNumbers(String numbersString) {
		ArrayList<Double> numbers = new ArrayList<>();
		String[] numberTokens = numbersString.split(", |,| ");
		for (String token : numberTokens) {
			try {
				double number = Double.parseDouble(token);
				numbers.add(number);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return numbers;
	}
}
