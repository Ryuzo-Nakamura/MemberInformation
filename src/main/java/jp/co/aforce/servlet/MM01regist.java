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

import jp.co.aforce.bean.Member;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MemberDAO;
import jp.co.aforce.tool.MemberInfo;

@WebServlet(urlPatterns={"/servlet/MM01regist"})
public class MM01regist extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			Member member = new Member();
			
			member.setLastName(request.getParameter("last_name"));
			member.setFirstName(request.getParameter("first_name"));
			member.setGender(request.getParameter("gender"));
			member.setBirthYear(Integer.parseInt(request.getParameter("birth_year")));
			member.setBirthMonth(Integer.parseInt(request.getParameter("birth_month")));
			member.setBirthDay(Integer.parseInt(request.getParameter("birth_day")));
			member.setPhoneNumber((String)request.getParameter("phone_number").replace("-", ""));
			member.setMailAddress(request.getParameter("mail_address"));
			member.setJob(request.getParameter("job"));
		
			String message = "";
			message = MemberInfo.checkNull(member);
			if(message.length() != 0) {
				MemberInfo.setMemberInfo(session, member);
			}else {
				MemberDAO dao = new MemberDAO();
				int countAll = dao.MM01S01(member);
				if(countAll >= 1) {
					message = Message.E_WMM0001;
					MemberInfo.setMemberInfo(session, member);
				}else {
					int countPhoneNumber = dao.MM01S02(member.getPhoneNumber());
					int countMailAddress = dao.MM01S03(member.getMailAddress());
					if(countPhoneNumber >= 1 && countMailAddress >= 1) {
						message = Message.E_WMM0006 + "<br>" + Message.E_WMM0007;
						MemberInfo.setMemberInfo(session, member);
					} else if(countPhoneNumber >= 1) {
						message = Message.E_WMM0006;
						MemberInfo.setMemberInfo(session, member);
					}else if(countMailAddress >= 1){
						message = Message.E_WMM0007;
						MemberInfo.setMemberInfo(session, member);
					}else {					
						LocalDateTime localDate = LocalDateTime.now();
						DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss");
						String date = dtFormat.format(localDate);
						String member_id= "A" + date;
						member.setMemberId(member_id);
						int line = dao.MM01I01(member);
						if(line != 1) {
							message = Message.E_WMM0002;
						}else {
							message = Message.I_WMM0001;
							MemberInfo.removeMemberInfo(session);
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
}
