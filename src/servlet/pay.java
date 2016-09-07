package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pay
 */
@WebServlet("/pay")
public class pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pay() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		// TODO Auto-generated method stub
		String money=request.getParameter("money");
		int money1=Integer.parseInt(money);
		String id=request.getParameter("ID");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Driver loaded");
		try {
	
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sshopping", "root", "88729291");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from customer");
			int money2=0;
			while(resultSet.next()){
				 money2=resultSet.getInt(6);
			}
			PreparedStatement pstmtModify = connection.prepareStatement("update customer set cmoney=? "
					+ "where cid=?");
			pstmtModify.setLong(1, money1+money2);
			pstmtModify.setString(2, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Database connected!");
	
		
	}

}
