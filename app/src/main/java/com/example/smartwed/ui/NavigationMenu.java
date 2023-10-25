//package com.example.smartwed.ui;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.smartwed.DetailActivity;
//import com.example.smartwed.Home_Activity;
//import com.example.smartwed.OrdersActivity;
//import com.example.smartwed.R;
//import com.google.android.material.navigation.NavigationView;
//
//public class NavigationMenu extends AppCompatActivity {
//    NavigationView nav;
//    ActionBarDrawerToggle toggle;
//    DrawerLayout drawerLayout;
//    Toolbar toolbar;
//    private ImageView upload_profile;
//    private Bitmap imageTostore;
//    private Uri imageFilePath;
//    private static final int PICK_IMAGE = 1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.navigation_menu);
////        toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////        nav = (NavigationView) findViewById(R.id.navmenu);
////        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
////        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
////        drawerLayout.addDrawerListener(toggle);
////        toggle.syncState();
//
//
//        upload_profile=(ImageView) findViewById(R.id.profile_upload);
//
//        upload_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gallery = new Intent();
//                gallery.setType("image/*");
//                gallery.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE);
//            }
//        });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        try {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode==PICK_IMAGE && resultCode == RESULT_OK && data.getData()!=null)
//            {
//                imageFilePath = data.getData();
//                imageTostore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
//                upload_profile.setImageBitmap(imageTostore);
//            }
//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//}