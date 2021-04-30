package com.example.mall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mall.R;
import com.example.mall.contract.IProduct;
import com.example.mall.model.bean.ProductInfo;

import java.util.List;

/**
 * Create by hsw
 * on 2021/4/21.
 */
public class MActivityAdapter extends RecyclerView.Adapter<MActivityAdapter.ViewHolder> {
    private List<ProductInfo> mProductInfoList;

    private IProduct iProduct;
    public MActivityAdapter(List<ProductInfo> productInfoList, IProduct person) {
        mProductInfoList = productInfoList;
        this.iProduct = person;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView product_name;
        TextView product_param;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            product_name = (TextView) view.findViewById(R.id.product_name);
            product_param = (TextView) view.findViewById(R.id.product_param);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        view.setFocusable(true);
        final ViewHolder holder = new ViewHolder(view);


        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ProductInfo productInfo = mProductInfoList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + productInfo.getName(), Toast.LENGTH_SHORT).show();
                iProduct.inSert(productInfo);//回调接口
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ProductInfo ProductInfo = mProductInfoList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + ProductInfo.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductInfo ProductInfo = mProductInfoList.get(position);
        holder.fruitImage.setImageResource(ProductInfo.getImageId());
        holder.product_name.setText(ProductInfo.getName());
        holder.product_param.setText("550ml*1瓶");
    }



    @Override
    public int getItemCount() {
        return mProductInfoList.size();
    }



}
