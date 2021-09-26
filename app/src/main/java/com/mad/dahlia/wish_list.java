package com.mad.dahlia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class wish_list extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference DB;
    WishListAdapter adapter;
    ArrayList<wishlistModel> list;
    String dressName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);


        recyclerView = findViewById(R.id.rvwishlist);
        DB = FirebaseDatabase.getInstance().getReference().child("WishList").child("UserName");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new WishListAdapter(this, list);
        recyclerView.setAdapter(adapter);

        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    wishlistModel listAdapter = dataSnapshot.getValue(wishlistModel.class);
                    list.add(listAdapter);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Refresh(){
        Intent intent = new Intent(this, wish_list.class);
        startActivity(intent);
    }
}