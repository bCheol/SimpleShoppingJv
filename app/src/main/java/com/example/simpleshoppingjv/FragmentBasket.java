package com.example.simpleshoppingjv;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentBasket extends Fragment {

    SQLiteDatabase sqLiteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_basket, container, false);

        //리사이클러뷰 설정
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        AdapterBasket adapterBasket = new AdapterBasket();

        //장바구니(DB) 목록 가져오기
        sqLiteDatabase = MainActivity.myDBHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from basketTable",null);
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String title= cursor.getString(1);
            String link= cursor.getString(2);
            String image = cursor.getString(3);
            String lprice = cursor.getString(4);
            adapterBasket.addItem(new ColumnBasket(id, title, link, image, lprice));
        }
        cursor.close();

        recyclerView.setAdapter(adapterBasket);

        Toast toast = Toast.makeText(requireContext(),"삭제됐습니다.",Toast.LENGTH_SHORT);

        //장바구니(DB) 삭제
        adapterBasket.setOnBtnClick(new BasketDeleteClickListener() {
            @Override
            public void onBtnClick(AdapterBasket.ViewHolder holder, View view, int position) {
                sqLiteDatabase = MainActivity.myDBHelper.getWritableDatabase();
                String sql = "Delete from basketTable where id = '" + adapterBasket.basketItem.get(position).getId() + "';" ;
                sqLiteDatabase.execSQL(sql);
                adapterBasket.basketItem.remove (position);
                adapterBasket.notifyItemRemoved (position);
                toast.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);
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