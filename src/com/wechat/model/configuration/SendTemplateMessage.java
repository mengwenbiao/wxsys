
package com.wechat.model.configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.hutool.http.HttpUtil;

public class SendTemplateMessage {
	
	public static void sendTemplate(String openid) {
		String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
		String data="{\r\n" + 
				"           \"touser\":\""+openid+"\",\r\n" + 
				"           \"template_id\":\"e0nS3g32Gc2Xu-9RmXQgoF4Rxls1dPVjPRX8pw1oLhA\",\r\n" + 
				"           \"data\":{\r\n" + 
				"                   \"first\": {\r\n" + 
				"                       \"value\":\"投诉成功！\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"keyword1\":{\r\n" + 
				"                       \"value\":\"刘松组\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"keyword2\": {\r\n" + 
				"                       \"value\":\""+new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(Calendar.getInstance().getTime())+"\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"keyword3\": {\r\n" + 
				"                       \"value\":\"已反映\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"remark\":{\r\n" + 
				"                       \"value\":\"感谢您的大力支持！\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   }\r\n" + 
				"           }\r\n" + 
				"       }";
		
		HttpUtil.post(url, data);
		
	}

}