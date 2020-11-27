import java.sql.*;
import java.io.*;
import java.util.*;

public class dataProcess {
	public Data dataProcess() throws Exception {
		Connection conn = null;
		int i = 0;
		boolean l = true; // 用于判断ID是否重复
		Student[] stu = new Student[300];// 初始化Student类数组方便存储各项数据
		for (int j = 0; j < 300; j++) {
			stu[j] = new Student();
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载数据库驱动
			System.out.println("数据库驱动加载成功！"); // 输出的信息
			String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // 获取连接URL
			String user = "scott"; // 连接用户名
			String password = "lzh"; // 连接密码
			conn = DriverManager.getConnection(url, user, password); // 获取数据库连接
			if (conn != null) {
				System.out.println("成功与Oracle数据库建立连接！！");
			}
			Statement stmt = conn.createStatement();// 创建SQL命令对象
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");// 返回SQL语句查询结果集(集合)

			// 循环输出每一条记录
			while (rs.next()) {
				judge1: for (int j = 0; j < i; j++) { // 判断是否与已存储的ID重复
					if (rs.getString("ID").equals(stu[j].ID)) {
						l = false; // 已存在该ID，不再存储该学生成绩
						break judge1;
					} else
						l = true;
				}
				if (l == true) { // 存储学生成绩 输出每个字段
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

			System.out.println("读取完毕");
			// 关闭连接
			stmt.close();// 关闭命令对象连接
			conn.close();// 关闭数据库连接
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接错误");
			System.exit(0);
		}

		File file = new File("student.txt");// 读取student.txt
		FileInputStream fis = null;
		fis = new FileInputStream(file);
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String line = null;
		String info[] = null;
		List<Map<String, String>> sourceList = new ArrayList<Map<String, String>>();// 初始化sourceList来存储hashmap中的key和value
		while ((line = br.readLine()) != null) {
			info = line.split("\\,", -1);// 用split以制表符为间隔分割成一段字符串
			LinkedHashMap<String, String> emp = new LinkedHashMap<String, String>();// 初始化hashmap把value根据key存储到info[]中
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
			sourceList.add(emp);// 将hashmap的key和value存储进sourceList里面
		}

		for (int k = 1; k < sourceList.size(); k++) { // 将sourceList里的value存储到student类数组里
			judge2: for (int j = 0; j < i; j++) { // 判断是否与已存储的ID重复
				if (String.valueOf(Integer.valueOf(sourceList.get(k).get("ID")) - 202000).equals(stu[j].ID)) { // 若ID重复且部分数据缺失,则尝试用另外一个数据源的数据补缺
					if (stu[j].Height == null)
						stu[j].Height = sourceList.get(k).get("Height");
					if (stu[j].Constitution == null)
						stu[j].Constitution = sourceList.get(k).get("Constitution");
					for (int m = 0; m < 10; m++)
						if (stu[j].Grade[m] == null)
							stu[j].Grade[m] = sourceList.get(k).get("C" + String.valueOf(m + 1));
					l = false; // 已存在该ID，不再存储该学生成绩
					break judge2;
				} else
					l = true;
			}
			if (l == true) { // 存储学生成绩
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

		for (int j = 0; j < i; j++) { // 对缺失的成绩赋0
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
			if (Float.valueOf(stu[j].Height) < 2) // 对数据源中的不一致性数据进行统一
				stu[j].Height = String.valueOf(Integer.valueOf((int) (Float.valueOf(stu[j].Height) * 100)));
			if (stu[j].Constitution == null || stu[j].Constitution.equals(""))
				stu[j].Constitution = "0";
			for (int m = 0; m < 10; m++) {
				if (stu[j].Grade[m] == null || stu[j].Grade[m].equals(""))
					stu[j].Grade[m] = "0";
			}
		}

		Student Temp = new Student();// 排序
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
		
		System.out.println("数据源处理完毕" + "\r\n");

		// for (int j = 0; j < i; j++) {// 合并数据源并处理完成后依次输出数据
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