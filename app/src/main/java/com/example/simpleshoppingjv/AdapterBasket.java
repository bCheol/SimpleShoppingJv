package com.example.simpleshoppingjv;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterBasket extends RecyclerView.Adapter<AdapterBasket.ViewHolder> implements BasketDeleteClickListener{

    ArrayList<ItemBasket> items = new ArrayList<>();
    BasketDeleteClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_basket, parent, false);
        return new AdapterBasket.ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemBasket item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnBtnClick(BasketDeleteClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBtnClick(AdapterBasket.ViewHolder holder, View view, int position) {
        if(listener!=null){
            listener.onBtnClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, lprice, shopBtn, basketDeleteBtn;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView, final BasketDeleteClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            lprice = itemView.findViewById(R.id.lprice);
            shopBtn = itemView.findViewById(R.id.shopBtn);
            basketDeleteBtn = itemView.findViewById(R.id.basketDeleteBtn);
            basketDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position =getAdapterPosition();
                    if(listener != null){
                        listener.onBtnClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
        public void setItem(ItemBasket item){
            title.setText(item.getTitle());
            Glide.with(itemView.getContext())
                    .load(item.getImage())
                    .placeholder(R.drawable.img_loading)
                    .error(R.drawable.img_error)
                    .into(imageView);
            lprice.setText(item.getLprice());
            shopBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /*    Intent intent = new Intent(itemView.getContext(), WebViewActicity.class);
                    intent.putExtra("url", item.getLink());
                    itemView.getContext().startActivity(intent);*/
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public void addItem(ItemBasket item){
        items.add(item);
    }
}