import java.sql.*;
import java.io.*;
import java.util.*;

public class dataProcess {
	public Data dataProcess() throws Exception {
		Connection conn = null;
		int i = 0;
		boolean l = true; // �����ж�ID�Ƿ��ظ�
		Student[] stu = new Student[300];// ��ʼ��Student�����鷽��洢��������
		for (int j = 0; j < 300; j++) {
			stu[j] = new Student();
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // �������ݿ�����
			System.out.println("���ݿ��������سɹ���"); // �������Ϣ
			String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // ��ȡ����URL
			String user = "scott"; // �����û���
			String password = "lzh"; // ��������
			conn = DriverManager.getConnection(url, user, password); // ��ȡ���ݿ�����
			if (conn != null) {
				System.out.println("�ɹ���Oracle���ݿ⽨�����ӣ���");
			}
			Statement stmt = conn.createStatement();// ����SQL�������
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");// ����SQL����ѯ�����(����)

			// ѭ�����ÿһ����¼
			while (rs.next()) {
				judge1: for (int j = 0; j < i; j++) { // �ж��Ƿ����Ѵ洢��ID�ظ�
					if (rs.getString("ID").equals(stu[j].ID)) {
						l = false; // �Ѵ��ڸ�ID�����ٴ洢��ѧ���ɼ�
						break judge1;
					} else
						l = true;
				}
				if (l == true) { // �洢ѧ���ɼ� ���ÿ���ֶ�
					stu[i].ID = rs.getString("ID");
					stu[i].Name = rs.getString("NAME");
					stu[i].City = rs.getString("City");
					stu[i].Gender = rs.getString("Gender");
					stu[i].Height = rs.getString("Height");
					stu[i].Constitution = rs.getString("Constitution");
					for (int m = 0; m < 10; m++)
						stu[i].Grade[m] = rs.getString("C" + String.valueOf(m + 1));
					i++;
				}
			}

			System.out.println("��ȡ���");
			// �ر�����
			stmt.close();// �ر������������
			conn.close();// �ر����ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ����Ӵ���");
			System.exit(0);
		}

		File file = new File("student.txt");// ��ȡstudent.txt
		FileInputStream fis = null;
		fis = new FileInputStream(file);
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String line = null;
		String info[] = null;
		List<Map<String, String>> sourceList = new ArrayList<Map<String, String>>();// ��ʼ��sourceList���洢hashmap�е�key��value
		while ((line = br.readLine()) != null) {
			info = line.split("\\,", -1);// ��split���Ʊ��Ϊ����ָ��һ���ַ���
			LinkedHashMap<String, String> emp = new LinkedHashMap<String, String>();// ��ʼ��hashmap��value����key�洢��info[]��
			emp.put("ID", info[0]);
			emp.put("Name", info[1]);
			emp.put("City", info[2]);
			emp.put("Gender", info[3]);
			emp.put("Height", info[4]);
			emp.put("C1", info[5]);
			emp.put("C2", info[6]);
			emp.put("C3", info[7]);
			emp.put("C4", info[8]);
			emp.put("C5", info[9]);
			emp.put("C6", info[10]);
			emp.put("C7", info[11]);
			emp.put("C8", info[12]);
			emp.put("C9", info[13]);
			emp.put("C10", info[14]);
			emp.put("Constitution", info[15]);
			sourceList.add(emp);// ��hashmap��key��value�洢��sourceList����
		}

		for (int k = 1; k < sourceList.size(); k++) { // ��sourceList���value�洢��student��������
			judge2: for (int j = 0; j < i; j++) { // �ж��Ƿ����Ѵ洢��ID�ظ�
				if (String.valueOf(Integer.valueOf(sourceList.get(k).get("ID")) - 202000).equals(stu[j].ID)) { // ��ID�ظ��Ҳ�������ȱʧ,����������һ������Դ�����ݲ�ȱ
					if (stu[j].Height == null)
						stu[j].Height = sourceList.get(k).get("Height");
					if (stu[j].Constitution == null)
						stu[j].Constitution = sourceList.get(k).get("Constitution");
					for (int m = 0; m < 10; m++)
						if (stu[j].Grade[m] == null)
							stu[j].Grade[m] = sourceList.get(k).get("C" + String.valueOf(m + 1));
					l = false; // �Ѵ��ڸ�ID�����ٴ洢��ѧ���ɼ�
					break judge2;
				} else
					l = true;
			}
			if (l == true) { // �洢ѧ���ɼ�
				stu[i].ID = String.valueOf(Integer.valueOf(sourceList.get(k).get("ID")) - 202000);
				stu[i].Name = sourceList.get(k).get("Name");
				stu[i].City = sourceList.get(k).get("City");
				if (sourceList.get(k).get("Gender") == "male")
					stu[i].Gender = "girl";
				else
					stu[i].Gender = "boy";
				float H = Float.valueOf(sourceList.get(k).get("Height"));
				H *= 100;
				stu[i].Height = String.valueOf(Integer.valueOf((int) H));
				for (int m = 0; m < 10; m++) {
					stu[i].Grade[m] = sourceList.get(k).get("C" + String.valueOf(m + 1));
				}
				stu[i].Constitution = sourceList.get(k).get("Constitution");
				i++;
			}
		}

		for (int j = 0; j < i; j++) { // ��ȱʧ�ĳɼ���0
			if (stu[j].ID == null || stu[j].ID.equals(""))
				stu[j].ID = "0";
			if (stu[j].Name == null || stu[j].Name.equals(""))
				stu[j].Name = "0";
			if (stu[j].City == null || stu[j].City.equals(""))
				stu[j].City = "0";
			if (stu[j].Gender == null || stu[j].Gender.equals(""))
				stu[j].Gender = "0";
			if (stu[j].Height == null || stu[j].Height.equals(""))
				stu[j].Height = "0";
			if (Float.valueOf(stu[j].Height) < 2) // ������Դ�еĲ�һ�������ݽ���ͳһ
				stu[j].Height = String.valueOf(Integer.valueOf((int) (Float.valueOf(stu[j].Height) * 100)));
			if (stu[j].Constitution == null || stu[j].Constitution.equals(""))
				stu[j].Constitution = "0";
			for (int m = 0; m < 10; m++) {
				if (stu[j].Grade[m] == null || stu[j].Grade[m].equals(""))
					stu[j].Grade[m] = "0";
			}
		}

		Student Temp = new Student();// ����
		for (int j = 0; j < i - 1; j++) {
			int temp = j;
			for (int f = j + 1; f < i; f++)
				if (Integer.valueOf(stu[f].ID) < Integer.valueOf(stu[temp].ID)) {
					temp = f;
				}
			Temp = stu[j];
			stu[j] = stu[temp];
			stu[temp] = Temp;
		}

		for(int j=0;j<i;j++) {
			for(int k=0;k<4;k++) {
				stu[j].Grade[5+k]=String.valueOf(Integer.valueOf(stu[j].Grade[5+k])*10);
			}
		}
		
		System.out.println("����Դ�������" + "\r\n");

		// for (int j = 0; j < i; j++) {// �ϲ�����Դ��������ɺ������������
		// System.out.println("ID:" + stu[j].ID + "\t" + "Name:" + stu[j].Name + "\t" +
		// "City:" + stu[j].City + "\t"
		// + "Gender:" + stu[j].Gender + "\t" + "Height:" + stu[j].Height);
		// for (int m = 0; m < 10; m++) {
		// System.out.print("C" + String.valueOf(m + 1) + ":" + stu[j].Grade[m] + "\t");
		// }
		// System.out.println("Constitution:" + stu[j].Constitution);
		//System.out.print("\r\n");
		// }

		Data stuData = new Data();
		stuData.stu = stu;
		stuData.i = i;
		return stuData;
	}

	public static void main(String[] args) {

	}
}