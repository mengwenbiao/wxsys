package com.wechat.test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wechat.model.configuration.TokenConfig;
import com.wechat.model.dao.crm.RankingDao;
import com.wechat.model.dao.crm.impl.RankingDaoImpl;
import com.wechat.model.pojo.Ranking;


public class TestWx {
	
	@Test
	public void testDaoImpl() {
		
		RankingDao rank=new RankingDaoImpl();
		List<Ranking> a=rank.queryRank();
		
		System.out.println(a);
//		Iterator<Ranking> rk=a.iterator();
//		while(rk.hasNext()) {
//			Ranking nickname=rk.next();
//			String nick=nickname.getNickname();
//			String openid=nickname.getOpenid();
//			System.out.println(nick+"---"+openid);
//			
//		}
		for(Ranking b:a) {
			System.out.println(b);
			
		}
	}
	
	
	@Test
	public void testDao() {
		Ranking rk=new Ranking("1111","1");
		RankingDao rank=new RankingDaoImpl();
		rank.addRanking(rk);

		
	}
	
	
	@Test
	public void cut() {
		String a="G:\\Test\\header.jpg";
		String b="G:\\Test\\target.jpg";
		
		
	}
	
	
	@Test
	public void ssupload() {
		///wechatdemo/WebContent/WEB-INF/classes
		String result=TokenConfig.upload("../../img/haibaouser.jpg", "image");
		System.out.println(result);
	}
	
	
	@Test
	public void testTicket() {
		//TokenConfig.getQrCodeTicket(userOpenid);
		
	}
	
	
	@Test
	public void testImage() {
		File file1=new File("G:\\a\\haibao.jpg");
		File file2=new File("G:\\a\\ss.jpg");
		//ImgUtils.getIamgePressText(file1, file2,"你好", -230,-520);
		
		
	}
	
	
	
	@Test
	public void testUpload() {
		//E:\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\wechatdemo\img\Templateimg
		System.out.println(TokenConfig.getAccessToken());
		//String file="http://thirdwx.qlogo.cn/mmopen/VaxZ4UVDKX51iahhbnpfjJOIiaJQPBWlkpy1zS8FjugfIQpeViawgdibp4NBw4sD72y5AnrW4wlsLVgic5icWXjWNHV46qPVj7q0jT/132";
		String file="E:\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\wechatdemo\\img\\Templateimg\\targethaibao.jpg";
		String info=TokenConfig.upload(file,"image");
		System.out.println(info);
		
	}
	
	
	@Test
	public void getUserInfo() {
		String user="o8ft36JLsgbT1lX0L1QqdHZphCBY";
		String  info=TokenConfig.getUserInfo(user);
		System.out.println(info);
		
	}
	
	
	@Test
	public void getQrCodeTickets() {
		//示例
		String  info=TokenConfig.getQrCodeTicket("1111");
		System.out.println(info);
		
	}
	

}
