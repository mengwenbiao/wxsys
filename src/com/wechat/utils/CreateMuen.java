package com.wechat.utils;

import com.wechat.model.bean.AccessToken;
import com.wechat.model.configuration.TokenConfig;

import cn.hutool.http.HttpUtil;

//创建菜单
public class CreateMuen {
	
	public static void main(String[] args) {
		
		String KEYONE="a001";
		String KEYTWO="a002";
		String KEYTHREE="a003";
		String KEYFORE="a004";
		String url=" https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN",TokenConfig.getAccessToken());
		String data="  {\r\n" + 
				"     \"button\":[\r\n" + 
				"      {\r\n" + 
				"           \"name\":\"活动\",\r\n" + 
				"           \"sub_button\":[\r\n" + 
				"           {	\r\n" + 
				"               \"type\":\"click\",\r\n" + 
				"               \"name\":\"免费赠送\",\r\n" + 
				"               \"key\":\""+KEYONE+"\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"    	\"type\":\"click\",\r\n" + 
				"               \"name\":\"促销\",\r\n" + 
				"               \"key\":\""+KEYTWO+"\"\r\n" + 
				"             },\r\n" + 
				"            {\r\n" + 
				"               \"type\":\"click\",\r\n" + 
				"               \"name\":\"团购\",\r\n" + 
				"               \"key\":\""+KEYTHREE+"\"\r\n" + 
				"            }]\r\n" + 
				"       },\r\n" + 
				"      {\r\n" + 
				"           \"name\":\"帮助\",\r\n" + 
				"           \"sub_button\":[\r\n" + 
				"           {	\r\n" + 
				"               \"type\":\"view\",\r\n" + 
				"               \"name\":\"搜索\",\r\n" + 
				"               \"url\":\"http://www.soso.com/\"\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"type\":\"view\",\r\n" + 
				"               \"name\":\"百度\",\r\n" + 
				"               \"url\":\"http://www.baidu.com/\"\r\n" + 
				"             },\r\n" + 
				"            {\r\n" + 
				"\r\n" + 
				"               \"type\":\"view\",\r\n" + 
				"               \"name\":\"关于我们\",\r\n" + 
				"               \"url\":\"http://vipsm.natapp1.cc/wechatdemo/index.html\"\r\n" + 
				"            }]\r\n" + 
				"       },\r\n" + 
				"      {	\r\n" + 
				"          \"type\":\"click\",\r\n" + 
				"          \"name\":\"投诉\",\r\n" + 
				"          \"key\":\""+KEYFORE+"\"\r\n" + 
				"      }]\r\n" + 
				" }";
		String result=HttpUtil.post(url, data);
		System.out.println(result);
	}

}
