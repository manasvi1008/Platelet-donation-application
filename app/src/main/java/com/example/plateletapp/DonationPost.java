package com.example.plateletapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import com.google.firebase.database.ServerValue;

public class DonationPost {
    private String name;
    private String address;
    private String mobile;
    private Object timestamp;

    public DonationPost() {
        // Default constructor required for Firebase
    }

    public DonationPost(String name, String address, String mobile) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.timestamp = ServerValue.TIMESTAMP; // Set current server timestamp
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    // Helper method to get current timestamp
    private static Object getCurrentTimestamp() {
        return ServerValue.TIMESTAMP;
    }
}
