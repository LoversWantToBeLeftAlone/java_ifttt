package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Message;

/**
 * Servlet implementation class showMessages
 */
@WebServlet("/showMessages")
public class showMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String content="This is a test message!";
		String model="public";
		Message m=new Message(content,model);
		HttpSession httpsession = request.getSession();
		httpsession.setAttribute("message", content);
		if(request.getParameter("submit").equals("privateMessage")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("查看消息.jsp");// 跳到用户首页
			dispatcher.forward(request, response);
			System.out.println("333");
		}
	}
}
