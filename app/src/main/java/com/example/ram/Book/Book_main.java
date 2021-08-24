package com.example.ram.Book;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class Book_main extends AppCompatActivity
{
    String url;
    FirebaseFirestore firebaseFirestore;
    ArrayList<Book_Down> downs_ModesArray=new ArrayList<>();
    Intent intent;
    TextView percent;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle=getIntent().getExtras();
        url= Objects.requireNonNull(bundle).getString("link");
        intent=getIntent();
        percent=findViewById(R.id.percent);
        AlertDialog.Builder  builder=new AlertDialog.Builder(Book_main.this);
        builder.setView(R.layout.progress);
         dialog=builder.create();
         dialog.setCancelable(false);
        dialog.show();
        SetupFire_base();
        dataFromFire_base();
    }




    private void SetupFire_base(){
        firebaseFirestore=FirebaseFirestore.getInstance();
    }
    private void dataFromFire_base()
    {
        if(downs_ModesArray.size()>0)
            downs_ModesArray.clear();
        firebaseFirestore.collection(url)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        for(DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult()))
                        {
                            Book_Down SyllabusDown =new Book_Down(documentSnapshot.getString("name"),documentSnapshot.getString("link"));
                            downs_ModesArray.add(SyllabusDown);
                        }
                        final GridView gridView=findViewById(R.id.gridview);
                        gridView.setAdapter(new Book_Adapter(Book_main.this,downs_ModesArray,percent));
                        dialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                        public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Book_main.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
