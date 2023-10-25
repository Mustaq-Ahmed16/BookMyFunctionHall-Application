package com.example.smartwed.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwed.Models.removeHallModel;
import com.example.smartwed.R;

import java.util.ArrayList;

public class removeHallAdapter extends RecyclerView.Adapter<removeHallAdapter.viewHolder> {



    ArrayList<removeHallModel> list;
    Context context;


    public removeHallAdapter(ArrayList<removeHallModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate
                (R.layout.remove_hall_sample,parent,false);



        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull removeHallAdapter.viewHolder holder, int position) {
        final removeHallModel model=list.get(position);
        holder.removeimage.setImageBitmap(model.getRemoveimage());
        holder.removeorderno.setText(model.getRemoveorderno());
        holder.removename.setText(model.getRemovename());

        holder.removeprice.setText(model.getRemoveprice());
        holder.removedescription.setText(model.getRemovedescription());



        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()


                                               {


                                                   @Override
                                                   public boolean onLongClick(View v) {

                                                       new AlertDialog.Builder(context).setTitle("Delete Hall")
                                                               .setMessage("Are you sure you want delete?")
                                                               .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialog, int which) {
                                                                       DataBaseHelper helper=new DataBaseHelper(context);
                                                                       if(helper.deletehall(model.getRemoveorderno())>0)
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


    public void filterList(ArrayList<removeHallModel> filterList) {
        list = filterList;
        notifyDataSetChanged();

    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView removeimage;
        TextView removename,removeprice,removeorderno,removedescription;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            removeimage=itemView.findViewById(R.id.removeimage);
           removeorderno=itemView.findViewById(R.id.removeorderno);
        removename=itemView.findViewById(R.id.removename);

           removedescription = itemView.findViewById(R.id.removedescription);
            removeprice = itemView.findViewById(R.id.removeprice);


        }


    }
}
