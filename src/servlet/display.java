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
		String ID = request.getParameter("account"); // ��¼���������id����bug,��һ���˵�¼ˢ��ID
		System.out.println("ID:" + ID);
		if (ID == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ID cant be null!');history.back();</script>");
		}
		else {
			try {
				c = connectToMySql.showinformation(ID); // ��¼������û�
				HttpSession httpsession = request.getSession();
				httpsession.setAttribute("name", c.getName());
				httpsession.setAttribute("level", c.getLevel());
				httpsession.setAttribute("sex", c.getSex());
				httpsession.setAttribute("jifeng", c.getLevel()*10);//����Ϊ�ȼ���10.��ʱ��ô��
				httpsession.setAttribute("money", c.getMoney());
				RequestDispatcher dispatcher = request.getRequestDispatcher("����Ա�鿴����.jsp");// ����Ա�鿴
				dispatcher.forward(request, response);
				System.out.println(c.getName() + "---->" + c.getID());
			} catch (ClassNotFoundException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}

		// PrintWriter out = response.getWriter();
		// out.println("<script>alert('ID doesnt exist! Please try
		// again!');history.back();</script>");
	}
}
