package com.example.smartwed.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwed.DetailActivity;
import com.example.smartwed.Models.OrderModel;
import com.example.smartwed.R;


import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder>{
    ArrayList<OrderModel> list;
    Context context;
    Button bn_chatss;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_order_sample,viewGroup,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        final OrderModel model=list.get(i);
        viewHolder.orderImage.setImageBitmap(model.getOrderImage());
        viewHolder.ordernumber.setText(model.getOrdernumber());
        viewHolder.BookMarqueeName.setText(model.getBookMarqueeName());
        viewHolder.customername.setText(model.getCustomername());
        viewHolder.customerphone.setText(model.getCustomerphone());
     viewHolder.ordermenu.setText(model.getOrdermenu());
        viewHolder.orderprice.setText(model.getOrderprice());
        viewHolder.orderdate.setText(model.getOrderdate());
        viewHolder.ordertime.setText(model.getOrdertime());
        viewHolder.txtpending.setText(model.getTxtpending());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrdernumber()));
              intent.putExtra("type",2);
                context.startActivity(intent);


            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()


                                                   {


                                                       @Override
                                                       public boolean onLongClick(View v) {

                                                           new AlertDialog.Builder(context).setTitle("Delete order")
                                                                   .setMessage("Are you sure you want delete?")
                                                                   .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                                                       @Override
                                                                       public void onClick(DialogInterface dialog, int which) {
                                                                           DataBaseHelper helper=new DataBaseHelper(context);
                                                                           if(helper.deleteorder(model.getOrdernumber())>0)
                                                                           {

                                                                               Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();


                                                                           }
                                                                           else
                                                                           {
                                                                               Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                                                                           }
                                                                       }
                                                                   })
                                                                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                                       @Override
                                                                       public void onClick(DialogInterface dialog, int which) {

                                                                       }
                                                                   }).show();

                                                           return false;
                                                       }
                                                   }





        );




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView BookMarqueeName,ordernumber,
                orderprice,orderdate,ordertime,ordermenu,
                customername,customerphone,txtpending;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage=itemView.findViewById(R.id.orderImage);
            BookMarqueeName=itemView.findViewById(R.id.BookMarqueeName);
            ordernumber=itemView.findViewById(R.id.ordernumber);
           customername=itemView.findViewById(R.id.customername);
         customerphone=itemView.findViewById(R.id.customerphone);
            ordermenu=itemView.findViewById(R.id.ordermenu);
            orderprice=itemView.findViewById(R.id.orderprice);
            orderdate=itemView.findViewById(R.id.orderdate);
         ordertime=itemView.findViewById(R.id.ordertime);
            txtpending=itemView.findViewById(R.id.txtpending);
        }
    }



}
