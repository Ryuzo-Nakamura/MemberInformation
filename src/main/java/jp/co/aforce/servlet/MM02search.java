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
				MemberInfo.removeMemberInfo(session);
			}else {
				Member member = dao.MM02S02(member_id);
				MemberInfo.setMemberInfo(session, member);
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
}
