package com.pjt.gingging.model.dto;

public class Poster {
	private int poster_id;
	private int user_id;
	private int jog_id;

	private String reg_date;
	private String review;
	private String mood;
	private String weather;
	private String song;
	private String companion;
	private String user_photo;
	private String user_org_photo;

	public Poster() {

	}

	public Poster(int poster_id, int user_id, int jog_id, String reg_date, String review, String mood, String weather,
			String song, String companion, String user_photo, String user_org_photo) {
		super();
		this.poster_id = poster_id;
		this.user_id = user_id;
		this.jog_id = jog_id;
		this.reg_date = reg_date;
		this.review = review;
		this.mood = mood;
		this.weather = weather;
		this.song = song;
		this.companion = companion;
		this.user_photo = user_photo;
		this.user_org_photo = user_org_photo;
	}

	public int getPoster_id() {
		return poster_id;
	}

	public void setPoster_id(int poster_id) {
		this.poster_id = poster_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getJog_id() {
		return jog_id;
	}

	public void setJog_id(int jog_id) {
		this.jog_id = jog_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getCompanion() {
		return companion;
	}

	public void setCompanion(String companion) {
		this.companion = companion;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public String getUser_org_photo() {
		return user_org_photo;
	}

	public void setUser_org_photo(String user_org_photo) {
		this.user_org_photo = user_org_photo;
	}

	@Override
	public String toString() {
		return "Poster [poster_id=" + poster_id + ", user_id=" + user_id + ", jog_id=" + jog_id + ", reg_date="
				+ reg_date + ", review=" + review + ", mood=" + mood + ", weather=" + weather + ", song=" + song
				+ ", companion=" + companion + ", user_photo=" + user_photo + ", user_org_photo=" + user_org_photo
				+ "]";
	}

}
