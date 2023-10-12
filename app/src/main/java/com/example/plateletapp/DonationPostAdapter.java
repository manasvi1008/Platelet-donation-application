package com.example.plateletapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DonationPostAdapter extends ArrayAdapter<DonationPost> {

    private Context context;
    private List<DonationPost> donationPosts;

    public DonationPostAdapter(Context context, List<DonationPost> donationPosts) {
        super(context, 0, donationPosts);
        this.context = context;
        this.donationPosts = donationPosts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_donation_post, parent, false);
        }

        DonationPost donationPost = donationPosts.get(position);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvAddress = view.findViewById(R.id.tvAddress);
        TextView tvMobile = view.findViewById(R.id.tvMobile);
        TextView tvTimestamp = view.findViewById(R.id.tvTimestamp);
        tvName.setText(donationPost.getName());
        tvAddress.setText(donationPost.getAddress());
        tvMobile.setText(donationPost.getMobile());

        Object timestampObj = donationPost.getTimestamp();

        if (timestampObj instanceof Long) {
            Long timestamp = (Long) timestampObj;
            String timestampString = getFormattedTimestamp(timestamp);
            tvTimestamp.setText(timestampString);
        }

        return view;
    }

    private String getFormattedTimestamp(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date(timestamp));
    }
}
