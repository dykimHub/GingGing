package com.pjt.gingging.model.dto;

public class User {
	private int user_id;
	private String id;
	private String password;
	private String email;
	private String nick_name;
	private int age;
	private int gender;

	public User() {
		
	}

	public User(int user_id, String id, String password, String email, String nick_name, int age, int gender) {
		super();
		this.user_id = user_id;
		this.id = id;
		this.password = password;
		this.email = email;
		this.nick_name = nick_name;
		this.age = age;
		this.gender = gender;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", id=" + id + ", password=" + password + ", email=" + email
				+ ", nick_name=" + nick_name + ", age=" + age + ", gender=" + gender + "]";
	}


}
