package com.example.smartwed;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartwed.databinding.ActivityAddHallBinding;
import com.example.smartwed.Adapters.DataBaseHelper;
import com.example.smartwed.databinding.ActivityDetailBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.security.Permission;
import java.util.ArrayList;

public class AddHall extends AppCompatActivity {
    public ActivityAddHallBinding binding;
    //private static final int GALLER_ACTION_PICK_CODE = 100;
    // Uri imageUri;
    Bitmap bitmap;
    byte[] bitimg;
    DataBaseHelper DB;
    private ImageView imageupload;
    private Uri imageFilePath;
    private Bitmap imageTostore;
    private static final int PICK_IMAGE = 1;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddHallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DataBaseHelper helper = new DataBaseHelper(this);
if(binding.edthallname.equals("")||binding.edtdescription.equals("")||
        binding.edtprice.equals("")||binding.edtcapacity.equals(""))
{

    Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
}
       else {
            //    final int image = getIntent().getIntExtra("image", 0);
            final int price = getIntent().getIntExtra("price", 0);
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");


            // binding.imageupload.setImageResource(image);
            binding.edtprice.setText(String.format("%d", price));
            binding.edthallname.setText(name);
            binding.edtdescription.setText(description);


            binding.btnsubmit.setOnClickListener(new View.OnClickListener() {


                                                     public void onClick(View view) {

                                                         boolean valueinserted = helper.insertDetail(
                                                                 imageTostore,
                                                                 binding.edthallname.getText().toString(),
                                                                 binding.edtdescription.getText().toString(),


                                                                 binding.edtprice.getText().toString(),
                                                                 binding.edtcapacity.getText().toString(),
                                                                 binding.edtlocation.getText().toString()


                                                         );

                                                         if (valueinserted) {
                                                             Toast.makeText(AddHall.this, "Data inserted successful",
                                                                     Toast.LENGTH_SHORT).show();

                                                         } else
                                                             Toast.makeText(AddHall.this, "ERROR",
                                                                     Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
            );

        }



        binding.imageupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
                imageFilePath = data.getData();
                imageTostore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                binding.imageupload.setImageBitmap(imageTostore);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home1:
                startActivity(new Intent(AddHall.this, Customer_dashboard.class));
                break;


        }
        return super.onOptionsItemSelected(item);


    }



}