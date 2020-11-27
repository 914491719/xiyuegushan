import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Drawing {

	public void scatterPlot(Student[] stu, int i) {
		// ����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		mChartTheme.setExtraLargeFont(new Font("΢���ź�", Font.BOLD, 20));
		// ������������
		mChartTheme.setLargeFont(new Font("΢���ź�", Font.CENTER_BASELINE, 15));
		// ����ͼ������
		mChartTheme.setRegularFont(new Font("΢���ź�", Font.CENTER_BASELINE, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);

		DefaultXYDataset xydataset = new DefaultXYDataset();

		int size = i;
		double[][] datas = new double[2][size];
		for (int m = 0; m < i; m++) {
			datas[0][m] = Double.valueOf(stu[m].Grade[0]); // x��洢C1��Ŀ�ɼ�
			switch (stu[m].Constitution) {
			case "bad":
				datas[1][m] = 1; // y��洢���ܲ��Գɼ�
				break;
			case "general":
				datas[1][m] = 2;
				break;
			case "good":
				datas[1][m] = 3;
				break;
			case "excellent":
				datas[1][m] = 4;
				break;
			case "0":
				datas[1][m] = 0;
				break;
			}
			xydataset.addSeries("Student", datas); // ��datas�����д洢��C1��Ŀ�ɼ������ܲ��Գɼ��洢��xydataset���ݼ���
		}

		JFreeChart chart1 = ChartFactory.createScatterPlot("�γ�1�ɼ�Ϊx�ᣬ���ܳɼ�Ϊy���ɢ��ͼ ", "�γ�1�ɼ�", "���ܳɼ�", xydataset,
				PlotOrientation.VERTICAL, true, false, false);// ����jfreechart����xydatasetΪ���ݼ���һ��ɢ��ͼchart

		try {
			File output1 = new File("ɢ��ͼ.PNG");
			FileOutputStream out1 = null;
			out1 = new FileOutputStream(output1);
			ChartUtils.writeChartAsPNG(out1, chart1, 1600, 900);
			out1.close();
			System.out.println("ɢ��ͼ.PNG�����" + System.getProperty("user.dir") + "��");
		} catch (Exception e) {
		}

		ChartFrame frame = new ChartFrame("ɢ��ͼ", chart1, true);// ����jfreechart����һ����������ʾchart
		frame.pack();
		frame.setVisible(true);
	}

	public void histogram(Student[] stu, int i) {
		// ����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		mChartTheme.setExtraLargeFont(new Font("΢���ź�", Font.BOLD, 20));
		// ������������
		mChartTheme.setLargeFont(new Font("΢���ź�", Font.CENTER_BASELINE, 15));
		// ����ͼ������
		mChartTheme.setRegularFont(new Font("΢���ź�", Font.CENTER_BASELINE, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);

		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries xyseries = new XYSeries("Student");

		double quantity[] = new double[20];
		for (int j = 0; j < 20; j++) {
			quantity[j] = 0;
		}

		// ͳ�Ƹ�������������
		for (int j = 0; j < i; j++) {
			for (int m = 0; m < 20; m++) {
				if (Double.valueOf(stu[j].Grade[0]) > 0 + m * 5 & Integer.valueOf(stu[j].Grade[0]) <= 5 + m * 5) {
					quantity[m] += 1;
					break;
				}
			}
		}

		// �����������������������������뵽xyseries��
		for (int j = 0; j < 20; j++) {
			xyseries.add(2.5 + 5 * j, quantity[j]);
		}

		dataset.addSeries(xyseries);// ��xyseries���뵽dataset���ݼ���
		JFreeChart chart2 = ChartFactory.createHistogram("�γ�1��5��Ϊ����ĳɼ�ֱ��ͼ", "�ɼ�", "����", dataset);// ����jfreechart����datasetΪ���ݼ���һ��ֱ��ͼchart

		try {
			File output2 = new File("ֱ��ͼ.PNG");
			FileOutputStream out2 = null;
			out2 = new FileOutputStream(output2);
			ChartUtils.writeChartAsPNG(out2, chart2, 1600, 900);
			out2.close();
			System.out.println("ֱ��ͼ.PNG�����" + System.getProperty("user.dir") + "��");
		} catch (Exception e) {
		}

		ChartFrame frame = new ChartFrame("ֱ��ͼ", chart2, true);// ����jfreechart����һ����������ʾchart
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
	}
}