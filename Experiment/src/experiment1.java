public class experiment1 {
	public void experiment1(Data stuData) {
		// ʵ��1��һ��
		System.out.println("ʵ��1��һ��:");
		float[] total = new float[11]; // ��ʼ���ֺܷ�ƽ��������
		float[] average = new float[10];
		int count = 0;
		for (int z = 0; z < 11; z++)
			total[z] = 0;
		for (int x = 0; x < stuData.i; x++) { // ���㱱������ѧ������Ŀ�ܷ�
			if (stuData.stu[x].City.equals("Beijing")) {
				for (int y = 0; y < 10; y++) {
					total[y] += Float.valueOf(stuData.stu[x].Grade[y]);
				}
				total[10]++; // ��¼��������ѧ������
			}
		}
		for (int x = 0; x < 10; x++) // ���㱱������ѧ������Ŀƽ����
			average[x] = total[x] / total[10];
		for (int x = 0; x < 10; x++)
			System.out.println("�����ڱ�����ѧ����" + "C" + String.valueOf(x + 1) + "��Ŀ��ƽ�����ǣ�" + average[x]);
		System.out.println();

		// ʵ��1�ڶ���
		System.out.println("ʵ��1�ڶ���:");
		for (int x = 0; x < stuData.i; x++) { // ����ѧ���м����ڹ��ݣ��γ�1��80�����ϣ��ҿγ�10��9�����ϵ���ͬѧ������
			if (stuData.stu[x].City.equals("Guangzhou") & Integer.valueOf(stuData.stu[x].Grade[0]) >= 80
					& Integer.valueOf(stuData.stu[x].Grade[8]) >= 9 & stuData.stu[x].Gender.equals("boy")) {
				count += 1;
			}
		}
		System.out.println("ѧ���м����ڹ��ݣ��γ�1��80�����ϣ��ҿγ�9��9�����ϵ���ͬѧ������Ϊ:" + count + "\r\n");

		// ʵ��1������
		System.out.println("ʵ��1������:");
		float gzTotal = 0;
		float shTotal = 0;
		float gzAverage = 0;
		float shAverage = 0;
		float gzConstitution = 0;
		float shConstitution = 0;
		for (int x = 0; x < stuData.i; x++) { // �ȽϹ��ݺ��Ϻ�����Ů����ƽ�����ܲ��Գɼ�
			if (stuData.stu[x].City.equals("Guangzhou") & stuData.stu[x].Gender.equals("girl")) {
				switch (stuData.stu[x].Constitution) {// ����Ů������ͳ�ƺ����ܲ��Գɼ����������ܷ�
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
				switch (stuData.stu[x].Constitution) {// �Ϻ�Ů������ͳ�ƺ����ܲ��Գɼ����������ܷ�
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

		System.out.println("����Ů����ƽ�����ܲ��Գɼ��ܷ�:" + gzTotal + "\t" + "����Ů������:" + gzConstitution);
		System.out.println("�Ϻ�Ů����ƽ�����ܲ��Գɼ��ܷ�:" + shTotal + "\t" + "�Ϻ�Ů������:" + shConstitution);
		gzAverage = gzTotal / gzConstitution;
		shAverage = shTotal / shConstitution;
		System.out.println("����Ů����ƽ�����ܲ��Գɼ�:" + gzAverage);
		System.out.println("�Ϻ�Ů����ƽ�����ܲ��Գɼ�:" + shAverage);
		if (gzAverage > shAverage)// �Ƚ��������ܲ��Գɼ�ƽ����
			System.out.println("����Ů����ƽ�����ܲ��Գɼ����Ϻ�Ů������" + "\r\n");
		else
			System.out.println("�Ϻ�Ů����ƽ�����ܲ��Գɼ��ȹ���Ů������" + "\r\n");

		// ʵ��1������
		System.out.println("ʵ��1������:");
		double[][] grade = new double[stuData.i][10];
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

		double[] Ex = new double[9];// ��¼����ʱ�õ���ֵ
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
			Ex[m] /= stuData.i;// ����ѧϰ�ɼ���ƽ����
			Ex2[m] /= stuData.i;// E��x^2��
			Exy[m] /= stuData.i;// E(xy)
			Dx[m] = Ex2[m] - Ex[m] * Ex[m];// ����ѧϰ�ɼ��ķ���
			stdx[m] = Math.sqrt(Dx[m]);// ����ѧϰ�ɼ��ı�׼��
		}
		
		Ey /= stuData.i;// ���ɼ���ƽ����
		Ey2 /= stuData.i;// E(y^2)
		Dy = Ey2 - Ey * Ey;// ���ɼ��ķ���
		stdy = Math.sqrt(Dy);// ���ɼ��ı�׼��

		double[] r = new double[9];// �������ϵ��r�ļ�����ж�
		for (int m = 0; m < 9; m++) {
			r[m] = (Exy[m] - Ex[m] * Ey) / (stdx[m] * stdy);
			System.out.println("C" + String.valueOf(m + 1) + "�ɼ������ɼ������ϵ��rΪ:" + r[m]);
			if (r[m] < 0)
				r[m] *= -1;
			if (r[m] > 0 && r[m] < 0.3)
				System.out.println("C" + String.valueOf(m + 1) + "�ɼ������ɼ����������" + "\r\n");
			if (r[m] > 0.3 && r[m] < 0.5)
				System.out.println("C" + String.valueOf(m + 1) + "�ɼ������ɼ��Ͷ����" + "\r\n");
			if (r[m] > 0.5 && r[m] < 0.8)
				System.out.println("C" + String.valueOf(m + 1) + "�ɼ������ɼ��ж����" + "\r\n");
			if (r[m] > 0.8 && r[m] < 1)
				System.out.println("C" + String.valueOf(m + 1) + "�ɼ������ɼ��߶����" + "\r\n");
		}

	}

	public static void main(String[] args) {

	}
}