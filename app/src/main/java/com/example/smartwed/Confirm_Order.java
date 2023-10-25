package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartwed.Adapters.AddHallAdapter;
import com.example.smartwed.Adapters.OrderAdapter;
import com.example.smartwed.Adapters.confirmOrderAdapter;
import com.example.smartwed.Adapters.showhalladpater;
import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.Models.OrderModel;
import com.example.smartwed.Models.confirmOrderModel;
import com.example.smartwed.databinding.ActivityConfirmOrderBinding;
import com.example.smartwed.databinding.ActivityCustomerDashboardBinding;
import com.example.smartwed.Adapters.DataBaseHelper;

import java.util.ArrayList;

public class Confirm_Order extends AppCompatActivity {
    ActivityConfirmOrderBinding binding;
    DataBaseHelper helper;
   confirmOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfirmOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

  helper=new DataBaseHelper(this);

        final ArrayList<confirmOrderModel> list=helper.getconfirmOrder();
      adapter=new confirmOrderAdapter(list,this);
        binding.confirmorderrecyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.confirmorderrecyclerview.setLayoutManager(layoutManager);









    }
}