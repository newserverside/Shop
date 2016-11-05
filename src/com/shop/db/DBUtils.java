package com.shop.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	public static void createDB(String dbName) {
		final String QUERY = "CREATE DATABASE " + dbName;
		try (Connection con = DBConnection.getConnection(); Statement stmt = con.createStatement()) {
			stmt.executeUpdate(QUERY);
		} catch (SQLException e) {

			if (e.getSQLState().equals("42P04")) {
				// Database already exists error
				// https://www.postgresql.org/docs/9.6/static/errcodes-appendix.html
				System.err.println(e.getMessage());
			} else {
				// Some other problems, e.g. Server down, no permission, etc
				e.printStackTrace();
			}
		}

	}

	public static void dropDB(String dbName) {

		final String QUERY = "DROP DATABASE IF EXISTS " + dbName;
		try (Connection con = DBConnection.getConnection(); Statement stmt = con.createStatement()) {
			stmt.executeUpdate(QUERY);
		} catch (SQLException e) {

			if (e.getSQLState().equals("55006")) {
				// database is being accessed by other users
				// https://www.postgresql.org/docs/9.6/static/errcodes-appendix.html
				System.err.println(e.getMessage());
			} else {
				// Some other problems, e.g. Server down, no permission, etc
				e.printStackTrace();
			}
		}

	}

	public static int uptadeDB(String dbName, Path filePathWithQuery) {
		final String QUERY = readFromFile(filePathWithQuery);
		int result = 0;
		try (Connection con = DBConnection.getConnection(dbName); Statement stmt = con.createStatement()) {
			result = stmt.executeUpdate(QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static int uptadeDB(String dbName, String query) {
		final String QUERY = query;
		int result = 0;
		try (Connection con = DBConnection.getConnection(dbName); Statement stmt = con.createStatement()) {
			result = stmt.executeUpdate(QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String selectAllData(String dbName, String tableName) {
		StringBuilder sb = new StringBuilder();
		final String QUERY = "SELECT * FROM " + tableName;
		sb.append("Table: " + tableName).append("\n------\n");
		try (Connection con = DBConnection.getConnection(dbName);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY)) {

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			// header
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				String columnName = metaData.getColumnName(columnIndex);
				// System.out.printf("%s, ", columnName);
				sb.append(columnName).append(", ");
			}
			// System.out.printf("%n");
			sb.append("\n");

			// records
			while (rs.next()) {
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					Object object = rs.getObject(columnIndex);
					// System.out.printf("%s, ", object == null ? "NULL" :
					// object.toString());
					sb.append(object == null ? "NULL" : object.toString()).append(", ");
				}
				// System.out.printf("%n");
				sb.append("\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	static String readFromFile(Path path) {
		StringBuilder sb = new StringBuilder();
//		Path path = Paths.get(filePath);
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return sb.toString();
	}
}
