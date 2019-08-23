package com.neuedu.douban2;

public class MyMain {
	public static void main(String[] args) throws Exception {
		/*
		 * 相似算法的应用： 用sqoop在数据库中取出数据，然后分三步运行，最后在得出结果导入到数据库中
		 * 
		 */
		Two_02.myRun();
		Three_02.myRun();
		MySqoop.export_data();
	}
}
