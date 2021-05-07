package common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserInfoDAO dao = new UserInfoDAO();
		List<UserInfoVO> list = dao.UserSelect();
		JSONArray ary = new JSONArray();
		for(UserInfoVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", vo.getId());
			obj.put("name", vo.getName());
			obj.put("phone", vo.getPhone());
			obj.put("gender", vo.getGender());
			
			ary.add(obj);
		}
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MultipartRequest multi = new MultipartRequest(request, "c:/tmp", 8*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		String id = multi.getParameter("id");
		String name = multi.getParameter("name");
		String pass = multi.getParameter("pass");
		String phone = multi.getParameter("phone");
		String gender = multi.getParameter("gender");
		
		UserInfoVO vo = new UserInfoVO();
		UserInfoDAO dao = new UserInfoDAO();
		
		System.out.println(id+name+pass+phone+gender);
		
		vo.setId(id);
		vo.setName(name);
		vo.setPass(pass);
		vo.setPhone(phone);
		vo.setGender(gender);
		
		dao.insertuser(vo);
	}

}
