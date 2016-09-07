package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.connectToMySql;
import task.*;

/**
 * Servlet implementation class LOGIN
 */
@WebServlet("/STORETASK")
public class STORETASK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public STORETASK() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/* 判断任务类型 */
	public static int FLAG(int x, int y) {
		if (x == 1) {
			if (y == 1)
				return 1; // 定时发微博
			if (y == 2)
				return 2; // 定时发邮件
		}
		if (x == 2) {
			if (y == 1)
				return 3; // 指定邮箱收到邮件，发微博
			if (y == 2)
				return 4; // 指定邮箱收到邮件，发邮件
		}
		if (x == 3) {
			if (y == 1)
				return 5; // 指定ID发布包含指定内容的微博，发微博
			if (y == 2)
				return 6; // 指定ID发布包含指定内容的微博，发邮件
		}
		if (x == 4) {
			if (y == 1)
				return 0;
			if (y == 2)
				return 7; // 指定时间段内，指定ID没发微博，发邮件
		}
		return 0;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int x = 0, y = 0;
		if (request.getParameter("select").equals("1")) {//this1
			x = 1;
		}
		if (request.getParameter("select").equals("2")) {//this2
			x = 2;
		}
		if (request.getParameter("select").equals("3")) {//this3
			x = 3;
		}
		if (request.getParameter("select").equals("4")) {//this4
			x = 4;
		}
		if (request.getParameter("select1").equals("1")) {//that1
			y = 1;
		}
		if (request.getParameter("select1").equals("2")) {//that2
			y = 2;
		}

		int FLAG = FLAG(x, y);
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String content = request.getParameter("content");
		String newcontent = new String(content.getBytes("ISO-8859-1"), "utf8"); // 解决乱码
		String id=LOGIN.c.getID();
		TASK t = new TASK();
		t.T(FLAG,id, date, time, newcontent);//将任务保存
		LOGIN.c.taskList.add(t);
		try {
			connectToMySql.addTask(t);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGIN.c.addNum_of_task();
		PrintWriter out = response.getWriter();
		out.println("<script>alert('SUCCESS !');history.back();</script>");
	}

}
