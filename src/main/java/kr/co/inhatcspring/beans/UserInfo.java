package kr.co.inhatcspring.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserInfo {
	
	@NotEmpty(message = "아이디를 입력해주세요.")
	private String userid;
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
	private String password;
	
	@NotEmpty(message = "이름을 입력해주세요.")
	private String username;
	
	private int isadmin; //관리자 권한(1이면 관리자)
	
	public String getUserid() { 
		return userid;
	}
	public void setUserid(String userid) { 
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) { 
		this.password = password;
	}
	public String getUsername() { 
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
}
