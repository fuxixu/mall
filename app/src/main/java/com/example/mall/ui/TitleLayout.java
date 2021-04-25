package com.example.mall.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mall.R;
import com.example.mall.util.MessageWrap;

import org.greenrobot.eventbus.EventBus;
//implements View.OnClickListener
public class TitleLayout extends LinearLayout  implements View.OnClickListener{
    Context mContext;
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        initView();

    }

    private void initView() {
         mContext = BaseActivity.getContext();
        TextView tv_title_price = (TextView) findViewById(R.id.tv_title_price);
        Button btn_title_account = (Button) findViewById(R.id.btn_title_account);
        Button btn_title_cart = (Button) findViewById(R.id.btn_title_cart);
        Button btn_title_order = (Button) findViewById(R.id.btn_title_order);


//        btn_title_cart.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("nimei","点到了btn_title_cart");
//            }
//        });


        tv_title_price.setOnClickListener(this);
        btn_title_account.setOnClickListener(this);
        btn_title_cart.setOnClickListener(this);
        btn_title_order.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_price:
                MessageWrap messageWrap = new MessageWrap("tv_title_price");
                EventBus.getDefault().post(messageWrap);
                break;
            case    R.id.btn_title_account:
                MessageWrap messageWrap1 = new MessageWrap("btn_title_account");
                EventBus.getDefault().post(messageWrap1);
                Log.d("nimei","点到了");
                break;
            case R.id. btn_title_cart:
                Log.d("nimei","点到了btn_title_cart");
                MessageWrap messageWrap2 = new MessageWrap("btn_title_cart");
                EventBus.getDefault().post(messageWrap2);

                break;
            case R.id.btn_title_order:
                MessageWrap messageWrap3 = new MessageWrap("btn_title_order");
                EventBus.getDefault().post(messageWrap3);
                Log.d("nimei","点到了");
                break;
        }
    }

}
