package com.wechat.model.configuration;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.wechat.model.bean.AccessToken;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TokenConfig {
	
	
	private static String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static String APPID="wx5abc39a8a3fab6d4";
	private static String APPSECRET="5325a3a579ea9d3cec0ead9fe83c2ff9";
	
	//获取用户信息url
	private static String getUserUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	
	//客服的url
	private static String customerUrl=" https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	private static AccessToken at=null;
	
	//初始化token
	private  static void initToken() {
		//替换accessToken -APPID APPSECRET
		String url=accessTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		//从微信服务器获取对应权限
		String tokenStr=HttpUtil.get(url);
		
		//解析微信服务器发送过来的json请求
		JSONObject jsonObject=JSONUtil.parseObj(tokenStr);
		//取出token和expire
		String token=jsonObject.getStr("access_token");
		String expireIn=jsonObject.getStr("expires_in");
		//封装进入token值
		at=new AccessToken(token,expireIn);
		
	}
	//获取token
	public static String getAccessToken() {
		if(at==null||at.expiredStatus()) {
			initToken();
		}
		return at.getAccessToken();
	}
	
	//获取客服调用接口
	public static String getCustomerUrl() {
		
		customerUrl=customerUrl.replace("ACCESS_TOKEN", TokenConfig.getAccessToken());
		return customerUrl;
	}
	//获取关注用户信息
	public static String getUserInfo(String openid) {
		String url=getUserUrl.replace("ACCESS_TOKEN",TokenConfig.getAccessToken()).replace("OPENID", openid);
		String result=HttpUtil.get(url);
		return result;
	}
	//获取二维码Ticket
	public static String getQrCodeTicket(String userOpenid) {
		String token=TokenConfig.getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
		//生成临时字符串二维码
		String data="{"
				+ "\"expire_seconds\": 604800, "
				+ "\"action_name\": \"QR_STR_SCENE\", "
				+ "\"action_info\": {"
				+ "\"scene\": {"
				+ "\"scene_str\": \""+userOpenid+"\"}}}";
		String result=HttpUtil.post(url,data);
		JSONObject jsonObject=JSONUtil.parseObj(result);
		String ticket=jsonObject.getStr("ticket");
		return ticket;
	}
	//通过ticket换取二维码路径
	public static String getQrCode(String ticket) {
		String url="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		url=url.replace("TICKET", ticket);
		return url;
	}
	
	
	
	
	//上传临时素材,并获得media_id
	public static String upload(String path,String type) {
		String result=null;
		//获取本地图片
		File file=new File(path);
		String url="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		url=url.replace("ACCESS_TOKEN",TokenConfig.getAccessToken()).replace("TYPE",type);
		try {
			// 获取网络图片
			//URL mediaUrl = new URL(path);
			//HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			String fileName = System.currentTimeMillis()/1000+".jpg";
			
			URL urlObj=new URL(url);
			//强转成https  安全链接
			HttpsURLConnection conn=(HttpsURLConnection)urlObj.openConnection();
			//设置链接的信息
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);//不缓存
			//设置请求头信息
			conn.setRequestProperty("Connnection", "keep-Alive");
			conn.setRequestProperty("Charset", "utf8");
			//数据的边界
			String boundary="-----"+System.currentTimeMillis();
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			//获取输出流
			OutputStream out=conn.getOutputStream();
			//创建文件输入流
			InputStream in=new FileInputStream(file);
			//获取网络的图片
			//DataInputStream in = new DataInputStream(meidaConn.getInputStream());
			//第一部分 头部信息
			//准备头部信息
			StringBuilder sb=new StringBuilder();
			sb.append("--");//post提交表单要求
			sb.append(boundary);
			sb.append("\r\n");
			sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+fileName+"\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			out.write(sb.toString().getBytes());
			//System.out.println(sb.toString());
			//第二部分 文件内容
			byte[] b=new byte[1024];
			int len;
			while(-1!=(len=in.read(b))) {
				out.write(b,0,len);
			}
			//第三部分 尾部信息
			String foot="\r\n--"+boundary+"--\r\n";
			out.write(foot.getBytes());
			out.flush();
			out.close();
			//读取数据
			InputStream is2=conn.getInputStream();
			StringBuilder resp=new StringBuilder();
			while(-1!=(len=is2.read(b))) {
				resp.append(new String(b,0,len));
			}
			result=resp.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject=JSONUtil.parseObj(result);
		
		 return jsonObject.getStr("media_id");
	}
	//
	
	
}
