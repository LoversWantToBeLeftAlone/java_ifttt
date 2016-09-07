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

import Dao.Message;
import connect.connectToMySql;

/**
 * Servlet implementation class showPrivateMessage
 */
@WebServlet("/showPrivateMessage")
public class showPrivateMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showPrivateMessage() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String choice = request.getParameter("submit");
		String id = LOGIN.c.getID();
		System.out.println(choice);
		Message m = new Message();
		try {
			m.getPriMessage(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (choice.equals("show")) {
			if(m.privatem.isEmpty()){
				PrintWriter out = response.getWriter();
				out.println("<script>alert('this is no message!');history.back();</script>");
			}
			else{
			HttpSession httpsession = request.getSession();
			httpsession.setAttribute("content", m.privatem.get(0).content);
			System.out.println(m.privatem.get(0).content);
			RequestDispatcher dispatcher = request.getRequestDispatcher("私信.jsp");// 跳到用户首页
			dispatcher.forward(request, response);
			}
		}
		if (choice.equals("next")) {
			int index = 0;
			String content = request.getParameter("textarea");
			for (int i = 0; i < m.privatem.size(); i++) {// 获得当前消息的下标
				if (content.equals(m.privatem.get(i).content)) {
					index = i;
				}
			}
			if (index == m.privatem.size() - 1) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('this is the last message!');history.back();</script>");
			} else {
				HttpSession httpsession = request.getSession();
				httpsession.setAttribute("content", m.privatem.get(index + 1).content);
				System.out.println(m.privatem.get(index + 1).content);
				RequestDispatcher dispatcher = request.getRequestDispatcher("私信.jsp");// 跳到用户首页
				dispatcher.forward(request, response);
			}
		}
		if (choice.equals("last")) {// 上一条
			int index = 0;
			String content = request.getParameter("textarea");
			for (int i = 0; i < m.privatem.size(); i++) {// 获得当前消息的下标
				if (content.equals(m.privatem.get(i).content)) {
					index = i;
				}
			}
			if (index == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('this is the first message!');history.back();</script>");
			} else {
				HttpSession httpsession = request.getSession();
				httpsession.setAttribute("content", m.privatem.get(index - 1).content);
				System.out.println(m.privatem.get(index - 1).content);
				RequestDispatcher dispatcher = request.getRequestDispatcher("私信.jsp");// 跳到用户首页
				dispatcher.forward(request, response);
			}
		}
		if (choice.equals("delete")) {// 删除有问题
			String content = request.getParameter("textarea");
			System.out.println(content);
			try {
				System.out.println(content);
				connectToMySql.deletePriM(content);
				PrintWriter out = response.getWriter();
				out.println("<script>alert('delete success!');history.back();</script>");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
