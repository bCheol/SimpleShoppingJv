package com.example.simpleshoppingjv;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentSearch extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        EditText editText = root.findViewById(R.id.editText);
        //editText 키보드 설정
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        //스피너 생성
        Spinner spinner = root.findViewById(R.id.spinner);
        String[] spinnerItem = {"유사도순","날짜순","가격낮은순","가격높은순"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, spinnerItem);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //리사이클러뷰 설정
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        AdapterSearch adapter = new AdapterSearch();

        //레트로핏 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        //네이버에서 발급 받은 id, pwd
        String clientId = "zaOWIdW8nHmrEsOkNRH2";
        String clientSecret = "XZymtAbD_W";

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER){
                    inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    //스피너 선택
                    String sort="";
                    if(spinner.getSelectedItem().toString().equals("유사도순")){
                        sort="sim";
                    }else if(spinner.getSelectedItem().toString().equals("날짜순")){
                        sort="date";
                    }else if(spinner.getSelectedItem().toString().equals("가격낮은순")){
                        sort="asc";
                    }else if(spinner.getSelectedItem().toString().equals("가격높은순")){
                        sort="dsc";
                    }
                    inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                    //call 객체 생성, 실행
                    adapter.clearItem();
                    Call<GetData> call =retrofitAPI.getData(editText.getText().toString(),100, sort, clientId, clientSecret);
                    call.enqueue(new Callback<GetData>() {
                        @Override
                        public void onResponse(@NonNull Call<GetData> call, @NonNull Response<GetData> response) {
                            GetData data = response.body();
                            if (data != null) {
                                for(int i=0; i<data.getItem().size(); i++){
                                    String title = data.getItem().get(i).getTitle();
                                    String link = data.getItem().get(i).getLink();
                                    String image = data.getItem().get(i).getImage();
                                    String lprice = data.getItem().get(i).getLprice();
                                    adapter.addItem(new ItemSearch(title, link, image, lprice));
                                }
                                recyclerView.setAdapter(adapter);
                            }
                        }
                        @Override
                        public void onFailure(@NonNull Call<GetData> call, @NonNull Throwable t) {
                            Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return false;
            }
        });
        return root;
    }
}