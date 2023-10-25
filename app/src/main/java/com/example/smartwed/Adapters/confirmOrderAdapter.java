package com.example.smartwed.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwed.Adapters.DataBaseHelper;
import com.example.smartwed.Models.AddHallModel;
import com.example.smartwed.Models.confirmOrderModel;
import com.example.smartwed.Models.showaddhallmodel;
import com.example.smartwed.R;

import java.util.ArrayList;

public class confirmOrderAdapter extends RecyclerView.Adapter<confirmOrderAdapter.viewHolder> {
    ArrayList<confirmOrderModel> list;
    Context context;
    DataBaseHelper db;

    public confirmOrderAdapter(ArrayList<confirmOrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
        @NonNull
        @Override
        public viewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup,int i)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.confirm_order_module, viewGroup, false);

            return new viewHolder(view);
        }


        @Override
        public void onBindViewHolder (@NonNull viewHolder holder,int position){
            db=new DataBaseHelper(context);
            final confirmOrderModel model = list.get(position);
            holder.confirmimage.setImageBitmap(model.getConfirmimage());
            holder.confirmorderno.setText(model.getConfirmorderno());
            holder.confirmname.setText(model.getConfirmname());
            holder.confirmcustomername.setText(model.getConfirmcustomername());
            holder.confirmcustomerphone.setText(model.getConfirmcustomerphone());
            holder.confirmprice.setText(model.getConfirmprice());
            holder.confirmmenu.setText(model.getConfirmmenu());
            holder.confirmdate.setText(model.getConfirmdate());
            holder.confirmtime.setText(model.getConfirmtime());

            Boolean check = db.checkOrderexist(model.getConfirmorderno());
            if (check == false) {
                holder.btn_confirmorder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Order_id =model.getConfirmorderno();
                        db.insertApproveOrder(Order_id, "Approved");
                        holder.btn_confirmorder.setEnabled(false);
                        Toast.makeText(context, "Order Confirmed", Toast.LENGTH_SHORT).show();


                    }
                });


            } else {
                holder.btn_confirmorder.setEnabled(false);
            }





        }

        @Override
        public int getItemCount () {
            return list.size();
        }
    public void filterList(ArrayList<confirmOrderModel> filterList) {
        list = filterList;
        notifyDataSetChanged();

    }
        public class viewHolder extends RecyclerView.ViewHolder {
Button btn_confirmorder;
            ImageView confirmimage;
            TextView confirmorderno,confirmprice,
                    confirmmenu,confirmdate,confirmtime,
                    confirmname,confirmcustomername,confirmcustomerphone;


            public viewHolder(@NonNull View itemView) {
                super(itemView);
                confirmimage = itemView.findViewById(R.id.confirmimage);
                confirmorderno = itemView.findViewById(R.id.confirmorderno);
                confirmname= itemView.findViewById(R.id.confirmname);
              confirmcustomername= itemView.findViewById(R.id.confirmcustomername);
         confirmcustomerphone= itemView.findViewById(R.id.confirmcustomerphone);
                confirmmenu = itemView.findViewById(R.id.confirmmenu);
                confirmprice = itemView.findViewById(R.id.confirmprice);
                confirmdate = itemView.findViewById(R.id.confirmdate);
                confirmtime = itemView.findViewById(R.id.confirmtime);
             btn_confirmorder = itemView.findViewById(R.id.btn_confirmorder);


            }


        }


    }

