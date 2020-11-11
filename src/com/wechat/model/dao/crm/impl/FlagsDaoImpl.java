package com.wechat.model.dao.crm.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wechat.model.dao.crm.FlagsDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.Flags;
import com.wechat.utils.JdbcUtil;


public class FlagsDaoImpl extends BaseDaoImpl<Flags> implements FlagsDao {

	@Override
	public void add(Flags flags) {
		Connection conn=null;
		try {
			conn=JdbcUtil.getConnection();
			// 查询语句
			String sql = "insert into flags values(null,?,?,?,?)";
			// 预编译
			PreparedStatement ps = conn.prepareStatement(sql);
			// 获取值
			ps.setString(1, flags.getUsername());
			ps.setInt(2, flags.getSale());
			ps.setInt(3, flags.getFree());
			ps.setInt(4, flags.getTeamsale());
			ps.execute();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Flags> query() {
		String sql="select * from flags";
		return queryForList(sql,null);
//		List<Flags> flags = new ArrayList<Flags>();
//		Connection conn=null;
//		try {
//			conn=JdbcUtil.getConnection();
//			Statement s = conn.createStatement();
//			String sql = "select * from flags";
//			ResultSet rs = s.executeQuery(sql);
//			while (rs.next()) {
//				Flags flag = new Flags();
//				Integer ids = rs.getInt("id");
//				String username = rs.getString("username");
//				int sale = rs.getInt("sale");
//				int free = rs.getInt("free");
//				int teamsale = rs.getInt("teamsale");
//				flag.setId(ids);
//				flag.setUsername(username);
//				flag.setSale(sale);
//				flag.setFree(free);
//				flag.setTeamsale(teamsale);
//				flags.add(flag);
//			}
//			System.out.println(flags);
//			s.close();
//			conn.close();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return flags;
	}

	@Override
	public List<Flags> list(int start, int count) {
		List<Flags> flags = new ArrayList<Flags>();
		Connection conn=null;
		try {
			conn=JdbcUtil.getConnection();
			String sql = "select * from flags order by id asc limit ?,? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Flags flag = new Flags();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				int sale = rs.getInt("sale");
				int free = rs.getInt("free");
				int teamsale = rs.getInt("teamsale");
				flag.setId(id);
				flag.setUsername(username);
				flag.setSale(sale);
				flag.setFree(free);
				flag.setTeamsale(teamsale);
				flags.add(flag);
			}
			System.out.println(flags);
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flags;
	}

	@Override
	public List<Flags> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public int getTotal() {
		int total = 0;
		Connection conn = null;
		try {
			conn=JdbcUtil.getConnection();
			Statement s = conn.createStatement();
			String sql = "select count(*) from flags";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total:" + total);
			s.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public Flags get(int id) {
		Flags flags = null;
		Connection conn = null;
        try {
        	conn=JdbcUtil.getConnection();
            Statement s = conn.createStatement();
            String sql = "select * from flags where id = " + id;
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
            	flags = new Flags();
				int ids = rs.getInt("id");
				String username = rs.getString("username");
				int sale = rs.getInt("sale");
				int free = rs.getInt("free");
				int treamsale=rs.getInt("teamsale");
				flags.setId(ids);
				flags.setUsername(username);
				flags.setSale(sale);
				flags.setFree(free);
				flags.setSale(treamsale);
				System.out.println(flags);
            }
 
            s.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flags;
	}

	@Override
	public void update(Flags flag) {
		Connection conn = null;
		try {
			conn=JdbcUtil.getConnection();
            String sql = "update flags set username= ?, sale = ? , free = ? , teamsale=? where id = ?";
          
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, flag.getUsername());
            ps.setInt(2, flag.getSale());
            ps.setInt(3,flag.getFree());
            ps.setInt(4,flag.getTeamsale());
            ps.setInt(5, flag.getId());
 
            ps.execute();
            ps.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Flags> query(String username) {
		List<Flags> flags = new ArrayList<Flags>();
		Connection conn = null;
		try {
			conn=JdbcUtil.getConnection();
			Statement s = conn.createStatement();
			System.out.println("um:"+username);
			String sql = "select * from flags where username like'%" + username +"%"+"'" ;
			System.out.println("sql:"+sql);
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Flags flag = new Flags();
				Integer ids = rs.getInt("id");
				String username1 = rs.getString("username");
				int sale = rs.getInt("sale");
				int free = rs.getInt("free");
				int teamsale = rs.getInt("teamsale");
				flag.setId(ids);
				flag.setUsername(username1);
				flag.setSale(sale);
				flag.setFree(free);
				flag.setTeamsale(teamsale);
				flags.add(flag);
			}
			System.out.println(flags);
			s.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flags;
	}
}
