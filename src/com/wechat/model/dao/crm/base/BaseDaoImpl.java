package com.wechat.model.dao.crm.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wechat.utils.JdbcUtil;
import com.wechat.utils.MyQueryRunner;
import com.wechat.utils.ReflectUtil;

public class BaseDaoImpl<T> implements BaseDao<T>{
	
	protected MyQueryRunner qr=new MyQueryRunner();
	
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		this.clazz = ReflectUtil.getSupserGenericType(getClass());//使用这个reflect工具类，返回T的类型
	}
	
	//增改删
	public void dml(String sql,Object[] params) {
		Connection conn=null;
		try {
			conn=JdbcUtil.getConnection();
			//设置事务，手动提交
			conn.setAutoCommit(false);
			qr.update(conn, sql, params);
			//提交事务
			conn.commit();
		}catch(Exception e) {
			try {
				//回滚数据
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, null, null);
		}
	}
	
	//查
	public int queryOpenid(String sql) {
		int count=0;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(sql);
			count=ps.executeUpdate();
			return count;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null, null);
		}
	}

	@Override
	public T queryForBean(String sql, Object... args) {
		Connection conn=null;
		try {
			conn=JdbcUtil.getConnection();
			return qr.query(conn,sql,args,new BeanHandler<T>(clazz));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null, null);
		}
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		Connection conn=null;
		try {
			conn=JdbcUtil.getConnection();
			return qr.query(conn,sql,args,new BeanListHandler<T>(clazz));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null, null);
		}
	}
	
	@Override
	public int getTotal(String sql) {
		Connection conn=null;
		int total=0;
		try {
			conn=JdbcUtil.getConnection();
		
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询列表出现问题！！");
		}
//		System.out.println(total+"------");
		return total;
	}


}
