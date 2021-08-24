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

public class First_yearFragment extends Fragment{
   private View view1;
    public First_yearFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.istyear_fragment, container, false);
            Button button=view1.findViewById(R.id.civil_semester);
          final   CheckForSDCard isconnect=new CheckForSDCard(view1.getContext());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String string="/ist_year/ist_year_syllabus/civil_sem";
                   if(isconnect.isConnectingToInternet()){
                    paper(view,string);
                   }
                   else{ Toast.makeText(view1.getContext(), "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
                   }


            }});
        Button button2=view1.findViewById(R.id.mechanical_semester);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isconnect.isConnectingToInternet()){
                String string="/ist_year/ist_year_syllabus/mechanical_sem";
                paper(view,string);
                }
                else{ Toast.makeText(view1.getContext(), "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view1;


    }

    private void paper(View view, String string) {
        Intent intent =new Intent(view.getContext(), Book_main.class);
        intent.putExtra("link",string);
        view.getContext().startActivity(intent);
    }

}
