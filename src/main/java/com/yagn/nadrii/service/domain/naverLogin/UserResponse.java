package com.yagn.nadrii.service.domain.naverLogin;

public class UserResponse {
	private String id;
	private String nickname;
	private String name;
	private String enc_id;
	private String email;
	private String gender;
	private String age;
	private String birthday;
	private String profile_image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	
	public String getEnc_id() {
		return enc_id;
	}
	public void setEnc_id(String enc_id) {
		this.enc_id = enc_id;
	}
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", nickname=" + nickname + ", email=" + email + ", gender=" + gender
				+ ", age=" + age + ", birthday=" + birthday + ", profile_image=" + profile_image + "]";
	}
}
