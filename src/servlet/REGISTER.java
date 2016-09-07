package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.*;
import Dao.*;

/**
 * Servlet implementation class REGISTER
 */
@WebServlet("/REGISTER")
public class REGISTER extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public REGISTER() {
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

		request.setCharacterEncoding("utf8"); // 乱码问题
		String ID = request.getParameter("id");
		String NAME = request.getParameter("name");
		String SEX = request.getParameter("sex");
		String IDNUMBER = request.getParameter("idnumber");
		String ADDRESS = request.getParameter("address");
		String PWD = request.getParameter("password");
		String MONEY = request.getParameter("money");
		int money = Integer.parseInt(MONEY);
		int LEVEL = money % 1000;

		boolean flag = true;
		try {
			flag = connectToMySql.IsIdExist(ID);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (!flag) {
			consumer c = new consumer();
			c.C(ID, NAME, SEX, IDNUMBER, PWD, ADDRESS, money, money / 1000);

			try {
				connectToMySql.addinformation(c);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			PrintWriter out = response.getWriter();
			out.println("<script>alert('SUCCESS !');history.back();</script>");

		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Wrong! The id is already exist!');history.back();</script>");

		}

	}

}
