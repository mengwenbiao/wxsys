package com.wechat.model.dao.crm.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.LevelDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.utils.JdbcUtil;



public class LevelDaoImpl extends BaseDaoImpl<level> implements LevelDao {
	// 获取总共有多少条数据
	@Override
	public int getTotal() {
		int total = 0;
		try {
			Connection c = JdbcUtil.getConnection();
			Statement s = c.createStatement();

			String sql = "select count(*) from level";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			System.out.println("total:" + total);

			s.close();

			c.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	// 添加人
	@Override
	public void addLevel(level level) {
		String sql="insert into level values(null,?,?,?,?)";
		//获取时间戳
		Date da=new Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(da.getTime());
		Object[] param= {level.getUsername(),level.getRank(),level.getSuperd(),timestamp};
		dml(sql,param);
	}

	// 修改人
	@Override
	public void updateLevel(level level) {
		String sql="update level set username= ?, `rank` = ? , superd = ? where id = ?";
		Object[] param= {level.getUsername(),level.getRank(),level.getSuperd(),level.getId()};
		dml(sql,param);
	}

	// 删除人
	@Override
	public void deleteLevel(int id) {
		String sql="delete from level where id = " + id;
		Object[] param= {};
		dml(sql,param);
	}

	// 通过id获取信息
	@Override
	public level getLevel(int id) {
		String sql="select * from level where id = " + id;
		return queryForBean(sql, null);
	}

	// 通过名字模糊查询
	@Override
	public List<level> likeQueryListLevel(String name, int start, int count) {
		String sql="select * from level where username like '%" + name + "%' limit ?,?";
		Object[] param= {start,count};
		return queryForList(sql,param);
	}

	// 把persons转为集合
	@Override
	public List<level> list() {
		return listLevel(0, Short.MAX_VALUE);
	}

	// 按照id降序排序从start位置获取person表count条信息，添加到persons数组中
	@Override
	public List<level> listLevel(int start, int count) {
		String sql="select * from level order by id desc limit "+start+","+count+"";
		return queryForList(sql,null);
	}

}
