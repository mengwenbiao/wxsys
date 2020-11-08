package com.wechat.model.dao.crm;

import java.util.List;

import com.wechat.model.pojo.User;

public interface UserDao {

	public void addUser(User user);//增加用户信息
	public void updateUser(User user);//修改用户信息
	//注意：实际开发是没有修改的，多一个用户信息会更好
	
	public User queryUserById(String openid); //通过openid查用户信息
	public List<User> queryUserByTags(String tag);//通过用户标签来查用户信息
	public List<User> queryUserByLevel(String level);//根据用户等级来查询用户信息
	

}
