package com.pjt.gingging.model.dto;

public class Jogging {
	private int jog_id;
	private int user_id;
	private String jog_date;
	private String jog_name;
	private String address;
	private String length;
	private String time;

	public Jogging() {

	}

	public Jogging(int jog_id, int user_id, String jog_date, String jog_name, String address, String length,
			String time) {
		super();
		this.jog_id = jog_id;
		this.user_id = user_id;
		this.jog_date = jog_date;
		this.jog_name = jog_name;
		this.address = address;
		this.length = length;
		this.time = time;
	}

	public int getJog_id() {
		return jog_id;
	}

	public void setJog_id(int jog_id) {
		this.jog_id = jog_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getJog_date() {
		return jog_date;
	}

	public void setJog_date(String jog_date) {
		this.jog_date = jog_date;
	}

	public String getJog_name() {
		return jog_name;
	}

	public void setJog_name(String jog_name) {
		this.jog_name = jog_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Jogging [jog_id=" + jog_id + ", user_id=" + user_id + ", jog_date=" + jog_date + ", jog_name="
				+ jog_name + ", address=" + address + ", length=" + length + ", time=" + time + "]";
	}

}
