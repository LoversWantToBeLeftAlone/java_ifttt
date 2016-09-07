package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;

import connect.connectToMySql;
import servlet.*;
import weibo4j.model.WeiboException;

public class TASK extends Thread {

	public int FLAG; // 任务类型
	public String date; // 日期
	public String time; // 时间
	public String content; // 内容
	public String This;//this选择
	public String That;//that选择
	public String id;//任务执行者的ID
	public int status;//0/1；
	public TASK( ){
		
	}
	public void run() {

		switch (FLAG) {
		case 1:
			try {
				task1();
			} catch (InterruptedException | ClassNotFoundException | SQLException e1) {

				e1.printStackTrace();
			}
			break;
		case 2:
			try {
				task2();
			} catch (InterruptedException | ClassNotFoundException | SQLException e1) {

				e1.printStackTrace();
			}
			break;
		case 3:
			try {
				task3();
			} catch (MessagingException | InterruptedException | ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			break;
		case 4:
			try {
				task4();
			} catch (MessagingException | InterruptedException | ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			break;
		case 5:
			try {
				task5();
			} catch (WeiboException | InterruptedException | ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			break;
		case 6:
			try {
				task6();
			} catch (WeiboException | InterruptedException | ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			break;
		case 7:
			try {
				try {
					task7();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (WeiboException e) {

				e.printStackTrace();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			break;
		default:
			System.out.println("任务识别失败!");
			break;
		}
	}
	public void Ta(int FLAG, String id,String date, String time, String content,int status) {
		this.id=id;
		this.FLAG = FLAG;
		this.date = date;
		this.time = time;
		this.content = content;
		this.status=status;
		switch (FLAG) {
		case (1): {
			this.This = "time";
			this.That = "weibo";
		}
			break;
		case (2): {
			this.This = "time";
			this.That = "mail";
		}
			break;
		case (3): {
			this.This = "recvedMail";
			this.That = "weibo";
		}
			break;
		case (4): {
			this.This = "recvedMail";
			this.That = "mail";
		}
			break;
		case (5): {
			this.This = "sendWeibo";
			this.That = "mail";
		}
			break;
		case (6): {
			this.This = "sendWeibo";
			this.That = "weibo";
		}
			break;
		case (7): {
			this.This = "notSendWeibo";
			this.That = "weibo";
		}
			break;
		default:
			System.out.println("error!");
			break;
		}
	}

	public void T(int FLAG, String id,String date, String time, String content) {
		this.id=id;
		this.FLAG = FLAG;
		this.date = date;
		this.time = time;
		this.content = content;
		switch (FLAG) {
		case (1): {
			this.This = "time";
			this.That = "weibo";
		}
			break;
		case (2): {
			this.This = "time";
			this.That = "mail";
		}
			break;
		case (3): {
			this.This = "recvedMail";
			this.That = "weibo";
		}
			break;
		case (4): {
			this.This = "recvedMail";
			this.That = "mail";
		}
			break;
		case (5): {
			this.This = "sendWeibo";
			this.That = "mail";
		}
			break;
		case (6): {
			this.This = "sendWeibo";
			this.That = "weibo";
		}
			break;
		case (7): {
			this.This = "notSendWeibo";
			this.That = "weibo";
		}
			break;
		default:
			System.out.println("error!");
			break;
		}
	}

	public void delete(TASK c) {
		LOGIN.c.taskList.remove(c);
	}

	public void add(TASK c) {
		LOGIN.c.taskList.add(c);
	}

	// 定时发微博
	public void task1() throws InterruptedException, ClassNotFoundException, SQLException {
		int flag = 0;
		while (true) {
			if (LOGIN.c.getFlag() && flag == 0) {
				Date nowdate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String d = sdf.format(nowdate);

				Date nowtime = new Date();
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm-ss");
				String t = sdf1.format(nowtime);
				Thread.sleep(1000);
				System.out.println("定时 " + date + "  " + time + " ,当前时间为 " + d + "  " + t);

				if (d.equals(date)) {
					if (t.equals(time)) {
						WeiboAndYoujian.faweibo(content);
						System.out.println("定时发微博成功!");
						flag = 1;
						connectToMySql.updateStatus(content);
					}
				}
			}
		}
	}

	// 定时发邮件
	public void task2() throws InterruptedException, ClassNotFoundException, SQLException {
		int flag = 0;
		while (true) {
			if (LOGIN.c.getFlag() && flag == 0) {
				Date nowdate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String d = sdf.format(nowdate);

				Date nowtime = new Date();
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm-ss");
				String t = sdf1.format(nowtime);

				Thread.sleep(1000);

				System.out.println("定时 " + date + "  " + time + " ,当前时间为 " + d + "  " + t);

				if (d.equals(date)) {
					if (t.equals(time)) {
						WeiboAndYoujian.fayoujian(content);
						System.out.println("定时发邮件成功!");
						flag = 1;
						connectToMySql.updateStatus(content);
					}
				}
			}
		}
	}

	// 指定邮箱收到邮件，发微博
	public void task3() throws MessagingException, InterruptedException, ClassNotFoundException, SQLException {
		int num = WeiboAndYoujian.num_of_youjian();
		while (true) {
			if (LOGIN.c.getFlag()) {
				System.out.println("正在监控指定邮箱是否收到邮件。");
				Thread.sleep(1000);
				System.out.println("正在监控指定邮箱是否收到邮件。。");
				Thread.sleep(1000);
				System.out.println("正在监控指定邮箱是否收到邮件。。。");
				Thread.sleep(1000);
				if (num != WeiboAndYoujian.num_of_youjian()) {
					WeiboAndYoujian.faweibo(content);
					System.out.println("指定邮箱收到邮件，微博发送成功!");
					Thread.sleep(500);
					System.out.println("指定邮箱收到邮件，微博发送成功!");
					Thread.sleep(500);
					num = WeiboAndYoujian.num_of_youjian();
					connectToMySql.updateStatus(content);
				}
			}
		}
	}

	// 指定邮箱收到邮件，发邮件
	public void task4() throws MessagingException, InterruptedException, ClassNotFoundException, SQLException {
		int num = WeiboAndYoujian.num_of_youjian();
		while (true) {
			if (LOGIN.c.getFlag()) {
				System.out.println("正在监控指定邮箱是否收到邮件。");
				Thread.sleep(1000);
				System.out.println("正在监控指定邮箱是否收到邮件。。");
				Thread.sleep(1000);
				System.out.println("正在监控指定邮箱是否收到邮件。。。");
				Thread.sleep(1000);
				if (num != WeiboAndYoujian.num_of_youjian()) {
					WeiboAndYoujian.fayoujian(content);
					System.out.println("指定邮箱收到邮件，邮件发送成功!");
					Thread.sleep(500);
					System.out.println("指定邮箱收到邮件，邮件发送成功!");
					Thread.sleep(500);
					num = WeiboAndYoujian.num_of_youjian();
					connectToMySql.updateStatus(content);
				}
			}
		}
	}

	// 指定ID发布包含指定内容的微博，发微博
	public void task5() throws WeiboException, InterruptedException, ClassNotFoundException, SQLException {
		String str = "xjq"; // 指定内容
		int flag = 0;
		while (true) {
			if (LOGIN.c.getFlag() && flag == 0) {
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。");
				Thread.sleep(1000);
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。。");
				Thread.sleep(1000);
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。。。");
				Thread.sleep(1000);
				
				String str1 = WeiboAndYoujian.WEIBO_TEXT();
				
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。");
				Thread.sleep(1000);
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。。");
				Thread.sleep(1000);
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。。。");
				Thread.sleep(1000);
				if (str1.indexOf(str) != -1) {
					WeiboAndYoujian.faweibo(content);
					System.out.println("指定ID发布包含指定内容的微博，微博发送成功!");
					flag = 1;
					connectToMySql.updateStatus(content);
				}
			}
		}
	}

	// 指定ID发布包含指定内容的微博，发邮件
	public void task6() throws WeiboException, InterruptedException, ClassNotFoundException, SQLException {
		String str = "xjq"; // 指定内容
		int flag = 0;
		while (true) {
			if (LOGIN.c.getFlag() && flag == 0) {
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。");
				Thread.sleep(1000);
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。。");
				Thread.sleep(1000);
				System.out.println("正在监控指定ID是否发布包含指定内容的微博。。。");
				Thread.sleep(1000);
				String str1 = WeiboAndYoujian.WEIBO_TEXT();
				if (str1.indexOf(str) != -1) {
					WeiboAndYoujian.fayoujian(content);
					System.out.println("指定ID发布包含指定内容的微博，邮件发送成功!");
					flag = 1;
					connectToMySql.updateStatus(content);
				}
			}
		}
	}

	// 指定时间段指定ID未发布微博，发邮件
	public void task7() throws WeiboException, InterruptedException, ClassNotFoundException, SQLException {
		int num = WeiboAndYoujian.NUM_OF_WEIBO();
		int time = 30; // 指定时间
		int i = 0;
		int flag = 0;
		while (true) {
			if (LOGIN.c.getFlag() && flag == 0) {
				Thread.sleep(1000);
				i++;
				System.out.println("指定时间长度为 " + time + " 秒，距开始任务已经经过了 " + i + " 秒");
				if (i == time) {
					if (num == WeiboAndYoujian.NUM_OF_WEIBO()) {
						WeiboAndYoujian.fayoujian(content);
						System.out.println("指定时间段指定ID未发布微博，邮件发送成功!");
					} else
						System.out.println("指定时间段指定ID发布了微博，邮件发送失败!");
					flag = 1;
					connectToMySql.updateStatus(content);
				}
			}
		}
	}
}
