package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.connectToMySql;

/**
 * Servlet implementation class STARTTASK
 */
@WebServlet("/PERFORMTASK")
public class PERFORMTASK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int index = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PERFORMTASK() {
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

		if (request.getParameter("select").equals("1")) {
			index = 0;
		}
		if (request.getParameter("select").equals("2")) {
			index = 1;
		}
		if (request.getParameter("select").equals("3")) {
			index = 2;
		}
		if (request.getParameter("select").equals("4")) {
			index = 3;
		}
		if (request.getParameter("select").equals("5")) {
			index = 4;
		}
		if (request.getParameter("select").equals("6")) {
			index = 5;
		}
		if (request.getParameter("select").equals("7")) {
			index = 6;
		}
		if (request.getParameter("select").equals("8")) {
			index = 7;
		}
		if (request.getParameter("select").equals("9")) {
			index = 8;
		}
		if (request.getParameter("select").equals("10")) {
			index = 9;
		}

		request.setCharacterEncoding("utf8"); // 防止乱码

		if (request.getParameter("Submit").equals("start")) {
			LOGIN.c.setFlag(true);
			System.out.println("选择第  " + (index + 1) + " 个任务，扣除会员费100元");
			LOGIN.c.setLevelAndMoney(
					(LOGIN.c.getMoney() - 100 + LOGIN.c.getLevel() * 2) / 1000,
					LOGIN.c.getMoney() - 100 + LOGIN.c.getLevel() * 2); // 扣除费用，有打折
			try {
				connectToMySql.modify(LOGIN.c, LOGIN.c.getID());
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			LOGIN.c.taskList.get(index).start();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("任务提示界面.jsp");// 跳到用户首页
			dispatcher.forward(request, response);
		}
		if (request.getParameter("Submit").equals("pause")) {
			LOGIN.c.setFlag(false);
		}
		if (request.getParameter("Submit").equals("restart")) {
			LOGIN.c.setFlag(true);
		}
	}

}
