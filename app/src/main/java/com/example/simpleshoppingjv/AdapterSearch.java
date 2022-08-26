package com.example.simpleshoppingjv;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder>{

    ArrayList<ItemSearch> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemSearch item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, lprice, shopBtn, basketAddBtn ;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            lprice = itemView.findViewById(R.id.lprice);
            shopBtn = itemView.findViewById(R.id.shopBtn);
            basketAddBtn = itemView.findViewById(R.id.basketAddBtn);
        }
        public void setItem(ItemSearch item){
            title.setText(item.getTitle());
            Glide.with(itemView.getContext()).load(item.getImage()).into(imageView);
            lprice.setText(item.getLprice());
            shopBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /*    Intent intent = new Intent(itemView.getContext(), WebViewActicity.class);
                    intent.putExtra("url", item.getLink2());
                    Log.d("test",item.getLink2());
                    itemView.getContext().startActivity(intent);*/
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink2()));
                    itemView.getContext().startActivity(intent);
                }
            });
            basketAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.myDBHelper.add(item.getTitle(),item.getLink2(),item.getImage(),item.getLprice());
                    Toast.makeText(itemView.getContext(),"장바구니에 추가됐습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addItem(ItemSearch item){
        items.add(item);
    }

    public void clearItem(){
        items.clear();
    }
}