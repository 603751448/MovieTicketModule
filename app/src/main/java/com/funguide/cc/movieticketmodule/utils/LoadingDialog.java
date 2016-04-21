package com.funguide.cc.movieticketmodule.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.funguide.cc.movieticketmodule.R;

/**
 * Created by c on 4/19 0019.
 */
public class LoadingDialog extends AlertDialog{

    /**Dialog 显示的文View**/
    private TextView tips_loading_msg;
    private String message = null;

    public LoadingDialog(Context context) {
        super(context);
        message = getContext().getResources().getString(R.string.msg_load_ing);
    }

    public LoadingDialog(Context context, int theme, String message){
        super(context,theme);
        this.message = message;
        this.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_tips_loading);
        tips_loading_msg = (TextView) this.findViewById(R.id.tips_loading_msg);
        tips_loading_msg.setText(this.message);
    }

    /**setDialog显示的文字**/
    public void setText(String message){
        this.message = message;
        if(this.message!=null){
            tips_loading_msg.setVisibility(View.VISIBLE);
            tips_loading_msg.setText(this.message);
        }else {
            tips_loading_msg.setVisibility(View.GONE);
        }
    }

    public void setText(int resId){
        setText(getContext().getResources().getString(resId));
    }

}
