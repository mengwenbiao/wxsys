package com.wechat.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.factory.DaoFactory;
import com.wechat.model.pojo.User;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TestSql {
	
	@Test
	public void test() {
		UserDao userDao1 = (UserDao) DaoFactory.getInstance().getDaoByName("userDao1");
		String sql = "select * from user order by subscribe_time limit ?,?";
		Object[] params = new Object[] {1,5};
		List<User> users = userDao1.queryForList(sql, params);
		System.out.println(users);
	}
	
	@Test
	public void test5() {
		UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
		List<User> user = userDao.queryDateCounts();
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		StringBuffer str = new StringBuffer();
		System.out.println(user);
		for(User users:user) {
//			System.out.println(users.getCounts());
			str.append(users.getCounts()+",");
//			list.add(users.getCounts());
//			list1.add(users.getDate());
		}
		System.out.println(str.length());
		System.out.println(str);
		str.delete(str.length()-1, str.length());
		System.out.println(str);
//		System.out.println(list);
//		System.out.println(list1);
	}
	
	private static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String APPID = "wx8b26b3270d890433";
	private static String APPSECRET = "2e6a863992a0d191212e744114efe500";
	//用户的url
	private static String userUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	@Test
	public void test6() {
		
		String urlString=accessTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		
		String result = HttpUtil.get(urlString);
		//result是一个json格式字符串 json key-value
		JSONObject jsonObject = JSONUtil.parseObj(result);
		String accesstoken=jsonObject.getStr("access_token");
//		System.out.println(accesstoken+"====");
		String openid="ovpSs6vpTW7DAkhe7VeKZ672ZGUo";
		String ruserUrl=userUrl.replace("ACCESS_TOKEN", accesstoken).replace("OPENID",openid);
		
		// 调用获取用户信息的接口
		// 获取用户信息中的昵称和头像
		String result1 = HttpUtil.get(ruserUrl);
		JSONObject parseObj = JSONUtil.parseObj(result1);
		System.out.println(parseObj);
		String subscribe = parseObj.get("subscribe").toString();
		String openid1 = parseObj.get("openid").toString();
		String nickname = parseObj.get("nickname").toString();
		String sex = parseObj.get("sex").toString();
		String city = parseObj.get("city").toString();
		String country = parseObj.get("country").toString();
		String province = parseObj.get("province").toString();
		String language = parseObj.get("language").toString();
		String headimgurl=parseObj.get("headimgurl").toString();
		String subscribe_time = parseObj.get("subscribe_time").toString();
//		String unionid = parseObj.get("unionid").toString();
//		System.out.println(unionid+"----");
		String remark = parseObj.get("remark").toString();
		String groupid = parseObj.get("groupid").toString();
		String tagid_list = parseObj.get("tagid_list").toString();
		String subscribe_scene = parseObj.get("subscribe_scene").toString();
		String qr_scene = parseObj.get("qr_scene").toString();
		String qr_scene_str = parseObj.get("qr_scene_str").toString();
		//存储
		UserDao userDao=(UserDao) DaoFactory.getInstance().getDaoByName("userDao");
//		UserDao userDao= new UserDaoImpl();
		User user=new User();
		user.setSubscribe(subscribe);
		user.setOpenid(openid1);
		user.setNickname(nickname);
		user.setSex(sex);
		user.setCity(city);
		user.setCountry(country);
		user.setProvince(province);
		user.setLanguage(language);
		user.setHeadimgurl(headimgurl);
		user.setSubscribe_time(subscribe_time);
//		user.setUnionid(unionid);
		user.setRemark(remark);
		user.setGroupid(groupid);
		user.setTagid_list(tagid_list);
		user.setSubscribe_scene(subscribe_scene);
		user.setQr_scene(qr_scene);
		user.setQr_scene_str(qr_scene_str);
		System.out.println(user);
		User user1 = (User) userDao.query4Login(openid1);
//		String openid2=user1.getOpenid();
//		System.out.println(openid2);
		if(user1==null) {
			userDao.addUser(user);
		}
		System.out.println(nickname);
	}
	
	// String result2=TextTemplate.getCustomerTemplate(nickname+"欢迎您关注本公众号",xmlMap);
	// HttpUtil.post(url, result2);
	
	
	
	
}
