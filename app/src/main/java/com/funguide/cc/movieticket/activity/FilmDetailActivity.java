package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.utils.KeyBoardUtils;
import com.funguide.cc.movieticket.views.ExpandableTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 影片详情
 */
public class FilmDetailActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.film_poster_pic_img)
    ImageView filmPosterPicImg;
    @Bind(R.id.film_poster_name_txt)
    TextView filmPosterNameTxt;
    @Bind(R.id.film_poster_score_txt)
    TextView filmPosterScoreTxt;
    @Bind(R.id.film_poster_introduce_txt)
    TextView filmPosterIntroduceTxt;
    @Bind(R.id.film_poster_type_txt)
    TextView filmPosterTypeTxt;
    @Bind(R.id.film_poster_right_img)
    ImageView filmPosterRightImg;
    @Bind(R.id.film_poster_runtime_txt)
    TextView filmPosterRuntimeTxt;
    @Bind(R.id.film_poster_releasetime_txt)
    TextView filmPosterReleasetimeTxt;
    @Bind(R.id.film_poster_lly)
    LinearLayout filmPosterLly;
    @Bind(R.id.film_chooseSeat_btn)
    Button filmChooseSeatBtn;
    @Bind(R.id.expandable_text)
    TextView expandableText;
    @Bind(R.id.expand_collapse)
    ImageButton expandCollapse;
    @Bind(R.id.expand_text_view)
    ExpandableTextView expandTextView;
    @Bind(R.id.film_comment_list)
    ListView filmCommentList;
    @Bind(R.id.film_comment_txt)
    TextView filmCommentTxt;
    @Bind(R.id.film_comment_edit)
    EditText filmCommentEdit;
    @Bind(R.id.film_comment_btn)
    Button filmCommentBtn;
    @Bind(R.id.film_comment_rly)
    RelativeLayout filmCommentRly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        titleCenterTxt.setText("电影名字");
        filmPosterRightImg.setVisibility(View.GONE);
        expandTextView.setText("导演：周星驰\n主演：邓超/张雨绮/罗志祥/林允文章\n" +
                "周星驰全力筹备的《美人鱼》是一部融合浪漫与惊险的爱情大片，该片于2014年开拍，预计2016年春节前后上映。" +
                "故事讲述的是在海上航行的一艘船，突然不幸遇劫，美人鱼挺身而出，" +
                "船上的一名英俊青年对她一见钟情，并由此谱写出一段惊心动魄的人鱼恋曲。" +
                "该片将有不少特效场景，有可能拍成3D影片.电影美人鱼剧情讲述的是「轩」的地产计划涉及填海工程，威胁靠海以为生的居民。" +
                "背负家族秘密的「珊」被派遣前往阻止。二人在交手过程中互生情愫，虽然「轩」最终... ");

    }


    @OnClick({R.id.title_back_img, R.id.film_chooseSeat_btn, R.id.film_comment_txt,R.id.film_comment_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.film_chooseSeat_btn:
                startActivity(SelcectSeatActivity.class);
                break;
            case R.id.film_comment_txt:
                filmCommentTxt.setVisibility(View.GONE);
                filmCommentRly.setVisibility(View.VISIBLE);
                KeyBoardUtils.openKeybord(filmCommentEdit,FilmDetailActivity.this);
                break;
            case R.id.film_comment_btn:
                filmCommentRly.setVisibility(View.GONE);
                KeyBoardUtils.closeKeybord(filmCommentEdit,FilmDetailActivity.this);
                filmCommentTxt.setVisibility(View.VISIBLE);
                filmCommentEdit.setText("");
                break;
        }
    }

}
