package jp.co.aforce.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.constant.Constant.Item;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MemberDAO;

@WebServlet(urlPatterns={"/servlet/MM01regist"})
public class MM01regist extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			String gender = request.getParameter("gender");
			String birth_year = request.getParameter("birth_year");
			String birth_month = request.getParameter("birth_month");
			String birth_day = request.getParameter("birth_day");
			String phone_number = (String)request.getParameter("phone_number").replace("-", "");
			String mail_address = request.getParameter("mail_address");
			String job = request.getParameter("job");
		
			String message = "";
			message = checkNull(last_name, first_name, gender,
					birth_year, birth_month, birth_day,
					phone_number, mail_address, job);
			if(message.length() != 0) {
				setMemberInfo(session, last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
			}else {
				MemberDAO dao = new MemberDAO();
				int countAll = dao.MM01S01(last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
				if(countAll >= 1) {
					message = Message.E_WMM0001;
					setMemberInfo(session, last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
				}else {
					int countPhoneNumber = dao.MM01S02(phone_number);
					int countMailAddress = dao.MM01S03(mail_address);
					if(countPhoneNumber >= 1 && countMailAddress >= 1) {
						message = Message.E_WMM0006 + "<br>" + Message.E_WMM0007;
					} else if(countPhoneNumber >= 1) {
						message = Message.E_WMM0006;
						setMemberInfo(session, last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
					}else if(countMailAddress >= 1){
						message = Message.E_WMM0007;
						setMemberInfo(session, last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
					}else {					
						LocalDateTime localDate = LocalDateTime.now();
						DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss");
						String date = dtFormat.format(localDate);
						String member_id= "A" + date;
						int line = dao.MM01I01(member_id, last_name, first_name, gender, birth_year, birth_month, birth_day, phone_number, mail_address, job);
						if(line != 1) {
							message = Message.E_WMM0002;
						}else {
							message = Message.I_WMM0001;
							removeMemberInfo(session);
						}
					}
				}
			}
			session.setAttribute("regist_message", message);
			response.sendRedirect("/MemberInformation/views/regist.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String checkNull(String last_name, String first_name, String gender,
			String birth_year, String birth_month, String birth_day,
			String phone_number, String mail_address, String job) {
		String errorMessage = Message.W_CMM0001;
		String message = "";
		if(last_name.length() == 0) {
			message += errorMessage.replace("{0}", Item.LAST_NAME) + "<br>";
		}
		if(first_name.length() == 0) {
			message += errorMessage.replace("{0}", Item.FIRST_NAME) + "<br>";
		}
		if(gender.length() == 0) {
			message += errorMessage.replace("{0}", Item.GENDER) + "<br>";
		}
		if(birth_year.length() == 0) {
			message += errorMessage.replace("{0}", Item.BIRTH_YEAR) + "<br>";
		}
		if(birth_month.length() == 0) {
			message += errorMessage.replace("{0}", Item.BIRTH_MONTH) + "<br>";
		}
		if(birth_day.length() == 0) {
			message += errorMessage.replace("{0}", Item.BIRTH_DAY) + "<br>";
		}
		if(phone_number.length() == 0) {
			message += errorMessage.replace("{0}", Item.PHONE_NUMBER) + "<br>";
		}
		if(mail_address.length() == 0) {
			message += errorMessage.replace("{0}", Item.MAIL＿ADDRESS) + "<br>";
		}
		if(job.length() == 0) {
			message += errorMessage.replace("{0}", Item.JOB) + "<br>";
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
