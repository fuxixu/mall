package com.example.mall.util;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.util.Log;

import com.example.mall.R;
import com.example.mall.model.db.DbCart;
import com.example.mall.ui.OrderFragment;
import com.example.mall.ui.PayFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventUtils {

    private static EventUtils bus = null;

    private EventUtils() {
    }

    synchronized public static EventUtils getInstance()  {
        if (bus == null) {
            bus = new EventUtils();
        }
        return bus;
    }

    //以 R id 的方式作为表示符
    public void  post (int id){
        MessageWrap r = new MessageWrap(id);
        EventBus.getDefault().post(r);
    }



    //以 R id 的方式作为表示符
    public void  postAllPrice (){
        MessageWrap r = new MessageWrap(R.id.title_price_tv);
        EventBus.getDefault().post(r);
        MessageWrap a = new MessageWrap(R.id.cart_all_price);
        EventBus.getDefault().post(a);
    }

}