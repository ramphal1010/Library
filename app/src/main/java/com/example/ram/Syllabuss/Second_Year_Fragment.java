package com.example.ram.Syllabuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.ram.Book.Book_main;
import com.example.ram.R;
import com.example.ram.offline.CheckForSDCard;
@SuppressWarnings("deprecation")

public class Second_Year_Fragment extends Fragment {
    private View view;
    public Second_Year_Fragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_year_fragment, container, false);
        Button button2_1=view.findViewById(R.id.button2_1);
        Button button2_2=view.findViewById(R.id.button2_2);
        Button button2_3=view.findViewById(R.id.button2_3);
        Button button2_4=view.findViewById(R.id.button2_4);
        Button button2_5=view.findViewById(R.id.button2_5);
        Button button2_6=view.findViewById(R.id.button2_6);
        Button button2_7=view.findViewById(R.id.button2_7);
        Button button2_8=view.findViewById(R.id.button2_8);
        Button button2_9=view.findViewById(R.id.button2_9);
        Button button2_10=view.findViewById(R.id.button2_10);
        Button button2_11=view.findViewById(R.id.button2_11);
        button2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Cs/cs_syllabus/cs_2_year";
                check_connection(view,string);
            }
        });
        button2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/IT/it_syllabus/it_2_year";
                check_connection(view,string);
            }
        });
        button2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Ecc/ecc_syllabus/ecc_2_year";
                check_connection(view,string);
            }
        });
        button2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Ece/ece_syllabus/ece_2_year";
                check_connection(view,string);

            }
        });
        button2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/EEE/eee_syllabus/eee_2_year";
                check_connection(view,string);
            }
        });
        button2_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string="/Civil/civil_syllabus/civil_2_year";
                check_connection(view,string);
            }
        });
        button2_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Mining/mining_syllabus/mining_2_year";
                check_connection(view,string);
            }
        });
        button2_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Chemical/chemical_syllabus/chemical_2_year";
                check_connection(view,string);
            }
        });
        button2_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Electrical/electrical_syllabus/electrical_2_year";
                check_connection(view,string);
            }
        });
        button2_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Mechanical/mechanical_syllabus/mechanical_2_year";
                check_connection(view,string);
            }
        });
        button2_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string="/p&i/p&i_syllabus/p&i_2_year";
                check_connection(view,string);
            }
        });



        return view;
    }

    private void check_connection(View view, String string){
        final CheckForSDCard is_connect=new CheckForSDCard(view.getContext());
        if(is_connect.isConnectingToInternet()){
            Intent intent =new Intent(view.getContext(), Book_main.class);
            intent.putExtra("link",string);
            view.getContext().startActivity(intent);
        }
        else{ Toast.makeText(view.getContext(), "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
        }

    }


}
