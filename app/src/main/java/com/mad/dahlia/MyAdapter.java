package com.mad.dahlia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    DatabaseReference ref;
    ArrayList<Feedback> list;

    public MyAdapter(Context context, ArrayList<Feedback> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          Feedback comment = list.get(position);
          holder.comment.setText(comment.getComment());

          holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  ref = FirebaseDatabase.getInstance().getReference("Feedback").child("comment");
                  ref.child(comment.getComment().toString()).removeValue();
                  Toast.makeText(context.getApplicationContext(),"Comment deleted",Toast.LENGTH_LONG).show();
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button deleteBtn;
       TextView comment;
        public MyViewHolder(@NonNull View itemView ){
            super(itemView);

            comment = itemView.findViewById(R.id.commentTxt);
            deleteBtn = itemView.findViewById(R.id.btnDelete);
        }
    }
}
