package jp.co.aforce.servlet;

import java.io.IOException;

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

@WebServlet(urlPatterns={"/servlet/MM02update"})
public class MM02update extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			Member member = new Member();
			
			member.setMemberId(request.getParameter("member_id"));
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
				int count = dao.MM02S01(member.getMemberId());
				if(count == 0) {
					message = Message.E_WMM0005;
					MemberInfo.setMemberInfo(session, member);
				}else {
					int countPhoneNumber = dao.MM02S03(member.getMemberId(), member.getPhoneNumber());
					int countMailAddress = dao.MM02S04(member.getMemberId(), member.getPhoneNumber());
					if(countPhoneNumber >= 1 && countMailAddress >= 1) {
						message = Message.E_WMM0006 + "<br>" + Message.E_WMM0007;
					} else if(countPhoneNumber >= 1) {
						message = Message.E_WMM0006;
						MemberInfo.setMemberInfo(session, member);
					}else if(countMailAddress >= 1){
						message = Message.E_WMM0007;
						MemberInfo.setMemberInfo(session, member);
					}else {
						int line = dao.MM02U01(member);
						if(line != 1) {
							message = Message.E_WMM0003;
						}else {
							message = Message.I_WMM0002.replace("{0}", member.getMemberId());
							session.removeAttribute("member_id");
							session.setAttribute("search_message", "");
							MemberInfo.removeMemberInfo(session);
						}
					}
				}
			}
			session.setAttribute("update_message", message);
			response.sendRedirect("/MemberInformation/views/update.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
