package com.example.ram.filelist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.ram.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import java.io.File;
import java.util.Objects;

@SuppressWarnings("deprecation")

public class PDF_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view_for_list);
        PDFView pdfView=findViewById(R.id.pdfview);
        Intent intent=this.getIntent();
        String path= Objects.requireNonNull(intent.getExtras()).getString("PATH");
        File  file=new File(Objects.requireNonNull(path));
        if(file.canRead())
        {
            pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PDF_Activity.this,String.valueOf(nbPages),Toast.LENGTH_LONG).show();
                }
            }).load();
        }
    }
}
