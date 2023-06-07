package jp.co.aforce.tool;

import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Member;
import jp.co.aforce.constant.Constant.Item;
import jp.co.aforce.constant.Constant.Message;

public class MemberInfo {
	
	public static String checkNull(Member member) {
		String errorMessage = Message.W_CMM0001;
		String message = "";
		if(member.getLastName().length() == 0) {
			message += errorMessage.replace("{0}", Item.LAST_NAME) + "<br>";
		}
		if(member.getFirstName().length() == 0) {
			message += errorMessage.replace("{0}", Item.FIRST_NAME) + "<br>";
		}
		if(member.getGender().length() == 0) {
			message += errorMessage.replace("{0}", Item.GENDER) + "<br>";
		}
		if(Integer.valueOf(member.getBirthYear()).toString().length() == 0) {
			message += errorMessage.replace("{0}", Item.BIRTH_YEAR) + "<br>";
		}
		if(Integer.valueOf(member.getBirthMonth()).toString().length() == 0) {
			message += errorMessage.replace("{0}", Item.BIRTH_MONTH) + "<br>";
		}
		if(Integer.valueOf(member.getBirthDay()).toString().length() == 0) {
			message += errorMessage.replace("{0}", Item.BIRTH_DAY) + "<br>";
		}
		if(member.getPhoneNumber().length() == 0) {
			message += errorMessage.replace("{0}", Item.PHONE_NUMBER) + "<br>";
		}
		if(member.getMailAddress().length() == 0) {
			message += errorMessage.replace("{0}", Item.MAIL＿ADDRESS) + "<br>";
		}
		if(member.getJob().length() == 0) {
			message += errorMessage.replace("{0}", Item.JOB) + "<br>";
		}
		
		return message;
	}
	
	public static void setMemberInfo(HttpSession session, Member member) {
		session.setAttribute("last_name", member.getLastName());
		session.setAttribute("first_name", member.getFirstName());
		session.setAttribute("gender", member.getGender());
		session.setAttribute("birth_year", member.getBirthYear());
		session.setAttribute("birth_month", member.getBirthMonth());
		session.setAttribute("birth_day", member.getBirthDay());
		session.setAttribute("phone_number", member.getPhoneNumber());
		session.setAttribute("mail_address", member.getMailAddress());
		session.setAttribute("job", member.getJob());
	}
	
	public static void removeMemberInfo(HttpSession session) {
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
