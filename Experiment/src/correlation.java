public class correlation {
	public void correlationMatrix(Data stuData) {
		double[][] grade = new double[stuData.i][10];
		for (int j = 0; j < stuData.i; j++) {// 把成绩存入grade数组
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

		double[] E = new double[stuData.i]; // 学生的平均分
		double[] E2 = new double[stuData.i];
		double[] D = new double[stuData.i];// 学生成绩的方差
		double[] std = new double[stuData.i];// 学生成绩的标准差
		double Exy = 0;
		for (int j = 0; j < stuData.i; j++)
			for (int m = 0; m < 10; m++) {
				E[j] = E[j] + grade[j][m];
				E2[j] = E2[j] + grade[j][m] * grade[j][m];
			}
		for (int j = 0; j < stuData.i; j++) {
			E[j] /= 10;
			E2[j] /= 10;
			D[j] = E2[j] - E[j] * E[j];
			std[j] = Math.sqrt(D[j]);
		}

		for (int x = 0; x < stuData.i; x++)
			for (int y = x; y < stuData.i; y++) {
				double r = 0;
				Exy = 0;
				for (int m = 0; m < 10; m++) {
					Exy += grade[x][m] * grade[y][m];
				}
				Exy /= 10;
				r = (Exy - E[x] * E[y]) / (std[x] * std[y]);
				stuData.stu[x].correlation[y] = r;
				if (x != y) {
					stuData.stu[y].correlation[x] = r;
				}
			}
		// 输出相关性矩阵
		System.out.println("相关矩阵如下:");
		for (int x = 0; x < stuData.i; x++) {
			for (int y = 0; y < stuData.i; y++) {
				System.out.print(format(stuData.stu[x].correlation[y]) + "\t");
			}
			System.out.println();
		}

	}

	// 将输出结果保留小数点后三位,方便观看,实际数据未做处理
	public static String format(double value) {
		return String.format("%.3f", value).toString();
	}

}