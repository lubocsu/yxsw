package com.upsoft.system.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpUtil {
	public static HttpURLConnection getHttpConnection(String url){
		URL u = null;
		HttpURLConnection conn = null;
		try {
			u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setReadTimeout(30000);
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 建立实际的连接
			// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
//			conn.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static CloseableHttpClient getDefaultHttpClient(){
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		return httpclient;
	}
}
