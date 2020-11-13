package com.wechat.model.dao.crm;

import java.util.List;
import java.util.Map;

import com.wechat.model.pojo.Ranking;

public interface RankingDao {
	public void addRanking(Ranking rank);//增加信息
	public List<Ranking> queryRank();//查询所有信息
	public int query(String openid);
	
}
