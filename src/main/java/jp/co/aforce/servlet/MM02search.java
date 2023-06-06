package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Member;
import jp.co.aforce.constant.Constant.Item;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MemberDAO;

@WebServlet(urlPatterns={"/servlet/MM02search"})
public class MM02search extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		try {
			String uri = request.getParameter("URI");
			
			String member_id = request.getParameter("member_id");
			
			
			MemberDAO dao = new MemberDAO();
			int count = dao.MM02S01(member_id);
			if(count == 0) {
				String message = Message.E_WMM0005;
				session.setAttribute("search_message", message);
				removeMemberInfo(session);
			}else {
				Member member = dao.MM02S02(member_id);
				String last_name = member.getLastName();
				String first_name = member.getFirstName();
				String gender = member.getGender();
				String birth_year = String.valueOf(member.getBirthYear());
				String birth_month = String.valueOf(member.getBirthMonth());
				String birth_day = String.valueOf(member.getBirthDay());
				String phone_number = member.getPhoneNumber();
				String mail_address = member.getMailAddress();
				String job = member.getJob();
				setMemberInfo(session, last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
				session.removeAttribute("search_message");
			}
			session.setAttribute("member_id", member_id);
			session.removeAttribute("update_message");
			session.removeAttribute("delete_message");
			response.sendRedirect(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String checkNull(String member_id) {
		String errorMessage = Message.W_CMM0001;
		String message = "";
		if(member_id.length() == 0) {
			message += errorMessage.replace("{0}", Item.LAST_NAME) + "<br>";
		}
		return message;
	}
	
	public void setMemberInfo(HttpSession session, String last_name, String first_name, String gender,
			String birth_year, String birth_month, String birth_day,
			String phone_number, String mail_address, String job) {
		session.setAttribute("last_name", last_name);
		session.setAttribute("first_name", first_name);
		session.setAttribute("gender", gender);
		session.setAttribute("birth_year", birth_year);
		session.setAttribute("birth_month", birth_month);
		session.setAttribute("birth_day", birth_day);
		session.setAttribute("phone_number", phone_number);
		session.setAttribute("mail_address", mail_address);
		session.setAttribute("job", job);
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
