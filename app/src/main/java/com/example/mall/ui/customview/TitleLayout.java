package com.example.mall.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mall.R;
import com.example.mall.model.db.DbCart;
import com.example.mall.ui.OrderFragment;
import com.example.mall.ui.PayFragment;
import com.example.mall.util.MessageWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//implements View.OnClickListener
public class TitleLayout extends LinearLayout  implements View.OnClickListener{
    TextView title_price_tv;
    Context mContext;
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        EventBus.getDefault().register(this);

        LayoutInflater.from(context).inflate(R.layout.title, this);
        initView();

    }

    private void initView() {


        title_price_tv = (TextView) findViewById(R.id.title_price_tv);
        Button btn_title_account = (Button) findViewById(R.id.btn_title_account);
        Button btn_title_cart = (Button) findViewById(R.id.btn_title_cart);
        Button btn_title_order = (Button) findViewById(R.id.btn_title_order);

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

    /**
     * Create by hsw
     * on 2021/4/21.
     *
     * EventBUs接收信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        switch (message.getMessage()) {
            case  R.id.title_price_tv:
                title_price_tv.setText("¥"+String.valueOf(DbCart.getInstance().getAllPrice()));
                Log.d("bb","传送成功"+message.getAllPrice());
                break;

        }
    }

}
