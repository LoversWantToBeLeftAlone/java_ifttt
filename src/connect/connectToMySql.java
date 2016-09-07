package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dao.*;

import servlet.*;
import task.TASK;

public class connectToMySql {

	admin admin = new admin(); // �½�һ������Ա�Ķ��󣬼̳л�Ա�࣬��һ����Ա����

	/* �����ݿ������Ϣ�浽������ */
	public void readFromMySql() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from customer");
		while (resultSet.next()) {
			String id = resultSet.getString(1);
			String name = resultSet.getString(2);
			String sex = resultSet.getString(3);
			String idnumber = resultSet.getString(4);
			String passwords = resultSet.getString(5);
			String address = resultSet.getString(6);
			int money = resultSet.getInt(7);
			int level = resultSet.getInt(8);
			consumer c = new consumer();
			c.C(id, name, sex, idnumber, passwords, address, money, level);// ����һ��consumer����
			admin.add(c);
		}
		connection.close();
	}

	public static ArrayList<TASK> getAllDoneTask(String id)
			throws ClassNotFoundException, SQLException {
		ArrayList<TASK> list = new ArrayList<TASK>(); // ������洢TASK��һ���û�һ�������
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from task");
		while (resultSet.next()) {
			int flag = resultSet.getInt(1);
			String cid = resultSet.getString(2);
			String date = resultSet.getString(5);
			String time = resultSet.getString(6);
			String content = resultSet.getString(7);
			int status = resultSet.getInt(8);
			TASK t = new TASK();
			System.out.println(cid + "->" + id);
			if (cid.equals(id) && status == 1) {// �Ѿ�ִ�й�
				System.out.println("true");
				t.Ta(flag, cid, date, time, content, status);
				list.add(t);
			}
		}
		return list;
	}

	public static ArrayList<TASK> getAllTask(String id)
			throws ClassNotFoundException, SQLException {
		ArrayList<TASK> list = new ArrayList<TASK>(); // ������洢TASK��һ���û�һ�������
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from task");
		while (resultSet.next()) {
			int flag = resultSet.getInt(1);
			String cid = resultSet.getString(2);
			String date = resultSet.getString(5);
			String time = resultSet.getString(6);
			String content = resultSet.getString(7);
			int status = resultSet.getInt(8);
			TASK t = new TASK();
			System.out.println(cid + "->" + id);
			if (cid.equals(id)) {
				System.out.println("true");
				t.Ta(flag, cid, date, time, content, status);
				list.add(t);
			}
		}
		return list;
	}

	public static void addTask(TASK t) throws SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("database connected");
		PreparedStatement pstmtInsert = connection
				.prepareStatement("insert into task(flag,cid,this,that,"
						+ "dt,tm,content,statu) values(?,?,?,?,?,?,?,?)");
		pstmtInsert.setInt(1, t.FLAG);
		pstmtInsert.setString(2, t.id);
		pstmtInsert.setString(3, t.This);
		pstmtInsert.setString(4, t.That);
		pstmtInsert.setString(5, t.date);
		pstmtInsert.setString(6, t.time);
		pstmtInsert.setString(7, t.content);
		pstmtInsert.setInt(8, 0);
		System.out.println(t.id + "->" + t.This + "->" + t.That + "->" + t.date
				+ "->" + t.time);
		pstmtInsert.executeUpdate();
		System.out.println("�û�������ӳɹ���");
		connection.close();
	}

	public static void addPublicMessage(String content) throws SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("database connected");
		PreparedStatement pstmtInsert = connection
				.prepareStatement("insert into publicmessage(publicContent) values(?)");
		pstmtInsert.setString(1, content);
		System.out.println(content);
		pstmtInsert.executeUpdate();
		System.out.println("���淢���ɹ���");
		connection.close();
	}

	public static void addPrivateMessage(String id, String content)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("database connected");
		PreparedStatement pstmtInsert = connection
				.prepareStatement("insert into privatemessage(cid,privateContent) values(?,?)");
		pstmtInsert.setString(1, id);
		pstmtInsert.setString(2, content);
		System.out.println(content);
		pstmtInsert.executeUpdate();
		System.out.println("˽�ŷ����ɹ���");
		connection.close();
	}

	public static void updateStatus(String content)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");

		PreparedStatement pstmtModify = connection
				.prepareStatement("update task set statu=? where content = ?");
		pstmtModify.setInt(1, 1);
		pstmtModify.setString(2, content);
		pstmtModify.executeUpdate();
	}

	/* ��consumer��һ������洢�����ݿ�������У����һ���û��� */
	public static void addinformation(consumer c)
			throws ClassNotFoundException, SQLException {
		connectToMySql database = new connectToMySql();
		database.admin.consumerList.clear();// �������
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		PreparedStatement pstmtInsert = connection
				.prepareStatement("insert into customer(cid,cname,csex,"
						+ "cidNumber,cpasswords,caddress,cmoney,clevel) values(?,?,?,?,?,?,?,?)");
		pstmtInsert.setString(1, c.getID());
		pstmtInsert.setString(2, c.getName());
		pstmtInsert.setString(3, c.getSex());
		pstmtInsert.setString(4, c.getIdnumber());
		pstmtInsert.setString(5, c.getPasswords());
		pstmtInsert.setString(6, c.getAddr());
		pstmtInsert.setLong(7, c.getMoney());
		pstmtInsert.setLong(8, c.getLevel());
		pstmtInsert.executeUpdate();
		database.readFromMySql();
		System.out.println("�û���ӳɹ���");
		connection.close();
	}

	/* �����ݿ��������ɾ��consumer��һ������ */
	public void delete(consumer c) throws SQLException, ClassNotFoundException {

		connectToMySql database = new connectToMySql();
		database.admin.consumerList.clear();// �������
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		if (admin.consumerList.contains(c)) {
			PreparedStatement pstmtDelete = connection
					.prepareStatement("delete from customer where cid=?");
			pstmtDelete.setString(1, c.getID());// ɾ�����ݿ��������
			pstmtDelete.executeUpdate();
			database.readFromMySql();
		}
		if (!admin.search(c))
			System.out.println("�û������ڣ�");
		connection.close();
	}

	/* �޸����ݿ����һ����Ϣ */
	public static void modify(consumer c, String id) throws SQLException,
			ClassNotFoundException {
		connectToMySql database = new connectToMySql();
		database.admin.consumerList.clear();// �������
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");

		PreparedStatement pstmtModify = connection
				.prepareStatement("update customer set cname = ?, csex = ?, cidNumber = ?, cpasswords = ?, caddress = ?, cmoney = ?, clevel=? where cid = ?");

		pstmtModify.setString(1, c.getName());
		pstmtModify.setString(2, c.getSex());
		pstmtModify.setString(3, c.getIdnumber());
		pstmtModify.setString(4, c.getPasswords());
		pstmtModify.setString(5, c.getAddr());
		pstmtModify.setLong(6, c.getMoney());
		pstmtModify.setLong(7, c.getLevel());
		pstmtModify.setString(8, id);
		pstmtModify.executeUpdate();
		database.readFromMySql();
		LOGIN.c.setIdAndPass(c.getID(), c.getPasswords());
		LOGIN.c.setName(c.getName());
		LOGIN.c.setSex(c.getSex());
		LOGIN.c.setIdnumber(c.getIdnumber());
		LOGIN.c.setAddress(c.getAddr());
		LOGIN.c.setLevelAndMoney(c.getLevel(), c.getMoney());

		System.out.println(c.getLevel() + "---------------------");
		System.out.println(LOGIN.c.getLevel() + "---------------------");

		connection.close();
	}

	public void changeLevel(String id, int level)
			throws ClassNotFoundException, SQLException {
		if (IsIdExist(id)) {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/java", "root", "88729291");
			System.out.println("Database connected!");
			PreparedStatement pstmtModify = connection
					.prepareStatement("update customer set clevel = ? where cid = ?");
			pstmtModify.setInt(1, level);
			System.out.println(level + "--->" + id);
			pstmtModify.setString(2, id);
			pstmtModify.executeUpdate();// �������
			connection.close();
		}

	}

	public static void changeMoney(String id, int money)
			throws ClassNotFoundException, SQLException {
		if (IsIdExist(id)) {
			LOGIN.c.setMoney(money);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/java", "root", "88729291");
			System.out.println("Database connected!");
			consumer c = showinformation(id);
			int m = c.getMoney();
			PreparedStatement pstmtModify = connection
					.prepareStatement("update customer set cmoney = ? where cid = ?");
			pstmtModify.setInt(1, m + money);
			System.out.println(money + "--->" + id);
			pstmtModify.setString(2, id);
			pstmtModify.executeUpdate();// �������
			connection.close();
		}
	}

	public static void deletePriM(String message) throws SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		PreparedStatement pstmtDelete = connection
				.prepareStatement("delete from privatemessage where privateContent=?");
		pstmtDelete.setString(1, message);// ɾ�����ݿ��������
		pstmtDelete.executeUpdate();
	}

	public static void deletePubM(String message) throws SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/java", "root", "88729291");
		System.out.println("Database connected!");
		PreparedStatement pstmtDelete = connection
				.prepareStatement("delete from publicmessage where publicContent=?");
		pstmtDelete.setString(1, message);// ɾ�����ݿ��������
		pstmtDelete.executeUpdate();
	}

	/* �ж�id�Ƿ���������ݿ��� */
	public static boolean IsIdExist(String id) throws ClassNotFoundException,
			SQLException {
		connectToMySql database = new connectToMySql();
		database.admin.consumerList.clear();// �������
		database.readFromMySql();// �洢
		int a = 0;
		for (int i = 0; i < database.admin.consumerList.size(); i++) {
			consumer c1 = database.admin.consumerList.get(i);
			if (c1.getID().equals(id)) {
				a++;
				break;
			}
		}
		return a > 0;
	}

	/* �����˺�id�õ��������consumer���� */
	public static consumer showinformation(String id)
			throws ClassNotFoundException, SQLException {
		connectToMySql database = new connectToMySql();
		database.admin.consumerList.clear();// �������
		database.readFromMySql();
		consumer c2 = new consumer();
		for (int i = 0; i < database.admin.consumerList.size(); i++) {
			consumer c1 = database.admin.consumerList.get(i);
			if (c1.getID().equals(id)) {
				c2 = c1;
				break;
			}
		}
		return c2;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		connectToMySql d = new connectToMySql();
		/*
		 * ArrayList<TASK> list = d.getAllDoneTask("131220001"); for (int i = 0;
		 * i < list.size(); i++) { System.out.println( list.get(i).id + ">" +
		 * list.get(i).This + ">" + list.get(i).That + ">" +
		 * list.get(i).content); }
		 */
		String detail = "";
		ArrayList<TASK> list = d.getAllTask("131220001");
		for (int i = 0; i < list.size(); i++) {
			int type = list.get(i).FLAG;
			String date = list.get(i).date;
			String time = list.get(i).time;
			String content = list.get(i).content;
			detail += "type:" + type + "		date:" + date + "		time:" + time
					+ "		content:" + content + "\n";
		}
		System.out.println(detail);
		d.deletePriM("you are sb!");
	}
}
