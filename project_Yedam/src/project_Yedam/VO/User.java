package project_Yedam.VO;

public class User {

	private static int userSerial = 0;
	private int userNum;
	private String id;
	private String password;
	private String name;
	private String phoneNum;
	private UserGrade authority = UserGrade.NORMAL;
	
	public User() {
		// todo : consider setting userNum by Auto increment in database
		userSerial++;
		this.userNum = userSerial;
	}
	
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
	public UserGrade getAuthority() {
		return authority;
	}
	public void setAuthority(UserGrade authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "이름 : " + name + ", 전화번호 : " + phoneNum;
	}
	
	
}
