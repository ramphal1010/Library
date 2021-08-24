package com.example.ram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ram.Book.Book_main;
@SuppressWarnings("deprecation")
public class Extra_section extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Electives");
        setContentView(R.layout.extra_section_layout);
        Intent intent =new Intent(Extra_section.this, Book_main.class);
        String string="/Elective_paper";
        intent.putExtra("link",string);
        startActivity(intent);
    }
}
