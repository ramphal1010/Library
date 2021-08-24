package com.example.ram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
@SuppressWarnings("deprecation")
public class About extends AppCompatActivity {
   ImageView insta_ram,link_din;
   DatabaseReference databaseReference;
   TextView rating;
   String link_for_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About Us");
        setContentView(R.layout.about_layout);
        insta_ram=findViewById(R.id.instagram);
        link_din=findViewById(R.id.link_din);
        rating=findViewById(R.id.rate_us);
        insta_ram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/rissi_sni/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/rissi_sni/")));
                }
            }
        });
        link_din.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String profile_url = "https://in.linkedin.com/in/ramphal-sharma-219025181";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url));
                    intent.setPackage("com.linkedin.android");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try{
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url)));
                }
            }
        });
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_link_for_rating();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName())));
                }
            }
        });

    }

    private void get_link_for_rating() {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("links");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                link_for_rating=dataSnapshot.child("rating_for_app").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
