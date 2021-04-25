package com.example.mall;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.mall.model.bean.MainRecyclerViewItemData;
import com.example.mall.adapter.MainActivityRecyclerViewAdpter;
import com.example.mall.ui.AccountDialogFragment;
import com.example.mall.ui.CartDialogFragment;
import com.example.mall.ui.OrderHistoryDialogFragment;
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
    private List<MainRecyclerViewItemData> mainRecyclerViewItemDataList = new ArrayList<MainRecyclerViewItemData>();
    MainActivityRecyclerViewAdpter adapter;

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
        initData();
        setRecyclerView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        tabLayout = findViewById(R.id.tab_layout);


        /**
         * Create by hsw
         * on 2021/4/21.
         * tabLayout子项监听
         */
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

    }

    /**
     * Create by hsw
     * on 2021/4/21.
     * tablayout子项选择判断
     */
    private void selectTab(int  id) {
        currentLevel = id;
        if (mainRecyclerViewItemDataList != null) {
            mainRecyclerViewItemDataList.clear();
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
        adapter = new MainActivityRecyclerViewAdpter(mainRecyclerViewItemDataList);

        recyclerView.setAdapter(adapter);


    }

    private void initData() {
        setTabItem(R.string.tab_hot_product);
        setTabItem(R.string.tab_all_product);
        setTabItem(R.string.tab_drinks);
        setTabItem(R.string.tab_noodle);
        setTabItem(R.string.tab_smoke);
        setTabItem(R.string.tab_snacks);
        setTabItem(R.string.tab_food);
    }
    private void setTabItem(int name) {
        QTabView qTabView = new QTabView(getBaseContext()).setTitle(
                new QTabView.TabTitle.Builder().setContent(this.getString(name)).build());
        qTabView.setFocusable(true);
        tabLayout.addTab(qTabView);
    }


    private void  initFruits(int id) {
        for (int i = 0; i < 15; i++) {
            MainRecyclerViewItemData apple = new MainRecyclerViewItemData(getRandomLengthName("薯条"+id), R.drawable.display_chips);
            mainRecyclerViewItemDataList.add(apple);
            MainRecyclerViewItemData banana = new MainRecyclerViewItemData(getRandomLengthName("方便面"+id), R.drawable.display_noodle);
            mainRecyclerViewItemDataList.add(banana);
            MainRecyclerViewItemData orange = new MainRecyclerViewItemData(getRandomLengthName("农夫山泉"+id), R.drawable.display_water);
            mainRecyclerViewItemDataList.add(orange);
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

}