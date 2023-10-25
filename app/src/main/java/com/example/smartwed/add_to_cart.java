package com.example.smartwed;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartwed.Adapters.addToCartAdapter;
import com.example.smartwed.Models.addToCartModel;
import com.example.smartwed.databinding.ActivityAddToCartBinding;
import com.example.smartwed.Adapters.DataBaseHelper;

import java.util.ArrayList;

public class add_to_cart extends AppCompatActivity {
   ActivityAddToCartBinding binding;
    DataBaseHelper helper;
  addToCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddToCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper = new DataBaseHelper(this);
        ArrayList<addToCartModel> list = helper.getAddToCart();


        adapter = new addToCartAdapter(list, this);
        binding.addToCartrecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        binding.addToCartrecyclerview.setLayoutManager(layoutManager);
        binding.addToCartrecyclerview.getAdapter().notifyDataSetChanged();
        binding.addToCartrecyclerview.scheduleLayoutAnimation();

    }

    }