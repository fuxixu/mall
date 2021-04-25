package com.example.mall;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AppComponentFactory;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.mall.adapter.Fruit;
import com.example.mall.adapter.LinerRecycleAdapter;
import com.example.mall.ui.AccountDialogFragment;
import com.example.mall.ui.BaseActivity;
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
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    LinerRecycleAdapter adapter;

    /**
     * 当前选中的级别
     */
    private int currentLevel;

    public static final int LEVEL_HOT = 0;//热门商品
    public static final int LEVEL_ALL = 1;//全部商品
    public static final int LEVEL_DRINKS = 2;//饮料
    public static final int LEVEL_NOODLE = 3;//方便面
    public static final int LEVEL_SMOKE = 4;//香烟
    public static final int LEVEL_SNACKS = 5;//零食
    public static final int LEVEL_TAPAS = 6;//小吃

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
        if (fruitList != null) {
            fruitList.clear();
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
        adapter = new LinerRecycleAdapter(fruitList);

        recyclerView.setAdapter(adapter);


    }

    private void initData() {
        setTabItem("热门商品");
        setTabItem("全部商品");
        setTabItem("饮料");
        setTabItem("方便面");
        setTabItem("香烟");
        setTabItem("零食");
        setTabItem("小吃");
    }
    private void setTabItem(String name) {
        QTabView qTabView = new QTabView(getBaseContext()).setTitle(
                new QTabView.TabTitle.Builder().setContent(name).build());
        qTabView.setFocusable(true);
        tabLayout.addTab(qTabView);
    }


    private void  initFruits(int id) {
        for (int i = 0; i < 15; i++) {
            Fruit apple = new Fruit(getRandomLengthName("薯条"+id), R.drawable.display_chips);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("方便面"+id), R.drawable.display_noodle);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("农夫山泉"+id), R.drawable.display_water);
            fruitList.add(orange);
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
            case "tv_title_price":

                break;
            case  "btn_title_account":
                Log.d("nimei","点到了");
                final  AccountDialogFragment accountDialogFragment = new AccountDialogFragment();
                accountDialogFragment.show(getSupportFragmentManager(), "");
                break;
            case "btn_title_cart" :
                final CartDialogFragment niceDialogFragment = new CartDialogFragment();
                niceDialogFragment.show(getSupportFragmentManager(), "");
                break;
            case "btn_title_order":
                final OrderHistoryDialogFragment orderHistoryDialogFragment = new OrderHistoryDialogFragment();
                orderHistoryDialogFragment.show(getSupportFragmentManager(), "");
                Log.d("nimei","点到了");
                break;
        }
    }

}