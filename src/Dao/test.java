package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test{
	public static void t() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/worker", "root", "");
		System.out.println("Database connected!");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select employee.eid,employee.ename from employee where employee.eid" + "='29401'");
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
		}
		connection.close();
	}
	public static void T(){
		System.out.print("hello world");
	}
}
