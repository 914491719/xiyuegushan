import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class sampleSort {
	public void stuSampleSort(Data stuData) throws IOException {

		double[][] threeSample = new double[stuData.i][3];
		String[][] threeSampleID = new String[stuData.i][3];

		for (int j = 0; j < stuData.i; j++) {

			double[] copyCorrelation = new double[stuData.i];
			System.arraycopy(stuData.stu[j].correlation, 0, copyCorrelation, 0, 106);
			Arrays.sort(copyCorrelation);
			threeSample[j][0] = copyCorrelation[104];
			threeSample[j][1] = copyCorrelation[103];
			threeSample[j][2] = copyCorrelation[102];

			for (int i = 0; i < stuData.i; i++) {
				if (stuData.stu[j].correlation[i] == threeSample[j][0])
					threeSampleID[j][0] = stuData.stu[i].ID;
				if (stuData.stu[j].correlation[i] == threeSample[j][1])
					threeSampleID[j][1] = stuData.stu[i].ID;
				if (stuData.stu[j].correlation[i] == threeSample[j][2])
					threeSampleID[j][2] = stuData.stu[i].ID;
			}
		}

		File file = new File("sample.txt");
		FileWriter out = new FileWriter(file);
		for (int i = 0; i < stuData.i; i++) {
			for (int m = 0, k = 0; m < 3; m++) {
				out.write(threeSampleID[i][m]);
				if (k < 2) {
					out.write("\t");
					k++;
				}
			}
			out.write("\n");
		}
		out.close();
		System.out.println("距离每个样本最接近的三个样本的ID已以sample.txt格式输出在" + System.getProperty("user.dir") + "下");
	}

}