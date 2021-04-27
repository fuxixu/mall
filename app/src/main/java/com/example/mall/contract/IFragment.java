package com.example.mall.contract;

import androidx.fragment.app.DialogFragment;

public interface IFragment {

    public DialogFragment onCreate(DialogFragment fragment);
    public void destroy(DialogFragment fragment);
}
