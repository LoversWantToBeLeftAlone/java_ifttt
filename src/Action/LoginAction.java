package Action;   
  
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
  
public class LoginAction implements Action {   
  
    public String execute(HttpServletRequest request,   
            HttpServletResponse response) {   
        // 得到用户名和密码   
        String userName = request.getParameter("name");   
        String userPwd = request.getParameter("pwd");   
        if (userName.equals("netjava") && userPwd.equals("netjava")) {   
            request.setAttribute("name", userName);   
            return "main.jsp";   
        } else {   
            return "login.jsp";   
        }   
    }   
  
}
