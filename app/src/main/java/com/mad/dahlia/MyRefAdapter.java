package com.mad.dahlia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRefAdapter extends RecyclerView.Adapter<MyRefAdapter.MyRefViewHolder> {

    Context contextRef;
    ArrayList<Refund> listRef;

    public MyRefAdapter(Context contextRef, ArrayList<Refund> listRef) {
        this.contextRef = contextRef;
        this.listRef = listRef;
    }

    @NonNull

    @Override
    public MyRefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View vr = LayoutInflater.from(contextRef).inflate(R.layout.itemref,parent,false);
       return new MyRefViewHolder(vr);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRefAdapter.MyRefViewHolder holder, int position) {
         Refund refund = listRef.get(position);
         holder.purchDateView.setText(refund.getDate());
         holder.purchTypeView.setText(refund.getType());
         holder.proCodeView.setText(refund.getCode());
         holder.pronameView.setText(refund.getName());
         holder.reasonView.setText(refund.getReason());
    }

    @Override
    public int getItemCount() {
        return listRef.size();
    }

    public static class MyRefViewHolder extends RecyclerView.ViewHolder {

        TextView purchDateView, purchTypeView, proCodeView, pronameView,reasonView;

        public MyRefViewHolder(@NonNull  View itemView) {
            super(itemView);
            purchDateView = itemView.findViewById(R.id.purchDateView);
            purchTypeView = itemView.findViewById(R.id.purchTypeView);
            proCodeView = itemView.findViewById(R.id.proCodeView);
            pronameView = itemView.findViewById(R.id.pronameView);
            reasonView = itemView.findViewById(R.id.reasonView);

        }
    }


}
