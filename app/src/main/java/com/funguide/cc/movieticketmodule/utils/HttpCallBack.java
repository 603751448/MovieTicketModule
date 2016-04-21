package com.funguide.cc.movieticketmodule.utils;
	/**
 *     ┏┓　　　┏┓
 *   ┏┛┻━━━┛┻┓
 *   ┃　　　　　　　┃ 　
 *   ┃　　　━　　　┃
 *   ┃　┳┛　┗┳　┃
 *   ┃　　　　　　　┃
 *   ┃　　　┻　　　┃
 *   ┃　　　　　　　┃
 *   ┗━┓　　　┏━┛
 *      ┃　　　┃   神兽保佑　　　　　　　　
 *	   ┃　　　┃   代码无BUG！
 *	   ┃　　　┗━━━┓
 *	   ┃　　　　　　　┣┓
 *	   ┃　　　　　　　┏┛
 *	   ┗┓┓┏━┳┓┏┛
 *	     ┃┫┫　 ┃┫┫
 *	     ┗┻┛　 ┗┻┛
 */
// 接口请求返回数据的接口
public  interface HttpCallBack {
	void onSuccess(String msg, int requestCode);
	void onFailure(int errorCode, String msg, int requestCode);

}
