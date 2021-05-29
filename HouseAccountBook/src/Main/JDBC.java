package Main;

import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JDBC {
	static void table(int year,int month,int leap) {
		int d=month+1;
		ArrayList<String> ary=new ArrayList<>(); //SQL文格納用

		//ローカルDBとの接続
		try {
			Class.forName("org.h2.Driver");
			String url="jdbc:h2:tcp://localhost/~/test";
			Connection con=DriverManager.getConnection(url,"sa","");
			DatabaseMetaData dm=con.getMetaData();
			ResultSet rs=dm.getTables(null, null, "Book"+year+"_"+d, null);
			Statement st=con.createStatement();

			//データがない場合の作成準備
			String qry1="CREATE TABLE IF NOT EXISTS BOOK"+year+"_"+d+"(日 int,出費 int,収入 int)";
			for(int i=0,q=1;i<mdays[leap][month];i++,q++)
				ary.add("INSERT INTO BOOK"+year+"_"+d+" VALUES("+q+",0,0)");

			//データがない場合の作成
			if(!rs.next()) {
				st.executeUpdate(qry1);
				for(String a:ary) {
					st.executeUpdate(a);
				}
			}
			rs.close();
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//閏年なら[1][]
	static int[][] mdays= {
			{31,28,31,30,31,30,31,31,30,31,30,31},
			{31,29,31,30,31,30,31,31,30,31,30,31}
	};

	//閏年かの判定
	static int isLeap(int year) {
		return (year%4==0&&year%100!=0||year%400==0)?1:0;
	}

}
