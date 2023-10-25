package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smartwed.Adapters.OrderAdapter;
import com.example.smartwed.Models.OrderModel;
import com.example.smartwed.Sessions.SessionManagement;
import com.example.smartwed.databinding.ActivityOrdersBinding;
import com.example.smartwed.Adapters.DataBaseHelper;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    ActivityOrdersBinding binding;
    SessionManagement sessionManagement;
    int iddd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DataBaseHelper helper=new DataBaseHelper(this);
        sessionManagement=new SessionManagement(getApplicationContext());
         iddd=sessionManagement.getSession();

        final ArrayList<OrderModel> list=helper.getOrder(iddd);
       final OrderAdapter adapter=new OrderAdapter(list,this);
        binding.orderrecyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.orderrecyclerview.setLayoutManager(layoutManager);


        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter.notifyDataSetChanged();
                binding.swiperefreshlayout.setRefreshing(false);
            }
        });
    }


}