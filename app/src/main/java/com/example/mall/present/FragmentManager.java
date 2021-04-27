package com.example.mall.present;

import android.widget.FrameLayout;

import androidx.fragment.app.DialogFragment;

import com.example.mall.contract.IFragment;

public class FragmentManager  implements IFragment {

    private DialogFragment fragment;
    @Override
    public DialogFragment onCreate(DialogFragment fragment) {
        this.fragment = fragment;
        return fragment;
    }

    @Override
    public void destroy(DialogFragment fragment) {
        fragment.dismiss();
    }
}
