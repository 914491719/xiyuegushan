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
		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("微软雅黑", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		DefaultXYDataset xydataset = new DefaultXYDataset();

		int size = i;
		double[][] datas = new double[2][size];
		for (int m = 0; m < i; m++) {
			datas[0][m] = Double.valueOf(stu[m].Grade[0]); // x轴存储C1科目成绩
			switch (stu[m].Constitution) {
			case "bad":
				datas[1][m] = 1; // y轴存储体能测试成绩
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
			xydataset.addSeries("Student", datas); // 将datas数组中存储的C1科目成绩和体能测试成绩存储到xydataset数据集中
		}

		JFreeChart chart1 = ChartFactory.createScatterPlot("课程1成绩为x轴，体能成绩为y轴的散点图 ", "课程1成绩", "体能成绩", xydataset,
				PlotOrientation.VERTICAL, true, false, false);// 调用jfreechart创建xydataset为数据集的一个散点图chart

		try {
			File output1 = new File("散点图.PNG");
			FileOutputStream out1 = null;
			out1 = new FileOutputStream(output1);
			ChartUtils.writeChartAsPNG(out1, chart1, 1600, 900);
			out1.close();
			System.out.println("散点图.PNG输出在" + System.getProperty("user.dir") + "下");
		} catch (Exception e) {
		}

		ChartFrame frame = new ChartFrame("散点图", chart1, true);// 调用jfreechart创建一个窗口来显示chart
		frame.pack();
		frame.setVisible(true);
	}

	public void histogram(Student[] stu, int i) {
		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("微软雅黑", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries xyseries = new XYSeries("Student");

		double quantity[] = new double[20];
		for (int j = 0; j < 20; j++) {
			quantity[j] = 0;
		}

		// 统计各分数区段人数
		for (int j = 0; j < i; j++) {
			for (int m = 0; m < 20; m++) {
				if (Double.valueOf(stu[j].Grade[0]) > 0 + m * 5 & Integer.valueOf(stu[j].Grade[0]) <= 5 + m * 5) {
					quantity[m] += 1;
					break;
				}
			}
		}

		// 将各分数区段人数和所在区段输入到xyseries中
		for (int j = 0; j < 20; j++) {
			xyseries.add(2.5 + 5 * j, quantity[j]);
		}

		dataset.addSeries(xyseries);// 将xyseries输入到dataset数据集中
		JFreeChart chart2 = ChartFactory.createHistogram("课程1以5分为间隔的成绩直方图", "成绩", "数量", dataset);// 调用jfreechart创建dataset为数据集的一个直方图chart

		try {
			File output2 = new File("直方图.PNG");
			FileOutputStream out2 = null;
			out2 = new FileOutputStream(output2);
			ChartUtils.writeChartAsPNG(out2, chart2, 1600, 900);
			out2.close();
			System.out.println("直方图.PNG输出在" + System.getProperty("user.dir") + "下");
		} catch (Exception e) {
		}

		ChartFrame frame = new ChartFrame("直方图", chart2, true);// 调用jfreechart创建一个窗口来显示chart
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
	}
}