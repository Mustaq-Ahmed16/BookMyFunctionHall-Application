package com.example.smartwed.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.Models.OrderModel;
import com.example.smartwed.Models.addToCartModel;
import com.example.smartwed.Models.confirmOrderModel;
import com.example.smartwed.Models.removeHallModel;
import com.example.smartwed.Models.showaddhallmodel;
import com.example.smartwed.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    final static String DBName = "SmartWedding.db";
    final static int DBVersion = 21;
    public static final String DATE = "date";
    //Bitmap image = img;
    byte[] imageInBytes;
    private Object Context;
    Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DBName,
                null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders"
                + "(id integer primary key autoincrement," +
                "name text," +
                "phone text," + "capacity text" + ",menu text," + "date text," + "time text," + "price text," + "image Blob," +
                "description text," +
                "hallname text," +
                " status text," +
                " customer_id integer)");

        db.execSQL("create Table users(id integer primary key autoincrement,name TEXT,password TEXT)");
        db.execSQL("create Table manager(name TEXT  PRIMARY KEY,password TEXT)");


        db.execSQL("create table manageraddhall(id1 integer primary key autoincrement," +
                "image Blob,name text,description text,price String," +
                "capacity String," +
                "location String," +
                "status String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(context.getString(R.string.drop_table_if_exists_orders));
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists manager");

        db.execSQL("drop Table if exists manageraddhall");
        onCreate(db);
    }

    public Boolean insertData(String name, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("password", password);
        long result = MyDB.insert("users", null, contentvalues);
        if (result == -1) return false;
        else
            return true;

    }

    public Boolean checkusername(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users " +
                "where name=?", new String[]{name});

        if (cursor.getCount() > 0)

            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword(String name, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from users where" +
                " name=? and password=?", new String[]{name, password});
        if (cursor.getCount() > 0)
            return true;
        else return false;


    }

    //ManagerLoginSignup
    public Boolean insertManager(String name, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("password", password);
        long result = MyDB.insert("manager", null, contentvalues);
        if (result == -1) return false;
        else
            return true;

    }

    public Boolean checkManagerUserName(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from manager " +
                "where name=?", new String[]{name});

        if (cursor.getCount() > 0)

            return true;
        else
            return false;

    }

    public Boolean checkManagerPassword(String name, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from manager where" +
                " name=? and password=?", new String[]{name, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }


    public int updatepass(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", pass);
        return db.update("users", contentValues, "name = ?", new String[]{email});

    }


    public boolean insertDetail(Bitmap image, String name, String description, String price, String capacity, String location) {

        SQLiteDatabase db = getReadableDatabase();
        //   Bitmap image = image;
        ByteArrayOutputStream objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        imageInBytes = objectByteArrayOutputStream.toByteArray();

        ContentValues values = new ContentValues();


        values.put("image", imageInBytes);
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        values.put("capacity", capacity);
        values.put("location", location);

        long id1 = db.insert("manageraddhall", null, values);

        if (id1 <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertOrder(String name, String phone, String capacity, String menu, String date, String time, String price,

                               Bitmap image, String desc, String hallname, int id) {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //
        SQLiteDatabase db = getReadableDatabase();
        ByteArrayOutputStream objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        byte[] imageInBytes1 = objectByteArrayOutputStream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("capacity", capacity);
        values.put("Menu", menu);
        values.put("Date", date);
        values.put("Time", time);
        values.put("price", price);
        values.put("image", imageInBytes1);
        values.put("description", desc);
        values.put("hallname", hallname);
        values.put("id", id);
        long idd = db.insert("orders", null, values);

        if (idd <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrderModel> getOrder(int iddd) {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,name,phone,menu,date,time,price,image,hallname,status from orders where id='" + iddd + "'",
                null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                OrderModel model = new OrderModel();
                model.setOrdernumber(cursor.getString(0));
                model.setCustomername(cursor.getString(1));
                model.setCustomerphone(cursor.getString(2));


                model.setOrdermenu(cursor.getString(3));
                model.setOrderdate(cursor.getString(4));
                model.setOrdertime(cursor.getString(5));
                model.setOrderprice(cursor.getString(6));

                byte[] imageByte1 = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte1, 0, imageByte1.length);
                model.setOrderImage(bitmap);
                //   model.setOrderImage(cursor.getInt(4));

                model.setBookMarqueeName(cursor.getString(8));
                model.setTxtpending(cursor.getString(9));

                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }


    //confirmOrder
    public ArrayList<confirmOrderModel> getconfirmOrder() {
        ArrayList<confirmOrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,name,phone,menu,date,time,price,image,hallname from orders",
                null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                confirmOrderModel model = new confirmOrderModel();
                model.setConfirmorderno(cursor.getString(0));
                model.setConfirmcustomername(cursor.getString(1));
                model.setConfirmcustomerphone(cursor.getString(2));


                model.setConfirmmenu(cursor.getString(3));
                model.setConfirmdate(cursor.getString(4));
                model.setConfirmtime(cursor.getString(5));
                model.setConfirmprice(cursor.getString(6));

                byte[] imageByte2 = cursor.getBlob(7);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte2, 0, imageByte2.length);
                model.setConfirmimage(bitmap);


                model.setConfirmname(cursor.getString(8));

                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }


    public boolean updateorder(String name, String phone, int capacity, String menu, String date, String time, int price,
                               Bitmap image, String desc, String hallname, int id) {


        SQLiteDatabase db1 = getReadableDatabase();


        ByteArrayOutputStream objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        byte[] imageInBytes2 = objectByteArrayOutputStream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("capacity", capacity);
        values.put("Menu", menu);
        values.put("Date", date);
        values.put("Time", time);
        values.put("price", price);
        values.put("image", imageInBytes2);
        values.put("description", desc);
        values.put("hallname", hallname);
        long row = db1.update("orders", values, "id=" + id, null);


        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getOrderById(int id) {


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id='" + id + "'", null);

        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }


    //updatehall
    public boolean updatehall(Bitmap image, String hallname, String description, String price, String capacity,
                              int id) {


        SQLiteDatabase db = getReadableDatabase();


        ByteArrayOutputStream objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        byte[] imageInBytesaddhall = objectByteArrayOutputStream.toByteArray();
        ContentValues values = new ContentValues();
        values.put("image", imageInBytesaddhall);
        values.put("name", hallname);


        values.put("description", description);
        values.put("price", price);
        values.put("capacity", capacity);


        long row = db.update("manageraddhall", values, "id1=" + id, null);


        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getHallById(int id) {


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from manageraddhall where id1='" + id + "'", null);

        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }


    public int deleteorder(String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id=" + id, null);


    }


    public int deletehall(String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("manageraddhall", "id1=" + id, null);


    }

    public ArrayList<AddHallModel> getHallDetail() {
        ArrayList<AddHallModel> halladd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id1,image,name,description,price,capacity,location from manageraddhall", null);
        if (cursor.getCount() > 0) {
            try {
                while (cursor.moveToNext()) {
                    AddHallModel model = new AddHallModel();

                    model.setOrderno(cursor.getString(0));
                    byte[] imageByte = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    model.setImage(bitmap);
                    model.setName(cursor.getString(2));
                    model.setDescription(cursor.getString(3));
                    model.setPrice(cursor.getString(4));
                    model.setCapacity(cursor.getString(5));
                    model.setLocation(cursor.getString(6));


                    halladd.add(model);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        database.close();
        return halladd;
    }

    public ArrayList<showaddhallmodel> getshowHallDetail() {
        ArrayList<showaddhallmodel> halladd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from manageraddhall", null);
        if (cursor.getCount() > 0) {
            try {
                while (cursor.moveToNext()) {
                    showaddhallmodel model = new showaddhallmodel();
                    model.setAddhallno(cursor.getString(0));
                    byte[] imageByte = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    model.setAddimage(bitmap);
                    model.setAddname(cursor.getString(2));
                    model.setAdddescription(cursor.getString(3));
                    model.setAddprice(cursor.getString(4));

                    halladd.add(model);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        database.close();
        return halladd;
    }

    public Cursor getHallData(int id) {
        SQLiteDatabase objectSqliteDatabase = this.getReadableDatabase();
        Cursor cursor = objectSqliteDatabase.rawQuery("select * from manageraddhall WHERE id1 = '" + id + "'", null);
        return cursor;
    }

    //RemoveHall
    public ArrayList<removeHallModel> getRemoveHallDetail() {
        ArrayList<removeHallModel> halladd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from manageraddhall", null);
        if (cursor.getCount() > 0) {
            try {
                while (cursor.moveToNext()) {
                    removeHallModel model = new removeHallModel();

                    model.setRemoveorderno(cursor.getString(0));
                    byte[] imageByte = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    model.setRemoveimage(bitmap);
                    model.setRemovename(cursor.getString(2));
                    model.setRemovedescription(cursor.getString(3));
                    model.setRemoveprice(cursor.getString(4));


                    halladd.add(model);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        database.close();
        return halladd;
    }

    //comfmirmorderApproved
    public Boolean checkOrderexist(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from orders where status = '" + "Approved" + "' and id = '" + id + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public void insertApproveOrder(String id, String status) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);

        long checkIfQueryRun = MyDB.update("orders", contentValues, "id = ?", new String[]{id});
        if (checkIfQueryRun != 0) {
//            Toast.makeText(context, "Order Approved", Toast.LENGTH_SHORT).show();
            MyDB.close();
        } else {
            Toast.makeText(context, "Order Failed to Approved ", Toast.LENGTH_SHORT).show();
        }
    }

    public int checkUserid(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor getid = MyDB.rawQuery("Select id from users where name =? and password = ?", new String[]{username, password});
        if (getid != null && getid.moveToFirst()) {
            return getid.getInt(0);
        } else {
            return 0;
        }
    }

    public Boolean checkHallexist(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from orders where customer_id = '" + id + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public Boolean checkAddToCart(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from manageraddhall where id1 = '" + id + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public ArrayList<addToCartModel> getAddToCart() {
        ArrayList<addToCartModel> halladd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from manageraddhall WHERE status ='" + "yes" + "'", null);
        if (cursor.getCount() > 0) {
            try {
                while (cursor.moveToNext()) {
                    addToCartModel model = new addToCartModel();

                    model.setAddToCartorderno(cursor.getString(0));
                    byte[] imageByte = cursor.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                    model.setAddToCartimage(bitmap);
                    model.setAddToCartname(cursor.getString(2));
                    model.setAddToCartdescription(cursor.getString(3));
                    model.setAddToCartprice(cursor.getString(4));
                    model.setAddToCartcapacity(cursor.getString(5));
                    model.setAddtocartlocation(cursor.getString(6));


                    halladd.add(model);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        database.close();
        return halladd;
    }

    public void insertaddtofavorite(String id, String status) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);

        long checkIfQueryRun = MyDB.update("manageraddhall", contentValues, "id1 = ?", new String[]{String.valueOf(id)});

        if (checkIfQueryRun != 0) {
//           Toast.makeText(context, "Order Approved", Toast.LENGTH_SHORT).show();
            MyDB.close();
        } else {
            Toast.makeText(context, "Failed to add in Favorites", Toast.LENGTH_SHORT).show();
        }
    }
}
