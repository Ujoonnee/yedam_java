package project_Yedam.VO;

public class User {

	private int userNum;
	private String id;
	private String password;
	private String name;
	private String phoneNum;
	private String authority = "general";
	
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phoneNum;
	}
	public void setPhoneNum(String phone) {
		this.phoneNum = phone;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "ID : " + id + ", 이름 : " + name + ", 전화번호 : " + phoneNum;
	}
	
	
}
