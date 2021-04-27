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
import com.example.mall.model.bean.ProductData;

import java.util.List;

/**
 * Create by hsw
 * on 2021/4/21.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<ProductData> ProductData;
    public CartAdapter(List<ProductData> ProductData) {
        this.ProductData = ProductData;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mview;
        ImageView  cart_ryv_item_image;
        TextView cart_ryv_item_param;
        TextView cart_ryv_item_price;
        TextView cart_ryv_item_number;
        public ViewHolder(View view) {
            super(view);
            mview = view;
            cart_ryv_item_image = (ImageView) view.findViewById(R.id.cart_ryv_item_image);
            cart_ryv_item_param = (TextView) view.findViewById(R.id.cart_ryv_item_param);
            cart_ryv_item_price = (TextView) view.findViewById(R.id.cart_ryv_item_price);
            cart_ryv_item_number = (TextView) view.findViewById(R.id.cart_ryv_item_number);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.cart_ryv_item_linear);
            setImageViewSize(linearLayout,cart_ryv_item_image);
        }


        /**
         * 设置imageview的大小
         * 文字容器的高度一致
         * on 2021/4/21.
         */
        private  void  setImageViewSize(LinearLayout linearLayout,ImageView imageView){
            linearLayout.measure(0,0);
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(linearLayout.getMeasuredHeight()*2,
                    linearLayout.getMeasuredHeight()*2);
            imageView.setLayoutParams(params);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_ryv_item, parent, false);
        view.setFocusable(true);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductData data = ProductData.get(position);
        holder.cart_ryv_item_image.setImageResource(data.getImageId());
        holder.cart_ryv_item_price.setText("¥"+Float.toString(data.getPrice()));
        holder.cart_ryv_item_param.setText(data.getParam());
        holder.cart_ryv_item_number.setText(""+data.getNumber());
    }

    private void setSize(View view){


    }



    @Override
    public int getItemCount() {
        return ProductData.size();
    }


}
