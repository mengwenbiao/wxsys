package com.wechat.model.dao.crm.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.LevelDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.utils.JdbcUtil;



public class LevelDaoImpl extends BaseDaoImpl<level> implements LevelDao {
//	//添加
//	@Override
//	public void addLevel(level level) {
//		String sql="insert into level (id,superd,rank,user_id) values(null,?,?,?)";
//		Object[] params=new Object[] {level.getSuperd(),level.getRank(),level.getUser_id()};
//		dml(sql,params);		
//	}
//	//id查询
//	@Override
//	public void queryLevel(int id) {
//		String sql="select * from level where id="+id;
//		Object[] params=new Object[] {};
//		level user=queryForBean(sql, params);
//		System.out.println(user);		
//	}
//	//分页查询
//	@Override
//	public List<level> pageQueryData(Map<String, Object> map) {
//		int start=(int) map.get("start");
//		int size=(int) map.get("size");
//		String sql="select * from level order by createtime desc limit ?,?";
//		Object[] params=new Object[] {start,size};
//		List<level> users=queryForList(sql, params);
//		return users;
//	}
//	@Override
//	public List<level> queryUserByTags(String tag) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public List<level> queryUserByLevel(String level) {
//		// TODO Auto-generated method stub
//		return null;
//	}
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
	public void add(level level) {
		try {
			Connection c = JdbcUtil.getConnection();
			String sql = "insert into level values(null,?,?,?,null)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, level.getUsername());
			ps.setInt(2, level.getRank());
			ps.setString(3, level.getSuperd());

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				level.setId(id);
			}
			ps.close();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 修改人
	@Override
	public void update(level level) {
		try {
			Connection c = JdbcUtil.getConnection();
			String sql = "update level set username= ?, rank = ? , superd = ? where id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, level.getUsername());
			ps.setInt(2, level.getRank());
			ps.setString(3, level.getSuperd());
			ps.setInt(4, level.getId());

			ps.execute();
			ps.close();
			c.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 删除人
	@Override
	public void delete(int id) {
		try {
			Connection c = JdbcUtil.getConnection();
			Statement s = c.createStatement();
			String sql = "delete from level where id = " + id;
			s.execute(sql);
			s.close();
			c.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 通过id获取信息
	@Override
	public level get(int id) {
		level level = null;
		try {
			Connection c = JdbcUtil.getConnection();
			Statement s = c.createStatement();
			String sql = "select * from level where id = " + id;
			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				level = new level();

				int idx = rs.getInt("id");
				String username = rs.getString("username");
				int rank = rs.getInt("rank");
				String superd = rs.getString("superd");

				level.setId(idx);
				level.setUsername(username);
				level.setRank(rank);
				level.setSuperd(superd);
			}

			s.close();
			c.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}

	// 通过名字模糊查询
	@Override
	public List<level> likeQueryList(String name, int start, int count) {
		List<level> levels = new ArrayList<level>();
		try {
			Connection c = JdbcUtil.getConnection();
			String sql = "select * from level where username like '%" + name + "%' limit ?,?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				level level = new level();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				int rank=rs.getInt("rank");
				String superd = rs.getString("superd");
				level.setId(id);
				level.setUsername(username);
				level.setRank(rank);
				level.setSuperd(superd);
				levels.add(level);
			}
			ps.close();
			c.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return levels;
	}

	// 把persons转为集合
	@Override
	public List<level> list() {
		return list(0, Short.MAX_VALUE);
	}

	// 按照id降序排序从start位置获取person表count条信息，添加到persons数组中
	@Override
	public List<level> list(int start, int count) {
		List<level> levels = new ArrayList<level>();
		try {
			Connection c = JdbcUtil.getConnection();
			String sql = "select * from level order by id desc limit ?,? ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				level level = new level();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				int rank=rs.getInt("rank");
				String superd = rs.getString("superd");
				level.setId(id);
				level.setUsername(username);
				level.setRank(rank);
				level.setSuperd(superd);
				levels.add(level);
			}
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return levels;
	}

}
