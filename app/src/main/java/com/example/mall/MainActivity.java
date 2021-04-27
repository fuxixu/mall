package com.example.mall;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.mall.adapter.MyTabAdapter;
import com.example.mall.model.bean.MActivityData;
import com.example.mall.adapter.MActivityAdapter;
import com.example.mall.ui.AccountDialogFragment;
import com.example.mall.ui.CartDialogFragment;
import com.example.mall.ui.OrderHistoryDialogFragment;
import com.example.mall.ui.customview.GridSpacingItemDecoration;
import com.example.mall.util.MessageWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    VerticalTabLayout tabLayout;
    private List<MActivityData> MActivityDataList = new ArrayList<MActivityData>();
    MActivityAdapter adapter;

    String tag = "测试按键";

    /**
     * 当前选中的级别
     */
    private int currentLevel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }

        initFruits(currentLevel);
        initView();
        initTablayout();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        setRecyclerView();
    }

    private void initTablayout() {

        tabLayout = findViewById(R.id.tab_layout);
        /**
         * Create by hsw
         * on 2021/4/21.
         * tabLayout子项监听
         */
        tabLayout.setFocusable(true);
//        tabLayout.setTabAdapter(new MyTabAdapter());

        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                Log.d("nimei","点击了"+position+"");
                selectTab(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

            setTabItem(R.string.tab_hot_product);
            setTabItem(R.string.tab_all_product);
            setTabItem(R.string.tab_drinks);
            setTabItem(R.string.tab_noodle);
            setTabItem(R.string.tab_smoke);
            setTabItem(R.string.tab_snacks);
            setTabItem(R.string.tab_food);
    }
    /**
     * Create by hsw
     * on 2021/4/21.
     * 设置tabLayout
     */

    private void setTabItem(int name) {

        QTabView qTabView = new QTabView(getBaseContext())
                .setTitle(new QTabView.TabTitle.Builder()
                        .setContent(this.getString(name))
                        .setTextColor(Color.WHITE, 0xBBFFFFFF)
                        .setTextSize(20)
                        .build());



        qTabView.setFocusable(true);
        tabLayout.addTab(qTabView);
    }

    /**
     * Create by hsw
     * on 2021/4/21.
     * tablayout子项选择判断
     */
    private void selectTab(int  id) {
        currentLevel = id;
        if (MActivityDataList != null) {
            MActivityDataList.clear();
        }
        initFruits(currentLevel);
        adapter.notifyDataSetChanged();


    }


    /**
     * Create by hsw
     * on 2021/4/21.
     * 设置recyclerview适配器
     */
    private void setRecyclerView() {
        recyclerView.setFocusable(true);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MActivityAdapter(MActivityDataList);
        recyclerView.setAdapter(adapter);

    }





    private void  initFruits(int id) {
        for (int i = 0; i < 15; i++) {
            MActivityData apple = new MActivityData(getRandomLengthName("薯条"+id), R.drawable.display_chips);
            MActivityDataList.add(apple);
            MActivityData banana = new MActivityData(getRandomLengthName("方便面"+id), R.drawable.display_noodle);
            MActivityDataList.add(banana);
            MActivityData orange = new MActivityData(getRandomLengthName("农夫山泉"+id), R.drawable.display_water);
            MActivityDataList.add(orange);
        }
    }
    private String getRandomLengthName(String name) {
        return name;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Create by hsw
     * on 2021/4/21.
     * 监听标题栏弹出dialogfragment
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        switch (message.getMessage()) {
            case  R.id.tv_title_price:

                break;
            case R.id.btn_title_account:
                Log.d("nimei","点到了");


                final  AccountDialogFragment accountDialogFragment = new AccountDialogFragment();
                accountDialogFragment.show(getSupportFragmentManager(), "");
                break;
            case R.id.btn_title_cart :
                final CartDialogFragment niceDialogFragment = new CartDialogFragment();
                niceDialogFragment.show(getSupportFragmentManager(), "");
                break;
            case R.id.btn_title_order:
                final OrderHistoryDialogFragment orderHistoryDialogFragment = new OrderHistoryDialogFragment();
                orderHistoryDialogFragment.show(getSupportFragmentManager(), "");
                Log.d("nimei","点到了");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //模拟器测试时键盘中的的Enter键，模拟ok键（推荐TV开发中使用蓝叠模拟器）
            case KeyEvent.KEYCODE_ENTER:
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                Log.d(tag,"你按下中间键");
                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                Log.d(tag,"你按下下方键");
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                Log.d(tag,"你按下左键");
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:

                Log.d(tag,"你按下右键");
                break;

            case KeyEvent.KEYCODE_DPAD_UP:
                Log.d(tag,"你按下上方键");
                break;
        }
        return super.onKeyDown(keyCode, event);

    }
}