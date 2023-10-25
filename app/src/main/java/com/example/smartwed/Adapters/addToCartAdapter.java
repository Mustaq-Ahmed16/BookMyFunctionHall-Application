package com.example.smartwed.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwed.Models.addToCartModel;
import com.example.smartwed.R;

import java.util.ArrayList;

public class addToCartAdapter extends RecyclerView.Adapter<addToCartAdapter.viewHolder> {
    ArrayList<addToCartModel> list;
    Context context;
    DataBaseHelper db;

    public addToCartAdapter(ArrayList<addToCartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public addToCartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_to_cart_module, parent, false);

        return new addToCartAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addToCartAdapter.viewHolder holder, int position) {
        db=new DataBaseHelper(context);
        final addToCartModel model=list.get(position);
        holder.addToCartimage.setImageBitmap(model.getAddToCartimage());
        holder.addToCartorderno.setText(model.getAddToCartorderno());
        holder.addToCartname.setText(model.getAddToCartname());
        holder.addToCartprice.setText(model.getAddToCartprice());
        holder.addToCartdescription.setText(model.getAddToCartdescription());
        holder.addToCartcapacity.setText(model.getAddToCartcapacity());
        holder.addtocartlocation.setText(model.getAddtocartlocation());


    }

    @Override
    public int getItemCount () {
        return list.size();
    }
    public void filterList(ArrayList<addToCartModel> filterList) {
        list = filterList;
        notifyDataSetChanged();

    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView addToCartimage;
        TextView addToCartorderno,addToCartname, addToCartdescription,
                addToCartprice,addToCartcapacity,
              addtocartlocation;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            addToCartimage=itemView.findViewById(R.id.addToCartimage);
            addToCartorderno=itemView.findViewById(R.id.addToCartorderno);
            addToCartname = itemView.findViewById(R.id.addToCartname);
            addToCartdescription = itemView.findViewById(R.id.addToCartdescription);
            addToCartprice = itemView.findViewById(R.id.addToCartprice);
            addToCartcapacity = itemView.findViewById(R.id.addToCartcapacity);
            addtocartlocation = itemView.findViewById(R.id.addtocartlocation);



        }


    }
}
