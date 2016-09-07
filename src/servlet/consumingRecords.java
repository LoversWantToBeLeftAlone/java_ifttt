package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connect.connectToMySql;
import task.TASK;

/**
 * Servlet implementation class consumingRecords
 */
@WebServlet("/consumingRecords")
public class consumingRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public consumingRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<TASK> list = new ArrayList<TASK>(); // 用链表存储TASK，一个用户一个任务表
		String ID = LOGIN.c.getID();
		try {
			list = connectToMySql.getAllDoneTask(ID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession httpsession = request.getSession();
		httpsession.setAttribute("id", ID);
		int index = 0;
		if (request.getParameter("select").equals("1")) {
			int type=list.get(0).FLAG;
			String date=list.get(0).date;
			String time=list.get(0).time;
			String content=list.get(0).content;
			String detail="type:"+type+"-->"+"date:"+date+"-->"+"time:"+time+"-->"+content+"content"; 
			 httpsession.setAttribute("detail", detail);
			RequestDispatcher dispatcher = request
						.getRequestDispatcher("消费记录.jsp");// 跳到用户首页
			dispatcher.forward(request, response);
		}
		if (request.getParameter("select").equals("2")) {
			int type=list.get(0).FLAG;
			String date=list.get(0).date;
			String time=list.get(0).time;
			String content=list.get(0).content;
			String detail="type:"+type+"-->"+"date:"+date+"-->"+"time:"+time+"-->"+content+"content"; 
			 httpsession.setAttribute("detail", detail);
		}
	}
}