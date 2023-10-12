package com.example.plateletapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference donationPostsRef;
    private List<DonationPost> donationPostsList;
    private DonationPostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        listView = findViewById(R.id.listViewDonationPosts);

        EditText etSearch = findViewById(R.id.etSearch);
        Button btnSearch = findViewById(R.id.btnSearch);

        donationPostsList = new ArrayList<>();
        adapter = new DonationPostAdapter(this, donationPostsList);
        listView.setAdapter(adapter);

        donationPostsRef = FirebaseDatabase.getInstance().getReference().child("donationPosts");

        btnSearch.setOnClickListener(view -> {
            String searchQuery = etSearch.getText().toString().trim();
            searchDonationPosts(searchQuery);
        });

        retrieveDonationPosts();
    }

    private void retrieveDonationPosts() {
        donationPostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationPostsList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DonationPost donationPost = postSnapshot.getValue(DonationPost.class);
                    donationPostsList.add(donationPost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PatientActivity.this, "Failed to retrieve donation posts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchDonationPosts(String searchQuery) {
        Query searchQueryRef = donationPostsRef.orderByChild("address")
                .startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff");

        searchQueryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationPostsList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DonationPost donationPost = postSnapshot.getValue(DonationPost.class);
                    donationPostsList.add(donationPost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PatientActivity.this, "Failed to search donation posts", Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void openPlateletPage(View view) {
        // Code to open the donation procedure page
        Intent intent = new Intent(this, Platelethmrmdy.class);
        startActivity(intent);
    }
    public void goToMainPage(View view) {
        // Code to go back to the main page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
