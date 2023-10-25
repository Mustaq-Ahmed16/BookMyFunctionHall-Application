package com.example.smartwed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.smartwed.Adapters.AddHallAdapter;
import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.databinding.ActivityCustomerDashboardBinding;
import com.example.smartwed.Adapters.DataBaseHelper;

import java.util.ArrayList;

public class Customer_dashboard extends AppCompatActivity {
    ActivityCustomerDashboardBinding binding;
    DataBaseHelper helper;
    AddHallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper=new DataBaseHelper(this);
       ArrayList<AddHallModel> list=helper.getHallDetail();
LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(binding.recyclerview.getContext(),
        R.anim.layout_fall_dwon);
binding.recyclerview.setLayoutAnimation(controller);

       adapter=new AddHallAdapter(list,this);
       binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        binding.recyclerview.setLayoutManager(layoutManager);
binding.recyclerview.getAdapter().notifyDataSetChanged();
binding.recyclerview.scheduleLayoutAnimation();





        binding.Searchhall.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//
//        return super.onCreateOptionsMenu(menu);
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.orders:
//                startActivity(new Intent(Customer_dashboard.this, OrdersActivity.class));
//                break;
//
//
//
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void filter(String text){
        ArrayList<AddHallModel> filterList = new ArrayList<>();

        for(AddHallModel item : helper.getHallDetail()){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.filterList(filterList);
    }


}