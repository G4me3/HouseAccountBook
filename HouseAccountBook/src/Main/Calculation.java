package Main;

public class Calculation {

	//年間出費の計算
	static void CalcYearExpenditure(int year) {
		int totalOfEx=0;
		for(int sum:HouseAccount.expenditure)
			totalOfEx+=sum;
		System.out.println("年間出費："+totalOfEx);
	}

	//年間収入の計算
	static void CalcYearIncome(int year) {
		int totalOfIncome=0;
		for(int sum:HouseAccount.income)
			totalOfIncome+=sum;
		System.out.println("年間収入"+totalOfIncome);
	}

	//最大出費の計算
	static void CalcMaxExpenditure(int year) {
		int maxExpenditure=HouseAccount.expenditure.get(0);
		for(int max:HouseAccount.expenditure) {
			if(maxExpenditure<max)
				maxExpenditure=max;
		}
		System.out.println("最大出費："+maxExpenditure);
	}

	//最大収入の計算
	static void CalcMaxIncome(int year) {
		int maxIncome=HouseAccount.income.get(0);
		for(int max:HouseAccount.income) {
			if(maxIncome<max)
				maxIncome=max;
		}
		System.out.println("最大出費："+maxIncome);
	}

}
