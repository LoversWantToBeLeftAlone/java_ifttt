package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import Dao.consumer;
import connect.connectToMySql;

/**
 * Servlet implementation class display
 */
@WebServlet("/display")
public class display extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static consumer c;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public display() {
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
		String ID = request.getParameter("account"); // 登录界面输入的id，有bug,后一个人登录刷新ID
		System.out.println("ID:" + ID);
		if (ID == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ID cant be null!');history.back();</script>");
		}
		else {
			try {
				c = connectToMySql.showinformation(ID); // 记录下这个用户
				HttpSession httpsession = request.getSession();
				httpsession.setAttribute("name", c.getName());
				httpsession.setAttribute("level", c.getLevel());
				httpsession.setAttribute("sex", c.getSex());
				httpsession.setAttribute("jifeng", c.getLevel()*10);//积分为等级乘10.暂时这么算
				httpsession.setAttribute("money", c.getMoney());
				RequestDispatcher dispatcher = request.getRequestDispatcher("管理员查看界面.jsp");// 管理员查看
				dispatcher.forward(request, response);
				System.out.println(c.getName() + "---->" + c.getID());
			} catch (ClassNotFoundException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}

		// PrintWriter out = response.getWriter();
		// out.println("<script>alert('ID doesnt exist! Please try
		// again!');history.back();</script>");
	}
}
