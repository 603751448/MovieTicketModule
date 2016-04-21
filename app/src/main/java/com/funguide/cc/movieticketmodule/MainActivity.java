package com.funguide.cc.movieticketmodule;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**入口**/
public class MainActivity extends BaseActivity {
    private Button btn_entrance;
    @Override
    protected void initView() {
        setTitle("影票入口");
        //测试提交代码(222c)
        findViewById(R.id.txtTitleRight).setVisibility(View.GONE);
        findViewById(R.id.back).setVisibility(View.GONE);
        btn_entrance = (Button) this.findViewById(R.id.btn_entrance);
        btn_entrance.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_entrance:
                entrance();          //影票入口
                break;
        }

    }
    private void entrance(){
        Intent intent = new Intent(MainActivity.this,TicketMainActivity.class);
        MainActivity.this.startActivity(intent);
        Toast.makeText(MainActivity.this, "影票入口", Toast.LENGTH_SHORT).show();
    }


}
