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

public class Third_Year_Fragment extends Fragment{
   private View view;

    public Third_Year_Fragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.thirdyearfragment, container, false);
        Button button3_1=view.findViewById(R.id.button3_1);
        Button button3_2=view.findViewById(R.id.button3_2);
        Button button3_3=view.findViewById(R.id.button3_3);
        Button button3_4=view.findViewById(R.id.button3_4);
        Button button3_5=view.findViewById(R.id.button3_5);
        Button button3_6=view.findViewById(R.id.button3_6);
        Button button3_7=view.findViewById(R.id.button3_7);
        Button button3_8=view.findViewById(R.id.button3_8);
        Button button3_9=view.findViewById(R.id.button3_9);
        Button button3_10=view.findViewById(R.id.button3_10);
        Button button3_11=view.findViewById(R.id.button3_11);

        button3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Cs/cs_syllabus/cs_3_year";
                check_connection(view,string);
            }
        });
        button3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/IT/it_syllabus/it_3_year";
                check_connection(view,string);
            }
        });
        button3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Ecc/ecc_syllabus/ecc_3_year";
                check_connection(view,string);

            }
        });
        button3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Ece/ece_syllabus/ece_3_year";
                check_connection(view,string);

            }
        });
        button3_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/EEE/eee_syllabus/eee_3_year";
                check_connection(view,string);

            }
        });
        button3_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string="/Civil/civil_syllabus/civil_3_year";
                check_connection(view,string);
            }
        });
        button3_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Mining/mining_syllabus/mining_3_year";
                check_connection(view,string);
            }
        });
        button3_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Chemical/chemical_syllabus/chemical_3_year";
                check_connection(view,string);
            }
        });
        button3_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Electrical/electrical_syllabus/electrical_3_year";
                check_connection(view,string);
            }
        });
        button3_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string="/Mechanical/mechanical_syllabus/mechanical_3_year";
                check_connection(view,string);
            }
        });
        button3_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string="/p&i/p&i_syllabus/p&i_3_year";
                check_connection(view,string);
            }
        });
        return view;
    }

    private void check_connection(View view,String string){
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
