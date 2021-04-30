package com.example.mall.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mall.R;
import com.example.mall.adapter.CartAdapter;
import com.example.mall.model.bean.ProductInfo;
import com.example.mall.model.db.DbCart;
import com.example.mall.ui.customview.LinearViewItem;
import com.example.mall.ui.customview.ListerRecycler;
import com.example.mall.util.EventUtils;
import com.example.mall.util.MessageWrap;
import com.example.mall.util.Size;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;



public class CartFragment extends DialogFragment implements View.OnClickListener {

    String tag = "aa";
    private View mRootView;
    private List<ProductInfo> productList = new ArrayList<ProductInfo>();
    private TextView cart_all_number;
    private TextView cart_all_price;
    private ListerRecycler cart_ryv;
    private CartAdapter cartAdapter;
    private LinearViewItem viewById;

    //判断view是否获取到焦点
    private Boolean focusListener;
    /**
     * Create by hsw
     * on 2021/4/26
     * eventbus发送数据
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cart_pay:
                MessageWrap messageWrap = new MessageWrap(v.getId());
                EventBus.getDefault().post(messageWrap);
                EventUtils.getInstance().postAllPrice();
                break;
            case R.id.cart_clear:
                DbCart.getInstance().deleteAll();
                notifiyAdapter();
                break;
        }
    }

    /**
     * Create by hsw
     * on 2021/4/26
     * 遥控器按键监听
     */
    private void keyListener(){
        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    //模拟器测试时键盘中的的Enter键，模拟ok键（推荐TV开发中使用蓝叠模拟器）
                    //case KeyEvent.KEYCODE_ENTER:
                    // case KeyEvent.KEYCODE_DPAD_CENTER:
                    // case KeyEvent.KEYCODE_DPAD_DOWN:
                    // case KeyEvent.KEYCODE_DPAD_UP:  //下方键
                    case KeyEvent.KEYCODE_DPAD_LEFT:  //左键
                        //条件判断  松开按键   返回-1；
                        if (event.getAction() == KeyEvent.ACTION_UP && cart_ryv.getPosition()!=-1 ){
                            subOne();
                        }
                        break;

                    case KeyEvent.KEYCODE_DPAD_RIGHT:  //右键
                        //条件判断  松开按键   recyclerview失去焦点返回-1；
                        if (event.getAction() == KeyEvent.ACTION_UP&& cart_ryv.getPosition()!=-1 ){
                            addOne();
                        }
                        break;
                }
                return false;
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.cart_fragment, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题栏
        return mRootView;
    }

    LinearLayoutManager linearLayoutManager;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cart_all_number = view.findViewById(R.id.cart_all_number);
        cart_all_price = view.findViewById(R.id.cart_all_price);
        Button cart_pay = view.findViewById(R.id.cart_pay);
        Button cart_clear = view.findViewById(R.id.cart_clear);
        cart_pay.setOnClickListener(this);
        cart_clear.setOnClickListener(this);

        initProduct();

        initView();//
        keyListener();
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        windowParams.y = 100;
        window.setAttributes(windowParams);
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * Size.getWith()), (int) (dm.heightPixels * Size.getHight()));
        }
    }
    /**
     * Create by hsw
     * on 2021/4/26
     * 设置recyclerview适配器
     */

    private void  initView(){
        cart_ryv = mRootView.findViewById(R.id.cart_ryv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        cart_ryv.setLayoutManager(linearLayoutManager);
        cartAdapter = new CartAdapter(productList);
        cart_ryv.setAdapter(cartAdapter);
    }


    /**
     * Create by hsw
     * on 2021/4/26
     * 适配器数据
     */
    private void  initProduct() {
        productList.clear();
        productList = DbCart.getInstance().searchAll();//查询所有数据
        cart_all_number.setText(String.valueOf(DbCart.getInstance().getAllNumber()));
        cart_all_price.setText("¥"+String.valueOf(DbCart.getInstance().getAllPrice()));

        EventUtils.getInstance().postAllPrice();
    }



    private void subOne(){
        int a = DbCart.getInstance().subOne(productList.get(cart_ryv.getPosition()));//减少数据库的数量
            notifiyAdapter();
    }

    private void addOne(){
        DbCart.getInstance().addOne(productList.get(cart_ryv.getPosition()));//增加数据库的数量
            notifiyAdapter();
    }

    /**
     * 什么时候可以调用这个方法   所有的数量有变化的时候都可以调用
     * Create by hsw
     * on 2021/4/26
     * 数据库数量改变
     * 刷新适配器
     * 包含初始化数据   刷新适配器   发送 event allprice
     *
     */
    private  int mCurrentFocusPosition;

    private void notifiyAdapter(){ //

           initProduct();
           mCurrentFocusPosition = cart_ryv.getPosition();
        //cart_ryv.setItemAnimator(null);//移除动画
        cart_ryv.post(new Runnable() {
            @Override
            public void run() {
                if (DbCart.getInstance().searchAll().size()!=0){
                    cart_ryv.getLayoutManager().findViewByPosition(mCurrentFocusPosition).requestFocus();
                }else{
                    //ToastUtils.showToast(new MainActivity(),"购物车已经没有商品");
                }

            }
        });

        cartAdapter = new CartAdapter(productList);
        cart_ryv.setAdapter(cartAdapter);

       }
}
