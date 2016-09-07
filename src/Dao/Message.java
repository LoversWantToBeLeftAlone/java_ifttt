package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.connectToMySql;
import Dao.consumer;

public class Message extends Thread {
	public String content;
	public String recv;// 这条消息由谁接受
	// public int number;
	public ArrayList<Message> publicm = new ArrayList<Message>();
	public ArrayList<Message> privatem = new ArrayList<Message>();

	public Message() {
	}

	public Message(String model, String content) {
		// this.number=number;
		this.recv = model;
		this.content = content;
	}

	public void getAllMessage() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from publicMessage");
		while (resultSet.next()) {
			String content = resultSet.getString(1);
			Message m = new Message("public", content);
			publicm.add(m);
		}
		connection.close();
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		Statement statement1 = connection1.createStatement();
		ResultSet resultSet1 = statement1.executeQuery("select * from privateMessage");
		while (resultSet1.next()) {
			String id = resultSet1.getString(1);
			String content = resultSet1.getString(2);
			Message m = new Message(id, content);
			privatem.add(m);
		}
		connection1.close();
	}

	public void getPriMessage(String id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from privateMessage");
		while (resultSet.next()) {
			String recv = resultSet.getString(1);
			String content = resultSet.getString(2);
			if (recv.equals(id)) {
				Message m = new Message(recv, content);
				privatem.add(m);
			}
		}
		connection.close();
	}

	public static void deleteMessage(String content, String flag) throws ClassNotFoundException, SQLException {
		if (flag.equals("public")) {
			connectToMySql.deletePubM(content);
			System.out.println("删除公告成功！");
		}
		if (flag.equals("private")) {
			connectToMySql.deletePriM(content);
			System.out.println("删除私信成功！");
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Message m = new Message();
		m.getPriMessage("131220001");
	
		for (int i = 0; i < m.privatem.size(); i++) {
			System.out.println(m.privatem.get(i).recv + "---->" + m.privatem.get(i).content);
		}
	}
}
