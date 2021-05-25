import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseAccount {
	static List<Integer> day=new ArrayList<>();			//日にちの格納用
	static List<Integer> expenditure=new ArrayList<>();	//出費の格納用
	static List<Integer> income=new ArrayList<>();			//収入の格納用

	static void show(int year,int month) {
		System.out.println("\n"+month+"月の家計簿はこちらです");

		//DBの接続
		try {
			Class.forName("org.h2.Driver");
			String url="jdbc:h2:tcp://localhost/~/test";
			Connection con=DriverManager.getConnection(url,"sa","");
			String sql="SELECT * FROM BOOK"+year+"_"+month;
			Statement st=con.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery(sql);


			if(day.size()>=JDBC.mdays[JDBC.isLeap(year)][month]) {
				day.clear();
				expenditure.clear();
				income.clear();
			}
			//データの格納
			while(rs.next()) {
				day.add(rs.getInt(1));
				expenditure.add(rs.getInt(2));
				income.add(rs.getInt(3));

			}

			//出力(テスト)
			for(int p=1;p<=5;p++) {
				for(int o=1;o<=3;o++) {
					for(int i=(p-1)*7;i<p*7&i<day.size();i++) {
						if(o==1) {
							if(i%7==0) {
								System.out.printf("%3s", "日");
							}
							System.out.printf("%5d", day.get(i));
						}else if(o==2) {
							if(i%7==0) {
								System.out.printf("%2s","出費");
							}
							System.out.printf("%5d",expenditure.get(i));
						}else {
							if(i%7==0) {
								System.out.printf("%2s", "収入");
							}
							System.out.printf("%5d", income.get(i));
						}
					}
					System.out.println();
				}
				System.out.println();
			}

			//諸データの出力
			Calculation.CalcYearExpenditure(year);
			Calculation.CalcYearIncome(year);
			Calculation.CalcMaxExpenditure(year);
			Calculation.CalcMaxIncome(year);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	static void setter(int year,int month, int day,int select) {
		Scanner sc=new Scanner(System.in);
		System.out.println("いくら？");
		int howMuch=sc.nextInt();

		try {
			Class.forName("org.h2.Driver");
			String url="jdbc:h2:tcp://localhost/~/test";
			Connection con=DriverManager.getConnection(url,"sa","");
			String sql3="SELECT * FROM BOOK"+year+"_"+month;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql3);

			//出費
			if(select==0) {
				for(int i=0;i<day;i++)
					rs.next();

				if(rs.getInt(2)>0)
					howMuch+=rs.getInt(2);

				String sql="UPDATE BOOK"+year+"_"+month+" SET 出費="+howMuch+"WHERE 日="+day;
				st.executeUpdate(sql);
				show(year,month);
				Main.second();
			}
			//収入
			if(select==1) {
				for(int i=1;i<day;i++)
					rs.next();

				if(rs.getInt(3)>0)
					howMuch+=rs.getInt(3);

				String sql2="UPDATE BOOK"+year+"_"+month+" SET 収入="+howMuch+"WHERE 日="+day;
				st.executeUpdate(sql2);
				show(year,month);
				Main.second();
			}

			sc.close();
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}


