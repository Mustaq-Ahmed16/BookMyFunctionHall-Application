package com.example.smartwed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.smartwed.Adapters.DataBaseHelper;

import com.example.smartwed.databinding.ActivityOrdersBinding;
import com.example.smartwed.databinding.ActivityUpdateHallByManagerBinding;

public class UpdateHall_by_manager extends AppCompatActivity {
    Button updatemed;
    int id;
    String medname, medprice, medquantity,halldescription;
    EditText mediname;
    EditText mediprice;
    EditText mediquantity;
   EditText halldesc;
    ImageView imageView;
   DataBaseHelper DB;
    private Uri imageFilePath;
    private Bitmap imageTostore,bitmap;
    private byte[] bitimg;
    private static int PICK_IMAGE_REQUEST = 100;
    private static final int PICK_IMAGE = 1;
ActivityUpdateHallByManagerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_hall_by_manager);

        binding = ActivityUpdateHallByManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DataBaseHelper helper = new DataBaseHelper(this);
        DB = new DataBaseHelper(this);

        {
            final int id = getIntent().getIntExtra("ID", 0);
            Cursor cursor = DB.getHallById(id);
            bitimg = cursor.getBlob(1);
            bitmap = BitmapFactory.decodeByteArray(bitimg, 0, bitimg.length);
            binding.updateimage.setImageBitmap(bitmap);
            binding.edtupdatehallname.setText(cursor.getString(2));
            binding.edtupdatedescription.setText(cursor.getString(3));
            binding.edtupdateprice.setText(String.format("%d", cursor.getInt(4)));
            binding.edtupdatecapacity.setText(cursor.getString(5));


            binding.btnupdatesubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isupdate = DB.updatehall(

                            bitmap,
                            binding.edtupdatehallname.getText().toString(),
                            binding.edtupdatedescription.getText().toString(),
                            binding.edtupdateprice.getText().toString(),


                            binding.edtupdatecapacity.getText().toString(), id

                    );
                    if (isupdate) {
                        Toast.makeText(UpdateHall_by_manager.this, "Updated", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(UpdateHall_by_manager.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        binding.updateimage.setOnClickListener(new View.OnClickListener() {
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
                binding.updateimage.setImageBitmap(imageTostore);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    }





