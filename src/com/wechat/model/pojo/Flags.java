package com.wechat.model.pojo;

public class Flags {
	private int id;
	private String username;
	private int sale;
	private int free;
	private int teamsale;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getTeamsale() {
		return teamsale;
	}
	public void setTeamsale(int teamsale) {
		this.teamsale = teamsale;
	}
	public Flags(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	public Flags(int id, String username, int sale, int free, int teamsale) {
		super();
		this.id = id;
		this.username = username;
		this.sale = sale;
		this.free = free;
		this.teamsale = teamsale;
	}
	
	public Flags() {
		super();
	}
	@Override
	public String toString() {
		return "Flags [id=" + id + ", username=" + username + ", sale=" + sale + ", free=" + free + ", teamsale="
				+ teamsale + "]";
	}
	
	
}
