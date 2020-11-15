package com.wechat.model.dao.crm.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import com.wechat.model.dao.crm.RankingDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.Ranking;
import com.wechat.model.pojo.User;
import com.wechat.utils.JdbcUtil;

public class RankingDaoImpl extends BaseDaoImpl<Ranking> implements RankingDao{

	@Override
	public void addRanking(Ranking rank) {
		String sql="insert into ranking value (null,?,?)";
		Object[] param= {rank.getOpenid(),rank.getNickname()};
		dml(sql,param);
		
	}


	@Override
	public List<Ranking> queryRank() {
		String sql="select count(*) as count,nickname from ranking  group by nickname order by count desc";
		return queryForList(sql,null);
	}


	@Override
	public int query(String openid) {
		String sql="select count(*) from ranking group by openid";
		int count=0;
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtil.getConnection();
			ps=conn.prepareStatement(sql);
			count=ps.executeUpdate();
			System.out.println(count);
			return count;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, null, null);
		}
	}

}
