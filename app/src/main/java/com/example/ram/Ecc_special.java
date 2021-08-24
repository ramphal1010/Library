package com.example.ram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ram.Book.Book_main;
@SuppressWarnings("deprecation")
public class Ecc_special extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecc_special);
        Button third_sem=findViewById(R.id.third_sem);
        Button fourth_sem=findViewById(R.id.fourth_sem);
        Button fifth_sem=findViewById(R.id.fifth_sem);
        Button six_sem=findViewById(R.id.six_sem);
        third_sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String third_sem="/Ecc_special/Ecc/3sem";
                Book(third_sem);
            }
        });
        fourth_sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fourth_sem="/Ecc_special/Ecc/4sem";
                Book(fourth_sem);
            }
        });
        fifth_sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fifth_sem="/Ecc_special/Ecc/5sem";
                Book(fifth_sem);
            }
        });
        six_sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String six_sem="/Ecc_special/Ecc/6sem";
                Book(six_sem);
            }
        });

    }
    private void Book(String string)
    {
        Intent intent =new Intent(Ecc_special.this, Book_main.class);
        intent.putExtra("link",string);
        startActivity(intent);

    }
}
