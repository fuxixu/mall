package com.example.mall.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.R;
import com.example.mall.adapter.CartAdapter;
import com.example.mall.model.bean.ProductData;
import com.example.mall.util.MessageWrap;
import com.example.mall.util.Size;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends DialogFragment implements View.OnClickListener {
    String tag = "diag测试按键";
    private View mRootView;
    private List<ProductData> ProductData = new ArrayList<ProductData>();

    private TextView cart_all_number;
    private TextView cart_all_price;


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
                break;


        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.cart_dialog_fragment, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);


        return mRootView;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button cart_pay = view.findViewById(R.id.cart_pay);
        cart_all_number = view.findViewById(R.id.cart_all_number);
        cart_all_price = view.findViewById(R.id.cart_all_price);
        cart_pay.setOnClickListener(this);
        initView();

        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    //模拟器测试时键盘中的的Enter键，模拟ok键（推荐TV开发中使用蓝叠模拟器）
                    case KeyEvent.KEYCODE_ENTER:
                        break;
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                        Log.d(tag, "你按下中间键");
                        break;

                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        if (event.getAction() == KeyEvent.ACTION_UP){
                            Log.d(tag, "你按下下方键");
                            Log.d(tag,"down--->");
                        }

                        break;

                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        Log.d(tag, "你按下左键");
                        if (event.getAction() == KeyEvent.ACTION_UP){
                            Log.d(tag,"down--->");
                        }
                        break;

                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        if (event.getAction() == KeyEvent.ACTION_UP){
                            Log.d(tag,"down--->");
                        }
                        Log.d(tag, "你按下右键");
                        break;

                    case KeyEvent.KEYCODE_DPAD_UP:
                        Log.d(tag, "你按下上方键");
                        break;
                }
                return false;
            }
        });
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
        initProduct(1);
        RecyclerView cart_ryv = mRootView.findViewById(R.id.cart_ryv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        cart_ryv.setLayoutManager(linearLayoutManager);
        CartAdapter cartAdapter = new CartAdapter(ProductData);
        cart_ryv.setAdapter(cartAdapter);
    }


    private void  initProduct(int id) {
        int number = 0;
        float price  = 0;
        for (int i = 0; i < 10; i++) {

            String j = "农夫山泉"+id;
            int k = R.drawable.display_water;
            String l = "550ml*1瓶";
            float m  = (float) 2.50;
            ProductData pruduct = new ProductData(j,k,l,m,id+i);
            number = number + pruduct.getNumber();
            price =  pruduct.getPrice()*pruduct.getNumber();
            ProductData.add(pruduct);


        }
        cart_all_number.setText(String.valueOf(number));
        cart_all_price.setText("¥"+String.valueOf(price));


    }



}
