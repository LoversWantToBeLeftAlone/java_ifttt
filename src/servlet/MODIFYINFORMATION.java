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

import Dao.consumer;
import connect.*;

/**
 * Servlet implementation class MODIFYINFORMATION
 */
@WebServlet("/MODIFYINFORMATION")
public class MODIFYINFORMATION extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MODIFYINFORMATION() {
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
		String id = LOGIN.c.getID();
		String name = request.getParameter("name");
		String newname = new String(name.getBytes("ISO-8859-1"), "utf8"); // 解决乱码
		String sex = request.getParameter("sex");
		String newsex = new String(sex.getBytes("ISO-8859-1"), "utf8"); // 解决乱码
		String idnumber = request.getParameter("idnumber");
		String new_password = request.getParameter("new_password");
		String address = request.getParameter("address");
		String newaddress = new String(address.getBytes("ISO-8859-1"), "utf8"); // 解决乱码
		String money = request.getParameter("money");
		int MONEY = Integer.parseInt(money);
		consumer c = new consumer();
		c.C(id, newname, newsex, idnumber, new_password, newaddress, MONEY,
				MONEY / 1000);
		try {
			connectToMySql.modify(c, LOGIN.c.getID());
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println("<script>alert('Modify information success!');history.back();</script>");
	}
}
