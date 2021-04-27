package com.example.mall.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.R;
import com.example.mall.adapter.OrderAdapter;
import com.example.mall.model.bean.OrderData;
import com.example.mall.util.Size;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends DialogFragment implements View.OnClickListener {
    private View mRootView;
    private List<OrderData> orderDataList = new ArrayList<OrderData>();
    @Override
    public void onClick(View v) {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.orderhistory_dialog_fragment, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return mRootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    /**
     * 初始化recyclervieww
     * 设置适配器
     * Create by hsw
     * on 2021/4/27.
     */
    private void  initView(){
        int a = R.id.order_recycler_view;
        RecyclerView order_recycler_view = mRootView.findViewById(a);

        initOder();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_recycler_view.setLayoutManager(linearLayoutManager);
        OrderAdapter orderAdapter = new OrderAdapter(orderDataList);
        order_recycler_view.setAdapter(orderAdapter);
    }
    private void  initOder() {
        for (int i = 0; i < 15; i++) {

            String a = "2020.11.17-17:14:56";
            String b = "330ml";
            String c = "8.00";
            String d = "等待配送";
            OrderData orderData = new OrderData(a,b,c,d);
            orderDataList.add(orderData);
        }
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
            dialog.getWindow().setLayout((int) (dm.widthPixels * 1), (int) (dm.heightPixels * 1));

        }
        }
}
