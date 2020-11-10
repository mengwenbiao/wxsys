package com.wechat.model.dao.crm.impl;


import java.util.List;
import java.util.Map;

import com.wechat.model.dao.crm.RankingDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.Ranking;
import com.wechat.model.pojo.User;

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


}
