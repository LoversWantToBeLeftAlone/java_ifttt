package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.connectToMySql;

/**
 * Servlet implementation class recharge
 */
@WebServlet("/recharge")
public class recharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recharge() {
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
		String ID=request.getParameter("id");
		String MONEY=request.getParameter("money");
		int money=Integer.parseInt(MONEY);
		
		System.out.println(ID+":"+MONEY	);
		try {
			if(connectToMySql.IsIdExist(ID)){
			connectToMySql.changeMoney(ID,money);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('sucess!!');history.back();</script>");
			}
			else{
				PrintWriter out = response.getWriter();
				out.println("<script>alert('ID not exist!!');history.back();</script>");
			
			}
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('error!!!please try later');history.back();</script>");
		}
	}
}
