package com.funguide.cc.movieticketmodule.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.funguide.cc.movieticketmodule.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 入口
 **/
public class MainActivity extends Activity {

    @Bind(R.id.btn_entrance)
    Button btnEntrance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    private void entrance() {
        Intent intent = new Intent(MainActivity.this, TicketMainActivity.class);
        MainActivity.this.startActivity(intent);
        Toast.makeText(MainActivity.this, "影票入口", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_entrance)
    public void onClick() {
        entrance();
    }
}
