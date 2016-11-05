package com.shop.db;

import java.nio.file.Paths;

public class DBTest {
	public static void main(String[] args) {
		String dbName = "shop";

		// DBUtils.createDB(dbName);
		// DBUtils.uptadeDB(dbName, Paths.get("createTables.txt"));
		// DBUtils.uptadeDB(dbName, Paths.get("insertRecords.txt"));

//		int res = DBUtils.uptadeDB(dbName, "update brand set name = 'HPE' where id=1");
		// System.out.println(res + " rows affected!");
		//
		String selectData = DBUtils.selectAllData(dbName, "brand");
		System.out.println(selectData);

		// DBUtils.dropDB(dbName);
		// System.out.println("Database deleted successfully!");
	}
}
