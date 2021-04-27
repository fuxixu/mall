package com.example.mall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.R;
import com.example.mall.model.bean.OrderData;
import com.example.mall.model.bean.ProductData;

import java.util.List;

/**
 * Create by hsw
 * on 2021/4/21.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<OrderData> ProductData;
    public OrderAdapter(List<OrderData> ProductData) {
        this.ProductData = ProductData;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mview;
        TextView order_time;
        TextView order_name;
        TextView oder_all_price;
        TextView oder_status;
        public ViewHolder(View view) {
            super(view);
            mview = view;

            int a = R.id.order_time;
            int b = R.id.order_name;
            int c =R.id.oder_all_price;
            int d = R.id.oder_status;
            order_time = (TextView) view.findViewById(a);
            order_name = (TextView) view.findViewById(b);
            oder_all_price = (TextView) view.findViewById(c);
            oder_status = (TextView) view.findViewById(d);
        }


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int a = R.layout.order_recycler_item;
        View view = LayoutInflater.from(parent.getContext()).inflate(a, parent, false);
        view.setFocusable(true);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderData data = ProductData.get(position);
        String a =data.getName();
        String b =data.getTime();
        String c =data.getAllPrice();
        String d = data.getStatus();
        holder.order_time.setText(a);
        holder.order_name.setText(b);
       holder.oder_all_price.setText(c);
       holder.oder_status.setText(d);
    }


    @Override
    public int getItemCount() {
        return ProductData.size();
    }


}
