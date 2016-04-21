package com.funguide.cc.movieticketmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.funguide.cc.movieticketmodule.utils.Constans;
import com.funguide.cc.movieticketmodule.utils.SharedPrefUtils;

import java.util.Map;

public abstract class BaseActivity extends Activity {
    /**通过一个hander触发loadingDialog的显示隐藏**/
    public static final int SHOW_MESSAGE = 0x01;
    public static final int SHOW_LOADING = 0x02;
    public static final int HIDE_MESSAGE = 0x03;
    protected Activity self; /**上下文对象**/
//    protected LoadingDialog loadingDialog;/**请求数据的时候弹出的等待loadingDialog**/


    protected Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case SHOW_MESSAGE:      //显示消息
                            String msgtip = "提示消息";
                            if (msg.obj != null) {
                                msgtip = msg.obj.toString();
                            }
                            break;
                        case SHOW_LOADING:     // 显示加载状态
//                    loadingDialog.show();
                            break;
                        case HIDE_MESSAGE:    // 隐藏
//                    loadingDialog.dismiss();
                            break;

                    }
                }
            };

    /**loadingDialog 的状态实现方法**/
    protected void setLoadingDialog(final int status){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(status);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefUtils.getInstants(this);
        initData();
        initView();
    }

    //初始化数据
    public abstract void initData();
    //初始化控件
    public abstract void initView();


    /**不需要添加参数的intent跳转**/
    protected void startActivity(Class<?> activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    /**带bundle的intent跳转**/
    protected void startActivity(Class<?> activity,Bundle bundle){
        Intent intent = new Intent(this,activity);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    /**	带bundle和回调的intent跳转**/
    protected void startActivityForResult(Class<?> activity,Bundle bundle){
        Intent intent = new Intent(this,activity);
        intent.putExtra(Constans.BACK_TARGET,this.getClass());
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,Constans.reqcode_address);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    /** 不带bundle和回调的intent跳转 **/
    protected void startActivityForResult(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivityForResult(intent,Constans.reqcode_address);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    /** 干掉之盏里面所有activity的intent跳转 **/
    protected void startActivityWithFlag(Class<?> activity){
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /** 普通参数类型的intent跳转这里是用了一个map集合 **/
    protected void startActivity(Class<?>activity, Map<String,String> data){
        Intent intent = new Intent(this,activity);
        for (String key : data.keySet()){
            intent.putExtra(key,data.get(key));
        }
        startActivity(intent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    /** 普通参数类型的intent跳转这里是用了一个map集合带回调的 **/
    protected void startActivityForResult(Class<?> activity,Map<String,String > data,int result){
        Intent intent = new Intent(this,activity);
        for (String key : data.keySet()){
            intent.putExtra(key,data.get(key));
        }
        startActivityForResult(intent,result);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);   /**跳转动画**/
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
