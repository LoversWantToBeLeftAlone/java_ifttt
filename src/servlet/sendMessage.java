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

/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sendMessage")
public class sendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sendMessage() {
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
		String content = request.getParameter("textfield");
		String type = request.getParameter("select");
		String id=request.getParameter("ID");
		System.out.println(content+"->"+type+"->"+id);
		try {
			if(type.equals("public")){
			connectToMySql.addPublicMessage(content);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('success send a  public message!');history.back();</script>");
			}
			if(type.equals("private")){
				connectToMySql.addPrivateMessage(id,content);
				PrintWriter out = response.getWriter();
				out.println("<script>alert('sucess send a private message!');history.back();</script>");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
