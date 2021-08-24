package com.example.ram;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import com.example.ram.Syllabuss.MainActivity;
import com.example.ram.filelist.Main_Activity;
import com.example.ram.Paper.Paper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
@SuppressWarnings("deprecation")

public class Home_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

          private ActionBarDrawerToggle Toggle;
          FragmentManager fragmentManager;
          protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        DrawerLayout  drawerLayout = findViewById(R.id.homedrawer);
        Toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView =  findViewById(R.id.homenav);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textView=findViewById(R.id.upload_file);
        String text="Upload if you have some important data and files ";
        SpannableString spannableString=new SpannableString(text);
        ClickableSpan clickableSpan=new ClickableSpan() {

            public void onClick(@Nonnull View view) {
                Intent intent =new Intent(Home_Activity.this,Send_Mail.class);
                startActivity(intent);
            }


            public void updateDrawState(@Nonnull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }

        };
        spannableString.setSpan(clickableSpan,0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =findViewById(R.id.homedrawer);

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(@Nonnull MenuItem item)
    {
        if (Toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        if (id == R.id.home)
        {
            Intent intent1 = new Intent(Home_Activity.this, Home_Activity.class);
            startActivity(intent1);
        }
        if (id == R.id.Upload_files)
        {

          Intent intent1=new Intent(Home_Activity.this,Send_Mail.class);
             startActivity(intent1);
        }
        if (id == R.id.share)
        {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                Toast.makeText(Home_Activity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        if (id == R.id.about)
        {   Intent intent=new Intent(Home_Activity.this,About.class);
           startActivity(intent);
        }
        if(id==R.id.Elective){
            Intent intent=new Intent(Home_Activity.this,Extra_section.class);
            startActivity(intent);
        }
        if (id == R.id.offline)
        {
            Intent intent1=new Intent(Home_Activity.this, Main_Activity.class);
            startActivity(intent1);
        }
        if (id == R.id.ecc_special)
        {
            Intent intent1=new Intent(Home_Activity.this, Ecc_special.class);
            startActivity(intent1);
        }

        return false;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        public boolean onNavigationItemSelected(@Nullable MenuItem item)
        {



                switch (Objects.requireNonNull(item). getItemId()) {
                    case R.id.books: {
                        Intent intent = new Intent(Home_Activity.this, Books_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        return true;
                    }

                    case R.id.syllabus1: {
                        Intent intent = new Intent(Home_Activity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        return true;
                    }
                    case R.id.paper:
                        Intent intent = new Intent(Home_Activity.this, Paper.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        return true;
                }


            return false;
        }
    };


}
