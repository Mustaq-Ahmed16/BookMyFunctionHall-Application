package com.example.smartwed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.smartwed.Adapters.DataBaseHelper;
import com.example.smartwed.Sessions.SessionManagement;
import com.example.smartwed.databinding.ActivityDetailBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class DetailActivity extends AppCompatActivity {
    public ActivityDetailBinding binding;
    boolean[] selectedmenu;
    public DataBaseHelper DB;
    private static int id;
    SessionManagement sessionManagement;
    int cyear, cmonth, cday, chour, cminute;
    ArrayList<Integer> menulist = new ArrayList<>();
    String[] menuArray = {"Pulao", "Qorma", "Steam Chicken", "Custard"};
    Bitmap bitmap;
    byte[] bitimg;
    String hallName,detaildesc;
    String HallPrice,capacity;
    int id2;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sessionManagement=new SessionManagement(getApplicationContext());
        id2=sessionManagement.getSession();
        final DataBaseHelper helper = new DataBaseHelper(this);
        selectedmenu = new boolean[menuArray.length];
        DB = new DataBaseHelper(this);

        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(DetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                binding.txtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, cyear, cmonth, cday);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() -1000);
                datePickerDialog.show();
            }
        });

        binding.btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                chour = calendar.get(Calendar.HOUR_OF_DAY);
                cminute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(DetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                binding.txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, chour, cminute, false);
                timePickerDialog.show();
            }
        });







//menu selection
        binding.edtmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        DetailActivity.this

                );
                builder.setTitle("Select Menu");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(menuArray, selectedmenu, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            menulist.add(which);
                            Collections.sort(menulist);


                        } else {
                            menulist.remove(which);
                        }
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < menulist.size(); j++) {
                            stringBuilder.append(menuArray[menulist.get(j)]);
                            if (j != menulist.size() - 1) {

                                stringBuilder.append(",");


                            }
                        }
                        binding.edtmenu.setText(stringBuilder.toString());


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < selectedmenu.length; j++) {
                            selectedmenu[j] = false;
                            menulist.clear();
                            binding.edtmenu.setText("");

                        }
                    }
                });
                builder.show();


            }
        });
        if (getIntent().getIntExtra("type", 0) == 1) {

            id = getIntent().getIntExtra("id1", 0);
            Cursor c = DB.getHallData(id);
            if (c != null) {
                while (c.moveToNext()) {
                    bitimg = c.getBlob(1);
                    bitmap = BitmapFactory.decodeByteArray(bitimg, 0, bitimg.length);
                    hallName = c.getString(2);
                    detaildesc = c.getString(3);
                    HallPrice = c.getString(4);
                    capacity = c.getString(5);
                }
                binding.detailimage.setImageBitmap(bitmap);
                binding.detailname.setText(hallName);
                binding.detaildescription.setText(detaildesc);
                binding.detailprice.setText(HallPrice);
                binding.capacity.setText(capacity);
            } else {
                Toast.makeText(DetailActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
            }
            if(binding.edtname.equals("")||binding.edtphoneno.equals("")||binding.edtmenu.equals(""))
            {

                Toast.makeText(DetailActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean check = DB.checkHallexist(String.valueOf(id2));
                if (check == false) {

                    binding.btnorder.setOnClickListener(new View.OnClickListener() {


                        public void onClick(View view) {

                            boolean isinserted = DB.insertOrder(binding.edtname.getText().toString(),
                                    binding.edtphoneno.getText().toString(),
                                    binding.capacity.getText().toString(),
                                    binding.edtmenu.getText().toString(),
                                    binding.txtDate.getText().toString(),
                                    binding.txtTime.getText().toString(),
                                    HallPrice,

                                    bitmap,
                                    detaildesc,
                                    hallName,id2


                            );

                            if (isinserted) {

                                binding.btnorder.setEnabled(false);
                                Toast.makeText(DetailActivity.this, "Hall Booking successful",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DetailActivity.this, "Hall Already Booked",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    binding.btnorder.setEnabled(false);
                }






            }
        }






        else {
            final int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = DB.getOrderById(id);
            binding.edtname.setText(cursor.getString(1));
            binding.edtphoneno.setText(cursor.getString(2));
            binding.capacity.setText(cursor.getString(3));

            binding.edtmenu.setText(cursor.getString(4));
            binding.txtDate.setText(cursor.getString(5));
            binding.txtTime.setText(cursor.getString(6));
            binding.detailprice.setText(String.format("%d", cursor.getInt(7)));

            bitimg = cursor.getBlob(8);
            bitmap = BitmapFactory.decodeByteArray(bitimg, 0, bitimg.length);
            binding.detailimage.setImageBitmap(bitmap);
            binding.detaildescription.setText(cursor.getString(9));

            binding.detailname.setText(cursor.getString(10));


            binding.btnorder.setText("Update Now");

            binding.btnorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isupdated = DB.updateorder(binding.edtname.getText().toString(),

                            binding.edtphoneno.getText().toString(),
                            Integer.parseInt(binding.capacity.getText().toString()),
                            binding.edtmenu.getText().toString(),
                            binding.txtDate.getText().toString(),
                            binding.txtTime.getText().toString(),
                            Integer.parseInt(binding.detailprice.getText().toString()),
                            bitmap,
                            binding.detaildescription.getText().toString(),


                            binding.detailname.getText().toString(), id

                    );
                    if (isupdated) {
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    public void btn_chats_exp(View view) {
        Intent intent = new Intent(this, chat_module.class);
        startActivity(intent);

    }

    public void profile_opening(View view) {
        Intent intent = new Intent(this, profile1.class);
        startActivity(intent);
    }

    public void back_cust1(View view) {
        Intent intent = new Intent(this, Customer_dashboard.class);
        startActivity(intent);
    }

    public void keyboard_down(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}