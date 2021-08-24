package com.example.ram;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
@SuppressWarnings("deprecation")
public class Splash extends AppCompatActivity {
    private static int splash_timeout=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       ImageView imageView=findViewById(R.id.splash_logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,Home_Activity.class);
                startActivity(intent);
                finish();
            }
        },splash_timeout);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        imageView.startAnimation(animation);
    }
}
