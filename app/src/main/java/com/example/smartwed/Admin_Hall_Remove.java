package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.smartwed.Adapters.AddHallAdapter;
import com.example.smartwed.Adapters.removeHallAdapter;
import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.Models.removeHallModel;
import com.example.smartwed.databinding.ActivityAdminHallRemoveBinding;
import com.example.smartwed.databinding.ActivityCustomerDashboardBinding;
import com.example.smartwed.Adapters.DataBaseHelper;

import java.util.ArrayList;

public class Admin_Hall_Remove extends AppCompatActivity {
    ActivityAdminHallRemoveBinding binding;
    DataBaseHelper helper;
    removeHallAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityAdminHallRemoveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper=new DataBaseHelper(this);
        ArrayList<removeHallModel> list=helper.getRemoveHallDetail();


        adapter=new removeHallAdapter(list,this);
        binding.removerecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        binding.removerecyclerview.setLayoutManager(layoutManager);

    }
}