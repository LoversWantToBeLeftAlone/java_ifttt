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

import Dao.Message;;

/**
 * Servlet implementation class showPublicMessage
 */
@WebServlet("/showPublicMessage")
public class showPublicMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showPublicMessage() {
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
		String choice=request.getParameter("submit");
		System.out.println(choice);
		Message m=new Message();

		try {
			m.getAllMessage();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(choice.equals("show")){
			if(m.publicm.isEmpty()){
				PrintWriter out = response.getWriter();
				out.println("<script>alert('this is no message!');history.back();</script>");
			}
			else{
			HttpSession httpsession = request.getSession();
			httpsession.setAttribute("content",m.publicm.get(0).content );
			System.out.println(m.publicm.get(0).content);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("公告.jsp");// 跳到用户首页
			dispatcher.forward(request, response);
			}
		}
		if(choice.equals("next")){
			int index=0;
			String content=request.getParameter("textarea");
			for(int i=0;i<m.publicm.size();i++){//获得当前消息的下标
				if(content.equals(m.publicm.get(i).content)){
					index=i;
				}
			}
			if(index==m.publicm.size()-1){
				PrintWriter out = response.getWriter();
				out.println("<script>alert('this is the last message!');history.back();</script>");
			}
			else{
				HttpSession httpsession = request.getSession();
				httpsession.setAttribute("content",m.publicm.get(index+1).content );
				System.out.println(m.publicm.get(index+1).content);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("公告.jsp");// 跳到用户首页
				dispatcher.forward(request, response);
			}
		}
		if(choice.equals("last")){//上一条
			int index=0;
			String content=request.getParameter("textarea");
			for(int i=0;i<m.publicm.size();i++){//获得当前消息的下标
				if(content.equals(m.publicm.get(i).content)){
					index=i;
				}
			}
			if(index==0){
				PrintWriter out = response.getWriter();
				out.println("<script>alert('this is the first message!');history.back();</script>");
			}
			else{
				HttpSession httpsession = request.getSession();
				httpsession.setAttribute("content",m.publicm.get(index-1).content );
				System.out.println(m.publicm.get(index-1).content);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("公告.jsp");// 跳到用户首页
				dispatcher.forward(request, response);
			}
		}
	}
}
