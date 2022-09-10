package com.example.simpleshoppingjv;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
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

    ArrayList<ItemDB> searchItem = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemDB item = searchItem.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return searchItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, lprice, shopBtn, basketAddBtn ;
        ImageView imageView;
        Toast toast ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            lprice = itemView.findViewById(R.id.lprice);
            shopBtn = itemView.findViewById(R.id.shopBtn);
            basketAddBtn = itemView.findViewById(R.id.basketAddBtn);
            toast = Toast.makeText(itemView.getContext(), "장바구니에 추가됐습니다.", Toast.LENGTH_SHORT);
        }
        public void setItem(ItemDB item){
            title.setText(item.getTitle());
            Glide.with(itemView.getContext())
                    .load(item.getImage())
                    .placeholder(R.drawable.img_loading)
                    .error(R.drawable.img_error)
                    .into(imageView);
            lprice.setText(item.getLprice());
            //자세히 클릭
            shopBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                    itemView.getContext().startActivity(intent);
                }
            });
            //장바구니(DB) 추가
            basketAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase sqLiteDatabase = MainActivity.myDBHelper.getWritableDatabase();
                    String sql = "Insert into basketTable values ('" + item.getTitle() + "','" + item.getLink() + "','" + item.getImage() + "','" +  item.getLprice() + "');" ;
                    sqLiteDatabase.execSQL(sql);
                    sqLiteDatabase.close();
                    toast.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);
                }
            });
        }
    }

    public void addItem(ItemDB item){
        searchItem.add(item);
    }

    public void clearItem(){
        searchItem.clear();
    }
}