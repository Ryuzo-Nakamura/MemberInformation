package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Member;

public class MemberDAO extends DAO{
	
	public int MM01S01(String last_name, String first_name, String gender,
			String birth_year, String birth_month, String birth_day,
			String phone_number, String mail_address, String job) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM MEMBER_INFO "
				+ "WHERE LAST_NAME = ?"
				+ "AND FIRST_NAME = ?"
				+ "AND GENDER = ?"
				+ "AND BIRTH_YEAR = ?"
				+ "AND BIRTH_MONTH = ?"
				+ "AND BIRTH_DAY = ?"
				+ "AND PHONE_NUMBER = ?"
				+ "AND MAIL_ADDRESS = ?"
				+ "AND JOB = ?");
		st.setString(1, last_name);
		st.setString(2, first_name);
		st.setString(3, gender);
		st.setString(4, birth_year);
		st.setString(5, birth_month);
		st.setString(6, birth_day);
		st.setString(7, phone_number);
		st.setString(8, mail_address);
		st.setString(9, job);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}
	
	public int MM01S02(String phone_number) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM MEMBER_INFO "
				+ "WHERE PHONE_NUMBER = ?");
		st.setString(1, phone_number);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}
	
public int MM01S03(String mail_address) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM MEMBER_INFO "
				+ "WHERE MAIL_ADDRESS = ?");
		st.setString(1, mail_address);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}
	
	public int MM01I01(String member_id, String last_name, String first_name, String gender,
			String birth_year, String birth_month, String birth_day,
			String phone_number, String mail_address, String job) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO MEMBER_INFO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, member_id);
		st.setString(2, last_name);
		st.setString(3, first_name);
		st.setString(4, gender);
		st.setInt(5, Integer.parseInt(birth_year));
		st.setInt(6, Integer.parseInt(birth_month));
		st.setInt(7, Integer.parseInt(birth_day));
		st.setString(8, phone_number);
		st.setString(9, mail_address);
		st.setString(10, job);
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
	public int MM02S01(String member_id) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT COUNT(*) FROM MEMBER_INFO WHERE MEMBER_ID = ?");
		st.setString(1, member_id);
		ResultSet rs = st.executeQuery();
		rs.next();
		int count = rs.getInt("COUNT(*)");
		
		st.close();
		con.close();
			
		return count;
	}
	
	public Member MM02S02(String member_id) throws Exception {
		
		Member member = new Member();
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT *"
				+ "FROM MEMBER_INFO "
				+ "WHERE MEMBER_ID = ?");
		st.setString(1, member_id);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			member.setMemberId(member_id);
			member.setLastName(rs.getString("LAST_NAME"));
			member.setFirstName(rs.getString("FIRST_NAME"));
			member.setGender(rs.getString("GENDER"));
			member.setBirthYear(Integer.parseInt(rs.getString("BIRTH_YEAR")));
			member.setBirthMonth(Integer.parseInt(rs.getString("BIRTH_MONTH")));
			member.setBirthDay(Integer.parseInt(rs.getString("BIRTH_DAY")));
			member.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			member.setMailAddress(rs.getString("MAIL_ADDRESS"));
			member.setJob(rs.getString("JOB"));
		}
		
		st.close();
		con.close();
			
		return member;
	}
	
	public int MM02U01(String member_id, String last_name, String first_name, String gender,
			String birth_year, String birth_month, String birth_day,
			String phone_number, String mail_address, String job) throws Exception{
		
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE MEMBER_INFO "
				+ "SET LAST_NAME = ?, FIRST_NAME =  ?, GENDER = ?,"
				+ "BIRTH_YEAR = ?, BIRTH_MONTH = ?, BIRTH_DAY = ?,"
				+ "PHONE_NUMBER = ?, MAIL_ADDRESS = ?, JOB = ?"
				+ "WHERE MEMBER_ID = ?");
		st.setString(1, last_name);
		st.setString(2, first_name);
		st.setString(3, gender);
		st.setInt(4, Integer.parseInt(birth_year));
		st.setInt(5, Integer.parseInt(birth_month));
		st.setInt(6, Integer.parseInt(birth_day));
		st.setString(7, phone_number);
		st.setString(8, mail_address);
		st.setString(9, job);
		st.setString(10, member_id);
		int line = st.executeUpdate();

		st.close();
		con.close();
		
		return line;
	}
	
public int MM03D01(String member_id) throws Exception {
		
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"DELETE FROM MEMBER_INFO WHERE MEMBER_ID = ?");
		st.setString(1, member_id);
		int line = st.executeUpdate();
		
		
		st.close();
		con.close();
			
		return line;
	}
}
