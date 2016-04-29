package com.funguide.cc.movieticket.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.funguide.cc.movieticket.BaseActivity;
import com.funguide.cc.movieticket.R;
import com.funguide.cc.movieticket.adapter.ViewHolder;
import com.funguide.cc.movieticket.adapter.listview.CommonAdapter;
import com.funguide.cc.movieticket.model.Cinema;
import com.funguide.cc.movieticket.model.CinemaSort;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 全部影院列表页面
 **/
public class AllCinemaActivity extends BaseActivity  {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.title_center_txt)
    TextView titleCenterTxt;
    @Bind(R.id.title_right_img)
    ImageView titleRightImg;

    @Bind(R.id.cinema_sort_lly)
    LinearLayout cinemaSortLly;
    @Bind(R.id.cinema_sort1_txt)
    TextView cinemaSort1Txt;
    @Bind(R.id.cinema_sort1_rly)
    RelativeLayout cinemaSort1Rly;
    @Bind(R.id.cinema_sort2_txt)
    TextView cinemaSort2Txt;
    @Bind(R.id.cinema_sort2_rly)
    RelativeLayout cinemaSort2Rly;
    @Bind(R.id.cinema_sort3_txt)
    TextView cinemaSort3Txt;
    @Bind(R.id.cinema_sort3_rly)
    RelativeLayout cinemaSort3Rly;
    @Bind(R.id.cinema_list)
    ListView cinemaList;

    List<Cinema> cinemas=new ArrayList<>();

    private PopupWindow popupWindow1;
    boolean popu2Isshow =false;
    private PopupWindow popupWindow2;
    boolean popu1Isshow=false;
    private CommonAdapter<String> rightAdapter;
    List<CinemaSort> cinemaSorts;
    List<String> addres = new ArrayList<>();
    private List<String> sort = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cinema);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initView() {
        titleCenterTxt.setText("全部影院");
        titleRightImg.setVisibility(View.VISIBLE);
//       titleRightImg.setImageResource(R.mipmap.);

        cinemaList.setAdapter(new CommonAdapter<Cinema>(this,R.layout.layout_recommen_cinema_item,cinemas) {
            @Override
            public void convert(ViewHolder holder, int postion, Cinema cinema) {
                holder.setText(R.id.cinema_name_txt,cinema.getCinemaName());
                holder.setText(R.id.cinema_addre_txt,cinema.getAddre());
                holder.setText(R.id.cinema_lowestprice_txt,cinema.getLowestPrice()+"元起");
                holder.setText(R.id.cinema_distance_txt,"2.3km");
            }
        });

    }
    public void initData() {
        for (int i = 0; i <20; i++) {
            Cinema ciname=new Cinema();
            ciname.setCinemaName("嘉年华国际影城（活力方店）");
            ciname.setAddre("北京市朝阳区姚家元路甲一号");
            ciname.setLowestPrice(48);
            cinemas.add(ciname);
        }

        cinemaSorts = new ArrayList<>();
        addres.add(0, "全部");
        for (int i = 0; i < 10; i++) {
            CinemaSort sort = new CinemaSort();
            sort.setArea("雁塔区" + i);
            sort.setCount("12" + i);
            ArrayList a = new ArrayList();
            for (int j = 0; j < 4; j++) {
                a.add("锦业路" + j);
            }
            sort.setAddre(a);
            cinemaSorts.add(sort);
        }
        sort.add("默认排序");
        sort.add("离我最近");
        sort.add("价格最低");
    }


    @OnClick({R.id.title_back_img, R.id.title_right_img,R.id.cinema_sort1_rly,R.id.cinema_sort2_rly,R.id.cinema_sort3_rly})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.title_right_img:   //  影院搜索
                startActivity(FuzzySearchActivity.class);
                break;
            case R.id.cinema_sort1_rly:
                if (popupWindow1 == null || !popupWindow1.isShowing()){
                    showPopupwindow1();
                    setTextDrawable(cinemaSort1Txt, R.mipmap.icon_merchant_arrow_up);
                }else {
                    popupWindow1.dismiss();
                }
                break;
            case R.id.cinema_sort2_rly:
                if (popupWindow2 == null || !popupWindow2.isShowing()){
                    showPopupwindow2();
                    setTextDrawable(cinemaSort2Txt, R.mipmap.icon_merchant_arrow_up);
                }else {
                    popupWindow2.dismiss();
                }
                break;
            case R.id.cinema_sort3_rly:

                break;
        }
    }
    int currentPostion;

    private void showPopupwindow1() {
        View popuView1 = LayoutInflater.from(this).inflate(R.layout.layout_cinema_sort_pop, null);
        ListView listViewLeft = (ListView) popuView1.findViewById(R.id.cinema_sort_left_list);
        final ListView listViewRight = (ListView) popuView1.findViewById(R.id.cinema_sort_right_list);
        listViewLeft.setSelection(0);
        listViewLeft.setAdapter(new CommonAdapter<CinemaSort>(this, R.layout.layout_cinema_sort_item, cinemaSorts) {
            @Override
            public void convert(ViewHolder holder, int postion, CinemaSort cinemaSort) {
                holder.setText(R.id.cinema_sort_item_area_txt, cinemaSort.getArea());
                holder.setText(R.id.cinema_sort_item_num_txt, cinemaSort.getCount());
            }
        });
        rightAdapter = new CommonAdapter<String>(this, R.layout.layout_cinema_sort_right_item, addres) {
            @Override
            public void convert(ViewHolder holder, int postion, String s) {
                holder.setText(R.id.cinema_sort_item_addre_txt, addres.get(postion));
                if (postion == currentPostion) {
                    holder.setVisible(R.id.cinema_sort_item_view, true);
                } else {
                    holder.setVisible(R.id.cinema_sort_item_view, false);
                }
            }
        };
        listViewRight.setAdapter(rightAdapter);
        listViewRight.setSelection(0);
        listViewRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentPostion = position;
                listViewRight.setSelection(currentPostion);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        listViewRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cinemaSort1Txt.setText(addres.get(position));
                popupWindow1.dismiss();
            }
        });
        listViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (addres != null) {
                    addres.clear();
                    addres.add(0, "全部");
                    for (String addre : cinemaSorts.get(position).getAddre()) {
                        addres.add(addre);
                    }
                    Log.d("addres", addres.toString());
                    rightAdapter.notifyDataSetInvalidated();
                }
            }
        });

        showPopupwindow(popuView1);
    }

    private void showPopupwindow2() {
        View popuView2 = LayoutInflater.from(this).inflate(R.layout.layout_cinema_sort_pop2, null);
        ListView rightlist2 = (ListView) popuView2.findViewById(R.id.cinema_sort_right_list2);
        rightlist2.setAdapter(new CommonAdapter<String>(this, R.layout.layout_cinema_sort_right_item2, sort) {
            @Override
            public void convert(ViewHolder holder, int postion, String s) {
                holder.setText(R.id.cinema_sort_item_addre_txt, sort.get(postion));
            }
        });
        showPopupwindow22(popuView2);
    }

    private void showPopupwindow22(View view) {
        popu2Isshow = true;
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        if (popupWindow2==null)
        {
            popupWindow2 = new PopupWindow(view, display.getWidth(),display.getHeight(), true);
        }
//        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(true);
//        popupWindow2.setAnimationStyle(android.R.style.Animation_Dialog);
//        popupWindow2.setTouchable(true);
//        popupWindow2.setFocusable(false);
//        popupWindow2.setOutsideTouchable(true);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow2.dismiss();
                popupWindow2=null;
                return false;
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("onDismiss","popupWindow1");
                popu2Isshow = false;
                popupWindow2.dismiss();
                popupWindow2=null;
                setTextDrawable(cinemaSort2Txt, R.mipmap.icon_merchant_arrow_down);
            }
        });
        popupWindow2.showAsDropDown(cinemaSortLly, 0, 0);


    }

    /**
     * 显示popuwindow
     *
     * @param view
     */
    private void showPopupwindow(View view) {
        popu1Isshow = true;
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        if (popupWindow1==null){
            popupWindow1 = new PopupWindow(view, display.getWidth(),display.getHeight(), true);
        }
//        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow1.setOutsideTouchable(true);
//        popupWindow1.setAnimationStyle(android.R.style.Animation_Dialog);
//        popupWindow1.setTouchable(true);
//        popupWindow1.setFocusable(false);
//        popupWindow1.setOutsideTouchable(true);
//        view.setFocusableInTouchMode(true);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow1.dismiss();
                popupWindow1=null;
                return false;
            }
        });
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("onDismiss","popupWindow1");
                popu1Isshow = false;
                popupWindow1.dismiss();
                popupWindow1=null;
                setTextDrawable(cinemaSort1Txt, R.mipmap.icon_merchant_arrow_down);
            }
        });
        popupWindow1.showAsDropDown(cinemaSortLly, 0, 0);
    };



}
