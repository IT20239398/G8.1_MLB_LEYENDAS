package com.mad.dahlia;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ListViewHolder> {
    android.content.Context con;
    ArrayList<wishlistModel> list;
    DatabaseReference DB;

    public WishListAdapter(Context context, ArrayList<wishlistModel> list) {
        this.con = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(con).inflate(R.layout.wish_list_items, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        wishlistModel wish = list.get(position);
        holder.name.setText(wish.getName());
        holder.price.setText(wish.getPrice());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB = FirebaseDatabase.getInstance().getReference("WishList").child("UserName");
                DB.child(wish.getName().toString()).removeValue();
                Toast.makeText(con.getApplicationContext(), "Item removed successfully", Toast.LENGTH_LONG).show();

            }

        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView dress;
        Button delete;

        public ListViewHolder(@NonNull View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.wldname);
            price = itemView.findViewById(R.id.wlprice);
            dress = itemView.findViewById(R.id.drsImage);
            delete = itemView.findViewById(R.id.itemdelete);
        }
    }
}
