package common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/userUpdataServlet")
public class UserUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserUpdataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		
		UserInfoDAO dao = new UserInfoDAO();
		UserInfoVO vo = new UserInfoVO();
		vo.setId(id);
		vo.setPhone(phone);
		
		UserInfoVO rvo = dao.updateUser(vo);
		phone = rvo.getPhone();
		
		response.getWriter().print(phone);
		
	}

}
