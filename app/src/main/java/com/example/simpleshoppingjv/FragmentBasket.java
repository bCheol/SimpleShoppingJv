package com.example.simpleshoppingjv;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentBasket extends Fragment {

    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    AdapterBasket adapterBasket;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_basket, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapterBasket = new AdapterBasket();

        sqLiteDatabase = MainActivity.myDBHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from basketTable",null);
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            String title= cursor.getString(0);
            String link= cursor.getString(1);
            String image = cursor.getString(2);
            String lprice = cursor.getString(3);
            adapterBasket.addItem(new ItemBasket(title, link, image, lprice));
        }
        cursor.close();

        recyclerView.setAdapter(adapterBasket);
        //장바구니 삭제
        adapterBasket.setOnBtnClick(new BasketDeleteClickListener() {
            @Override
            public void onBtnClick(AdapterBasket.ViewHolder holder, View view, int position) {
                sqLiteDatabase = MainActivity.myDBHelper.getWritableDatabase();
                String sql = "Delete from basketTable where link = '" + adapterBasket.items.get(position).getLink() + "';" ;
                sqLiteDatabase.execSQL(sql);
                Toast.makeText(requireContext(),"삭제됐습니다.",Toast.LENGTH_SHORT).show();
                adapterBasket.items.remove (position);
                adapterBasket.notifyItemRemoved (position);
            }
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sqLiteDatabase.close();
    }
}