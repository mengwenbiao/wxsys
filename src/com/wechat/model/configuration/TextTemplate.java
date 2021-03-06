package com.wechat.model.configuration;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.wechat.model.bean.ImageMediaId;
import com.wechat.model.dao.crm.RankingDao;
import com.wechat.model.dao.crm.impl.FlagsDaoImpl;
import com.wechat.model.dao.crm.impl.RankingDaoImpl;
import com.wechat.model.pojo.Flags;
import com.wechat.model.pojo.Media;
import com.wechat.model.pojo.Ranking;
import com.wechat.utils.ImgUtils;




import com.wechat.model.bean.level;
import com.wechat.model.dao.crm.LevelDao;
import com.wechat.model.dao.crm.impl.FlagsDaoImpl;
import com.wechat.model.dao.crm.impl.LevelDaoImpl;

import com.wechat.model.dao.crm.impl.MediaDaoImpl;

import com.wechat.model.pojo.Flags;
import com.wechat.utils.StringUtil;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TextTemplate {

	private static String usernickname;

	private static String userOpenid;

	// 1 关注回复 2 自动回复 3 其他回复
	public static String getContent(int type) {

		switch (type) {
		case 1:
			return "关注公众号/:rose/:rose/:rose";
		case 2:
			return "感谢你的留言";
		case 3:
			return "老客户，欢迎您回家/:rose/:rose/:rose";
		}
		return null;
	}

	// 处理文本内容
	public static String getTextTemplate(Map<String, String> xmlMap) {

		String template = "<xml>\r\n" + "  <ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName>\r\n"
				+ "  <FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName>\r\n" + "  <CreateTime>"
				+ StringUtil.getWxCreateTime() + "</CreateTime>\r\n" + "  <MsgType><![CDATA[text]]></MsgType>\r\n"
				+ "  <Content><![CDATA[" + getContent(2) + "]]></Content>\r\n" +

				"</xml>";

		return template;
	}

	// 处理事件-带参数关注
	public static String getEventWithParamsTemplate(Map<String, String> xmlMap) {
		String userOpenId = xmlMap.get("FromUserName");
		// 获取扫描二维码的信息
		String toUser = xmlMap.get("EventKey");
		// qrscene_o8ft36DgD0lV9CoQQUjNNqh0rfnU
		String toUserId = toUser.substring(8);
		//二维码用户的openid
		userOpenid=toUserId.substring(0,28);

		// 获取二维码中带的openid的用户信息：上级信息
		String users = TokenConfig.getUserInfo(toUserId);
		JSONObject json1 = JSONUtil.parseObj(users);
		// 获得上级的昵称和openid
		String superNickname = json1.getStr("nickname");
		String superOpenid = json1.getStr("openid");
		Ranking rk=new Ranking(toUserId,superNickname);
		new RankingDaoImpl().addRanking(rk);
		System.out.println("上级用户名：" + superNickname + ",superOpenid:" + superOpenid);
		// 获取当前用户信息
		String info = TokenConfig.getUserInfo(userOpenId);// json文件格式
		// 获取当前用户的昵称和openid
		JSONObject jsonObject = JSONUtil.parseObj(info);
		String nickname = jsonObject.getStr("nickname");
		String openid = jsonObject.getStr("openid");
		usernickname = nickname;
		System.out.println("当前用户名：" + nickname + ",openid:" + openid);
		
		//获取客服接口，把助力消息发送给用户
		String url=TokenConfig.getCustomerUrl();
		String result=TextTemplate.getCustomerTemplate(nickname,xmlMap);
		HttpUtil.post(url, result);
		System.out.println("助力成功");
		//查看助力榜单
		String rankUrl=TextTemplate.getRanking();
		HttpUtil.post(url, rankUrl);
		//海报模板
		String str=toUserId.substring(28);
		//发送海报给关注公众号的用户
		String result2=TextTemplate.getCustomerImageTemplate(nickname,xmlMap);
		String media=null;
		List<Media> ms=new MediaDaoImpl().queryMediaById();
		//健壮性判断
//		for(Media md:ms) {
//			if(userOpenId.equals(md.getOpenid())) {
//				//临时素材库只能在微信服务器保存3天，则要判断media是否失效
//				//现在的时间是
//				long nowTime=System.currentTimeMillis();
//				//与上传的时间进行比较，如果超过3天就重新上传海报生成mediaid，如果没有失效，就调用数据库的mediaid
//				long createtime=md.getCreatetime()+259200000;
//				if(nowTime-createtime>=0) {
//					media=md.getMediaid();
//					break;
//				}else {
//					//从新生成
//					media=ImageMediaConfig.getMedia(xmlMap);
//					break;
//				}
//			}else {
//				media=ImageMediaConfig.getMedia(xmlMap);
//				break;
//			}
//		}
		//判断
		String template=null;
		if(str.equals("haibao")) {
			media=ImageMediaConfig.getMedia(xmlMap);
			template=getHaiBao(media,xmlMap);
		}else if(str.equals("two")) {
			media=ImageMediaConfig.getMedia2(xmlMap);
			template=getHaiBao(media,xmlMap);
		}else if(str.equals("three")) {
			media=ImageMediaConfig.getMedia3(xmlMap);
			template=getHaiBao(media,xmlMap);
		}
	
		//发送欢迎关注
		HttpUtil.post(url, result2);
		//发送海报
		HttpUtil.post(url, template);
		// 判断数据库中是否已经有此openid
		LevelDaoImpl ldi = new LevelDaoImpl();		
		List<level> listLevel = ldi.listLevel(0, ldi.getTotal());
		// 定义一个标记，为true，默认数据库中无此数据
		boolean exist = true;
		for (int i = 0; i < listLevel.size(); i++) {
			String openidSelect = listLevel.get(i).getOpenid();
			// 当有此数据时，设置exist=false
			if (openidSelect.equals(openid)) {
				exist = false;
			}
		}
		if (exist) {
			// 当没有此用户时把此用户信息存入数据库
			int rank = 1;
			level le = new level(null, nickname, openid, superNickname, superOpenid, rank);
			ldi.addLevel(le);

			// 判断此用户的上级助力人数有没有达到3人，达到的话，等级加一
			int countLower = ldi.getCountLower(superOpenid);
			// System.out.println("助力人数："+countLower);
			int superRank = countLower / 3 + 1;
			// System.out.println("助力等级："+superRank);
			// 判断上级用户的等级为多少，如果小于superRank，则修改为superRank
			level SuperLevel = ldi.getLevel(superOpenid);
			int ranking = SuperLevel.getRanking();
			// System.out.println("上级没有改变前的等级："+ranking);
			if (superRank > ranking) {
				SuperLevel.setRanking(superRank);
				ldi.updateLevel(SuperLevel);
			}

		}
		boolean est = true;
		FlagsDaoImpl users1 = new FlagsDaoImpl();
		// 查询数据库内的信息
		List<Flags> us = users1.query();
		for (int i = 0; i < us.size(); i++) {
			Flags f = us.get(i);
			String username = f.getUsername();
			if (nickname.equals(username)) {
				est = false;
			}
		}
		System.out.println("est:" + est);
		if (est) {
			Flags flag = new Flags();
			flag.setUsername(nickname);
			flag.setFree(1);
			flag.setSale(0);
			flag.setTeamsale(0);
			users1.add(flag);
		}
		return "success";

	}

	// 不带参数公众号
	public static String getEventWithOutParamsTemplate(Map<String, String> xmlMap) {
		String userOpenId = xmlMap.get("FromUserName");
		// System.out.println(userOpenId);
		String info = TokenConfig.getUserInfo(userOpenId);// json文件格式
		// System.out.println(info);
		// 解析微信服务器发送过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(info);
//		String openid=jsonObject.getStr("openid");
//		System.out.println("openid-->:"+openid);
		String nickname = jsonObject.getStr("nickname");
		
		String url2 = TokenConfig.getCustomerUrl();
		String media = ImageMediaConfig.getMedia(xmlMap);
		String result2 = TextTemplate.getCustomerImageTemplate(nickname, xmlMap);
		String template = getHaiBao(media, xmlMap);
		// 回复欢迎关注信息
		HttpUtil.post(url2, result2);
		// 回复海报
		HttpUtil.post(url2, template);
	
		// 判断数据库中是否已经有此openid
		LevelDaoImpl ldi = new LevelDaoImpl();
		List<level> listLevel = ldi.listLevel(0, ldi.getTotal());
		// 定义一个标记，为true，默认数据库中无此数据
		boolean exist = true;
		for (int i = 0; i < listLevel.size(); i++) {
			String openidSelect = listLevel.get(i).getOpenid();
			// 当有此数据时，设置exist=false
			if (openidSelect.equals(userOpenId)) {
				exist = false;
			}
		}
		//没有的话进行添加
		if (exist) {
			level superle = new level();
			superle.setNickname(nickname);
			superle.setOpenid(userOpenId);
			superle.setRanking(1);
			ldi.addLevel(superle);
		}

		boolean est = true;
		FlagsDaoImpl users1 = new FlagsDaoImpl();
		// 查询数据库内的信息
		List<Flags> us = users1.query();
		for (int i = 0; i < us.size(); i++) {
			Flags f = us.get(i);
			String username = f.getUsername();
			if (nickname.equals(username)) {
				est = false;
			}
		}
		System.out.println("est:" + est);
		if (est) {
			Flags flag = new Flags();
			flag.setUsername(nickname);
			flag.setFree(1);
			flag.setSale(0);
			flag.setTeamsale(0);
			users1.add(flag);
		}
		return "success";
	}

	// 关注客服发送海报
	public static String getHaiBao(String media, Map<String, String> xmlMap) {
		String result = "{\r\n" + "    \"touser\":\"" + xmlMap.get("FromUserName") + "\",\r\n"
				+ "    \"msgtype\":\"image\",\r\n" + "    \"image\":\r\n" + "    {\r\n" + "      \"media_id\":\""
				+ media + "\"\r\n" + "    }\r\n" + "}";

		return result;
	}

	// 直接取出参数
	public static String getEventParamsTemplate(Map<String, String> xmlMap) {

		String template = "<xml>\r\n" + "  <ToUserName><![CDATA[" + xmlMap.get("FromUserName") + "]]></ToUserName>\r\n"
				+ "  <FromUserName><![CDATA[" + xmlMap.get("ToUserName") + "]]></FromUserName>\r\n" + "  <CreateTime>"
				+ StringUtil.getWxCreateTime() + "</CreateTime>\r\n" + "  <MsgType><![CDATA[text]]></MsgType>\r\n"
				+ "  <Content><![CDATA[" + getContent(3) + "]]></Content>\r\n" + "</xml>";
		return template;
	}

	// 客服发送为谁助力
	public static String getCustomerTemplate(String name, Map<String, String> xmlMap) {
		String result = "" + "{\r\n" + "    \"touser\":\"" + userOpenid + "\",\r\n" + "    \"msgtype\":\"text\",\r\n"
				+ "    \"text\":\r\n" + "    {\r\n" + "         \"content\":\"" + usernickname
				+ "已为您助力/:rose/:rose/:rose" + "\"\r\n" + "    }\r\n" + "}";
		return result;
	}

	// //客服发送排行榜
	public static String getRanking() {
		String url = "http://vipsm.natapp1.cc/wechatdemo/Rank.html";
		String result = "{\r\n" + "    \"touser\":\"" + userOpenid + "\",\r\n" + "    \"msgtype\":\"news\",\r\n"
				+ "    \"news\":{\r\n" + "        \"articles\": [\r\n" + "         {\r\n"
				+ "             \"title\":\"点击链接查看您的助力排行榜\",\r\n" + "             \"description\":\"排行榜\",\r\n"
				+ "             \"url\":\"" + url + "\",\r\n" + "             \"picurl\":\"PIC_URL\"\r\n"
				+ "         }\r\n" + "         ]\r\n" + "    }\r\n" + "}";
		return result;
	}

	// 客服发送欢迎关注信息
	public static String getCustomerImageTemplate(String nickname, Map<String, String> xmlMap) {

		String result = "	{\r\n" + "		    \"touser\":\"" + xmlMap.get("FromUserName") + "\",\r\n"
				+ "		    \"msgtype\":\"text\",\r\n" + "		    \"text\":\r\n" + "		    {\r\n"
				+ "		         \"content\":\"" + "欢迎" + nickname + getContent(1) + "\"\r\n" + "		    }\r\n"
				+ "		}";
		return result;
	}

	// 点击菜单回复对应的海报
	public static String getEventClick(Map<String, String> xmlMap) {
		//获取客服接口
		String url2 = TokenConfig.getCustomerUrl();

		String key = xmlMap.get("EventKey");
		// 获取openid
		String opid = xmlMap.get("FromUserName");
		// 获取通过openid拿到json格式的用户信息
		String un = TokenConfig.getUserInfo(opid);
		// 解析json格式
		JSONObject jsnickname = JSONUtil.parseObj(un);
		// 拿到nickname
		String nickname = jsnickname.getStr("nickname");
		System.out.println("点击菜单拿到-->" + nickname);
		// 赠送
		if (key.equals("a001")) {
			// new出这个实现类
			FlagsDaoImpl users = new FlagsDaoImpl();
			// 查询数据库内的信息
			List<Flags> us = users.query();
			for (int i = 0; i < us.size(); i++) {
				//
				Flags flags = us.get(i);
//				System.out.println(i+"个:"+flags.toString());
				// 获取用户名
				String username = flags.getUsername();
//				System.out.println("u1-->"+username);
				// 判断当前数据库内获取到的username和nickname是否相等
				if (username.equals(nickname)) {
//					System.out.println("进了1这里！！！");
					int sale1 = flags.getSale();
					int free1 = flags.getFree();
					int teamsale1 = flags.getTeamsale();
//					System.out.println("username:"+username);
//					System.out.println("sale="+sale1+"freess="+free1+"teamsale="+teamsale1);
					// 若相等执行该操作
					int sale2 = sale1;
					int sale = flags.setSale(sale2);
					int free = flags.setFree(1);
					int teamsale2 = teamsale1;
					int teamsale = flags.setTeamsale(teamsale2);
					System.out.println("nickname:-->" + nickname);
					System.out.println("username:-->" + username);
//					System.out.println("sale="+sale+"free="+free+"teamsale="+teamsale);
					users.update(flags);
					break;
				} /*
					 * else{ System.out.println("进入2了这里！！！"); // Flags flag=new Flags();
					 * //将username设置为nickname // flags.setUsername(nickname); // flags.setSale(0);
					 * // flags.setFree(1); // flags.setTeamsale(0); // users.add(flags); break; }
					 */
			}

//			System.out.println("遍历数据库flags表："+us);
			String mediaId = ImageMediaConfig.getMedia(xmlMap);
			String result = getHaiBao(mediaId, xmlMap);
			// 回复海报
			HttpUtil.post(url2, result);
			return "success";
		} else if (key.equals("a002")) {
			// new出这个实现类
			FlagsDaoImpl users = new FlagsDaoImpl();
			// 查询数据库内的信息
			List<Flags> us = users.query();
			for (int i = 0; i < us.size(); i++) {
				Flags flags = us.get(i);
				// 获取用户名
				String username = flags.getUsername();
//				int free=flags.getFree();
				// 判断当前数据库内获取到的username和nickname是否相等
				if (username.equals(nickname)) {
					// 若相等执行该操作
					int sale1 = flags.getSale();
					int free1 = flags.getFree();
					int teamsale1 = flags.getTeamsale();
//					System.out.println("username:"+username);
//					System.out.println("sale="+sale1+"freess="+free1+"teamsale="+teamsale1);
					// 若相等执行该操作
					int sale = flags.setSale(1);
					int free2 = free1;
					int free = flags.setFree(free2);
					int teamsale2 = teamsale1;
					int teamsale = flags.setTeamsale(teamsale2);
					users.update(flags);
					break;
				} /*
					 * else { FlagsDaoImpl users1=new FlagsDaoImpl(); Flags flag=new Flags();
					 * //将username设置为nickname flag.setUsername(nickname); flag.setSale(1);
					 * flag.setFree(0); flag.setTeamsale(0); users1.add(flag); break; }
					 */
			}
// 			System.out.println("遍历数据库flags表："+us);
			String mediaId = ImageMediaConfig.getMedia2(xmlMap);
			String result = getHaiBao(mediaId, xmlMap);
			// 回复海报
			HttpUtil.post(url2, result);
		    return "success";
		
		} else if (key.equals("a003")) {
			// new出这个实现类
			FlagsDaoImpl users = new FlagsDaoImpl();
			// 查询数据库内的信息
			List<Flags> us = users.query();
			for (int i = 0; i < us.size(); i++) {
				Flags flags = us.get(i);
				// 获取用户名
				String username = flags.getUsername();
				// 判断当前数据库内获取到的username和nickname是否相等
				if (username.equals(nickname)) {
					// 若相等执行该操作
					int sale1 = flags.getSale();
					int free1 = flags.getFree();
					int teamsale1 = flags.getTeamsale();
//					System.out.println("username:"+username);
//					System.out.println("sale="+sale1+"freess="+free1+"teamsale="+teamsale1);
					// 若相等执行该操作
					int sale2 = sale1;
					int sale = flags.setSale(sale2);
					int free2 = free1;
					int free = flags.setFree(free2);
					int teamsale = flags.setTeamsale(1);
					users.update(flags);
					break;
				} /*
					 * else { FlagsDaoImpl users1=new FlagsDaoImpl(); Flags flag=new Flags();
					 * //将username设置为nickname flag.setUsername(nickname); flag.setSale(0);
					 * flag.setFree(0); flag.setTeamsale(1); users1.add(flag); break; }
					 */
			}
			String mediaId = ImageMediaConfig.getMedia3(xmlMap);
			String result = getHaiBao(mediaId, xmlMap);
			// 回复海报
			HttpUtil.post(url2, result);
			return "success";
		} else if (key.equals("a004")) {
			String openid = xmlMap.get("FromUserName");
			SendTemplateMessage.sendTemplate(openid);
			System.out.println("ok");
			return "";
		}
		return null;
	}

}
