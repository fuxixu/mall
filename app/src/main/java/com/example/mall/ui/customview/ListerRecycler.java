package com.example.mall.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListerRecycler extends RecyclerView {

    private int position;
    public ListerRecycler(@NonNull Context context) {
        super(context);
        init();
    }

    public ListerRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListerRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
//        setItemAnimator(null);
//        /**
//         * 该属性是当一个为view获取焦点时，定义viewGroup和其子控件两者之间的关系。
//         *
//         * 属性的值有三种：
//         * beforeDescendants：viewgroup会优先其子类控件而获取到焦点
//         * afterDescendants：viewgroup只有当其子类控件不需要获取焦点时才获取焦点
//         * blocksDescendants：viewgroup会覆盖子类控件而直接获得焦点
//         * */
//        setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
//        setChildrenDrawingOrderEnabled(true);
//        this.setFocusable(true);
    }


    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
         position = getChildViewHolder(child).getAdapterPosition();

    }

//    @Override
//    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
//        View view = null;
//        if (this.hasFocus() || position < 0 || (view = getLayoutManager().findViewByPosition(position)) == null) {
//            super.addFocusables(views,direction,focusableMode);
//        }else if(view.isFocusable()){
////将当前的view放到Focusable views列表中，再次移入焦点时会取到该view,实现焦点记忆功能
//            views.add(view);
//        }else{
//            super.addFocusables(views,direction,focusableMode);
//        }
//    }

    public int getPosition(){
        if (hasFocus()){

            return position;
        }
        return -1;
    }
//    @Override
//    protected int getChildDrawingOrder(int childCount, int i) {
//        View focusedChild = getFocusedChild();
//        if(focusedChild== null){
//            return super.getChildDrawingOrder(childCount, i);
//        }else{
//            int index = indexOfChild(focusedChild);
//
//            if(i == childCount-1){
//                return index;
//            }
//            if(i<index){
//                return i;
//            }
//            return i+1;
//        }
//    }

}
