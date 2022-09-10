package com.example.simpleshoppingjv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    static myDBHelper myDBHelper;
    static SQLiteDatabase sqLiteDatabase;
    long backpressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new myDBHelper(this);
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        myDBHelper.onCreate(sqLiteDatabase);

        FragmentSearch fragmentSearch = new FragmentSearch();
        FragmentBasket fragmentBasket = new FragmentBasket();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentView,fragmentSearch).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.searchView:
                        getSupportFragmentManager().beginTransaction().remove(fragmentBasket).commit();
                        getSupportFragmentManager().beginTransaction().show(fragmentSearch).commit();
                        break;
                    case R.id.shoppingBasket:
                        getSupportFragmentManager().beginTransaction().hide(fragmentSearch).commit();
                        getSupportFragmentManager().beginTransaction().add(R.id.fragmentView,fragmentBasket).commit();
                        break;
                }
                return true;
            }
        });
    }

    //DB 관리
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(@Nullable Context context) {
            super(context, "basket", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = "CREATE TABLE if not exists basketTable (id integer primary key autoincrement, title text, link text, image text, lprice text)";
            sqLiteDatabase.execSQL(sql);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String sql = "DROP TABLE IF EXISTS basketTable";
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }
    }

    @Override
    protected void onDestroy() {
        myDBHelper.close();
        super.onDestroy();
    }

    //뒤로가기 두 번 클릭
    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        if (System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            finish();
        }
    }
}