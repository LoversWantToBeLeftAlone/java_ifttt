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

import connect.*;
import Dao.*;

/**
 * Servlet implementation class LOGIN
 */
@WebServlet("/LOGIN")
public class LOGIN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static consumer c; // �����洢����¼���û�

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LOGIN() {
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

		String ID = request.getParameter("id"); // ��¼���������id����bug,��һ���˵�¼ˢ��ID
		String PWD = request.getParameter("password");// ��¼���������password
		String choice = request.getParameter("button");
		System.out.println(choice);
		if (choice.equals("register")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("ע�����.jsp");// �����û���ҳ
			dispatcher.forward(request, response);
		}

		// TODO �Զ����ɵ� catch ��

		else {
			boolean flag = false;
			try {
				flag = connectToMySql.IsIdExist(ID);// �ж������id�Ƿ����
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}

			if (flag) { // �˺Ŵ���

				String pwd = " ";
				try {
					pwd = connectToMySql.showinformation(ID).getPasswords();// ����id�õ�consumer���󣬼̶��õ���ȷ����
				} catch (ClassNotFoundException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if (PWD.equals(pwd)) { // ������ȷ

					/* ��ʱ�û��Ѿ��ɹ���½����Ҫ��ס���û����Ա�֮��Ĳ��� */

					try {
						c = connectToMySql.showinformation(ID); // ��¼������û�

					} catch (ClassNotFoundException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}

					String name = "��";

					name = c.getName();

					int level = 0;

					level = c.getLevel();

					/* ����id��Ӧ���û��Ļ�����Ϣ */
					HttpSession httpsession = request.getSession();
					httpsession.setAttribute("id", ID);
					/*
					 * httpsession.setAttribute("name", name);
					 * httpsession.setAttribute("level", level);
					 * httpsession.setAttribute("jifeng", level*10);
					 */
					if (ID.equals("root")) {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("����Ա.jsp");// �����û���ҳ
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("�û���ҳ.jsp");// �����û���ҳ
						dispatcher.forward(request, response);
					}

				} else {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('Wrong password! Please try again!');history.back();</script>");
				}
			} else {// ��¼ʧ��
				PrintWriter out = response.getWriter();
				out.println("<script>alert('Wrong id! Please try again!');history.back();</script>");
			}
		}
	}

}
