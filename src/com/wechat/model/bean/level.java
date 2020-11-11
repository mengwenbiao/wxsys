package com.wechat.model.bean;

public class level {
	private Integer id;
	private String username;
	private int rank;
	private String superd;
	public level() {
	}
	public level(Integer id, String username, int rank, String superd) {
		this.id = id;
		this.username = username;
		this.rank = rank;
		this.superd = superd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getSuperd() {
		return superd;
	}
	public void setSuperd(String superd) {
		this.superd = superd;
	}
	@Override
	public String toString() {
		return "level [id=" + id + ", username=" + username + ", rank=" + rank + ", superd=" + superd + "]";
	}
	
	
	
}
