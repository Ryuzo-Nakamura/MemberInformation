package jp.co.aforce.bean;

public class Member implements java.io.Serializable {
	
	private String member_id;
	private String last_name;
	private String first_name;
	private String gender;
	private int birth_year;
	private int birth_month;
	private int birth_day;
	private String phone_number;
	private String mail_address;
	private String job;
	
	public String getMemberId() {
		return member_id;
	}
	public void setMemberId(String member_id) {
		this.member_id = member_id;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getBirthYear() {
		return birth_year;
	}
	public void setBirthYear(int birth_year) {
		this.birth_year = birth_year;
	}
	public int getBirthMonth() {
		return birth_month;
	}
	public void setBirthMonth(int birth_month) {
		this.birth_month = birth_month;
	}
	public int getBirthDay() {
		return birth_day;
	}
	public void setBirthDay(int birth_day) {
		this.birth_day = birth_day;
	}
	public String getPhoneNumber() {
		return phone_number;
	}
	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getMailAddress() {
		return mail_address;
	}
	public void setMailAddress(String mail_address) {
		this.mail_address = mail_address;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	
}
