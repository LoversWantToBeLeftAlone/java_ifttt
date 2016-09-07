package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class forwardServlet
 */
@WebServlet("/forwardServlet")
public class forwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forwardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	response.setCharacterEncoding("UTF-8");
	String res="";
	String task=request.getParameter("task");
	String message="";
	int counter=0;
	if(task.equals("reset")){
		counter=1;
	}
	else{
		switch(counter){
		case(1):message="code once ,run anywhere";break;
		case(2):message="javascipt";break;
		case(3):message="progress";break;
		default:message="done";break;
		}
		counter++;
	}
	res="<message>"+message+"</message>";
	PrintWriter out=response.getWriter();
	response.setContentType("text/xml;charset=UTF-8");
	response.setHeader("Cache-Control","np-Cache");
	out.println("<response>");
	out.println(res);
	out.println("</response>");
	out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
