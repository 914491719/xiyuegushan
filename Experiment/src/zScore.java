import java.util.Formatter;

public class zScore {
	public double[][] zScoreMatrix(Data stuData) {
		double[][] grade = new double[stuData.i][10];
		double[][] zScoreMatrix = new double[stuData.i][10];
		for (int j = 0; j < stuData.i; j++) {// �ѳɼ�����grade����
			for (int m = 0; m < 9; m++) {
				grade[j][m] = Double.valueOf(stuData.stu[j].Grade[m]);
			}
			switch (stuData.stu[j].Constitution) {
			case "bad":
				grade[j][9] = 25;
				break;
			case "general":
				grade[j][9] = 50;
				break;
			case "good":
				grade[j][9] = 75;
				break;
			case "excellent":
				grade[j][9] = 100;
				break;
			case "0":
				grade[j][9] = 0;
				break;
			}
		}

		// for (int j = 0; j < stuData.i; j++) {// �ϲ�����Դ��������ɺ������������
		// for (int m = 0; m < 11; m++) {
		// System.out.print("C" + String.valueOf(m + 1) + ":" + grade[j][m] + "\t");
		// }
		// System.out.print("\r\n");
		// }
		double[] E = new double[10];// ���ųɼ���ƽ����Ex
		double[] E2 = new double[10];// ���ųɼ���E(x^2)
		double[] D = new double[10];//���ųɼ��ķ���Dx
		double[] std = new double[10];// ���ųɼ��ı�׼��
		for (int m = 0; m < 10; m++)
			for (int j = 0; j < stuData.i; j++) {
				E[m] = E[m] + grade[j][m];
				E2[m] = E2[m] + grade[j][m] * grade[j][m];
			}
		for (int m = 0; m < 10; m++) {
			E[m] /= stuData.i;
			E2[m] /= stuData.i;
			D[m] = E2[m] - E[m] * E[m];
			std[m] = Math.sqrt(D[m]);
		}
		
		//����һ������������
		System.out.println("z-score��һ����������:");
		System.out.println("C1" + "\t" + "C2" + "\t" + "C3" + "\t" + "C4" + "\t" + "C5" + "\t" + "C6" + "\t" + "C7"
				+ "\t" + "C8" + "\t" + "C9" + "\t" + "���ܲ���");
		for (int j = 0; j < stuData.i; j++) {
			for (int m = 0; m < 10; m++) {
				zScoreMatrix[j][m] = (grade[j][m] - E[m]) / std[m];
				System.out.print(format(zScoreMatrix[j][m]) + "\t");
			}
			System.out.println();
		}
		return zScoreMatrix;
	}
	//������������С�������λ,����ۿ�,ʵ������δ������
	public static String format(double value) {  
        return String.format("%.3f", value).toString();  
    }  
}