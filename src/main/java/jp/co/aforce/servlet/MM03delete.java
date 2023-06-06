package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MemberDAO;

@WebServlet(urlPatterns={"/servlet/MM03delete"})
public class MM03delete extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String member_id = request.getParameter("member_id");
			
			String message = "";
			
			MemberDAO dao = new MemberDAO();
			int line = dao.MM03D01(member_id);
			if(line != 1) {
				message = Message.E_WMM0004;
			}else {
				message = Message.I_WMM0003.replace("{0}", member_id);
			}
			session.removeAttribute("member_id");
			session.setAttribute("search_message", "");
			removeMemberInfo(session);
			session.setAttribute("delete_message", message);
			response.sendRedirect("/MemberInformation/views/delete.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void removeMemberInfo(HttpSession session) {
		session.removeAttribute("last_name");
		session.removeAttribute("first_name");
		session.removeAttribute("gender");
		session.removeAttribute("birth_year");
		session.removeAttribute("birth_month");
		session.removeAttribute("birth_day");
		session.removeAttribute("phone_number");
		session.removeAttribute("mail_address");
		session.removeAttribute("job");
	}
}
