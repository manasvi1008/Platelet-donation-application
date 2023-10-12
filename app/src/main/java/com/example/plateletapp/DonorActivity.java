package com.example.plateletapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class DonorActivity extends AppCompatActivity {
    private EditText etName, etAddress, etMobile;
    private Button btnPost;
    private Button btnDonationProcedure;
    private Button btnHomePage;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        btnDonationProcedure = findViewById(R.id.btnDonationProcedure);
        btnHomePage = findViewById(R.id.btnHomePage);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etMobile = findViewById(R.id.etMobile);
        btnPost = findViewById(R.id.btnPost);
        databaseReference = FirebaseDatabase.getInstance().getReference("donationPosts");
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();

                if (!name.isEmpty() && !address.isEmpty() && !mobile.isEmpty()) {
                    String postId = databaseReference.push().getKey();

                    DonationPost post = new DonationPost(name, address, mobile);
                    if (postId != null) {
                        post.setTimestamp(System.currentTimeMillis());
                        databaseReference.child(postId).setValue(post);
                        Toast.makeText(DonorActivity.this, "Post created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(DonorActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openDonationProcedurePage(View view) {
        // Code to open the donation procedure page
        Intent intent = new Intent(this, DonationProcedureActivity.class);
        startActivity(intent);
    }

    public void goToMainPage(View view) {
        // Code to go back to the main page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
