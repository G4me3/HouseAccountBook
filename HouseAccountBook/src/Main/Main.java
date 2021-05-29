package Main;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
	static int x;
	static Scanner sc=new Scanner(System.in);
	static Calendar c=Calendar.getInstance();
	public static void main(String args[]) {
		System.out.println("初めての利用なら0、そうでないなら1を入力してください");
		do {
			x=sc.nextInt();
		}while(x<0|x>1);
		if(x==0)
			first();
		if(x==1)
			second();

		sc.close();
	}

	//データの初期作成(初めての利用)
	static void first() {

		int leap=JDBC.isLeap(c.get(Calendar.YEAR));
		for(int month=0;month<12;month++) {
			JDBC.table(c.get(Calendar.YEAR),month,leap);
		}
	}


	//2回目以降の利用
	static void second() {
		HouseAccount.show(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1);
		System.out.println("\n出費の入力：０\n収入の入力：１\n終了：２");
		x=sc.nextInt();

		//出費の入力へ
		if(x==0) {
			System.out.println("何年(例：2021)：");
			int year=sc.nextInt();
			System.out.println("何月：");
			int month=sc.nextInt();
			System.out.println("何日：");
			int day=sc.nextInt();

			HouseAccount.setter(year, month, day, 0);

		}

		//収入の入力へ
		if(x==1) {
			System.out.println("何年(例：2021)：");
			int year=sc.nextInt();
			System.out.println("何月：");
			int month=sc.nextInt();
			System.out.println("何日：");
			int day=sc.nextInt();

			HouseAccount.setter(year, month, day, 1);

		}

		//終了
		if(x==2) {
			System.exit(2);
		}
	}
}
