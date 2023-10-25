package com.example.smartwed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwed.AddHall;
import com.example.smartwed.Models.showaddhallmodel;
import com.example.smartwed.R;
import com.example.smartwed.UpdateHall_by_manager;

import java.util.ArrayList;

public class showhalladpater  extends RecyclerView.Adapter<showhalladpater.viewHolder> {


    ArrayList<showaddhallmodel> list;
    Context context;


    public showhalladpater(ArrayList<showaddhallmodel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override

    public showhalladpater.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate
                (R.layout.show_add_hall_module,parent,false);



        return new showhalladpater.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showhalladpater.viewHolder holder, int position) {
        final showaddhallmodel model=list.get(position);
        holder.addimage.setImageBitmap(model.getAddimage());
        holder.addhallno.setText(model.getAddhallno());
        holder.addname.setText(model.getAddname());

        holder.addprice.setText(model.getAddprice());
        holder.adddescription.setText(model.getAdddescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context, UpdateHall_by_manager.class);
                intent.putExtra("ID",Integer.parseInt(model.getAddhallno()));

                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void filterList(ArrayList<showaddhallmodel> filterList) {
        list = filterList;
        notifyDataSetChanged();

    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView addimage;
        TextView addhallno, adddescription, addprice,addname;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            addimage=itemView.findViewById(R.id.addimage);
            addhallno=itemView.findViewById(R.id.addhallno);
            addname=itemView.findViewById(R.id.addname);

            adddescription = itemView.findViewById(R.id.adddescription);
            addprice = itemView.findViewById(R.id.addprice);


        }


    }
}
