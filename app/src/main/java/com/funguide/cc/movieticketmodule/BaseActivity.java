package com.funguide.cc.movieticketmodule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.funguide.cc.movieticketmodule.utils.Constans;
import com.funguide.cc.movieticketmodule.utils.SharedPrefUtils;

import java.util.Map;

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    protected Activity self; /**上下文对象**/
//    protected LoadingDialog loadingDialog;/**请求数据的时候弹出的等待loadingDialog**/
    private View.OnClickListener backListener = new View.OnClickListener() {/**返回按钮的点击事件**/
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.back){
                    finish();
                }
            }
            };
    /**通过一个hander触发loadingDialog的显示隐藏**/
    public static final int SHOW_MESSAGE = 0x01;
    public static final int SHOW_LOADING = 0x02;
    public static final int HIDE_MESSAGE = 0x03;

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

    /**初始化控件和调用一些抽象类**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() == 0){
            return;
        }
        setContentView(getLayoutId());
//        loadingDialog = new LoadingDialog(self);
        View backView = findViewById(R.id.back);
        /**给返回按钮添加点击事件**/
        if(backView != null){
            backView.setOnClickListener(backListener);
        }
        SharedPrefUtils.getInstants(this);
        initView();

    }

    protected abstract void initView();  /** 这个抽象类的功能是为了子类初始化view **/
    protected abstract int getLayoutId();  /** 子类的布局文件 **/
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

    /** 请求的URL以及它的getset方法 **/
    protected String actionPath;
    String getActionPath(){
        return  actionPath;
    }

    void setActionPath(String actionPath){
        this.actionPath = actionPath;
    }
    /** 显示网络请求所获取的json字符串 **/
    protected void handleResp(String msg,Class clazz){};
    /** toast 请求错误时候的信息**/
    protected void handleErrResp(String msg, Class clazz) {
        Toast.makeText(self, getString(R.string.net_disable), Toast.LENGTH_SHORT).show();
    };
    /** 接口请求 **/
    protected void sendRequest(Map map,final Class clazz){
       /* HttpTask activeTask = new HttpTask(new HttpCallBack() {

            @Override
            public void onSuccess(String msg, int requestCode) {
                Log.i("Resp", msg);
                handleResp(msg, clazz);
            }
            @Override
            public void onFailure(int errorCode, String msg, int requestCode) {
                handleErrResp(msg, clazz);
            }
        }, map, Constans.BASE_URL + getActionPath(),self);
        activeTask.execute();*/
    }
    protected void resetDataCollection() {
    }

    /** activity头部的title **/
    protected void setTitle(String title) {
        if (findViewById(R.id.txtTitle) != null) {
            TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
            txtTitle.setText(title);
        }
    }

    /** setActivity头部右边显示的文字 **/
    protected void setRightLable(String name) {
        if (findViewById(R.id.txtTitleRight) != null && name != null) {
            TextView filter_active = (TextView) findViewById(R.id.txtTitleRight);
            filter_active.setVisibility(View.VISIBLE);
            filter_active.setText(name);
        } else if (findViewById(R.id.txtTitleRight) != null) {
            findViewById(R.id.txtTitleRight).setVisibility(View.GONE);
        }
    }

    /** 头部右边如果需要图片用这个方法添加图片 **/
    protected void showRightIcon(){
        showRightIcon(null,R.drawable.more);
    }

    //头部右边如果需要图片用这个方法添加图片
    protected void showRightIcon(int iconResId){
        showRightIcon(null,iconResId);
    }
    //头部右边如果需要图片和文字用这个方法添加图片和文字
    protected void showRightIcon(String name,int iconResId) {
        TextView filter_active = (TextView) findViewById(R.id.txtTitleRight);
        filter_active.setVisibility(View.VISIBLE);
        filter_active.setText(TextUtils.isEmpty(name)?"":name);
        Drawable moreDrawable = getResources().getDrawable(iconResId);
        moreDrawable.setBounds(0,0,moreDrawable.getMinimumWidth(),moreDrawable.getMinimumHeight());
        filter_active.setCompoundDrawables(null,null,moreDrawable,null);
        filter_active.setCompoundDrawablePadding(3);
    }
    //头部左边如果需要更改文字用这个方法
    protected void setLeftLable(String name) {
        if (findViewById(R.id.back) != null && name != null) {
            TextView filter_active = (TextView) findViewById(R.id.back);
            filter_active.setVisibility(View.VISIBLE);
            filter_active.setOnClickListener(backListener);
            filter_active.setText(name);
        } else if (findViewById(R.id.back) != null) {
            findViewById(R.id.back).setVisibility(View.GONE);
        }
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
