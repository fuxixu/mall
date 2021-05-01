package com.example.mall;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.mall.contract.IProduct;
import com.example.mall.adapter.DisplayAdapter;
import com.example.mall.model.bean.ProductInfo;
import com.example.mall.model.db.DbCart;
import com.example.mall.model.db.DbOrder;
import com.example.mall.ui.PayFragment;
import com.example.mall.ui.CartFragment;
import com.example.mall.ui.OrderFragment;
import com.example.mall.ui.customview.GridSpacingItemDecoration;
import com.example.mall.util.EventUtils;
import com.example.mall.util.MessageWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class MainActivity extends AppCompatActivity  implements IProduct {
    String tag = "测试按键";

    private RecyclerView recyclerView;
   private VerticalTabLayout tabLayout;
    private List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
    private DisplayAdapter adapter;
   private  CartFragment cartFragment;
    private PayFragment payFragment;
    private OrderFragment orderFragment;

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
        //初始化数据库
        DbCart.init(this);
        DbOrder.init(this);
        initFruits(currentLevel);
        initTablayout();
        initAdpter();
        EventUtils.getInstance().postAllPrice();//初始title总价格
    }
    /**
     * Create by hsw
     * on 2021/4/21.
     * 设置recyclerview适配器
     */
    private void initAdpter() {
        recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setFocusable(true);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

        int spanCount = 5; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));


            adapter = new DisplayAdapter(productInfoList,this);
            recyclerView.setAdapter(adapter);
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
                        .setTextSize(35)
                        .build());



        qTabView.setFocusable(true);
        tabLayout.addTab(qTabView);
    }

    /**
     * Create by hsw
     * on 2021/4/21.
     * tabLayout切换fragment
     * tablayout子项选择判断
     * 刷新适配器
     */
    private void selectTab(int  id) {
        currentLevel = id;
        if (productInfoList != null) {
            productInfoList.clear();
        }
        initFruits(currentLevel);
        adapter.notifyDataSetChanged();//

    }

    private void  initFruits(int id) {
        for (int i = 0; i < 15; i++) {

            String a = "薯条"+id;
            int b = R.drawable.display_chips;
            String c = "550ml*1瓶";
            float d = (float) 2.50;
            ProductInfo apple = new ProductInfo(a,b,c,d);
            productInfoList.add(apple);

            String e = "方便面"+id;
            int f = R.drawable.display_noodle;
            String g = "550ml*1瓶";
            float h = (float) 2.50;
            ProductInfo banana = new ProductInfo(e,f,g,h);
            productInfoList.add(banana);

            String j = "农夫山泉"+id;
            int k = R.drawable.display_water;
            String l = "550ml*1瓶";
            float m  = (float) 2.50;
            ProductInfo orange = new ProductInfo(j,k,l,m);
            productInfoList.add(orange);

        }
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
     *
     * EventBUs接收信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        switch (message.getMessage()) {
            case  R.id.pay_succeed:
                Log.d("nimei","点到了pay");
                payFragment.dismiss();

                break;

            case R.id.cart_pay: //顺序  注销cart  打开pay   数据保存到order数据库    清除cart数据库
                cartFragment.dismiss();
                payFragment = new PayFragment();
                payFragment.show(getSupportFragmentManager(), "");
                DbOrder.getInstance().save();
                DbCart.getInstance().deleteAll();
                break;
            case R.id.btn_title_account:
                openCart();

                break;
            case R.id.btn_title_cart :
                openCart();
                break;
            case R.id.btn_title_order:
                orderFragment = new OrderFragment();
                orderFragment.show(getSupportFragmentManager(), "");
                Log.d("nimei","点到了");
                break;

        }
    }
    /**
     * Create by fu
     * on 2021/4/29.
     *统一管理cartFragment实例
     *
     */
    private void openCart(){
            cartFragment = new CartFragment();
            cartFragment.show(getSupportFragmentManager(), "");
    }


    /**
     * Create by fu
     * on 2021/4/27.
     *
     * 监听遥控器上下左右按键
     */

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

    @Override
    public void inSert(ProductInfo info) {
        DbCart.getInstance().insert(info);

        EventUtils.getInstance().postAllPrice();
    }
}