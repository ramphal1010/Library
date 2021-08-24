package com.example.ram;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ram.Book.Book_main;
import com.example.ram.Book.Json_for_book;
import com.example.ram.offline.CheckForSDCard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("deprecation")

public class Books_Activity extends AppCompatActivity
{
    Spinner spinner1,spinner2,spinner3;
    int s1,s2;
    String link_for_subject;
    CheckForSDCard is_connect;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
  //  private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle(R.string.book_activity);
        setContentView(R.layout.books_layout);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);

         is_connect=new CheckForSDCard(Books_Activity.this);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                s1=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l)
            {
                if(is_connect.isConnectingToInternet()){

                String uri="https://ram2-7416e.firebaseio.com/book/";

                s2=j;
                if(s1>0) {

                    String branch=spinner1.getItemAtPosition(s1).toString().toLowerCase();
                    String url = uri+branch+".json";
                    System.out.println(url);
                    if (s2>=3 && s2<=8) {
                        String objects = s2 + "sem";
                        System.out.println(objects);
                        json(url, objects);
                    }
                }
                if(s1>=0){
                    if(s2==1){
                        String url=uri+"civil_sem.json";
                        String objects="civil_sem";
                        json(url,objects);
                    }
                    else if(s2==2){
                        String url=uri+"mechanical_sem.json";
                        String objects="mechanical_sem";
                        json(url,objects);
                    }
                }
                }
            else{ Toast.makeText(Books_Activity.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();

            }}
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                link_for_subject=spinner3.getItemAtPosition(i).toString();
                System.out.println(link_for_subject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    public void Submit(View view) {

        if(is_connect.isConnectingToInternet()) {
            if(link_for_subject==null){Toast.makeText(Books_Activity.this,"Please Back And Select Semester Carefully With Internet_connection",Toast.LENGTH_SHORT).show();}
          else {
            Book(view, link_for_subject);}
        }
        else{ Toast.makeText(Books_Activity.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();}
    }

    private void Book(View view, String string) {
        Intent intent =new Intent(view.getContext(), Book_main.class);
        intent.putExtra("link",string);
        view.getContext().startActivity(intent);
    }
    private  void json(String url,String objects){
         RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(getApplicationContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(Books_Activity.this);
        builder.setView(R.layout.progress);
        Dialog dialog=builder.create();
        new Json_for_book(mQueue,spinner3,getApplicationContext(),url,objects,dialog).execute();

    }

}
