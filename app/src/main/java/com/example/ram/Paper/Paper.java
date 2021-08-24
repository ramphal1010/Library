package com.example.ram.Paper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ram.Book.Book_main;
import com.example.ram.R;
import com.example.ram.offline.CheckForSDCard;
@SuppressWarnings("deprecation")
public class Paper extends AppCompatActivity
{

    Spinner spinner1,spinner2,spinner3,spinner4;
    int s1,s2;
    String link_for_subject;
    Intent intent;
    int p;
    CheckForSDCard is_connect;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle(R.string.paper_activity);
        setContentView(R.layout.paper_layout);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);
        spinner4=findViewById(R.id.spinner4);
        intent=getIntent();
        is_connect=new CheckForSDCard(Paper.this);
         spinner1();
         spinner2();
         spinner3();

    }
    private void spinner1(){
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                s1=i;
                if(s1>=1) {
                            spinner2.setAdapter(new ArrayAdapter<>(Paper.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.Semester_array)));
                    spinner2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }});

    }
    private void spinner2() {
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l)
            {
                if(is_connect.isConnectingToInternet()) {

                    String uri = "https://ram2-7416e.firebaseio.com/paper/";
                    s2 = j;
                    if (s1 >= 0) {
                        if (s2 >= 3 && s2 <= 8) {
                            String branch = spinner1.getItemAtPosition(s1).toString().toLowerCase();
                            String url = uri + branch + ".json";
                            System.out.println(url);
                            String objects = s2 + "sem";
                            System.out.println(objects);
                            json(url, objects);


                        } else if (s2 == 1) {
                            String url = uri + "civil_sem.json";
                            String objects = "civil_sem";
                            json(url, objects);
                        } else if (s2 == 2) {
                            String url = uri + "mechanical_sem.json";
                            String objects = "mechanical_sem";
                            json(url, objects);
                        }
                    } }
                else{ Toast.makeText(Paper.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();}

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }});
    }

    private void spinner3() {
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("item"+i);
                p=i;
                link_for_subject=spinner4.getItemAtPosition(i).toString();
                System.out.println(link_for_subject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }});

    }
    public void Submit(View view)
    {


        if(is_connect.isConnectingToInternet())
        {
            if(s1<=0 || s2<=0)
            {
                Toast.makeText(Paper.this, "Please Select Branch And Semester Carefully", Toast.LENGTH_SHORT).show();
            }
            else if (link_for_subject==null){
                Toast.makeText(Paper.this,"Please Back And Select Semester or Subject Carefully With Internet_connection",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Book(view, link_for_subject);
            }
        }
        else
        {
            Toast.makeText(Paper.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
        }
    }


    private void Book(View view, String string)
    {
        Intent intent =new Intent(view.getContext(), Book_main.class);
        intent.putExtra("link",string);
        view.getContext().startActivity(intent);

    }
    private  void json(String url,String objects)
    {
        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(getApplicationContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(Paper.this);
        builder.setView(R.layout.progress);
        Dialog dialog=builder.create();
        new Json(mQueue,spinner3,spinner4,getApplicationContext(),url,objects,dialog).execute();
    }


}



