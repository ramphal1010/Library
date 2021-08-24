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

public class Fourth_Year_Fragment extends Fragment{
   private View view;

    public Fourth_Year_Fragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourthfragment, container, false);
        Button button4_1=view.findViewById(R.id.button4_1);
        Button button4_2=view.findViewById(R.id.button4_2);
        Button button4_3=view.findViewById(R.id.button4_3);
        Button button4_4=view.findViewById(R.id.button4_4);
        Button button4_5=view.findViewById(R.id.button4_5);
        Button button4_6=view.findViewById(R.id.button4_6);
        Button button4_7=view.findViewById(R.id.button4_7);
        Button button4_8=view.findViewById(R.id.button4_8);
        Button button4_9=view.findViewById(R.id.button4_9);
        Button button4_10=view.findViewById(R.id.button4_10);
        Button button4_11=view.findViewById(R.id.button4_11);
        button4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Cs/cs_syllabus/cs_4_year";
                check_connection(view,string);
            }
        });
        button4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/IT/it_syllabus/it_4_year";
                check_connection(view,string);
            }
        });
        button4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Ecc/ecc_syllabus/ecc_4_year";
                check_connection(view,string);

            }
        });
        button4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Ece/ece_syllabus/ece_4_year";
                check_connection(view,string);

            }
        });
        button4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/EEE/eee_syllabus/eee_4_year";
                check_connection(view,string);

            }
        });
        button4_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string="/Civil/civil_syllabus/civil_4_year";
                check_connection(view,string);
            }
        });
        button4_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Mining/mining_syllabus/mining_4_year";
                check_connection(view,string);
            }
        });
        button4_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Chemical/chemical_syllabus/chemical_4_year";
                check_connection(view,string);
            }
        });
        button4_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Electrical/electrical_syllabus/electrical_4_year";
                check_connection(view,string);
            }
        });
        button4_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Mechanical/mechanical_syllabus/mechanical_4_year";
                check_connection(view,string);
            }
        });
        button4_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string="/p&i/p&i_syllabus/p&i_4_year";
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
