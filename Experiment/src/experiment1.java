public class experiment1 {
	public void experiment1(Data stuData) {
		// 实验1第一问
		System.out.println("实验1第一问:");
		float[] total = new float[11]; // 初始化总分和平均分数组
		float[] average = new float[10];
		int count = 0;
		for (int z = 0; z < 11; z++)
			total[z] = 0;
		for (int x = 0; x < stuData.i; x++) { // 计算北京地区学生各科目总分
			if (stuData.stu[x].City.equals("Beijing")) {
				for (int y = 0; y < 10; y++) {
					total[y] += Float.valueOf(stuData.stu[x].Grade[y]);
				}
				total[10]++; // 记录北京地区学生人数
			}
		}
		for (int x = 0; x < 10; x++) // 计算北京地区学生各科目平均分
			average[x] = total[x] / total[10];
		for (int x = 0; x < 10; x++)
			System.out.println("家乡在北京的学生的" + "C" + String.valueOf(x + 1) + "科目的平均分是：" + average[x]);
		System.out.println();

		// 实验1第二问
		System.out.println("实验1第二问:");
		for (int x = 0; x < stuData.i; x++) { // 计算学生中家乡在广州，课程1在80分以上，且课程10在9分以上的男同学的数量
			if (stuData.stu[x].City.equals("Guangzhou") & Integer.valueOf(stuData.stu[x].Grade[0]) >= 80
					& Integer.valueOf(stuData.stu[x].Grade[8]) >= 9 & stuData.stu[x].Gender.equals("boy")) {
				count += 1;
			}
		}
		System.out.println("学生中家乡在广州，课程1在80分以上，且课程9在9分以上的男同学的数量为:" + count + "\r\n");

		// 实验1第三问
		System.out.println("实验1第三问:");
		float gzTotal = 0;
		float shTotal = 0;
		float gzAverage = 0;
		float shAverage = 0;
		float gzConstitution = 0;
		float shConstitution = 0;
		for (int x = 0; x < stuData.i; x++) { // 比较广州和上海两地女生的平均体能测试成绩
			if (stuData.stu[x].City.equals("Guangzhou") & stuData.stu[x].Gender.equals("girl")) {
				switch (stuData.stu[x].Constitution) {// 广州女生人数统计和体能测试成绩量化累算总分
				case "bad":
					gzTotal += 25;
					gzConstitution += 1;
				case "general":
					gzTotal += 50;
					gzConstitution += 1;
				case "good":
					gzTotal += 75;
					gzConstitution += 1;
				case "excellent":
					gzTotal += 100;
					gzConstitution += 1;
				case "0":
					gzTotal += 0;
					gzConstitution += 1;
				}
			}
			if (stuData.stu[x].City.equals("Shanghai") & stuData.stu[x].Gender.equals("girl")) {
				switch (stuData.stu[x].Constitution) {// 上海女生人数统计和体能测试成绩量化累算总分
				case "bad":
					shTotal += 25;
					shConstitution += 1;
				case "general":
					shTotal += 50;
					shConstitution += 1;
				case "good":
					shTotal += 75;
					shConstitution += 1;
				case "excellent":
					shTotal += 100;
					shConstitution += 1;
				case "0":
					shTotal += 0;
					shConstitution += 1;
				}
			}
		}

		System.out.println("广州女生的平均体能测试成绩总分:" + gzTotal + "\t" + "广州女生人数:" + gzConstitution);
		System.out.println("上海女生的平均体能测试成绩总分:" + shTotal + "\t" + "上海女生人数:" + shConstitution);
		gzAverage = gzTotal / gzConstitution;
		shAverage = shTotal / shConstitution;
		System.out.println("广州女生的平均体能测试成绩:" + gzAverage);
		System.out.println("上海女生的平均体能测试成绩:" + shAverage);
		if (gzAverage > shAverage)// 比较两地体能测试成绩平均分
			System.out.println("广州女生的平均体能测试成绩比上海女生更好" + "\r\n");
		else
			System.out.println("上海女生的平均体能测试成绩比广州女生更好" + "\r\n");

		// 实验1第四问
		System.out.println("实验1第四问:");
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

		double[] Ex = new double[9];// 记录计算时用到的值
		double[] Ex2 = new double[9];
		double[] Exy = new double[9];
		double[] Dx = new double[9];
		double[] stdx = new double[9];
		double Ey = 0;
		double Ey2 = 0;
		double Dy = 0;
		double stdy = 0;
		
		for (int j = 0; j < stuData.i; j++) {
			Ey += grade[j][9];
			Ey2 = Ey2 + grade[j][9] * grade[j][9];
		}
		
		for (int m = 0; m < 9; m++) {
			for (int j = 0; j < stuData.i; j++) {
				Ex[m] += grade[j][m];
				Ex2[m] = Ex2[m] + grade[j][m] * grade[j][m];
				Exy[m] = Exy[m] + grade[j][m] * grade[j][9];
			}
		}

		for (int m = 0; m < 9; m++) {
			Ex[m] /= stuData.i;// 各科学习成绩的平均数
			Ex2[m] /= stuData.i;// E（x^2）
			Exy[m] /= stuData.i;// E(xy)
			Dx[m] = Ex2[m] - Ex[m] * Ex[m];// 各科学习成绩的方差
			stdx[m] = Math.sqrt(Dx[m]);// 各科学习成绩的标准差
		}
		
		Ey /= stuData.i;// 体测成绩的平均数
		Ey2 /= stuData.i;// E(y^2)
		Dy = Ey2 - Ey * Ey;// 体测成绩的方差
		stdy = Math.sqrt(Dy);// 体测成绩的标准差

		double[] r = new double[9];// 各科相关系数r的计算和判断
		for (int m = 0; m < 9; m++) {
			r[m] = (Exy[m] - Ex[m] * Ey) / (stdx[m] * stdy);
			System.out.println("C" + String.valueOf(m + 1) + "成绩与体测成绩的相关系数r为:" + r[m]);
			if (r[m] < 0)
				r[m] *= -1;
			if (r[m] > 0 && r[m] < 0.3)
				System.out.println("C" + String.valueOf(m + 1) + "成绩与体测成绩基本不相关" + "\r\n");
			if (r[m] > 0.3 && r[m] < 0.5)
				System.out.println("C" + String.valueOf(m + 1) + "成绩与体测成绩低度相关" + "\r\n");
			if (r[m] > 0.5 && r[m] < 0.8)
				System.out.println("C" + String.valueOf(m + 1) + "成绩与体测成绩中度相关" + "\r\n");
			if (r[m] > 0.8 && r[m] < 1)
				System.out.println("C" + String.valueOf(m + 1) + "成绩与体测成绩高度相关" + "\r\n");
		}

	}

	public static void main(String[] args) {

	}
}