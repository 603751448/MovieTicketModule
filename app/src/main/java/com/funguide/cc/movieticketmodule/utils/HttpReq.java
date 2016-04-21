package com.funguide.cc.movieticketmodule.utils;

import android.content.Context;

import com.funguide.cc.movieticketmodule.javabean.Parameter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpReq {
	private static final String METHOD_POST = "POST";
	private static final int HTTP_OK = 200;
	private final static String CHARTSET = "UTF-8"; // 字符编码
	private final static int BUFFER = 1024 * 8;// 缓冲区
	//URL请求get编码格式为GB2312
	public static byte[] sendHttpGet(String url, Map<String, String> params,Context context)
			throws Exception {
		StringBuffer sb = new StringBuffer(url);
		if (params != null && !params.isEmpty()) {
			sb.append("?");
			// Map.Entry 锟斤拷锟斤拷锟斤拷锟杰帮拷map锟侥斤拷锟斤拷值锟斤拷取锟斤拷锟斤拷

			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		URL path = new URL(sb.toString());

		HttpURLConnection conn = (HttpURLConnection) path.openConnection();
		//设置连接超时
		conn.setConnectTimeout(30000);
		//设置http头部参数
		/*conn.addRequestProperty("version",VersionNumber.getVersion(context));
		conn.addRequestProperty("platform", "2");*/
		conn.setRequestMethod("GET");
		conn.setReadTimeout(3000);
		conn.setRequestProperty("Charset", "GB2312");

		byte[] data = null;
		if (conn.getResponseCode() == 200) {
			InputStream is = conn.getInputStream();
			data = read(is);
		}
		return data;
	}
	//URL请求get编码格式为utf-8
	public static byte[] sendHttpGet1(String url, Map<String, String> params,Context context)
			throws Exception {
		StringBuffer sb = new StringBuffer(url);
		if (params != null && !params.isEmpty()) {
			sb.append("?");
			// Map.Entry 锟斤拷锟斤拷锟斤拷锟杰帮拷map锟侥斤拷锟斤拷值锟斤拷取锟斤拷锟斤拷

			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		URL path = new URL(sb.toString());


		System.out.println(path+"00000000000000000000000000000000000000");

		HttpURLConnection conn = (HttpURLConnection) path.openConnection();
		//设置连接超时
		conn.setConnectTimeout(30000);

		//设置http头部参数
		/*conn.addRequestProperty("version",VersionNumber.getVersion(context));
		conn.addRequestProperty("platform", "2");
		*/
		conn.setRequestMethod("GET");
		conn.setReadTimeout(3000);
		conn.setRequestProperty("Charset", "utf-8");
		byte[] data = null;
		if (conn.getResponseCode() == 200) {
			InputStream is = conn.getInputStream();
			data = read(is);
		}
		return data;
	}


	//将流转换成字节数组
	private static byte[] read(InputStream is) throws Exception {
		ByteArrayOutputStream bys = new ByteArrayOutputStream();
		int len = 0;
		byte[] bs = new byte[1024];
		while ((len = is.read(bs)) != -1) {
			bys.write(bs, 0, len);
		}
		bys.close();
		is.close();
		return bys.toByteArray();

	}


	// HttpClient请求Post请求
	//URL请求post
	public static String sendHttpPostString(String url, String params)
			throws Exception {
		URL path = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) path.openConnection();
		conn.setDoInput(true); // 向连接中写入数据
		conn.setDoOutput(true); // 从连接中读取数据
		conn.setUseCaches(false); // 禁止缓存
		conn.setInstanceFollowRedirects(true);//自动执行HTTP重定向
		//设置连接超时
		conn.setConnectTimeout(30000);
		//设置http头部参数
		/*conn.addRequestProperty("version",VersionNumber.getVersion(context));
		conn.addRequestProperty("platform", "2");*/
		conn.setRequestMethod("POST");
		conn.setReadTimeout(30000);
		//conn.setRequestProperty("Charset", "utf-8");// 设置内容类型
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置内容类型
		DataOutputStream out = new DataOutputStream(conn.getOutputStream()); // 获取输出流
		out.writeBytes(params);//将要传递的数据写入数据输出流
		out.flush();    //输出缓存
		out.close();    //关闭数据输出流
		String jsonStr=null;
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("返回code------------" + conn.getResponseCode());
			InputStream is = conn.getInputStream();
			jsonStr = new String(read(is), "utf-8");
		}
		conn.disconnect();//断开连接
		return jsonStr;
	}

/*	//HttpPost请求
	@SuppressWarnings("deprecation")
	public static String doPost(Map<String, Object> param, String url) {
		String strResult = null;
		HttpPost httpRequest = new HttpPost(url);
		LogUtil.i(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : param.keySet()) {
			String value = param.get(key).toString();
			LogUtil.i(key + "---->" + value);
			params.add(new BasicNameValuePair(key, value));
		}
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}*/





/*
	// HttpClient请求没参数名直接上传byte数组
	public static byte[] sendHttpPostNoParams(String path, byte[] params,
											  Context context) throws Exception {

		ByteArrayEntity byteDate=new ByteArrayEntity(params);

		DefaultHttpClient client = new DefaultHttpClient();

		//设置请求时的超时时间
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,10000);


		HttpPost post = new HttpPost(path);

		// http头部参数
			*//*post.addHeader("version", VersionNumber.getVersion(context));
			post.addHeader("platform", "2")*//*;
		post.setEntity(byteDate);
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

			System.out.println("返回code------------"+response.getStatusLine().getStatusCode());

			InputStream is = response.getEntity().getContent();
			return read(is);
		}
		// 关闭请求
		client.getConnectionManager().shutdown();
		post.abort();
		response = null;
		return null;

	}*/




	/**
	 * 模拟可发送文件的POST方式请求
	 *
	 * @param httpUrl
	 *            请求URL地址
	 * @param queryString
	 *            请求参数
	 * @param files
	 *            传送文件集合
	 * @return
	 * @throws Exception
	 */
	public static String postWithFile(String httpUrl, String queryString,
									  List<Parameter> files) throws Exception {
		// 数据分隔线
		final String BOUNDARY = "---------------------------7da2137580612";
		// 回车
		final String RETURN = "\r\n";
		// 前缀
		final String PREFIX = "--";

		HttpURLConnection conn = null;
		InputStream inStream = null;
		String response = null;
		try {
			URL url = new URL(httpUrl);
			// 根据URL打开远程连接
			conn = (HttpURLConnection) url.openConnection();
			// 设置参数
			conn.setRequestMethod(METHOD_POST);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", CHARTSET);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + BOUNDARY);

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			// 传送文本类型参数
			if (queryString != null && !queryString.equals("")) {
				// 分割文本类型参数字符串
				String[] params = queryString.split("&");
				for (String str : params) {
					if (str != null && !str.equals("")) {
						if (str.indexOf("=") > -1) {
							String[] param = str.split("=");
							// 获取参数值
							String value = (param.length == 2 ? StringUtil
									.decode(param[1]) : "");
							// 添加数据分隔线
							out.writeBytes(PREFIX + BOUNDARY + RETURN);
							// 添加参数名
							out.writeBytes("Content-Disposition: form-data; name=\""
									+ param[0] + "\"" + RETURN);
							// 添加\r\n
							out.writeBytes(RETURN);
							// 添加参数的值
							out.write(value.getBytes(CHARTSET));
							// 添加\r\n
							out.writeBytes(RETURN);
						}
					}
				}
			}
			// 遍历文件列表
			for (Parameter file : files) {
				// 获取文件名
				String fileName = file.getValue().substring(
						file.getValue().lastIndexOf("/") + 1);
				// 添加数据分隔线
				out.writeBytes(PREFIX + BOUNDARY + RETURN);
				// 添加文件信息
				out.writeBytes("Content-Disposition: form-data; name=\""
						+ file.getName() + "\"; filename=\"" + fileName + "\""
						+ RETURN);
				// 添加\r\n
				out.writeBytes(RETURN);

				// 创建文件输入流
				FileInputStream fis = new FileInputStream(file.getValue());
				// 设置缓冲区
				byte[] buffer = new byte[BUFFER];
				int count = 0;
				while ((count = fis.read(buffer)) != -1) {
					out.write(buffer, 0, count);
				}
				fis.close();
				out.writeBytes(RETURN);
			}
			// 添加数据分隔线
			out.writeBytes(PREFIX + BOUNDARY + PREFIX + RETURN);
			out.flush();
			out.close();

			// 获取返回码
			int responseCode = conn.getResponseCode();
			if (responseCode == HTTP_OK) {
				// 获取输入流
				inStream = conn.getInputStream();
				// 从输入流中获取信息
				response = getResponse(inStream);
			} else {
				// 请求失败
				response = "返回码：" + responseCode;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭连接
			conn.disconnect();
		}
		return response;
	}

	/**
	 * 获取输入流中信息
	 *
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws IOException
	 */
	private static String getResponse(InputStream inStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = -1;
		byte[] buffer = new byte[BUFFER];// 缓冲区
		while ((len = inStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		byte[] data = outputStream.toByteArray();
		return new String(data);
	}




}
