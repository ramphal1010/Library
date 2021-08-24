package com.example.ram;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
@SuppressWarnings("deprecation")
public class Send_Mail extends AppCompatActivity {
private EditText subject,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        subject=findViewById(R.id.subject);
        message=findViewById(R.id.message);
        Button button=findViewById(R.id.send_email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
            }
        });
    }

    private void sendmail() {
        String recipient="rishusharmagi1010@gmail.com";
        String sub=subject.getText().toString();
        String msg=message.getText().toString();
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+recipient));
        intent.putExtra(Intent.EXTRA_SUBJECT,sub);
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }
}
