package com.example.ram.filelist;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ram.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Main_Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_list_main);

     //   Toolbar toolbar=findViewById(R.id.toolbar);
       // setActionBar(toolbar);
        final GridView gridView=findViewById(R.id.gridview);

                 gridView.setAdapter(new CustomAdapter(Main_Activity.this,getPDFs()));



    }
    private ArrayList<PDFDoc> getPDFs(){

        ArrayList<PDFDoc> pdfDocs=new ArrayList<>();

            File download_folder=getExternalFilesDir("/downloadDirectory");
            PDFDoc pdfDoc;
            if (Objects.requireNonNull(download_folder).exists()) {
                File[] files = download_folder.listFiles();
                for (File file : Objects.requireNonNull(files)) {
                    if (file.getPath().endsWith("pdf")) {
                        pdfDoc = new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());
                        pdfDocs.add(pdfDoc);
                    }
                }
            }


        return pdfDocs;
    }
}
