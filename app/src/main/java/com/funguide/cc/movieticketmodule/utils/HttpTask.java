package com.funguide.cc.movieticketmodule.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressLint("NewApi")
public class HttpTask extends AsyncTask<String, String, String> {
	//MD5加密的键值
	private final String KEY = Constans.KEY;
	//接口请求后的结果放入这个接口中
	private HttpCallBack requestCallBack;
	//接口请求的参数放入这个map中
	private String param;
	//接口请求的URL
	private String url;
	//接口请求的code这里没用上 可以自己请求的时候预设
	private int requestCode;
	//接口请求的可以把参数封装成json的格式上传给服务器 
	private String jsonParams;
	//上下文对象
	private Context context;
    //下面为不同情况的构造方法根据实际情况选用
	public HttpTask(HttpCallBack requestCallBack) {
		this.requestCallBack = requestCallBack;
	}

	public HttpTask(HttpCallBack requestCallBack, String param, String url,Context context) {
		this.requestCallBack = requestCallBack;
		this.param = param;
		this.url = url;
		this.context=context;
	}

	public HttpTask(HttpCallBack requestCallBack, String params, String url,int requestCode,Context context) {
		this.requestCallBack = requestCallBack;
		this.jsonParams = params;
		this.url = url;
		this.requestCode=requestCode;
		this.context=context;
	}

    //通过异步任务请求接口
	@Override
	protected String doInBackground(String... params) {
		String result = null;
		Log.i("DjTest", "url="+url);
		try {
			if (param!=null) {
			/*	param.put("sign",initSign(KEY, param));
				System.out.println("ReqParam-------"+param);*/
				result = HttpReq.sendHttpPostString(url, param);
			} /*else {
				result = HttpReq.doPostReq(param, url);
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.i("DjTest", "httpRes="+url);
		if (result != null) {
			//接口请求成功返回的json字符串放入这个接口中，上层的方法调用的时候抛出这个json字符串
			requestCallBack.onSuccess(result, requestCode);
		}  else {
			//没有请求成功抛出异常
			requestCallBack.onFailure(0, "connection failed", requestCode);
		}
	}
	
	
	//获取当前时间timestamp
	 Calendar calendar = Calendar.getInstance();
	 SimpleDateFormat ftime = new SimpleDateFormat("yyyyMMddHHmmss");

	public  final String getNetMessageExt() {
		return ftime.format(calendar.getTime()).toString();
	}
	
	/*接口需要使用MD5进行签名验证，具体规则如下：
	1.需要将所有的请求参数按照name（参数名）+“=” + value(参数值) + “&”拼接成字符串。
	2.将拼好的字符串合并成字符串数组。
	3.将数组按字母正顺排序（a-z），忽略大小写。
	4.将数组遍历，取出值拼接成字符串，在末尾拼接key=*******（密钥）。
	5.将字符串使用MD5信息摘要算法加密，字符编码为utf-8。
	6.将密文全部转为大写。*/
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */

	
	private String initSign(String key,Map<String,Object> params){
		//需要将所有的请求参数按照name（参数名）+“=” + value(参数值) + “&”拼接成字符串。并生成数组
	 List<String>list = new ArrayList<String>();
	 for (Map.Entry<String, Object> entry : params.entrySet()) {
		    if(entry.getValue()!= null &&!entry.getValue().toString().isEmpty()){
		    StringBuffer sb =new StringBuffer();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
			list.add(sb.toString());
	     }
		}
	    // 根据a-z进行排序
	    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
	   //遍历数组取出值拼接成字符串，在末尾拼接key=*******（密钥）。
	   StringBuffer sbListStr =new StringBuffer();
	   for(String listStr:list){
		   sbListStr.append(listStr);
	   }
	    sbListStr.append("key"+"="+key);
	   //将字符串使用MD5信息摘要算法加密，字符编码为utf-8,并全部转换成大写。   
	    return MD51.MD5Encode(sbListStr.toString(),"utf-8").toUpperCase();
	}
	

	
}
