package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.smartwed.Adapters.AddHallAdapter;
import com.example.smartwed.Adapters.OrderAdapter;
import com.example.smartwed.Adapters.showhalladpater;
import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.Models.OrderModel;
import com.example.smartwed.Models.showaddhallmodel;
import com.example.smartwed.databinding.ActivityOrdersBinding;
import com.example.smartwed.databinding.ActivityShowaddhallBinding;
import com.example.smartwed.Adapters.DataBaseHelper;

import java.util.ArrayList;

public class showaddhall extends AppCompatActivity {
    DataBaseHelper helper;
    showhalladpater adapter;
    ActivityShowaddhallBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShowaddhallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper=new DataBaseHelper(this);
        ArrayList<showaddhallmodel> list=helper.getshowHallDetail();


        adapter=new showhalladpater(list,this);
        binding.showaddhallrecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        binding.showaddhallrecyclerview.setLayoutManager(layoutManager);









//        DataBaseHelper helper=new DataBaseHelper(this);

       // final ArrayList<showaddhall> list=helper.getaddHallDetail();



//
//        final showhalladpater adapter=new showhalladpater(list,this);
//        binding.showaddhallrecyclerview.setAdapter(adapter);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        binding.showaddhallrecyclerview.setLayoutManager(layoutManager);


//        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                adapter.notifyDataSetChanged();
//                binding.swiperefreshlayout.setRefreshing(false);
//            }
//        });
    }



}