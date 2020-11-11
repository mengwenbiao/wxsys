package com.wechat.model.dao.crm.impl;

import java.util.List;

import com.wechat.model.dao.crm.UserAdminDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.UserAdmin;
import com.wechat.utils.MyQueryRunner;

public class UserAdminDaoImpl extends BaseDaoImpl<UserAdmin> implements UserAdminDao {

	// 引入sql工具
	private MyQueryRunner qr = new MyQueryRunner();

	@Override
	public List<UserAdmin> query() {
		String sql="select * from useradmin";
//		Object[] param= {useradmin.getId(),useradmin.getUsername(),useradmin.getPassword(),useradmin.getRole()};
		List<UserAdmin> result=queryForList(sql,null);
		return result;
	}

	

	

}
