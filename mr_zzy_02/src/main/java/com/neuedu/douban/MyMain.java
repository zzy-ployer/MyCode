package com.neuedu.douban;

public class MyMain {
	public static void main(String[] args) throws Exception {
		/*
		 * 共现矩阵的应用： 用sqoop在数据库中取出数据，然后分四步运行，最后在得出结果
		 * 
		 */
		MySqoop.import_data();
		One.myRun();
		Two.myRun();
		Three.myRun();
		Four.myRun();
	}
}
