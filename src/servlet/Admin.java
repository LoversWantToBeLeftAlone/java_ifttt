package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connect.connectToMySql;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID = request.getParameter("id"); // 登录界面输入的id，有bug,后一个人登录刷新ID
		
		String LEVEL = request.getParameter("level");// 登录界面输入的password
		System.out.println(ID+"--------------->"+LEVEL);
		int level=Integer.parseInt(LEVEL);
		System.out.println(level);
		try {
			connectToMySql c=new connectToMySql();
			c.changeLevel(ID,level);
			System.out.println("s");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('sucess!');history.back();</script>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('this id doesnt exits!! Please try again!');history.back();</script>");
		}
	}
}
