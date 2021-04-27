package com.example.mall.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
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
        TextView tv_title_price = (TextView) findViewById(R.id.tv_title_price);
        Button btn_title_account = (Button) findViewById(R.id.btn_title_account);
        Button btn_title_cart = (Button) findViewById(R.id.btn_title_cart);
        Button btn_title_order = (Button) findViewById(R.id.btn_title_order);


        tv_title_price.setOnClickListener(this);
        btn_title_account.setOnClickListener(this);
        btn_title_cart.setOnClickListener(this);
        btn_title_order.setOnClickListener(this);

    }

    /**
     * Create by hsw
     * on 2021/4/21.
     * EventBUs
     * 传送数据
     */
    @Override
    public void onClick(View v) {

        MessageWrap messageWrap = new MessageWrap(v.getId());
              EventBus.getDefault().post(messageWrap);

    }


}
