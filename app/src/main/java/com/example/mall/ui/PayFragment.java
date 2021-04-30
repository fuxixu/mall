package com.example.mall.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mall.R;
import com.example.mall.util.MessageWrap;

import org.greenrobot.eventbus.EventBus;

public class PayFragment extends DialogFragment implements View.OnClickListener {
    private View mRootView;
    private Button pay_succeed;
    /**
     * Create by hsw
     * on 2021/4/26
     * eventbus发送数据
     */
    @Override
    public void onClick(View v) {
        MessageWrap messageWrap = new MessageWrap(v.getId());
        EventBus.getDefault().post(messageWrap);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.pay_fragment, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return mRootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pay_succeed = view.findViewById(R.id.pay_succeed);
        pay_succeed.setOnClickListener(this);
    }



    /**
     * 设置弹窗的大小
     * on 2021/4/21.
     */
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
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.5), (int) (dm.heightPixels * 0.5));

        }
    }

}
