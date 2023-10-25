package com.example.smartwed.Adapters;

import android.content.Context;
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
import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.R;

import java.util.ArrayList;

public class AddHallAdapter extends RecyclerView.Adapter<AddHallAdapter.viewHolder> {
    ArrayList<AddHallModel> list;
    Context context;
    DataBaseHelper db;
    public AddHallAdapter(ArrayList<AddHallModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AddHallAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate
                (R.layout.activity_main_order_moule,parent,false);



        return new AddHallAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddHallAdapter.viewHolder holder, int position)
    {
        final AddHallModel model=list.get(position);
        db=new DataBaseHelper(context);
     holder.image.setImageBitmap(model.getImage());
       holder.orderno.setText(model.getOrderno());
        holder.name.setText(model.getName());
            holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());
 holder.capacity.setText(model.getCapacity());
 holder.location.setText(model.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context, DetailActivity.class);
               intent.putExtra("id1",Integer.parseInt(model.getOrderno()));
                intent.putExtra("type" ,1);

             context.startActivity(intent);


          }
        });

        Boolean check = db.checkAddToCart(model.getOrderno());
        if (check == true) {

            holder.btnaddtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Order_id = model.getOrderno();
                    db.insertaddtofavorite(Order_id ,"yes");
                    holder.btnaddtocart.setEnabled(false);
                    Toast.makeText(context, "Add to Favorite Successful"+Order_id, Toast.LENGTH_SHORT).show();


                }
            });
        }
        else
        {
            holder.btnaddtocart.setEnabled(false);

        }



    }

    @Override
    public int getItemCount() {return list.size();}

    public void filterList(ArrayList<AddHallModel> filterList) {
            list = filterList;
            notifyDataSetChanged();

    }

    public class viewHolder extends RecyclerView.ViewHolder {
Button btnaddtocart;
            ImageView image;
            TextView orderno,name, description, price,capacity,location;


            public viewHolder(@NonNull View itemView) {
                super(itemView);
                  image=itemView.findViewById(R.id.image);
                orderno=itemView.findViewById(R.id.orderno);
                name = itemView.findViewById(R.id.name);
                description = itemView.findViewById(R.id.description);
             price = itemView.findViewById(R.id.price);
                capacity = itemView.findViewById(R.id.capacity);
               location = itemView.findViewById(R.id.location);
                btnaddtocart = itemView.findViewById(R.id.btnaddtocart);


            }


        }

}
