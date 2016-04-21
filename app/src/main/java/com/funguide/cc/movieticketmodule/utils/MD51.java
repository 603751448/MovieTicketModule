package com.funguide.cc.movieticketmodule.utils;

import java.security.MessageDigest;
/**
 * MD5加密
 *
 * @param    //c
 *			 待加密字符串
 * @return 加密后串码
 */
public class MD51 {

	public static String MD5Encode(String ctx, String charactName) {
		if (charactName == null || charactName.equals("")) {
			charactName = "UTF-8";
		}
		String resultString = null;
		try {
			resultString = ctx;
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(charactName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	/**
	 * 加密算法
	 * 
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (byte aB : b) {
			resultSb.append(byteToHexString(aB));
		}
		return resultSb.toString();
	}

	/**
	 * 加密算法
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };
}
