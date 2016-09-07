package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connect.*;
import Dao.*;

/**
 * Servlet implementation class LOGIN
 */
@WebServlet("/LOGIN")
public class LOGIN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static consumer c; // 用来存储所登录的用户

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LOGIN() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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

		String ID = request.getParameter("id"); // 登录界面输入的id，有bug,后一个人登录刷新ID
		String PWD = request.getParameter("password");// 登录界面输入的password
		String choice = request.getParameter("button");
		System.out.println(choice);
		if (choice.equals("register")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("注册界面.jsp");// 跳到用户首页
			dispatcher.forward(request, response);
		}

		// TODO 自动生成的 catch 块

		else {
			boolean flag = false;
			try {
				flag = connectToMySql.IsIdExist(ID);// 判断输入的id是否存在
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			if (flag) { // 账号存在

				String pwd = " ";
				try {
					pwd = connectToMySql.showinformation(ID).getPasswords();// 根据id得到consumer对象，继而得到正确密码
				} catch (ClassNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if (PWD.equals(pwd)) { // 密码正确

					/* 此时用户已经成功登陆，需要记住此用户，以便之后的操作 */

					try {
						c = connectToMySql.showinformation(ID); // 记录下这个用户

					} catch (ClassNotFoundException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}

					String name = "　";

					name = c.getName();

					int level = 0;

					level = c.getLevel();

					/* 储存id对应的用户的基本信息 */
					HttpSession httpsession = request.getSession();
					httpsession.setAttribute("id", ID);
					/*
					 * httpsession.setAttribute("name", name);
					 * httpsession.setAttribute("level", level);
					 * httpsession.setAttribute("jifeng", level*10);
					 */
					if (ID.equals("root")) {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("管理员.jsp");// 跳到用户首页
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("用户首页.jsp");// 跳到用户首页
						dispatcher.forward(request, response);
					}

				} else {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('Wrong password! Please try again!');history.back();</script>");
				}
			} else {// 登录失败
				PrintWriter out = response.getWriter();
				out.println("<script>alert('Wrong id! Please try again!');history.back();</script>");
			}
		}
	}

}
